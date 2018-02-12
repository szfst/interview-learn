##### һ��ioc
- IoC ��ʲô��wiki�����Ʒ�ת��Inversion of Control����дΪIoC����������������е�һ�����ԭ�򣬿����������ͼ��������֮�����϶ȡ���������ķ�ʽ��������ע�루Dependency Injection�����DI����ͨ�����Ʒ�ת�������ڱ�������ʱ����һ������ϵͳ�����ж�������ʵ�壬�����������Ķ�������ô��ݸ�����Ҳ����˵��������ע�뵽�����С�
- ioc�ķ�ʽ��DI������ģʽ��
- ioc�ô�������
- cglib����̬���������Ϊpython��װ����
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
����Ľ��Ϊ
```
before method run...
hello world
after method run... 
``` 
##### ����AOP
- AOP�Ǵ���ģʽ
- ������ģʽ��
	- ����������������һ����Ľӿڣ�ת���ɿͻ���Ҫ������һ���ӿڡ���ԭ�����ڽӿڲ����ݶ�����һ��������Щ�������һ������
	- Ŀ�ģ���������ݲ�ƥ������⡣ԭ���ӿ� -> ������ -> Ŀ��ӿ�
- ������ҳ��https://www.xilidou.com/2018/01/08/spring-ioc/
##### ������վ
- ������һ��http://www.importnew.com/15851.html
- ���������https://www.cnblogs.com/wang-meng/p/5701982.html
##### �ġ�����
һ��spring�е�BeanFactory��ApplicationContext����������Щ��
- 1��BeanFactory�����ȡbean�����ĵ�������bean�ļ��أ�ʵ������ά��bean֮���������ϵ������bean���������ڡ�
- 2��ApplicationContext�����ṩ����BeanFactory�����ṩ�Ĺ���֮�⣬���ṩ�˸������Ŀ�ܹ��ܣ�
	- a. ���ʻ�֧��
	- b. ��Դ���ʣ�Resource rs = ctx. getResource(��classpath:config.properties��), ��file:c:/config.properties��
	- c. �¼����ݣ�ͨ��ʵ��ApplicationContextAware�ӿ�
- 3�����õĻ�ȡApplicationContext�ķ�����
FileSystemXmlApplicationContext�����ļ�ϵͳ����urlָ����xml�����ļ�����������Ϊ�����ļ������ļ�������
ClassPathXmlApplicationContext����classpath��xml�����ļ����������Դ�jar���ж�ȡ�����ļ�
WebApplicationContextUtils����webӦ�õĸ�Ŀ¼��ȡ�����ļ�����Ҫ����web.xml�����ã��������ü���������servlet��ʵ��
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
�����ַ�ʽ��Ĭ�������ļ�Ϊweb-inf/applicationContext.xml��Ҳ��ʹ��context-paramָ�������ļ�
```
<context-param>
<param-name>contextConfigLocation</param-name>
<param-value>/WEB-INF/myApplicationContext.xml</param-value>
</context-param>
```