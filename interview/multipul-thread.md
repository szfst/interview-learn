##### 开启线程的三种方式？
- 1.继承Thread类
- 2.实现Runnable接口
- 3.实现callable接口，重写call方法
#####线程和进程的区别？
- 进程是资源的分配和调度的一个独立单元，而线程是CPU调度的基本单元
- 同一个进程中可以包括多个线程，并且线程共享整个进程的资源（寄存器、堆栈、上下文），一个进程至少包括一个线程
- .进程之间相互独立。但同一个进程之间的线程共享内存空间以及进程级的资源，某进程的线程在其他进程是看不到的
- 进程之间相互独立。但同一个进程之间的线程共享内存空间以及进程级的资源，某进程的线程在其他进程是看不到的
- 调度和切换：线程上下文切换比进程上下文切换要快的多
#####为什么要有线程，而不是仅仅用进程？
- 1.早期的确是这样的，但是后面随着计算机的发展，进程之间的切换是在太耗时间了。
- 2.进程只能在一个时间干一件事，如果想同时干两件事或多件事，进程就无能为力了
- 3.进程在执行的过程中如果阻塞，例如等待输入，整个进程就会挂起，即使进程中有些工作不依赖于输入的数据，也将无法执行
#####run()和start()方法区别
- 调用start方法方可启动线程，**而run方法只是thread的一个普通方法调用，还是在主线程里执行。**把需要并行处理的代码放在run()方法中，start()方法启动线程将自动调用 run()方法，这是由jvm的内存机制规定的。并且run()方法必须是public访问权限，返回值类型为void。
	- 用start方法来启动线程，真正实现了多线程运行，这时无需等待run方法体代码执行完毕而直接继续执行下面的代码。通过调用Thread类的 start()方法来启动一个线程，这时此线程处于就绪（可运行）状态，并没有运行，一旦得到cpu时间片，就开始执行run()方法，这里方法 run()称为线程体，它包含了要执行的这个线程的内容，Run方法运行结束，此线程随即终止。
	- run()方法只是类的一个普通方法而已，如果直接调用Run方法，程序中依然只有主线程这一个线程，其程序执行路径还是只有一条，还是要顺序执行，还是要等待run方法体执行完毕后才可继续执行下面的代码，这样就没有达到写线程的目的。
