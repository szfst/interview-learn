##### 一、volatile关键字
- 介绍：volatile相当于synchronized的弱实现，也就是说volatile实现了类似synchronized的语义，却又没有锁机制。它确保对volatile字段的更新以可预见的方式告知其他的线程
- 特性：
	- **禁止重排序**：Java 存储模型不会对valatile指令的操作进行重排序：这个保证对volatile变量的操作时按照指令的出现顺序执行的
	- **保证可见性**：volatile变量不会被缓存在寄存器中（只有拥有线程可见）或者其他对CPU不可见的地方，每次总是从主存中读取volatile变量的结果。也就是说对于volatile变量的修改，其它线程总是可见的，并且不是使用自己线程栈内部的变量。也就是在happens-before法则中，对一个valatile变量的写操作后，其后的任何读操作理解可见此写操作的结果。
- 尽管volatile变量的特性不错，但是volatile并不能保证线程安全的，也就是说volatile字段的操作不是原子性的，volatile变量只能保证可见性（一个线程修改后其它线程能够理解看到此变化后的结果），要想保证原子性，目前为止只能加锁
##### 二、synchronized关键字
- 存在锁，而且是悲观锁，也就是独占锁
- 独占锁是一种悲观锁，synchronized就是一种独占锁，会导致其他所有需要锁的线程挂起，等待持有锁的线程释放锁。。而另一个更加有效的锁就是乐观锁。所谓乐观锁就是，每次不加锁而是假设没有冲突而去完成某项操作，如果因为冲突失败就重试，直到成功为止。
- 锁存在以下问题：
	- （1）在多线程竞争下，加锁、释放锁会导致比较多的上下文切换和调度延时，引起性能问题。
	- （2）一个线程持有锁会导致其它所有需要此锁的线程挂起。
	- （3）如果一个优先级高的线程等待一个优先级低的线程释放锁会导致优先级导致，引起性能风险。
