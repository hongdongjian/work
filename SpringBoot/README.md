# SpringBoot
spring boot + hibernate + mysql 的 demo

## 知识点
#### spring boot的搭建，很简单，一个pom.xml文件即可
#### hibernate的配置，通过类的形式，在DatabaseConfig这个类里
#### mvc的分层：controller(控制层)、Dao(数据访问层)、Service(业务逻辑层)
#### 接口的使用，接口的继承

## spring的注解
#### spring的组件自动扫描机制会找到@Service,@Controller,@Repository（还有项目里未使用到的@Component）注解的类，将其声明为一个bean，在spring mvc中需要一段xml配置来开启自动扫描机制。（ <context:component-scan base - package=”springboot”> ）在spring boot中不需要。
#### @Service 标注业务层组件
#### @Controoler 标注控制层组件
######         在spring mvc中需要在xml中配置 < mvc:annotation-driven / >，否则org.springframework.web.servlet.DispatcherServlet无法找到控制器并把请求分发到控制器
#### @Repository 标注数据访问层组件
#### @Resource ：默认按byName自动注入（想通过type，可使用(@Resource(type=**.class))）
#### @Autowired ：默认按byType自动注入（想通过name，可使用(@Autowired() @Qualifier("baseDao"))）
######         注入：按照名称或类型找到唯一匹配的bean进行装配，当匹配到多个bean或没有时，spring容器启动时抛出BeanCreationException异常
######         我的理解，注入其实就是set，通过注解，找到合适的bean去set
######         相当于以前的ref
######         < bean id="office" class="springboot.service.impl.ImageServiceImpl" >    
######              < property name="imageDao" value="imageDaoImpl"/ >    
######         < /bean > 
#### @Transactional：进行事物管理
######        * 默认当抛出runtimeException时回滚
######        * 使用@Transactional(rollbackFor=Exception.class)，抛出Exception就回滚
######        * 使用@Transactional(rollbackFor=Exception.class), 抛出Exception不回滚
######        * 注：IOException、SQLException、RuntimeException 继承 Exception
#### @RequestMapping : 处理请求地址映射的注解，用于类上，则类中的所有响应请求的方法都是以该地址作为父路径，用于方法上，就是子路径，此时方法的路径就是父路径加子路径
#### @ResponseBody ：将内容或对象作为 HTTP 响应正文返回
#### @Entity : 此注解下的类都会被hibernate转成表，表名为class名
#### @Id : 表示这是主键
#### 。。。。。。。。。。。。。。

## hibernate的配置
#### 通过DatabaseConfig类将LocalSessionFactoryBean和HibernateTransactionManager加载到spring容器中
#### 在项目中通过@bean告诉sessionFactory方法，返回一个bean给spring 容器，而这个bean就是LocalSessionFactoryBean。
#### 然后通过@Resource自动注入到baseDaoImpl的sessionFactory中，然后通过sessionFactory的方法getCurrentSession()得到session，通过getCurrentSession()方法得到的session会自动释放，如果使用的是openSession()方法，则需要手动释放。接着我们就可以通过session来进行save,delete以及update等方法来处理数据。
######    在spring mvc中需要在xml中配置：
######        < bean id="transactionManager" class="org.springframework.orm.hibernate4.HibernateTransactionManager" >
######            < property name="dataSource" ref="dataSource" / >
######            < property name="sessionFactory" ref="sessionFactory" / >
######        < /bean >
######        < tx:annotation-driven / >
######      同样在spring-boot中不需要配置
##功能
#### 上传文件==，mvc模式下的上传文件==
#### 处理过程：handleFileUpload得到“/upload”请求,imageService.doUpload处理文件上传的方法，imageDao.save将路径保存到数据库，handleFileUpload将结果返回
