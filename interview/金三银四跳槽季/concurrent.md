#### synchronized 的实现原理以及锁优化？
- https://blog.csdn.net/wsyw126/article/details/70807304
- Synchronized的语义底层是通过一个monitor的对象来完成
- 当monitor被占用时就会处于锁定状态，线程执行monitorenter指令时尝试获取monitor的所有权，过程如下：
	- 如果monitor的进入数为0，则该线程进入monitor，然后将进入数设置为1，该线程即为monitor的所有者
	- 如果线程已经占有该monitor，只是重新进入，则进入monitor的进入数加1
	- 如果其他线程已经占用了monitor，则该线程进入阻塞状态，直到monitor的进入数为0，再重新尝试获取monitor的所有权
- 执行monitorexit的线程必须是objectref所对应的monitor的所有者：指令执行时，monitor的进入数减1，如果减1后进入数为0，那线程退出monitor，不再是这个monitor的所有者。其他被这个monitor阻塞的线程可以尝试去获取这个 monitor 的所有权。
- 优化：上篇文章中向大家介绍了Synchronized的用法及其实现的原理。现在我们应该知道，Synchronized是通过对象内部的一个叫做监视器锁（monitor）来实现的。但是监视器锁本质又是依赖于底层的操作系统的Mutex Lock来实现的。而操作系统实现线程之间的切换这就需要从用户态转换到核心态，这个成本非常高，状态之间的转换需要相对比较长的时间，这就是为什么Synchronized效率低的原因。因此，这种依赖于操作系统Mutex Lock所实现的锁我们称之为“重量级锁”。JDK中对Synchronized做的种种优化，其核心都是为了减少这种重量级锁的使用。JDK1.6以后，为了减少获得锁和释放锁所带来的性能消耗，提高性能，引入了“轻量级锁”和“偏向锁”。
- 锁的状态总共有四种：无锁状态、偏向锁、轻量级锁和重量级锁。随着锁的竞争，锁可以从偏向锁升级到轻量级锁，再升级的重量级锁（但是锁的升级是单向的，也就是说只能从低到高升级，不会出现锁的降级）
- 优化点：**添加轻量锁**（cas+自旋）、**偏向锁**（只有遇到其他线程尝试竞争偏向锁时，持有偏向锁的线程才会释放锁，线程不会主动去释放偏向锁）、**适应性自旋**（根据循环的次数来确定循环的次数）、**锁粗化**（将多次连接在一起的加锁、解锁操作合并为一次）、**锁消除**（删除不必要的加锁操作）（https://blog.csdn.net/wsyw126/article/details/70807304）
#### volatile 的实现原理
#### Java高级之信号灯Semaphore
- 控制信号量
- Java实现互斥线程同步有三种方式synchronized、lock 、单Semaphore
####  synchronized 在静态方法和普通方法的区别
- 修饰一个代码块，被修饰的代码块称为同步语句块，其作用的范围是大括号{}括起来的代码，作用的对象是调用这个代码块的对象； 
- 修饰一个方法，被修饰的方法称为同步方法，其作用的范围是整个方法，作用的对象是调用这个方法的对象； 
-  修改一个静态的方法，其作用的范围是整个静态方法，作用的对象是这个类的所有对象； 
-  修改一个类，其作用的范围是synchronized后面括号括起来的部分，作用主的对象是这个类的所有对象
####  怎么实现所有线程在等待某个事件的发生才会去执行
- https://blog.csdn.net/jiyiqinlovexx/article/details/51236323
- 闭锁CountDownLatch：闭锁是典型的等待事件发生的同步工具类，将闭锁的初始值设置1，所有线程调用await方法等待，当事件发生时调用countDown将闭锁值减为0，则所有await等待闭锁的线程得以继续执行
- .阻塞队列BlockingQueue：所有等待事件的线程尝试从空的阻塞队列获取元素，将阻塞，当事件发生时，向阻塞队列中同时放入N个元素(N的值与等待的线程数相同)，则所有等待的线程从阻塞队列中取出元素后得以继续执行。
- 信号量Semaphore：设置信号量的初始值为等待的线程数N，一开始将信号量申请完，让剩余的信号量为0，待事件发生时，同时释放N个占用的信号量，则等待信号量的所有线程将获取信号量得以继续执行。 
- 栅栏CyclicBarrier：设置栅栏的初始值为1，当事件发生时，调用barrier.wait()冲破设置的栅栏，将调用指定的Runable线程执行，在该线程中启动N个新的子线程执行。这个方法并不是让执行中的线程全部等待在某个点，待某一事件发生后继续执行。
#### CAS？CAS 有什么缺陷，如何解决
- [参考](https://baijiahao.baidu.com/s?id=1571172971189129&wfr=spider&for=pc)
- 更新库存：update stock set num=num_new where sid=sid**and num=num_old**
- ABA问题：
	- 解决：“版本号”的比对，一个数据一个版本，版本变化，即使值相同，也不应该修改成功。
update stock set num=num_new version=version_new where sid=sid and **version=version_old**
####  synchronized 和 lock 有什么区别？ 
####  Hashtable 是怎么加锁的 ？
- Hashtable的实现方法里面都添加了synchronized关键字来确保线程同步
- HashMap和Hashtable的底层实现都是数组+链表结构实现的，这点上完全一致
- [HashMap和Hashtable的区别](https://blog.csdn.net/double2hao/article/details/53411594)