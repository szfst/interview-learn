#####�����̵߳����ַ�ʽ��
- 1.�̳�Thread��
- 2.ʵ��Runnable�ӿ�
- 3.ʵ��callable�ӿڣ���дcall����
#####�̺߳ͽ��̵�����
- ��������Դ�ķ���͵��ȵ�һ��������Ԫ�����߳���CPU���ȵĻ�����Ԫ
- ͬһ�������п��԰�������̣߳������̹߳����������̵���Դ���Ĵ�������ջ�������ģ���һ���������ٰ���һ���߳�
- .����֮���໥��������ͬһ������֮����̹߳����ڴ�ռ��Լ����̼�����Դ��ĳ���̵��߳������������ǿ�������
- ����֮���໥��������ͬһ������֮����̹߳����ڴ�ռ��Լ����̼�����Դ��ĳ���̵��߳������������ǿ�������
- ���Ⱥ��л����߳��������л��Ƚ����������л�Ҫ��Ķ�
#####ΪʲôҪ���̣߳������ǽ����ý��̣�
- 1.���ڵ�ȷ�������ģ����Ǻ������ż�����ķ�չ������֮����л�����̫��ʱ���ˡ�
- 2.����ֻ����һ��ʱ���һ���£������ͬʱ�������»����£����̾�����Ϊ����
- 3.������ִ�еĹ������������������ȴ����룬�������̾ͻ���𣬼�ʹ��������Щ��������������������ݣ�Ҳ���޷�ִ��
#####run()��start()��������
- ����start�������������̣߳�**��run����ֻ��thread��һ����ͨ�������ã����������߳���ִ�С�**����Ҫ���д���Ĵ������run()�����У�start()���������߳̽��Զ����� run()������������jvm���ڴ���ƹ涨�ġ�����run()����������public����Ȩ�ޣ�����ֵ����Ϊvoid��
	- ��start�����������̣߳�����ʵ���˶��߳����У���ʱ����ȴ�run���������ִ����϶�ֱ�Ӽ���ִ������Ĵ��롣ͨ������Thread��� start()����������һ���̣߳���ʱ���̴߳��ھ����������У�״̬����û�����У�һ���õ�cpuʱ��Ƭ���Ϳ�ʼִ��run()���������﷽�� run()��Ϊ�߳��壬��������Ҫִ�е�����̵߳����ݣ�Run�������н��������߳��漴��ֹ��
	- run()����ֻ�����һ����ͨ�������ѣ����ֱ�ӵ���Run��������������Ȼֻ�����߳���һ���̣߳������ִ��·������ֻ��һ��������Ҫ˳��ִ�У�����Ҫ�ȴ�run������ִ����Ϻ�ſɼ���ִ������Ĵ��룬������û�дﵽд�̵߳�Ŀ�ġ�
