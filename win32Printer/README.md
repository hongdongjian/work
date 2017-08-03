# win32Printer
adjust printer status by win32

### this is a dll project
### you can edit it by vs

##### java中导入/DeBug/PrintDLL.dll,并动态链接里面的方法printerStatus
##### 传递1 表示EPSON L310 Serie打印机
##### 传递2 表示EPSON_L310_Serie打印机

##### 返回-1 找不到服务
##### 返回-2 失败
##### 返回0 表示没问题
##### 返回1 表示脱机
##### 返回2 表示缺纸
##### 返回3 表示缺墨