##### 三、CAS 操作
- 上面的乐观锁用到的机制就是CAS，Compare and Swap。
- CAS有3个操作数，内存值V，旧的预期值A，要修改的新值B。当且仅当预期值A和内存值V相同时，将内存值V修改为B，否则什么都不做。
- 非阻塞算法 （nonblocking algorithms）：一个线程的失败或者挂起不应该影响其他线程的失败或挂起的算法。现代的CPU提供了特殊的指令，可以自动更新共享数据，而且能够检测到其他线程的干扰，而 compareAndSet() 就用这些代替了锁定。
- 拿出AtomicInteger来研究在没有锁的情况下是如何做到数据正确性的。
<code>private volatile int value;</code>
首先毫无以为，在没有锁的机制下可能需要借助volatile原语，保证线程间的数据是可见的（共享的）这样才获取变量的值的时候才能直接读取。
```java
public final int get() {
        return value;
    }
```
然后来看看++i是怎么做到的。
public final int incrementAndGet() {
    for (;;) {
        int current = get();
        int next = current + 1;
        if (compareAndSet(current, next))
            return next;
    }
}
在这里采用了CAS操作，每次从内存中读取数据然后将此数据和+1后的结果进行CAS操作，如果成功就返回结果，否则重试直到成功为止。而compareAndSet利用JNI来完成CPU指令的操作。
```java
public final boolean compareAndSet(int expect, int update) {   
    return unsafe.compareAndSwapInt(this, valueOffset, expect, update);
    }
```
整体的过程就是这样子的，利用CPU的CAS指令，同时借助JNI来完成Java的非阻塞算法。其它原子操作都是利用类似的特性完成的。</br>
而整个J.U.C都是建立在CAS之上的，因此对于synchronized阻塞算法，J.U.C在性能上有了很大的提升。参考资料的文章中介绍了如果利用CAS构建非阻塞计数器、队列等数据结构。</br>
- CAS看起来很爽，但是会导致“ABA问题”:比如说一个线程one从内存位置V中取出A，这时候另一个线程two也从内存中取出A，并且two进行了一些操作变成了B，然后two又将V位置的数据变成A，这时候线程one进行CAS操作发现内存中仍然是A，然后one操作成功。尽管线程one的CAS操作成功，但是不代表这个过程就是没有问题的。如果链表的头在变化了两次后恢复了原值，但是不代表链表就没有变化。因此前面提到的原子操作AtomicStampedReference/AtomicMarkableReference就很有用了。这允许一对变化的元素进行原子操作。
##### 四、LOCK
- 之所以能够保证线程安全，是因为**Lock对象的lock()方法保证了只有一个线程能够只有此锁**。需要说明的是对于任何一个lock()方法，都需要一个unlock()方法与之对于，通常情况下为了保证unlock方法总是能够得到执行，unlock方法被置于finally块中。使用java.util.concurrent.locks.ReentrantLock.ReentrantLock对象作为Lock的唯一实现是如何设计和实现的。
- 尽管synchronized实现Lock的相同语义，并且在语法上比Lock要简单多，但是前者却比后者的开销要大得多。做一个简单的测试。
- 公平锁和非公平锁：如果获取一个锁是按照请求的顺序得到的，那么就是公平锁，否则就是非公平锁。</br>
在没有深入了解内部机制及实现之前，先了解下为什么会存在公平锁和非公平锁。公平锁保证一个阻塞的线程最终能够获得锁，因为是有序的，所以总是可以按照请求的顺序获得锁。不公平锁意味着后请求锁的线程可能在其前面排列的休眠线程恢复前拿到锁，这样就有可能提高并发的性能。这是因为通常情况下挂起的线程重新开始与它真正开始运行，二者之间会产生严重的延时。因此非公平锁就可以利用这段时间完成操作。这是非公平锁在某些时候比公平锁性能要好的原因之一。
##### 五、Condition
- 条件变量很大一个程度上是为了解决Object.wait/notify/notifyAll难以使用的问题。
- 条件（也称为条件队列 或条件变量）为线程提供了一个含义，以便在某个状态条件现在可能为 true 的另一个线程通知它之前，一直挂起该线程（即让其“等待”）。因为访问此共享状态信息发生在不同的线程中，所以它必须受保护，因此要将某种形式的锁与该条件相关联。等待提供一个条件的主要属性是：以原子方式 释放相关的锁，并挂起当前线程，就像 Object.wait 做的那样。
- 上述API说明表明条件变量需要与锁绑定，而且多个Condition需要绑定到同一锁上。前面的Lock中提到，获取一个条件变量的方法是Lock.newCondition()。
##### 六、CountDownLatch
- 是JDK 5+里面闭锁的一个实现，允许一个或者多个线程等待某个事件的发生。CountDownLatch有一个正数计数器，countDown方法对计数器做减操作，await方法等待计数器达到0。所有await的线程都会阻塞直到计数器为0或者等待线程中断或者超时。
##### 七、CyclicBarrier 
- 如果说CountDownLatch是一次性的，那么CyclicBarrier正好可以循环使用。它允许一组线程互相等待，直到到达某个公共屏障点 (common barrier point)。所谓屏障点就是一组任务执行完毕的时刻。
##### 八、Semaphore
- Semaphore 是一个计数信号量。从概念上讲，信号量维护了一个许可集。如有必要，在许可可用前会阻塞每一个 acquire()，然后再获取该许可。每个 release() 添加一个许可，从而可能释放一个正在阻塞的获取者。但是，不使用实际的许可对象，Semaphore 只对可用许可的号码进行计数，并采取相应的行动。
- 缓存池整好使用此思想来实现的，比如链接池、对象池等。
##### 九、ReadWriteLock
- ReadWriteLock描述的是：一个资源能够被多个读线程访问，或者被一个写线程访问，但是不能同时存在读写线程。也就是说读写锁使用的场合是一个共享资源被大量读取操作，而只有少量的写操作（修改数据）。清单1描述了ReadWriteLock的AP