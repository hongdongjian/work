package Util;

import Dto.Student;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Print {

	public enum Type {
		pdf, xlsx, docx
	}
	public static Map<String,Object> getParamMap() {
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("amount", 10);
		return paramMap;
	}
	
	public static void printXlsx(JasperPrint print, String outPath) throws IOException, JRException {
		System.out.println("打印xlsx文件是:"+print);
	      
	    //如果只注明文件名字，默认会生成在user.dir  
	    String fileName = "report.xlsx";

	    // 网络下载可设置
//	    response.setContentType("application/vnd.ms-excel");
//	    response.setHeader("Content-Disposition", "attachment;filename=\"" +
//	    java.net.URLEncoder.encode(fileName,"utf-8") + "\"");
	      
	    JRXlsxExporter xlsxExporter = new JRXlsxExporter();

	    xlsxExporter.setExporterInput(new SimpleExporterInput(print));

	    // 网络下载的输出流
//	    xlsxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));

		File file = new File(outPath + fileName);
		xlsxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(new FileOutputStream(file)));
	    
        SimpleXlsxReportConfiguration xlsxReportConfiguration = new SimpleXlsxReportConfiguration();
        xlsxReportConfiguration.setOnePagePerSheet(false);
        //删除空白行
        xlsxReportConfiguration.setRemoveEmptySpaceBetweenRows(true);
        //无背景色
        xlsxReportConfiguration.setWhitePageBackground(false);
        
        xlsxExporter.setConfiguration(xlsxReportConfiguration);
        
        xlsxExporter.exportReport();
        
	    System.out.println("打印结束====");
	    
	}
	
	public static void printPdf(JasperPrint print,String outPath) throws IOException, JRException {
		System.out.println("打印pdf文件是:"+print);
	      
	    //如果只注明文件名字，默认会生成在user.dir  
	    String fileName = "report.pdf";

	    // 网络下载可设置
//	    response.setContentType("application/pdf");
//	    response.setHeader("Content-Disposition", "attachment;filename=\"" +
//	    java.net.URLEncoder.encode(fileName,"utf-8") + "\"");
	      
	    JRPdfExporter exporter = new JRPdfExporter();

	    exporter.setExporterInput(new SimpleExporterInput(print));
	    //网络下载设置输出流
//	    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));

		File file = new File(outPath + fileName);
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(new FileOutputStream(file)));

	    exporter.exportReport();
	    System.out.println("打印结束====");
	}

	public static void printDocx(JasperPrint print,String outPath) throws IOException, JRException {
		System.out.println("打印doc文件是:"+print);

		//如果只注明文件名字，默认会生成在user.dir
		String fileName = "report.docx";

		//网络下载，设置
//		response.setContentType("application/x-download");
//		response.setHeader("Content-Disposition", "attachment;filename=\"" +
//				java.net.URLEncoder.encode(fileName,"utf-8") + "\"");

		JRDocxExporter exporter = new JRDocxExporter();


		exporter.setExporterInput(new SimpleExporterInput(print));
		// 网络下载设置输出流
//		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));

		File file = new File(outPath + fileName);
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(new FileOutputStream(file)));
		exporter.exportReport();
		System.out.println("打印结束====");
	}

	public static void print(Type type,String outPath) {
		try {

			// param参数
			Map<String,Object> paramMap = getParamMap();
			// field参数
			List<Student> studentList = SampleJRDataSourceFactory.createBeanCollection();

			JasperReport jasperReport;
			// jrxml位置
			String path = Print.class.getResource("").getPath() + "report.jrxml";
			path = path.substring(1,path.length());
			File x = new File(path);
			if (x.exists()) {
				System.out.println("exit");
			} else {
				System.out.println("not exit");
			}
			System.out.println(path);
			jasperReport = JasperCompileManager.compileReport(path);

			// 填充报表的参数
			JRDataSource dataSource = new JRBeanCollectionDataSource(studentList, true);
			// print文件
			JasperPrint print = JasperFillManager.fillReport(jasperReport, paramMap, dataSource);

			if (type == Type.xlsx) {
				printXlsx(print,outPath);
			} else if (type == Type.pdf) {
				printPdf(print,outPath);
			} else if (type == Type.docx) {
				printDocx(print,outPath);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
