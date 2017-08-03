##### swagger需要的jar查看[pom.xml](https://github.com/tobealeader/swagger/blob/master/pom.xml)文件
##### 建立[swagger](https://github.com/tobealeader/swagger/blob/master/src/java/com/swagger/config/SwaggerConfig.java)配置类
##### 在spring配置文件中，要对上面配置类文件开启组件扫描，<context:component-scan base-package="com.swagger.config"/>,这是对配置类的jar包进行扫描。（因为配置类中的一些属性需要注入）
##### 将[swagger-ui](https://github.com/tobealeader/swagger/tree/master/src/main/webapp/ui)放在webapp下
##### 这时访问http://localhost:8080/swagger/ui/index.html就能看到swagger-ui界面
##### 注：ui文件中的index.html中url为http://localhost:8080/swagger/api-docs，如果放在公网上，或者项目名不同都需对起进行修改
