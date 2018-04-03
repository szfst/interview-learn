#### BeanFactory �� FactoryBean��
https://blog.csdn.net/joenqc/article/details/66479154
- Beanfactory �� Factory bean������ BeanFactory ָ���� IOC �����ı�̳��󣬱��� ApplicationContext�� XmlBeanFactory �ȣ���Щ���� IOC �����ľ�����֣���Ҫʹ��ʲô���������ɿͻ�����,�� Spring Ϊ�����ṩ�˷ḻ��ѡ�� FactoryBean ֻ��һ�������� IOC�������б������һ�� bean,�ǶԸ��ִ�����̺���Դʹ�õĳ���,Factory bean ����Ҫʱ������һ�����󣬶������� FactoryBean����,���ǿ��԰���������һ�����󹤳��������ĵ��÷��ص��ǹ��������Ĳ�Ʒ�����е� Factory bean ��ʵ�������org.springframework.beans.factory.FactoryBean �ӿڣ���ʹ�������� factory bean ��ʱ�򣬸��������᷵�� factory bean ����,���Ƿ��������ɵĶ���Spring �����˴󲿷ֵ�ͨ����Դ�ͷ�����ʳ���� Factory bean ��ʵ�֣����а���:�� JNDI ��ѯ�Ĵ����Դ������Ĵ����������Դ���Ĵ����� RMI ����Ĵ���ȣ���Щ���Ƕ����Կ����Ǿ���Ĺ���,������SPRING Ϊ���ǽ����õĹ�����Ҳ����˵ Spring ͨ��ʹ�ó��󹤳�ģʽΪ����׼����һϵ�й���������һЩ�ض��Ķ���,��������ֹ��ظ��Ĺ���������Ҫʹ��ʱֻ��Ҫ�� IOC ���������úþ��ܷܺ����ʹ����.
- BeanFactory�Ǹ�Factory��Ҳ����IOC��������󹤳�����Spring�У����е�Bean������BeanFactory(Ҳ����IOC����)�����й���ģ��ṩ��ʵ����������ö���Ĺ��ܣ�FactoryBean�Ǹ�Bean�����Bean���Ǽ򵥵�Bean������һ���������������ζ������ɵĹ���Bean,����ʵ�������ģʽ�еĹ���ģʽ��������ģʽ���ơ�
#### Spring IOC ����⣬���ʼ������
https://www.cnblogs.com/ITtangtang/p/3978349.html
#### BeanFactory �� ApplicationContext
- ApplicationContext��BeanFactory�����������ṩ�˸�������ʵ��Ӧ�õĹ��ܡ���BeanFactory�У��ܶ๦����Ҫ�Ա�̵ķ�ʽʵ�֣�����ApplicationContext�������ͨ������ʵ�֡�
- BeanFactorty�ӿ��ṩ�����ÿ�ܼ��������ܣ������޷�֧��spring��aop���ܺ�webӦ�á���ApplicationContext�ӿ���ΪBeanFactory������������ṩBeanFactory���еĹ��ܡ�����ApplicationContext���ڹ�����������չ�������BeanFactorty��ApplicationContext���ṩ�����µĹ��ܣ� 
	- ��1��MessageSource, �ṩ���ʻ�����Ϣ����  
	- ��2����Դ���ʣ���URL���ļ�  
	- ��3���¼��������ԣ���֧��aop����
	- ��4�����������м̳й�ϵ�������� ��ʹ��ÿһ�������Ķ�רע��һ���ض��Ĳ�Σ�����Ӧ�õ�web�� 
