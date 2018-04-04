#### BeanFactory 和 FactoryBean？
- FactoryBean是一个能够生产bean的bean，在getObject方法中能够实现自定义加载bean，然后返回特定的bean，而不是自己本身。（参考《spring源码深度解析》p82）
- https://blog.csdn.net/joenqc/article/details/66479154
- Beanfactory 和 Factory bean，其中 BeanFactory 指的是 IOC 容器的编程抽象，比如 ApplicationContext， XmlBeanFactory 等，这些都是 IOC 容器的具体表现，需要使用什么样的容器由客户决定,但 Spring 为我们提供了丰富的选择。 FactoryBean 只是一个可以在 IOC而容器中被管理的一个 bean,是对各种处理过程和资源使用的抽象,Factory bean 在需要时产生另一个对象，而不返回 FactoryBean本身,我们可以把它看成是一个抽象工厂，对它的调用返回的是工厂生产的产品。所有的 Factory bean 都实现特殊的org.springframework.beans.factory.FactoryBean 接口，当使用容器中 factory bean 的时候，该容器不会返回 factory bean 本身,而是返回其生成的对象。Spring 包括了大部分的通用资源和服务访问抽象的 Factory bean 的实现，其中包括:对 JNDI 查询的处理，对代理对象的处理，对事务性代理的处理，对 RMI 代理的处理等，这些我们都可以看成是具体的工厂,看成是SPRING 为我们建立好的工厂。也就是说 Spring 通过使用抽象工厂模式为我们准备了一系列工厂来生产一些特定的对象,免除我们手工重复的工作，我们要使用时只需要在 IOC 容器里配置好就能很方便的使用了.
- BeanFactory是个Factory，也就是IOC容器或对象工厂，在Spring中，所有的Bean都是由BeanFactory(也就是IOC容器)来进行管理的，提供了实例化对象和拿对象的功能；FactoryBean是个Bean，这个Bean不是简单的Bean，而是一个能生产或者修饰对象生成的工厂Bean,它的实现与设计模式中的工厂模式和修饰器模式类似。
#### Spring IOC 的理解，其初始化过程
https://www.cnblogs.com/ITtangtang/p/3978349.html
#### BeanFactory 和 ApplicationContext
- ApplicationContext由BeanFactory派生而来，提供了更多面向实际应用的功能。在BeanFactory中，很多功能需要以编程的方式实现，而在ApplicationContext中则可以通过配置实现。
- BeanFactorty接口提供了配置框架及基本功能，但是无法支持spring的aop功能和web应用。而ApplicationContext接口作为BeanFactory的派生，因而提供BeanFactory所有的功能。而且ApplicationContext还在功能上做了扩展，相较于BeanFactorty，ApplicationContext还提供了以下的功能： 
	- （1）MessageSource, 提供国际化的消息访问  
	- （2）资源访问，如URL和文件  
	- （3）事件传播特性，即支持aop特性
	- （4）载入多个（有继承关系）上下文 ，使得每一个上下文都专注于一个特定的层次，比如应用的web层 