#####如何控制某个方法允许并发访问线程的个数？
```java
static Semaphore semaphore = new Semaphore(5,true)
semaphore.acquire();
semaphore.release();
```
Seamphore是一组信号量，用于管理一组资源。他也是实现了AQS抽象类，在许可不够时，线程会被挂起；而一旦一个线程释放一个资源，那么
，那么就可能重新唤醒等待队列中的线程继续执行。
Seamphore分为公平模式和非公平模式。
####在Java中sleep和wait方法的不同；
- [参考](https://www.cnblogs.com/loren-Yang/p/7538482.html)
- 这两个方法来自不同的类分别是Thread和Object 
- 最主要是sleep方法没有释放锁，而wait方法释放了锁，使得其他线程可以使用同步控制块或者方法(锁代码块和方法锁)
- wait，notify和notifyAll只能在同步控制方法或者同步控制块里面使用，而sleep可以在任何地方使用(使用范围)  
- sleep必须捕获异常，而wait，notify和notifyAll不需要捕获异常
- sleep方法属于Thread类中方法，表示让一个线程进入睡眠状态，等待一定的时间之后，自动醒来进入到可运行状态，不会马上进入运行状态，因为线程调度机制恢复线程的运行也需要时间，一个线程对象调用了sleep方法之后，并不会释放他所持有的所有对象锁，所以也就不会影响其他进程对象的运行。但在sleep的过程中过程中有可能被其他对象调用它的interrupt(),产生InterruptedException异常，如果你的程序不捕获这个异常，线程就会异常终止，进入TERMINATED状态，如果你的程序捕获了这个异常，那么程序就会继续执行catch语句块(可能还有finally语句块)以及以后的代码。
- 注意sleep()方法是一个静态方法，也就是说他只对当前对象有效，通过t.sleep()让t对象进入sleep，这样的做法是错误的，它只会是使当前线程被sleep 而不是t线程  
- wait属于Object的成员方法，一旦一个对象调用了wait方法，必须要采用notify()和notifyAll()方法唤醒该进程;如果线程拥有某个或某些对象的同步锁，那么在调用了wait()后，这个线程就会释放它持有的所有同步资源，而不限于这个被调用了wait()方法的对象。wait()方法也同样会在wait的过程中有可能被其他对象调用interrupt()方法而产生 InterruptedException异常. 
- 如果线程A希望立即结束线程B，则可以对线程B对应的Thread实例调用interrupt方法。如果此刻线程B正在wait/sleep/join，则线程B会立刻抛出InterruptedException，在catch() {} 中直接return即可安全地结束线程。
- 需要注意的是，InterruptedException是线程自己从内部抛出的，并不是interrupt()方法抛出的。对某一线程调用interrupt()时，如果该线程正在执行普通的代码，那么该线程根本就不会抛出InterruptedException。但是，一旦该线程进入到wait()/sleep()/join()后，就会立刻抛出InterruptedException。
- wait()和notify()因为会对对象的“锁标志”进行操作，所以它们必须在synchronized函数或synchronized block中进行调用。如果在non-synchronized函数或non-synchronizedblock中进行调用，虽然能编译通过，但在运行时会发生illegalMonitorStateException的异常。
	- yield方法  暂停当前正在执行的线程对象。  
yield()方法是停止当前线程，让同等优先权的线程或更高优先级的线程有执行的机会。如果没有的话，那么yield()方法将不会起作用，并且由可执行状态后马上又被执行。   join方法是用于在某一个线程的执行过程中调用另一个线程执行，等到被调用的线程执行结束后，再继续执行当前线程。如：t.join();//主要用于等待t线程运行结束，若无此句，main则会执行完毕，导致结果不可预测。
#####谈谈wait/notify关键字的理解
- [参考](ttp://blog.csdn.net/jianiuqi/article/details/53448849)
- wait( )，notify( )，notifyAll( )都不属于Thread类，而是属于Object基础类，也就是**每个对象都有wait( )，notify( )，notifyAll( ) 的功能**，因为每个对象都有锁，锁是每个对象的基础，当然操作锁的方法也是最基础了
- 当需要调用以上的方法的时候，**一定要对竞争资源进行加锁**，如果不加锁的话，则会报 IllegalMonitorStateException 异常
- 当想要调用wait( )进行线程等待时，必须要取得这个锁对象的控制权（对象监视器），一般是**放到synchronized(obj)代码中**
- 在**while循环**里而不是if语句下使用wait，这样，会在线程暂停恢复后都检查wait的条件，并在条件实际上并未改变的情况下处理唤醒通知
- 调用obj.wait( )释放了obj的锁，否则其他线程也无法获得obj的锁，也就无法在synchronized(obj){ obj.notify() } 代码段内唤醒A。
- notify( )方法只会通知等待队列中的第一个相关线程（不会通知优先级比较高的线程）
- notifyAll( )通知所有等待该竞争资源的线程（也不会按照线程的优先级来执行）
- 假设有三个线程执行了obj.wait( )，那么obj.notifyAll( )则能全部唤醒tread1，thread2，thread3，但是要继续执行obj.wait（）的下一条语句，必须获得obj锁，因此，tread1，thread2，thread3只有一个有机会获得锁继续执行，例如tread1，其余的需要等待thread1释放obj锁之后才能继续执行。
- 当调用obj.notify/notifyAll后，调用线程依旧持有obj锁，因此，thread1，thread2，thread3虽被唤醒，但是仍无法获得obj锁。**直到调用线程退出synchronized块，释放obj锁后，thread1，thread2，thread3中的一个才有机会获得锁继续执行。**
#####什么导致线程阻塞
- 线程执行了Thread.sleep(intmillsecond);方法，当前线程放弃CPU，睡眠一段时间，然后再恢复执行
- 线程执行了一个对象的wait()方法，直接进入阻塞状态，等待其他线程执行notify()或者notifyAll()方法。
- 线程执行一段同步代码，但是尚且无法获得相关的同步锁，只能进入阻塞状态，等到获取了同步锁，才能回复执行
- 线程执行某些IO操作，因为等待相关的资源而进入了阻塞状态。比如说监听system.in，但是尚且没有收到键盘的输入，则进入阻塞状态
#####线程如何关闭？
- [参考](http://blog.csdn.net/linux2_scdn/article/details/48052153)
- 针对没有阻塞的情况：设置标志变量，让线程正常自然死亡，和谐，也就是让其run结束
```java
    private boolean running = true;  
    public void stop() {  
        this.running = false;  
    }  
  
    public void run() {  
        try {  
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));  
            while (running) {  
                System.out.println("线程正在运行中...");  
                Thread.sleep(20000);  
            }  
            System.out.println("线程被终止.");  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
  
    }  
```
- 针对有阻塞的情况：中断阻塞，靠抛出异常终止线程，看似暴力，但如果是我们预期的异常，那也是安全的(interrupt()).不管有没有阻塞情况，都用标志变量控制线程循环。
- stop()函数终止线程就像是强行拔掉电源线关机一样，可能会带来未知风险，因此目前不再推荐使用这种方式。不安全。
#####讲一下java中的同步的方法
[参考](https://www.cnblogs.com/duanxz/p/3709608.html?utm_source=tuicool&utm_medium=referral)
- 一、同步方法:
	- 即有synchronized关键字修饰的方法。 由于java的每个对象都有一个内置锁，当用此关键字修饰方法时， 内置锁会保护整个方法。在调用该方法前，需要获得内置锁，否则就处于阻塞状态。
注： synchronized关键字也可以修饰静态方法，此时如果调用该静态方法，将会锁住整个类
- 二、同步代码块:
即有synchronized关键字修饰的语句块。 被该关键字修饰的语句块会自动被加上内置锁，从而实现同步
```java
synchronized(object){ 
}
```
- 三、wait与notify
- 四、使用特殊域变量(volatile)实现线程同步
- 五、使用重入锁实现线程同步
ReentrantLock类是可重入、互斥、实现了Lock接口的锁，它与使用synchronized方法和快具有相同的基本行为和语义，并且扩展了其能力。
- 六、使用局部变量实现线程同步
如果使用ThreadLocal管理变量，则每一个使用该变量的线程都获得该变量的副本，副本之间相互独立，这样每一个线程都可以随意修改自己的变量副本，而不会对其他线程产生影响。 ThreadLocal与同步机制都是为了解决多线程中相同变量的访问冲突问题
- 七、使用原子变量实现线程同步(AtomicInteger)
#####两个进程同时要求写或者读，能不能实现？如何防止进程的同步？
不能同时对一个文件写操作，因为这样会对数据造成不一致性。对同一个文件进行读是可以的，可以使用读写锁。
#####线程间操作List
[参考](http://wuwenjun0919-msn-com.iteye.com/blog/2174652)
要使用Collection..synchronizedList(new ArrayList<E>())
但是在进行操作的时候得保证使用同一把锁的对象。
#####Java中对象的生命周期
[参考](http://blog.csdn.net/sodino/article/details/38387049)
- 1. 创建阶段(Created)
	- 为对象分配存储空间
	- 开始构造对象
	- 从超类到子类堆static成员进行初始化
	- 超类成员变量顺序初始化，递归调用超类的构造方法
	- 子类成员变量顺序初始化，子类构造方法调用
- 2.应用阶段(In Use)
	- 对象至少被一个强引用持有着
- 3.不可见阶段(Invisible)
当一个对象处于不可见阶段时，说明程序本身不再持有该对象的任何强引用，虽然该这些引用仍然是存在着的。本地变量count在	最后一行时已经超出了其作用域，则在此时称之为count处于不可视阶段。当然这种情况编译器在编译的过程中会直接报错了。
```java
boolean bool = false;
if(bool == true){
	int count = 0;
	count++;
}
System.out.println(count);
```
- 4.不可达阶段(Unreachable)
对象处于不可达阶段是指该对象不再被任何强引用所持有。
与“不可见阶段”相比，“不可见阶段”是指程序不再持有该对象的任何强引用，这种情况下，该对象仍可能被JVM等系统下的某些已装载的静态变量或线程或JNI等强引用持有着，这些特殊的强引用被称为”GC root”。存在着这些GC root会导致对象的内存泄露情况，无法被回收。
- 5.收集阶段(Collected)
	- 当垃圾回收器发现该对象已经处于“不可达阶段”并且垃圾回收器已经对该对象的内存空间重新分配做好准备时，则对象进入了“收集阶段”。如果该对象已经重写了finalize()方法，则会去执行该方法的终端操作。
	- 这里要特别说明一下：不要重载finazlie()方法！原因有两点：
		-  会影响JVM的对象分配与回收速度:
		-  可能造成该对象的再次“复活
- 6.终结阶段
	- 当对象执行完finalize()方法后仍然处于不可达状态时，则该对象进入终结阶段。在该阶段是等待垃圾回收器对该对象空间进行回收。
- 7.对象空间重新分配阶段:
	- 垃圾回收器对该对象的所占用的内存空间进行回收或者再分配了，则该对象彻底消失了，称之为“对象空间重新分配阶段”。
#####java类的生命周期
- 加载
- （验证、准备，解析）链接
- 初始化
- 使用
- 卸载
#####Synchronized用法
[参考](http://www.importnew.com/21866.html)
- 修饰一个代码块，synchronized(this)
- 修饰一个方法，下面两个函数是等价的。
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
- 修饰一个静态的方法
我们知道静态方法是属于类的而不属于对象的。同样的，synchronized修饰的静态方法锁定的是这个类的所有对象
```
public synchronized static void method() {
   // todo
}
```
- 修饰一个类:synchronized作用于一个类T时，是给这个类T加锁，T的所有对象用的是同一把锁。
```java
class ClassName {
   public void method() {
      synchronized(ClassName.class) {
         // todo
      }
   }
```
#####synchronize的原理
[参考](http://blog.csdn.net/u012715840/article/details/58247556)
#####谈谈对Synchronized关键字，类锁，方法锁，重入锁的理解
#####static synchronized 方法的多线程访问和作用
#####同一个类里面两个synchronized方法，两个线程同时访问的问题
- Java中两个线程是不可以同时访问一个对象的两个不同的synchronized方法"[参考](https://www.jianshu.com/p/f23a90a79b3a)
#####volatile的原理
- 将当前处理器缓存行的数据会写回到系统内存。
- 这个写回内存的操作会引起在其他CPU里缓存了该内存地址的数据无效。(每个处理器通过嗅探在总线上传播的数据来检查自己缓存的值是不是过期了，当处理器发现自己缓存行对应的内存地址被修改，就会将当前处理器的缓存行设置成无效状态，当处理器要对这个数据进行修改操作的时候，会强制重新从系统内存里把数据读到处理器缓存里)
- 禁止指令重排序优化。有volatile修饰的变量，赋值后多执行了一个“load addl $0x0, (%esp)”操作，这个操作相当于一个内存屏障（指令重排序时不能把后面的指令重排序到内存屏障之前的位置），只有一个CPU访问内存时，并不需要内存屏障；
#####volatile关键字的用法
[参考](https://www.ibm.com/developerworks/cn/java/j-jtp06197.html)
- 标志状态
```java
volatile boolean shutdownRequested;
public void shutdown() { shutdownRequested = true; }
public void doWork() { 
    while (!shutdownRequested) { 
        // do stuff
    }
}
```
- 一次性安全发布（one-time safe publication）
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
如果 theFlooble 引用不是 volatile 类型，doWork() 中的代码在解除对 theFlooble 的引用时，将会得到一个不完全构造的 Flooble。volatile 类型的引用可以确保对象的发布形式的可见性，但是如果对象的状态在发布后将发生更改，那么就需要额外的同步。
- 独立观察（independent observation）
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
上面展示了身份验证机制如何记忆最近一次登录的用户的名字。将反复使用 lastUser 引用来发布值，以供程序的其他部分使用。该模式是前面模式的扩展；将某个值发布以在程序内的其他地方使用，但是与一次性事件的发布不同，这是一系列独立事件。
- “volatile bean” 模式
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
- 开销较低的读－写锁策略
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
上面显示的线程安全的计数器使用 synchronized 确保增量操作是原子的，并使用 volatile 保证当前结果的可见性。可以在读操作中使用 volatile 确保当前值的可见性，因此可以使用锁进行所有变化的操作，使用 volatile 进行只读操作。其中，锁一次只允许一个线程访问值，volatile 允许多个线程执行读操作，因此当使用 volatile 保证读代码路径时，要比使用锁执行全部代码路径获得更高的共享度 —— 就像读－写操作一样。
#####java volatile关键字的作用
- 禁止指令重排序，保证可见性，但是不保证原子性。
