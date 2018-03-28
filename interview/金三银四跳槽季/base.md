#### HashMap 的扩容过程
https://blog.csdn.net/aichuanwendang/article/details/53317351
https://www.cnblogs.com/KingIceMou/p/6976574.html
- jdk1.7 indexFor来重新计算每个元素在数组中的位置
```java
static int indexFor(int h, int length) {  
    return h & (length - 1);  
}
```
- jdk1.8经过rehash之后，元素的位置要么是在原位置，要么是在原位置再移动2次幂的位置（不用重新计算hash值得），因此，table中的元素只有两种情况：<code>(e.hash & oldCap) == 0</code>
	- 元素hash值第N+1位为0：不需要进行位置调整
	- 元素hash值第N+1位为1：调整至原索引的两倍位置
#### HashMap 1.7 与 1.8 的 区别，说明 1.8 做了哪些优化，如何优化的？
https://blog.csdn.net/jek123456/article/details/73869203
- jdk1.8使用一个Node数组来存储数据，但这个Node可能是链表结构，也可能是红黑树结构；如果插入的key的hashcode相同，那么这些key也会被定位到Node数组的同一个格子里。；如果同一个格子里的key不超过8个，使用链表结构存储。；如果超过了8个，那么会调用treeifyBin函数，将链表转换为红黑树。；那么即使hashcode完全相同，由于红黑树的特定，查找某个特定元素，也只需要O(log n)的开销；也就是说put/get的操作的时间复杂度只有O(log n)
- 为什么要这么操作呢？；我认为应该是为了避免Hash Collision DoS攻击；Java中String的hashcode函数的强度很弱，有心人可以很容易的构造出大量hashcode相同的String对象。；如果向服务器一次提交数万个hashcode相同的字符串参数，那么可以很容易的卡死JDK1.7版本的服务器。；但是String正确的实现了Compare接口，因此在JDK1.8版本的服务器上，Hash Collision DoS不会造成不可承受的开销。
#### Arrays.sort 实现原理和 Collection 实现原理 
- https://blog.csdn.net/qq_25673113/article/details/60468364
- https://blog.csdn.net/yangzhongblog/article/details/8184707
- collection.sort用的就是arrays.sort实现的
- 能保持稳定性stability
- arrays.sort使用的是timSort，当数据量小于某个值的时候，就使用二分查找插入排序，其他使用mergeSort（分区合并）
- 判断数组的大小，小于32使用二分插入排序
- 数组大于32时， 先算出一个合适的大小，在将输入按其升序和降序特点进行了分区。排序的输入的单位不是一个个单独的数字，而是一个个的块-分区。其中每一个分区叫一个run。针对这些 run 序列，每次拿一个run出来按规则进行合并。每次合并会将两个run合并成一个 run。合并的结果保存到栈中。合并直到消耗掉所有的run，这时将栈上剩余的 run合并到只剩一个 run 为止。这时这个仅剩的 run 便是排好序的结果。
####  LinkedHashMap的应用
- LinkedHashMap 能够做到按照插入顺序或者访问顺序进行迭代
- 增加 accessOrder 这个参数，如果不设置，默认为 false，代表按照插入顺序进行迭代；当然可以显式设置为 true，代表以访问顺序进行迭代。
- http://wiki.jikexueyuan.com/project/java-collection/linkedhashmap.html
- 每次调用get()，则将该对象移到链表的尾端。调用put插入新的对象也是存储在链表尾端，这样当内存缓存达到设定的最大值时，将链表头部的对象（近期最少用到的）移除。
#### LRUCache实现
- 双向链表+HashMap
- https://blog.csdn.net/hxqneuq2012/article/details/52709652
- 方法二：使用LinkedHashMap，LinkedHashMap 提供了 removeEldestEntry(Map.Entry<K,V> eldest) 方法。该方法可以提供在每次添加新条目时移除最旧条目的实现程序，默认返回 false，这样，此映射的行为将类似于正常映射，即永远不能移除最旧的元素
#### cloneable接口实现原理
- http://kentkwan.iteye.com/blog/739514
- 深拷贝（如果对象里面有别的对象的应用，需要也拷贝一份而不是直接使用应用）就要改写cloneable接口，否则，实现cloneable接口，重写clone方法为return super.clone()即可。
####  异常分类以及处理机制 
- throwable为父类，error和exception为子类
- 在try catch finally语句中，如果finally语句里面出现return，catch里面抛出的异常可能会被屏蔽
```java
    boolean testEx() throws Exception {  
        boolean ret = true;  
        try {  
            throw new Exception();
        } catch (Exception e) {  
            System.out.println("testEx, catch exception");  
            ret = false;  
            throw e;  
        } finally {  
            System.out.println("testEx, finally; return value=" + ret);  
            return ret;  
        }  
    } 
    public static void main(String[] args) {  
        TestException testException1 = new TestException();  
        try {  
            testException1.testEx();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    } 
```
#### sleep和wait的区别
- 最主要是sleep方法没有释放锁，而wait方法释放了锁，
- 这两个方法来自不同的类分别是Thread和Object
- wait，notify和notifyAll只能在同步控制方法或者同步控制块里面使用，而sleep可以在任何地方使用(使用范围)(只有获取了锁才能调用wait方法或者notify)（wait()和notify()因为会对对象的“锁标志”进行操作，所以它们必须在synchronized函数或synchronized block中进行调用。如果在non-synchronized函数或non-synchronizedblock中进行调用，虽然能编译通过，但在运行时会发生illegalMonitorStateException的异常。）
- sleep必须捕获异常，而wait，notify和notifyAll不需要捕获异常
- 注意sleep()方法是一个静态方法，也就是说他只对当前对象有效，通过t.sleep()让t对象进入sleep，这样的做法是错误的，它只会是使当前线程被sleep 而不是t线程
- wait属于Object的成员方法，一旦一个对象调用了wait方法，必须要采用notify()和notifyAll()方法唤醒该进程
#### 数组在内存中如何分配
- https://www.cnblogs.com/chenpi/p/5489732.html
- 一维数组二维数组多维数组等都是在堆上分配