- https://www.cnblogs.com/xiaoxi/p/5846416.html
- BeanFactory�����˹������ģʽ�������ȡbean�����ĵ�������bean�ļ��أ�ʵ������ά��bean֮���������ϵ������bean���������ڡ���ApplicationContext�����ṩ����BeanFactory�����ṩ�Ĺ���֮�⣬���ṩ�˸������Ŀ�ܹ��ܣ����ʻ�֧�֡�aop������ȡ�ͬʱBeanFactory�ڽ��������ļ�ʱ�������ʼ������,ֻ����ʹ�ö���getBean()�Ż�Ըö�����г�ʼ������ApplicationContext�ڽ��������ļ�ʱ�������ļ��е����ж��󶼳�ʼ����,getBean()����ֻ�ǻ�ȡ����Ĺ��̡�[�ο�](https://blog.csdn.net/qiesheng/article/details/60869592)
#### Spring Bean ���������ڣ���α������
![avatar](https://pic1.zhimg.com/80/v2-baaf7d50702f6d0935820b9415ff364c_hd.jpg)
- 1. ʵ����Bean:����BeanFactory���������ͻ�����������һ����δ��ʼ����beanʱ�����ʼ��bean��ʱ����Ҫע����һ����δ��ʼ��������ʱ�������ͻ����createBean����ʵ������ ����ApplicationContext���������������������󣬱�ʵ�������е�bean�� ����ͨ����ȡBeanDefinition�����е���Ϣ����ʵ������������һ�������Ǽ򵥵�ʵ��������δ��������ע�롣 ʵ�������󱻰�װ��BeanWrapper�����У�BeanWrapper�ṩ�����ö������ԵĽӿڣ��Ӷ�������ʹ�÷�������������ԡ�
- 2. ���ö������ԣ�����ע�룩:ʵ������Ķ��󱻷�װ��BeanWrapper�����У����Ҵ�ʱ������Ȼ��һ��ԭ����״̬����û�н�������ע�롣 
�����ţ�Spring����BeanDefinition�е���Ϣ��������ע�롣 
����ͨ��BeanWrapper�ṩ���������ԵĽӿ��������ע�롣
- 3. ע��Aware�ӿ�:�����ţ�Spring����ö����Ƿ�ʵ����xxxAware�ӿڣ�������ص�xxxAwareʵ��ע���bean��
- 4. BeanPostProcessor:�������������������bean�����Ѿ�����ȷ���죬���������Ҫ����ʹ��ǰ�ٽ���һЩ�Զ���Ĵ����Ϳ���ͨ��BeanPostProcessor�ӿ�ʵ�֡� 
- 5. InitializingBean��init-method:��BeanPostProcessor��ǰ�ô�����ɺ�ͻ���뱾�׶Ρ� 
InitializingBean�ӿ�ֻ��һ��������afterPropertiesSet()
- 6. DisposableBean��destroy-method:��init-methodһ����ͨ����destroy-methodָ���������Ϳ�����bean����ǰִ��ָ�����߼���
- ����bean��https://blog.csdn.net/qiesheng/article/details/60869592
#### Spring Bean �ļ��ع�����������
#### ���Ҫ��ʵ��Spring AOP��������ôʵ�֣�
#### ���Ҫ��ʵ��Spring IOC�����ע����Щ���⣿
- ѭ������
- ���ּ���bean�ķ�ʽ��xml��file��json
- ������
#### Spring ����ι�������ģ����������ƣ�
https://blog.csdn.net/jie_liang/article/details/77600742 
- ������ƣ����ʽ�����������ʽ�������
- ����PlatformTransactionManager��TransactionDefinition��TransactionStatus
#### Spring �Ĳ�ͬ���񴫲���Ϊ����Щ����ʲô�õģ�
- https://github.com/szfst/interview-learn/blob/master/me/spring/spring-transaction.md
#### Spring ���õ�����Щ���ģʽ
[�ο�](https://www.cnblogs.com/hwaggLee/p/4510687.html)
- ��̬������Aopʵ�����õ���JDK�Ķ�̬����CGLib�ֽ������ɼ�������
- ����ģʽ��cglib��java�������ֲ���
- ������ģʽ��AdvisorAdapter ������Advisor����Ҫ����MethodInterceptor������������������ÿһ��Advisor�е�Advice��Ҫ����ɶ�Ӧ��MethodInterceptor����
- ����ģʽ����������ԣ��ڸ���BeanFactory�Լ�ApplicationContext�����ж��õ���
- ����ģʽ������bean��ʱ��
- �۲��ߣ�Observer�������������һ��һ�Զ��������ϵ����һ�������״̬�����ı�ʱ���������������Ķ��󶼵õ�֪ͨ�����Զ����¡�
spring��Observerģʽ���õĵط���listener��ʵ�֡���ApplicationListener�� 
- ��װ����Decorator��
- ģ�巽����Template Method��
#### Spring MVC �Ĺ���ԭ��
- https://www.cnblogs.com/xiaoxi/p/6164383.html
- 1��  �û�����������ǰ�˿�����DispatcherServlet��2��  DispatcherServlet�յ��������HandlerMapping������ӳ������HandlerAdapter��������������5��  HandlerAdapter����������þ���Ĵ�����(Controller��Ҳ�к�˿�����)��6��  Controllerִ����ɷ���ModelAndView��7��  HandlerAdapter��controllerִ�н��ModelAndView���ظ�DispatcherServlet��8��  DispatcherServlet��ModelAndView����ViewReslover��ͼ��������9��  ViewReslover�����󷵻ؾ���View��10��DispatcherServlet����View������Ⱦ��ͼ������ģ�������������ͼ�У���11�� DispatcherServlet��Ӧ�û���
- https://blog.csdn.net/nimeijian/article/details/49209869
#### Spring ѭ��ע���ԭ��
- ͨ���½�һ������map�����ѭ����������ǰ������δ������ɵ�bean
- http://heeexy.com/2018/01/28/IoC/
```java
@Override
public Object getBean(String name) throws Exception {
    //���Ҷ����Ƿ��Ѿ�ʵ������
    Object bean = beanMap.get(name);
    if (bean != null) {
        return bean;
    }
    Object earlyBean = earlySingletonObjects.get(name);
    if (earlyBean != null) {
        System.out.println("ѭ����������ǰ������δ������ɵ�bean:" + name);
        return earlyBean;
    }
    //���û��ʵ�������Ǿ���Ҫ����createBean����������
    BeanDefinition beanDefinition = beanDefineMap.get(name);
    bean = createBean(beanDefinition);
    if (bean != null) {
        earlySingletonObjects.put(name, bean);
        //���󴴽��ɹ��Ժ�ע�������Ҫ�Ĳ���
        populatebean(bean, beanDefinition);
        //�ٰɶ������Map�з����´�ʹ�á�
        beanMap.put(name, bean);
        //�����ڵ���Map���Ƴ�
        earlySingletonObjects.remove(name);
    }
    //��������
    return bean;
}
```
- https://blog.csdn.net/jijianshuai/article/details/78122738
- ������ע�빹�ɵ�ѭ��������������û�������ֻ���׳�BeanCurrentlyInCreationException�쳣��ʾѭ������
- setterѭ������������setterע����ɵ�������ͨ��Spring������ǰ��¶����ɹ�����ע�뵫δ����������裨��setterע�룩��Bean����ɵģ�����ֻ�ܽ�������������Beanѭ�����������ڡ�prototype��������Bean��Spring�����޷��������ע�룬��Ϊ��prototype���������Bean��Spring���������л��棬����޷���ǰ��¶һ�������е�Bean��
#### Spring AOP����⣬���������������ô�໥�����ģ�
- https://segmentfault.com/a/1190000013882720#articleHeader17
#### Spring ��α�֤ Controller �����İ�ȫ
Spring ���߳��������Ĭ�ϵ��õ�Controller������һ����������һ����������ʹ���һ��Controller��������û�а취��Controller���Ե�������ÿ���������´�������ʽ�����أ�
���ǵ�Ȼ���ԣ�ֻ��Ҫ���������ע��**@Scope("prototype")**���ɣ�����ÿ��������õ��඼���������ɵģ�ÿ�����ɻ�Ӱ��Ч�ʣ�
��Ȼ�������Խ�����⣬��������ʱ��ɱ��������˲�ˬ��������������ô�����ǿ϶��ģ�
**ʹ��ThreadLocal�����������**����������������̵߳ı������У��ò�ͬ��������뿪����