#####��ο���ĳ�����������������̵߳ĸ�����
```java
static Semaphore semaphore = new Semaphore(5,true)
semaphore.acquire();
semaphore.release();
```
Seamphore��һ���ź��������ڹ���һ����Դ����Ҳ��ʵ����AQS�����࣬����ɲ���ʱ���̻߳ᱻ���𣻶�һ��һ���߳��ͷ�һ����Դ����ô
����ô�Ϳ������»��ѵȴ������е��̼߳���ִ�С�
Seamphore��Ϊ��ƽģʽ�ͷǹ�ƽģʽ��
####��Java��sleep��wait�����Ĳ�ͬ��
- [�ο�](https://www.cnblogs.com/loren-Yang/p/7538482.html)
- �������������Բ�ͬ����ֱ���Thread��Object 
- ����Ҫ��sleep����û���ͷ�������wait�����ͷ�������ʹ�������߳̿���ʹ��ͬ�����ƿ���߷���(�������ͷ�����)
- wait��notify��notifyAllֻ����ͬ�����Ʒ�������ͬ�����ƿ�����ʹ�ã���sleep�������κεط�ʹ��(ʹ�÷�Χ)  
- sleep���벶���쳣����wait��notify��notifyAll����Ҫ�����쳣
- sleep��������Thread���з�������ʾ��һ���߳̽���˯��״̬���ȴ�һ����ʱ��֮���Զ��������뵽������״̬���������Ͻ�������״̬����Ϊ�̵߳��Ȼ��ƻָ��̵߳�����Ҳ��Ҫʱ�䣬һ���̶߳��������sleep����֮�󣬲������ͷ��������е����ж�����������Ҳ�Ͳ���Ӱ���������̶�������С�����sleep�Ĺ����й������п��ܱ����������������interrupt(),����InterruptedException�쳣�������ĳ��򲻲�������쳣���߳̾ͻ��쳣��ֹ������TERMINATED״̬�������ĳ��򲶻�������쳣����ô����ͻ����ִ��catch����(���ܻ���finally����)�Լ��Ժ�Ĵ��롣
- ע��sleep()������һ����̬������Ҳ����˵��ֻ�Ե�ǰ������Ч��ͨ��t.sleep()��t�������sleep�������������Ǵ���ģ���ֻ����ʹ��ǰ�̱߳�sleep ������t�߳�  
- wait����Object�ĳ�Ա������һ��һ�����������wait����������Ҫ����notify()��notifyAll()�������Ѹý���;����߳�ӵ��ĳ����ĳЩ�����ͬ��������ô�ڵ�����wait()������߳̾ͻ��ͷ������е�����ͬ����Դ���������������������wait()�����Ķ���wait()����Ҳͬ������wait�Ĺ������п��ܱ������������interrupt()���������� InterruptedException�쳣. 
- ����߳�Aϣ�����������߳�B������Զ��߳�B��Ӧ��Threadʵ������interrupt����������˿��߳�B����wait/sleep/join�����߳�B�������׳�InterruptedException����catch() {} ��ֱ��return���ɰ�ȫ�ؽ����̡߳�
- ��Ҫע����ǣ�InterruptedException���߳��Լ����ڲ��׳��ģ�������interrupt()�����׳��ġ���ĳһ�̵߳���interrupt()ʱ��������߳�����ִ����ͨ�Ĵ��룬��ô���̸߳����Ͳ����׳�InterruptedException�����ǣ�һ�����߳̽��뵽wait()/sleep()/join()�󣬾ͻ������׳�InterruptedException��
- wait()��notify()��Ϊ��Զ���ġ�����־�����в������������Ǳ�����synchronized������synchronized block�н��е��á������non-synchronized������non-synchronizedblock�н��е��ã���Ȼ�ܱ���ͨ������������ʱ�ᷢ��illegalMonitorStateException���쳣��
	- yield����  ��ͣ��ǰ����ִ�е��̶߳���  
yield()������ֹͣ��ǰ�̣߳���ͬ������Ȩ���̻߳�������ȼ����߳���ִ�еĻ��ᡣ���û�еĻ�����ôyield()���������������ã������ɿ�ִ��״̬�������ֱ�ִ�С�   join������������ĳһ���̵߳�ִ�й����е�����һ���߳�ִ�У��ȵ������õ��߳�ִ�н������ټ���ִ�е�ǰ�̡߳��磺t.join();//��Ҫ���ڵȴ�t�߳����н��������޴˾䣬main���ִ����ϣ����½������Ԥ�⡣
#####̸̸wait/notify�ؼ��ֵ����
- [�ο�](ttp://blog.csdn.net/jianiuqi/article/details/53448849)
- wait( )��notify( )��notifyAll( )��������Thread�࣬��������Object�����࣬Ҳ����**ÿ��������wait( )��notify( )��notifyAll( ) �Ĺ���**����Ϊÿ����������������ÿ������Ļ�������Ȼ�������ķ���Ҳ���������
- ����Ҫ�������ϵķ�����ʱ��**һ��Ҫ�Ծ�����Դ���м���**������������Ļ�����ᱨ IllegalMonitorStateException �쳣
- ����Ҫ����wait( )�����̵߳ȴ�ʱ������Ҫȡ�����������Ŀ���Ȩ���������������һ����**�ŵ�synchronized(obj)������**
- ��**whileѭ��**�������if�����ʹ��wait�������������߳���ͣ�ָ��󶼼��wait����������������ʵ���ϲ�δ�ı������´�����֪ͨ
- ����obj.wait( )�ͷ���obj���������������߳�Ҳ�޷����obj������Ҳ���޷���synchronized(obj){ obj.notify() } ������ڻ���A��
- notify( )����ֻ��֪ͨ�ȴ������еĵ�һ������̣߳�����֪ͨ���ȼ��Ƚϸߵ��̣߳�
- notifyAll( )֪ͨ���еȴ��þ�����Դ���̣߳�Ҳ���ᰴ���̵߳����ȼ���ִ�У�
- �����������߳�ִ����obj.wait( )����ôobj.notifyAll( )����ȫ������tread1��thread2��thread3������Ҫ����ִ��obj.wait��������һ����䣬������obj������ˣ�tread1��thread2��thread3ֻ��һ���л�����������ִ�У�����tread1���������Ҫ�ȴ�thread1�ͷ�obj��֮����ܼ���ִ�С�
- ������obj.notify/notifyAll�󣬵����߳����ɳ���obj������ˣ�thread1��thread2��thread3�䱻���ѣ��������޷����obj����**ֱ�������߳��˳�synchronized�飬�ͷ�obj����thread1��thread2��thread3�е�һ�����л�����������ִ�С�**
#####ʲô�����߳�����
- �߳�ִ����Thread.sleep(intmillsecond);��������ǰ�̷߳���CPU��˯��һ��ʱ�䣬Ȼ���ٻָ�ִ��
- �߳�ִ����һ�������wait()������ֱ�ӽ�������״̬���ȴ������߳�ִ��notify()����notifyAll()������
- �߳�ִ��һ��ͬ�����룬���������޷������ص�ͬ������ֻ�ܽ�������״̬���ȵ���ȡ��ͬ���������ܻظ�ִ��
- �߳�ִ��ĳЩIO��������Ϊ�ȴ���ص���Դ������������״̬������˵����system.in����������û���յ����̵����룬���������״̬
#####�߳���ιرգ�
- [�ο�](http://blog.csdn.net/linux2_scdn/article/details/48052153)
- ���û����������������ñ�־���������߳�������Ȼ��������г��Ҳ��������run����
```java
    private boolean running = true;  
    public void stop() {  
        this.running = false;  
    }  
  
    public void run() {  
        try {  
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));  
            while (running) {  
                System.out.println("�߳�����������...");  
                Thread.sleep(20000);  
            }  
            System.out.println("�̱߳���ֹ.");  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
  
    }  
```
- �����������������ж����������׳��쳣��ֹ�̣߳����Ʊ����������������Ԥ�ڵ��쳣����Ҳ�ǰ�ȫ��(interrupt()).������û��������������ñ�־���������߳�ѭ����
- stop()������ֹ�߳̾�����ǿ�аε���Դ�߹ػ�һ�������ܻ����δ֪���գ����Ŀǰ�����Ƽ�ʹ�����ַ�ʽ������ȫ��
#####��һ��java�е�ͬ���ķ���
[�ο�](https://www.cnblogs.com/duanxz/p/3709608.html?utm_source=tuicool&utm_medium=referral)
- һ��ͬ������:
	- ����synchronized�ؼ������εķ����� ����java��ÿ��������һ�������������ô˹ؼ������η���ʱ�� �������ᱣ�������������ڵ��ø÷���ǰ����Ҫ���������������ʹ�������״̬��
ע�� synchronized�ؼ���Ҳ�������ξ�̬��������ʱ������øþ�̬������������ס������
- ����ͬ�������:
����synchronized�ؼ������ε����顣 ���ùؼ������ε�������Զ����������������Ӷ�ʵ��ͬ��
```java
synchronized(object){ 
}
```
- ����wait��notify
- �ġ�ʹ�����������(volatile)ʵ���߳�ͬ��
- �塢ʹ��������ʵ���߳�ͬ��
ReentrantLock���ǿ����롢���⡢ʵ����Lock�ӿڵ���������ʹ��synchronized�����Ϳ������ͬ�Ļ�����Ϊ�����壬������չ����������
- ����ʹ�þֲ�����ʵ���߳�ͬ��
���ʹ��ThreadLocal�����������ÿһ��ʹ�øñ������̶߳���øñ����ĸ���������֮���໥����������ÿһ���̶߳����������޸��Լ��ı���������������������̲߳���Ӱ�졣 ThreadLocal��ͬ�����ƶ���Ϊ�˽�����߳�����ͬ�����ķ��ʳ�ͻ����
- �ߡ�ʹ��ԭ�ӱ���ʵ���߳�ͬ��(AtomicInteger)
#####��������ͬʱҪ��д���߶����ܲ���ʵ�֣���η�ֹ���̵�ͬ����
����ͬʱ��һ���ļ�д��������Ϊ�������������ɲ�һ���ԡ���ͬһ���ļ����ж��ǿ��Եģ�����ʹ�ö�д����
#####�̼߳����List
[�ο�](http://wuwenjun0919-msn-com.iteye.com/blog/2174652)
Ҫʹ��Collection..synchronizedList(new ArrayList<E>())
�����ڽ��в�����ʱ��ñ�֤ʹ��ͬһ�����Ķ���
#####Java�ж������������
[�ο�](http://blog.csdn.net/sodino/article/details/38387049)
- 1. �����׶�(Created)
	- Ϊ�������洢�ռ�
	- ��ʼ�������
	- �ӳ��ൽ�����static��Ա���г�ʼ��
	- �����Ա����˳���ʼ�����ݹ���ó���Ĺ��췽��
	- �����Ա����˳���ʼ�������๹�췽������
- 2.Ӧ�ý׶�(In Use)
	- �������ٱ�һ��ǿ���ó�����
- 3.���ɼ��׶�(Invisible)
��һ�������ڲ��ɼ��׶�ʱ��˵���������ٳ��иö�����κ�ǿ���ã���Ȼ����Щ������Ȼ�Ǵ����ŵġ����ر���count��	���һ��ʱ�Ѿ������������������ڴ�ʱ��֮Ϊcount���ڲ����ӽ׶Ρ���Ȼ��������������ڱ���Ĺ����л�ֱ�ӱ����ˡ�
```java
boolean bool = false;
if(bool == true){
	int count = 0;
	count++;
}
System.out.println(count);
```
- 4.���ɴ�׶�(Unreachable)
�����ڲ��ɴ�׶���ָ�ö����ٱ��κ�ǿ���������С�
�롰���ɼ��׶Ρ���ȣ������ɼ��׶Ρ���ָ�����ٳ��иö�����κ�ǿ���ã���������£��ö����Կ��ܱ�JVM��ϵͳ�µ�ĳЩ��װ�صľ�̬�������̻߳�JNI��ǿ���ó����ţ���Щ�����ǿ���ñ���Ϊ��GC root������������ЩGC root�ᵼ�¶�����ڴ�й¶������޷������ա�
- 5.�ռ��׶�(Collected)
	- ���������������ָö����Ѿ����ڡ����ɴ�׶Ρ����������������Ѿ��Ըö�����ڴ�ռ����·�������׼��ʱ�����������ˡ��ռ��׶Ρ�������ö����Ѿ���д��finalize()���������ȥִ�и÷������ն˲�����
	- ����Ҫ�ر�˵��һ�£���Ҫ����finazlie()������ԭ�������㣺
		-  ��Ӱ��JVM�Ķ������������ٶ�:
		-  ������ɸö�����ٴΡ�����
- 6.�ս�׶�
	- ������ִ����finalize()��������Ȼ���ڲ��ɴ�״̬ʱ����ö�������ս�׶Ρ��ڸý׶��ǵȴ������������Ըö���ռ���л��ա�
- 7.����ռ����·���׶�:
	- �����������Ըö������ռ�õ��ڴ�ռ���л��ջ����ٷ����ˣ���ö��󳹵���ʧ�ˣ���֮Ϊ������ռ����·���׶Ρ���
#####java�����������
- ����
- ����֤��׼��������������
- ��ʼ��
- ʹ��
- ж��
#####Synchronized�÷�
[�ο�](http://www.importnew.com/21866.html)
- ����һ������飬synchronized(this)
- ����һ���������������������ǵȼ۵ġ�
```java
public synchronized void method()
{
   // todo
}
public void method()
{
   synchronized(this) {
      // todo
   }
}
```
- ����һ����̬�ķ���
����֪����̬������������Ķ������ڶ���ġ�ͬ���ģ�synchronized���εľ�̬���������������������ж���
```
public synchronized static void method() {
   // todo
}
```
- ����һ����:synchronized������һ����Tʱ���Ǹ������T������T�����ж����õ���ͬһ������
```java
class ClassName {
   public void method() {
      synchronized(ClassName.class) {
         // todo
      }
   }
```
#####synchronize��ԭ��
[�ο�](http://blog.csdn.net/u012715840/article/details/58247556)
#####̸̸��Synchronized�ؼ��֣��������������������������
#####static synchronized �����Ķ��̷߳��ʺ�����
#####ͬһ������������synchronized�����������߳�ͬʱ���ʵ�����
- Java�������߳��ǲ�����ͬʱ����һ�������������ͬ��synchronized����"[�ο�](https://www.jianshu.com/p/f23a90a79b3a)
#####volatile��ԭ��
- ����ǰ�����������е����ݻ�д�ص�ϵͳ�ڴ档
- ���д���ڴ�Ĳ���������������CPU�ﻺ���˸��ڴ��ַ��������Ч��(ÿ��������ͨ����̽�������ϴ���������������Լ������ֵ�ǲ��ǹ����ˣ��������������Լ������ж�Ӧ���ڴ��ַ���޸ģ��ͻὫ��ǰ�������Ļ��������ó���Ч״̬����������Ҫ��������ݽ����޸Ĳ�����ʱ�򣬻�ǿ�����´�ϵͳ�ڴ�������ݶ���������������)
- ��ָֹ���������Ż�����volatile���εı�������ֵ���ִ����һ����load addl $0x0, (%esp)����������������൱��һ���ڴ����ϣ�ָ��������ʱ���ܰѺ����ָ���������ڴ�����֮ǰ��λ�ã���ֻ��һ��CPU�����ڴ�ʱ��������Ҫ�ڴ����ϣ�
#####volatile�ؼ��ֵ��÷�
[�ο�](https://www.ibm.com/developerworks/cn/java/j-jtp06197.html)
- ��־״̬
```java
volatile boolean shutdownRequested;
public void shutdown() { shutdownRequested = true; }
public void doWork() { 
    while (!shutdownRequested) { 
        // do stuff
    }
}
```
- һ���԰�ȫ������one-time safe publication��
```java
public class BackgroundFloobleLoader {
    public volatile Flooble theFlooble;
    public void initInBackground() {
        // do lots of stuff
        theFlooble = new Flooble();  // this is the only write to theFlooble
    }
} 
public class SomeOtherClass {
    public void doWork() {
        while (true) { 
            // do some stuff...
            // use the Flooble, but only if it is ready
            if (floobleLoader.theFlooble != null) 
                doSomething(floobleLoader.theFlooble);
        }
    }
}
```
��� theFlooble ���ò��� volatile ���ͣ�doWork() �еĴ����ڽ���� theFlooble ������ʱ������õ�һ������ȫ����� Flooble��volatile ���͵����ÿ���ȷ������ķ�����ʽ�Ŀɼ��ԣ�������������״̬�ڷ����󽫷������ģ���ô����Ҫ�����ͬ����
- �����۲죨independent observation��
```java
public class UserManager {
    public volatile String lastUser;
    public boolean authenticate(String user, String password) {
        boolean valid = passwordIsValid(user, password);
        if (valid) {
            User u = new User();
            activeUsers.add(u);
            lastUser = user;
        }
        return valid;
    }
}
```
����չʾ�������֤������μ������һ�ε�¼���û������֡�������ʹ�� lastUser ����������ֵ���Թ��������������ʹ�á���ģʽ��ǰ��ģʽ����չ����ĳ��ֵ�������ڳ����ڵ������ط�ʹ�ã�������һ�����¼��ķ�����ͬ������һϵ�ж����¼���
- ��volatile bean�� ģʽ
```java
@ThreadSafe
public class Person {
    private volatile String firstName;
    private volatile String lastName;
    private volatile int age;
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getAge() { return age; }
    public void setFirstName(String firstName) { 
        this.firstName = firstName;
    }
    public void setLastName(String lastName) { 
        this.lastName = lastName;
    }
    public void setAge(int age) { 
        this.age = age;
    }
}
```
- �����ϵ͵Ķ���д������
```java
public class CheesyCounter {
    // Employs the cheap read-write lock trick
    // All mutative operations MUST be done with the 'this' lock held
    @GuardedBy("this") private volatile int value;
    public int getValue() { return value; }
    public synchronized int increment() {
        return value++;
    }
}
```
������ʾ���̰߳�ȫ�ļ�����ʹ�� synchronized ȷ������������ԭ�ӵģ���ʹ�� volatile ��֤��ǰ����Ŀɼ��ԡ������ڶ�������ʹ�� volatile ȷ����ǰֵ�Ŀɼ��ԣ���˿���ʹ�����������б仯�Ĳ�����ʹ�� volatile ����ֻ�����������У���һ��ֻ����һ���̷߳���ֵ��volatile �������߳�ִ�ж���������˵�ʹ�� volatile ��֤������·��ʱ��Ҫ��ʹ����ִ��ȫ������·����ø��ߵĹ���� ���� �������д����һ����
#####java volatile�ؼ��ֵ�����
- ��ָֹ�������򣬱�֤�ɼ��ԣ����ǲ���֤ԭ���ԡ