##### 一、ioc
- IoC 是什么：wiki：控制反转（Inversion of Control，缩写为IoC），是面向对象编程中的一种设计原则，可以用来减低计算机代码之间的耦合度。其中最常见的方式叫做依赖注入（Dependency Injection，简称DI）。通过控制反转，对象在被创建的时候，由一个调控系统内所有对象的外界实体，将其所依赖的对象的引用传递给它。也可以说，依赖被注入到对象中。
- ioc的方式：DI（工厂模式）
- ioc好处：解耦
- cglib：动态代理，我理解为python的装饰器
``` java
public class SampleClass {
    public void test(){
        System.out.println("hello world");
    }
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println("before method run...");
                Object result = proxy.invokeSuper(obj, args);
                System.out.println("after method run...");
                return result;
            }
        });
        SampleClass sample = (SampleClass) enhancer.create();
        sample.test();
    }
}
``` 
输出的结果为
```
before method run...
hello world
after method run... 
``` 
##### 二、AOP
- AOP是代理模式
- 适配器模式：
	- 适配器：适配器将一个类的接口，转换成客户需要的另外一个接口。是原本由于接口不兼容而不能一起工作的哪些类可以在一起工作。
	- 目的：解决不兼容不匹配的问题。原本接口 -> 适配器 -> 目标接口
- 介绍网页：https://www.xilidou.com/2018/01/08/spring-ioc/
##### 三、网站
- 面试题一：http://www.importnew.com/15851.html
- 面试题二：https://www.cnblogs.com/wang-meng/p/5701982.html
##### 四、整理
一、spring中的BeanFactory与ApplicationContext的作用有哪些？
- 1、BeanFactory负责读取bean配置文档，管理bean的加载，实例化，维护bean之间的依赖关系，负责bean的声明周期。
- 2、ApplicationContext除了提供上述BeanFactory所能提供的功能之外，还提供了更完整的框架功能：
	- a. 国际化支持
	- b. 资源访问：Resource rs = ctx. getResource(”classpath:config.properties”), “file:c:/config.properties”
	- c. 事件传递：通过实现ApplicationContextAware接口
- 3、常用的获取ApplicationContext的方法：
FileSystemXmlApplicationContext：从文件系统或者url指定的xml配置文件创建，参数为配置文件名或文件名数组
ClassPathXmlApplicationContext：从classpath的xml配置文件创建，可以从jar包中读取配置文件
WebApplicationContextUtils：从web应用的根目录读取配置文件，需要先在web.xml中配置，可以配置监听器或者servlet来实现
```
<listener>
<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>
<servlet>
<servlet-name>context</servlet-name>
<servlet-class>org.springframework.web.context.ContextLoaderServlet</servlet-class>
<load-on-startup>1</load-on-startup>
</servlet>
```
这两种方式都默认配置文件为web-inf/applicationContext.xml，也可使用context-param指定配置文件
```
<context-param>
<param-name>contextConfigLocation</param-name>
<param-value>/WEB-INF/myApplicationContext.xml</param-value>
</context-param>
```