- https://www.cnblogs.com/xiaoxi/p/5846416.html
- BeanFactory采用了工厂设计模式，负责读取bean配置文档，管理bean的加载，实例化，维护bean之间的依赖关系，负责bean的声明周期。而ApplicationContext除了提供上述BeanFactory所能提供的功能之外，还提供了更完整的框架功能：国际化支持、aop、事务等。同时BeanFactory在解析配置文件时并不会初始化对象,只有在使用对象getBean()才会对该对象进行初始化，而ApplicationContext在解析配置文件时对配置文件中的所有对象都初始化了,getBean()方法只是获取对象的过程。[参考](https://blog.csdn.net/qiesheng/article/details/60869592)
#### Spring Bean 的生命周期，如何被管理的
![avatar](https://pic1.zhimg.com/80/v2-baaf7d50702f6d0935820b9415ff364c_hd.jpg)
- 1. 实例化Bean:对于BeanFactory容器，当客户向容器请求一个尚未初始化的bean时，或初始化bean的时候需要注入另一个尚未初始化的依赖时，容器就会调用createBean进行实例化。 对于ApplicationContext容器，当容器启动结束后，便实例化所有的bean。 容器通过获取BeanDefinition对象中的信息进行实例化。并且这一步仅仅是简单的实例化，并未进行依赖注入。 实例化对象被包装在BeanWrapper对象中，BeanWrapper提供了设置对象属性的接口，从而避免了使用反射机制设置属性。
- 2. 设置对象属性（依赖注入）:实例化后的对象被封装在BeanWrapper对象中，并且此时对象仍然是一个原生的状态，并没有进行依赖注入。 
紧接着，Spring根据BeanDefinition中的信息进行依赖注入。 
并且通过BeanWrapper提供的设置属性的接口完成依赖注入。
- 3. 注入Aware接口:紧接着，Spring会检测该对象是否实现了xxxAware接口，并将相关的xxxAware实例注入给bean。
- 4. BeanPostProcessor:当经过上述几个步骤后，bean对象已经被正确构造，但如果你想要对象被使用前再进行一些自定义的处理，就可以通过BeanPostProcessor接口实现。 
- 5. InitializingBean与init-method:当BeanPostProcessor的前置处理完成后就会进入本阶段。 
InitializingBean接口只有一个函数：afterPropertiesSet()
- 6. DisposableBean和destroy-method:和init-method一样，通过给destroy-method指定函数，就可以在bean销毁前执行指定的逻辑。
- 管理bean：https://blog.csdn.net/qiesheng/article/details/60869592
#### Spring Bean 的加载过程是怎样的
#### 如果要你实现Spring AOP，请问怎么实现？
#### 如果要你实现Spring IOC，你会注意哪些问题？
- 循环依赖
- 多种加载bean的方式，xml，file，json
- 灵活，抽象
#### Spring 是如何管理事务的，事务管理机制？
https://blog.csdn.net/jie_liang/article/details/77600742 
- 事务机制：编程式事务管理、声明式事务管理
- 管理：PlatformTransactionManager、TransactionDefinition、TransactionStatus
#### Spring 的不同事务传播行为有哪些，干什么用的？
- https://github.com/szfst/interview-learn/blob/master/me/spring/spring-transaction.md
#### Spring 中用到了那些设计模式
[参考](https://www.cnblogs.com/hwaggLee/p/4510687.html)
- 动态代理：在Aop实现中用到了JDK的动态代理；CGLib字节码生成技术代理；
- 策略模式：cglib和java代理两种策略
- 适配器模式：AdvisorAdapter ，由于Advisor链需要的是MethodInterceptor（拦截器）对象，所以每一个Advisor中的Advice都要适配成对应的MethodInterceptor对象。
- 工厂模式：这个很明显，在各种BeanFactory以及ApplicationContext创建中都用到了
- 单例模式：创建bean的时候
- 观察者（Observer）：定义对象间的一种一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都得到通知并被自动更新。
spring中Observer模式常用的地方是listener的实现。如ApplicationListener。 
- 包装器（Decorator）
- 模板方法（Template Method）
#### Spring MVC 的工作原理？
- https://www.cnblogs.com/xiaoxi/p/6164383.html
- 1、  用户发送请求至前端控制器DispatcherServlet。2、  DispatcherServlet收到请求调用HandlerMapping处理器映射器及HandlerAdapter处理器适配器。5、  HandlerAdapter经过适配调用具体的处理器(Controller，也叫后端控制器)。6、  Controller执行完成返回ModelAndView。7、  HandlerAdapter将controller执行结果ModelAndView返回给DispatcherServlet。8、  DispatcherServlet将ModelAndView传给ViewReslover视图解析器。9、  ViewReslover解析后返回具体View。10、DispatcherServlet根据View进行渲染视图（即将模型数据填充至视图中）。11、 DispatcherServlet响应用户。
- https://blog.csdn.net/nimeijian/article/details/49209869
#### Spring 循环注入的原理？
- 通过新建一个缓存map解决：循环依赖，提前返回尚未加载完成的bean
- http://heeexy.com/2018/01/28/IoC/
```java
@Override
public Object getBean(String name) throws Exception {
    //查找对象是否已经实例化过
    Object bean = beanMap.get(name);
    if (bean != null) {
        return bean;
    }
    Object earlyBean = earlySingletonObjects.get(name);
    if (earlyBean != null) {
        System.out.println("循环依赖，提前返回尚未加载完成的bean:" + name);
        return earlyBean;
    }
    //如果没有实例化，那就需要调用createBean来创建对象
    BeanDefinition beanDefinition = beanDefineMap.get(name);
    bean = createBean(beanDefinition);
    if (bean != null) {
        earlySingletonObjects.put(name, bean);
        //对象创建成功以后，注入对象需要的参数
        populatebean(bean, beanDefinition);
        //再吧对象存入Map中方便下次使用。
        beanMap.put(name, bean);
        //从早期单例Map中移除
        earlySingletonObjects.remove(name);
    }
    //结束返回
    return bean;
}
```
- https://blog.csdn.net/jijianshuai/article/details/78122738
- 构造器注入构成的循环依赖，此依赖没法解决，只能抛出BeanCurrentlyInCreationException异常表示循环依赖
- setter循环依赖：对于setter注入造成的依赖是通过Spring容器提前暴露刚完成构造器注入但未完成其他步骤（如setter注入）的Bean来完成的，而且只能解决单例作用域的Bean循环依赖。对于“prototype”作用域Bean，Spring容器无法完成依赖注入，因为“prototype”作用域的Bean，Spring容器不进行缓存，因此无法提前暴露一个创建中的Bean。
#### Spring AOP的理解，各个术语，他们是怎么相互工作的？
- https://segmentfault.com/a/1190000013882720#articleHeader17
#### Spring 如何保证 Controller 并发的安全
Spring 多线程请求过来默认调用的Controller对象都是一个，而不是一个请求过来就创建一个Controller对象。那有没有办法让Controller不以单例而以每次请求都重新创建的形式存在呢？
答案是当然可以，只需要在类上**添加注解@Scope("prototype")**即可**，这样每次请求调用的类都是重新生成的（每次生成会影响效率）
虽然这样可以解决问题，但增加了时间成本，总让人不爽，还有其他方法么？答案是肯定的！
**使用ThreadLocal来保存类变量**，将类变量保存在线程的变量域中，让不同的请求隔离开来。
