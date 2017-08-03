#include "PrintDll.h"
#include <iostream>
#include <algorithm>
#include <Windows.h>
using namespace std;
#define PRINT_NAME1 "EPSON L310 Series"
#define PRINT_NAME2 "EPSON_L310_Series"

std::wstring c2w(const char *pc)
{
	std::wstring val = L"";

	if (NULL == pc)
	{
		return val;
	}

	size_t size_of_wc;
	size_t destlen = mbstowcs(0, pc, 0);
	if (destlen == (size_t)(-1))
	{
		return val;
	}
	size_of_wc = destlen + 1;
	wchar_t * pw = new wchar_t[size_of_wc];
	mbstowcs(pw, pc, size_of_wc);
	val = pw;
	delete pw;
	return val;
}

int printerStatus(int a)
{
	wstring strPrinterFriendlyName;
	if (a == 1)
		strPrinterFriendlyName = c2w(PRINT_NAME1);
	else if (a == 2)
		strPrinterFriendlyName = c2w(PRINT_NAME2);
	else
		return -1;
	HANDLE hPrinter;
	if (OpenPrinter(const_cast<LPWSTR>(strPrinterFriendlyName.c_str()), &hPrinter, NULL) == 0)
	{
		/*OpenPrinter call failed*/
		return -1;
	}

	DWORD dwBufsize = 0;
	PRINTER_INFO_2* pinfo = 0;
	int nRet = 0;
	nRet = GetPrinter(hPrinter, 2, (LPBYTE)pinfo, dwBufsize, &dwBufsize); //Get dwBufsize
	DWORD dwGetPrinter = 0;
	if (nRet == 0)
	{
		dwGetPrinter = GetLastError();
	}

	PRINTER_INFO_2* pinfo2 = (PRINTER_INFO_2*)malloc(dwBufsize); //Allocate with dwBufsize
	nRet = GetPrinter(hPrinter, 2, reinterpret_cast<LPBYTE>(pinfo2), dwBufsize, &dwBufsize);
	if (nRet == 0)
	{
		dwGetPrinter = GetLastError();
		return -2;
	}

	if (pinfo2->Attributes & PRINTER_ATTRIBUTE_WORK_OFFLINE)
	{
		free(pinfo2);
		ClosePrinter(hPrinter);
		return 1;
	}

	if (pinfo2->Status & PRINTER_STATUS_PAPER_OUT) {
		free(pinfo2);
		ClosePrinter(hPrinter);
		return 2;
	}

	if (pinfo2->Status & PRINTER_STATUS_NO_TONER) {
		free(pinfo2);
		ClosePrinter(hPrinter);
		return 3;
	}

	free(pinfo2);
	ClosePrinter(hPrinter);
	return 0;
}