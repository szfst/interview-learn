# spring
IOC
- 什么东西被控制反转：获得依赖对象的过程被反转了。
- spring的注入：是指在启动spring容器加载bean配置的时候，完成对变量的赋值行为。
- spring常见注入的方式：
	- 设值注入：自动调用set方法
	- 构造注入：在调用构造方法的时候注入
- bean的作用域：
	- --singleton：单例，指一个Bean 在容器中只存在一份，类似于设计模式中的单例模式（如果定义了两个容器，那么就会有两个）（**spring默认的bean作用域**）
	- --prototype：每次请求（使用）都会创建新的实例，destroy方法不生效，类似工厂模式（由于开销大，尽量避免使用这种方式）
	- --request：每次的http请求创建一个实例，且仅在当前的request内有效
	- --session：同上，但是当前session有效，而session的作用域相对较大，
	- --golbal session：基于portlet的web中有效（portlet定义了global session），如果是web中，同session，（portlet ： 基于java的一个web组件，由portlet容器管理，并且由容器处理请求，生产动态内容）
- bean的生命周期：定义，初始化，使用，销毁（IOC容器关闭的时候才用销毁方法）
- 关于bean初始化和销毁同时使用的注意情况：
	- 1，默认全局的初始化和销毁方法；
	- 2，实现接口的初始化和销毁方法；
	- 3，配置文件中配置初始化和销毁方法；
这三个方法同时使用时，1默认的则不执行，而23两种都会执行，并且是2实现接口的方式先于配置中3的执行。
- aware：spring 提供了很多 Aware 结尾的接口，实现了aware接口的bean在初始化之后，可以获取spring某些资源，可以对资源进行一些操作（慎重使用这些操作），可以用于简单的扩展。以applicationContextAware接口为例，要override 其中的setApplicationContext方法，该方法在初始化IOC容器时候（方法Before（）调用的时候）调用。也就是说，test1(）之前的@Test标识意味着执行测试方法之前先执行Before（）方法，执行测试方法之后执行After（）方法。
- 自动装配：为什么需要自动装配：前面讲依赖注入的两种方式（设值注入和构造注入），需要在xml的<bean>下面写property或者constructor-arg，不简洁。现在只需要在<beans>最后一行加上default-autowire="byName" (当然也可以是byType)。然后再在对应sevice类中声明dao成员变量，再实现一个set方法即可实现自动装配。
- resource：加载资源文件方式：1，classpath 2,file（硬盘地址） 3,url（可以网络地址） 4,什么都不写 相当于classpath；classpath 有用是由于工程的buildpath 下面有resourse 文件夹，所以把资源文件放在resources 下面通过classpath 就可以访问到了
- @Autowired 的三种注入方式，成员变量、构造函数、set 方法
- @Autowired注解那些众所周知的解析依赖性接口，比如：BeanFactory，ApplicationContext，Environment，ResourceLoader，ApplicationEventPublisher，MessageSource。eg:
```java
@Autowired
ApplicationContext context;
```
- @Autowired如果一个接口有两个实现类，那么可以@Autowired一个list或者map，把两个都注入进来；如果希望bean有序，可以让bean（实现类）实现order接口或者@order注解
- @Qualifier注解
	- 1.当ApplicationContext中某一类型的bean有多个时，可以通过该注解减小使用范围，或者直接指定用某一个特定的bean。
eg:@Qualifier("id")//其中id是bean的id
	- 2.@Qualifier注解可以用在成员变量上和成员方法的参数上
	- 3.当使用名称进行bean装配使用时，可以用@Resource注解
- 通过两个注解来共同完成相当于在xml中配置bean的效果。
通常与@bean共用的注解是@configuration而不是@component。
在方法头加上@bean注解，然后方法返回一个bean实例，完成向springIOC容器中注册一个bean实例。**注意**：在没有指定id的时候，@Bean注解之后返回的对象id是方法名称。
- 在Configuration中使用注解ImportResource("...") 引入一个xml文件，并对每一个对象使用@Value("${....}")引入对应properties文件中的键值。（注意：用@value("username")会返回当前系统用户的名称，错误的）
```java
@Configuration
@ImportResource("com/beanannotation/javabased/config.xml")
public class StoreConfig {
  
  @Value("${url}")
  String url;
  //注意：如果用username回获取到当前系统用户的名称，所以加上jdbc
  @Value("${jdbc.username}")
  String username;
  @Value("${password}")
  String password;
  
  @Bean("myDirverManager")
  public MyDriverManager myDriverManager(){
    return new MyDriverManager(url, username, password);
  }
}
```
- @bean和@component的区别
	- Componet 一般放在类上面，Bean放在方法上面，自己可控制是否生成bean
	- bean 一般会放在classpath scanning路径下面（也就是配置类路径），会自动生成bean
	- 有Componet /bean生成的bean都提供给autowire使用，也允许在通过类路径扫描自动发现
- AOP实现方式：1、预编译（aspectj） 2、运行时动态代理 （jdk动态代理，cglib动态代理）（springAOP，JbossAOP）
- springAOP并不是一个完整的aop的实现，它只解决企业中的常见问题
- spring AOP实现方式：如果一个类实现了接口，用javaSE默认的AOP方式，如果没有实现接口，则用cglib的实现方式；默认使用标注的javase动态代理作为aop代理，这使得任何接口（或者解耦集）都可以被代理。spring AOP中也可以使用cglib代理（如果一个业务对象并没有实现一个接口）
- spring AOP中使用cglib代理的时候，使用的是装饰器模式；final方法不能被通知，因为cglib是通过生成目标类的子类做代理，final不能被子类覆盖。

