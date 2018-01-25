##### 一、多线程基础：
- 锁竞争会消耗cpu资源
- **ynchronize取得的锁都是对象锁**，而不是一段代码（方法）当做锁，所以有可能两个线程获得两个对象的锁的情况，他们互不相影响。有一种情况则是相同的锁，即在静态方法上加synchronized关键字，表示锁定.class类，类一级别的锁（独占.class类）。
- 事务特性ACID
- synchronize锁可重入(同一个类或者子类父类都可以重入  )
```java	
public class SyncDubbo(){
public synchronized void metho1d(){
		System.out.println("method1...");
		method2();
}
public synchronized void method2(){
		System.out.println("method2...");
		method3();
}
public synchronized void method3(){
		System.out.println("method3...");
}
}
```	
- volatile：禁止指令重排序、保证可见性，不保证原子性（用AtomicInteger等atomic系列对象来保证int的原子性）
- 线程间同学：
	- 1、wait和notify必须配合synchronize关键字使用
	- 2、wait方法释放锁、notify方法不释放锁
- 单例模式和多线程：
	- 单例模式，最常见的就是饥饿模式和懒汉模式，一个直接实例化对象，一个在调用方法的时候实例化对象。在多线程的模式中，考虑到性能和线程安全问题，我们一般选择下面两种比较经典的单例模式，在能提高性能的同时，有保证了线程安全：dubble check instance,static inner class（好用简单安全常用） 
		- double check instance
```java	
	private static DubbleSingleton ds;
	public static DubbleSingleton getDs(){
		if(ds == null){
			try {
				//模拟初始化对象的准备时间...
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (DubbleSingleton.class) {
				if(ds == null){
					ds = new DubbleSingleton();
				}
			}
		}
		return ds;
	}
```		
-  -  - static innner class
```java
 public class InnerSingleton {
	private static class Singletion {
		private static Singletion single = new Singletion();
	}
	public static Singletion getInstance(){
		return Singletion.single;
	}	
}
```
- concurrentMap：
	- concurrentHashMap：分成多个段，减小锁的粒度：内部使用段（segment）来表示这些不同的部分，每个段其实就是一个小的hashtable，他们有自己的锁。只要多个修改炒作发生在不同的段上，他们就可以并发进行。吧一个整体分成16个段。也就是最高支持16个线程的并发修改炒作。这也是在多线程场景时减小锁的粒度从而降低锁竞争的一种方案。并且代码中大多共享变量使用volatile关键字声明，目的是第一时间获取修改的内容，性能非常好。
	- cocurrentSkipListMap：相比上面，是有序的（treeMap的并发版本）
- Copy-On-Write容器简称COW,是一种用于程序设计中的优化策略。应用在读多写少的情况下。
	- cow容器即写时复制的容器。通俗的理解就是当我们往一个容器里面添加元素的时候，不直接往当前的容器添加，而是现将当前容器进行copy，复制出一个姓的容器，然后姓的容器里面添加元素，添加完元素之后，再将原容器的引用指向新的容器。这样做的好处是我们可以对cw容器进行并发的读，而不需要加锁，因为当前的袁术不会添加任何元素。搜易cow容器也是一种读写分离的思想，读和写不同的容器。 
- queue
	- concurrentLinkedQueue：高性能无阻塞队列
	- BlockingQueue接口：
		- ArrayBlockingQueue
		- LinkedBlockingQueue
		- PriorityBlockingQueue:
		- DelayQueue
		- SynchronousQueue:一种没有缓冲的队列，生产者产生的数据直接会被消费者获取并消费。要先take才能调用add，blocking
- future模式的实现：
	- 启动一个线程把要返回的对象加进去查询，查询可能很耗时，可以先把结果返回去。get的时候wait，直到set完成之后notify就返回结果了。
- ReentrantReadWriteLock,读写锁：
	- 读多写少的情况下使用读写锁：用synchronized、ReentrantLock时，我们知道，同一个时间内，只能有一个线程进行访问被锁定的代码，那么读写锁则不同，其本质是分成两个锁，即读锁、写锁。在读写情况下，多个线程可以并发的访问，但是写锁的时候，只能一个个顺序访问。
	- 读读共享，写写互斥，读写互斥  
- 锁的优化：
	- 避免死锁
	- 减小锁的持有时间
	- 减小锁的粒度
	- 锁的分离
	- 尽量使用无锁的操作，如原子 操作（Atomic系列类），volatile关键字
- 分布式锁：
	- 实现1：用zookeeper去同步和协调，将要锁的对象注册zookeeper那里，要使用就和zookeeper说，使用完和zookeeper说 
	- 实现2：使用redis实现
- 高并发解决思路：
	- 网络：带宽等
	- 负载均衡：nginx等
	- cdn加速
	- **把业务拆分**：例如秒杀业务和主业务分离
	- 限流：java代码用信号量做限流，nginx限流，redis限流 