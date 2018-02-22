##### 一、再谈引用
- 强引用：就是指在程序代码之中普遍存在的，类似‘Object obj = new Object（）’这类的引用，只要强引用还存在，垃圾收集器永远不会回收掉被引用的对象。
- 软引用：用来描述一些还有用但并非必须的兑现。对于软引用关联着的对象，在系统将要发生内存溢出之前，将会把这些对象列入回收方位之中进行第二次回收。如果这次回收还没有足够的内存，才会抛出内存溢出异常。在JDK1.2之后，提高了softReference类来实现软引用。
- 弱引用：也是用来描述非必需对象的，但是它的强度比弱引用更弱一些，被弱引用关联的对象只能生存到下一次垃圾收集发生之前。当垃圾收集器工作时，无论当前内存是否足够，都会回收只被弱引用关联的对象。在JDK1.2之后，提供了weakReference类来实现弱引用。
- 虚引用：也叫幽灵引用或者幻影引用，它是最弱的一种引用关系。一个对象是否有虚引用的存在，完全不会对其生存时间构成影响，也无法通过虚引用来取得一个对象实例。作为一个对象设置虚引用关联的唯一目的就是能在这个对象呗收集器回收时收到一个系统通知。在jdk1.2之后，提供了PhantomReference类来实现虚引用。
##### 二、HashMap和Hashtable的区别
- HashMap是非线程安全的，Hashtable是线程安全的。
- HashMap的键值都可以为null，Hashtable的键值都不可以为null值。
- HashMap继承自AbstractMap类，Hashtable继承自Dictionary类。
##### 三、StringBuilder内部实现机制
- StringBuilder内部有一个字符数组，代码如下：
```java
char[] value;   //字符数组
int count;      //字符串长度
```
每一次append操作都是将新的字符串加入到可变长的字符数组中，长度计算方式与ArrayList类似。调用toString()方法时，new一个String对象即可。
```java
public String toString() {
        return new String(value, 0, count);// Create a copy, don't share the array
}
```