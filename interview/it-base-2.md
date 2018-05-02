### 哪些情况下的对象会被垃圾回收机制处理掉
- 请[参照](https://www.zhihu.com/question/35164211)
- 什么时候
	- 能说出新生代、老年代结构，能提出minor gc/full gc （Minor GC
从年轻代空间（包括 Eden 和 Survivor 区域）回收内存被称为 Minor GC。Major GC 是清理老年代。Full GC 是清理整个堆空间—包括年轻代和老年代。）
	- 能说明minor gc/full gc的触发条件、OOM的触发条件，降低GC的调优的策略。 
	- eden满了minor gc：当eden区没有足够空间进行分配时，jvm将发起一次Minor GC
	- 升到老年代的对象大于老年代剩余空间full gc，或者小于时被HandlePromotionFailure参数强制full gc
	- gc与非gc时间耗时超过了GCTimeRatio的限制引发OOM，调优诸如通过NewRatio控制新生代老年代比例，通过MaxTenuringThreshold控制进入老年前生存次数等
- 对什么东西
	- 从gc root开始搜索，搜索不到的对象
	- 补充强引用、弱引用、软引用、幻影引用区别等
	- 从root搜索不到，而且经过第一次标记、清理后，仍然没有复活的对象 （没有在finalize（）方法里面复活的对象）
- 做什么事情
	- 能说出诸如新生代做的是复制清理、from survivor、to survivor是干啥用的、老年代做的是标记清理、标记清理后碎片要不要整理、复制清理和标记清理有有什么优劣势等
	- 还能讲清楚串行、并行（整理/不整理碎片）、CMS等搜集器可作用的年代、特点、优劣势，并且能说明控制/调整收集器选择的方式。 

### 讲一下常见编码方式
utf-8，GBK

### 修改对象A的equals方法的签名，那么使用HashMap存放这个对象实例的时候，会调用哪个equals方法？
- 会调用对象对象的equals方法。
“==”如果是基本类型的话就是看他们的数据值是否相等就可以。
如果是引用类型的话，比较的是栈内存局部变量表中指向堆内存中的指针的值是否相等
“equals”如果对象的equals方法没有重写的话，equals方法和“==”是同一种。
hashcod是返回对象实例内存地址的hash映射。
理论上所有对象的hash映射都是不相同的。
 请[参照](https://www.jianshu.com/p/985534b21089)

### Java中String的了解
- 1.String类是final类是不可以被继承的，并且他的成员方法默认是final方法。java中final方法是不可以被继承的
- 2.String对象一旦被创建就是固定不变的了，对String对象的任何改变都不影响到原对象，相关的任何change操作都会生成新的对象
- 3.每当我们创建字符串常量时，JVM会首先检查字符串常量池，如果该字符串已经存在常量池中，那么就直接返回常量池中的实例引用。如果字符串不存在常量池中，就会实例化该字符串并且将其放到常量池中。由于String字符串的不可变性我们可以十分肯定常量池中一定不存在两个相同的字符
- 静态常量池：即*.class文件中的常量池，class文件中的常量池不仅仅包含字符串(数字)字面量，还包含类、方法的信息，占用class文件绝大部分空间.运行时常量池：则是jvm虚拟机在完成类装载操作后，将class文件中的常量池载入到内存中，并保存在方法区中，我们常说的常量池，就是指方法区中的运行时常量池。
- 4.intern方法使用：一个初始为空的字符串池，它由类String独自维护。当调用 intern方法时，如果池已经包含一个等于此String对象的字符串（用equals(oject)方法确定），则返回池中的字符串。否则，将此String对象添加到池中，并返回此String对象的引用。

### Java中实现多态的机制是什么？
- 请[参照](http://blog.csdn.net/bornlili/article/details/55213563)
- Java实现多态有三个必要条件：继承、重写、向上转型
	- 继承：在多态中必须存在有继承关系的子类和父类。
	- 重写：子类对父类中某些方法进行重新定义，在调用这些方法时就会调用子类的方法。
	- 向上转型：在多态中需要将子类的引用赋给父类对象，只有这样该引用才能够具备技能调用父类的方法和子类的方法。
- 在Java中有两种形式可以实现多态。继承和接口。
- 所以多态机制遵循的原则概括为：当超类对象引用变量引用子类对象时，被引用对象的类型而不是引用变量的类型决定了调用谁的成员方法，但是这个被调用的方法必须是在超类中定义过的，也就是说被子类覆盖的方法，但是它仍然要根据继承链中方法调用的优先级来确认方法，该优先级为：this.show(O)、super.show(O)、this.show((super)O)、super.show((super)O)。

### 如何将一个Java对象序列化到文件里？
- serilization
- fastjson
- protobuf
然后用outputstream写入文件
请[参照](http://blog.csdn.net/leefengboy/article/details/52724019)

### 说说你对Java反射的理解
- 请[参照](http://blog.csdn.net/piaoyi493279486/article/details/45624257)
- JAVA反射机制是在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法；对于任意一个对象，都能够调用它的任意一个方法和属性；这种动态获取的信息以及动态调用对象的方法的功能称为java语言的反射机制。
- Java反射机制主要提供了以下功能： 在运行时判断任意一个对象所属的类；在运行时判断任意一个类所具有的成员变量和方法；在运行时调用任意一个对象的方法；生成动态代理；在运行时构造任意一个类的对象；
- 利用反射机制能获得什么信息：一句话，类中有什么信息，它就可以获得什么信息，不过前提是得知道类的名字，要不就没有后文了
-  首先得根据传入的类的全名来创建Class对象。
	-  Class c=Class.forName("className");注明：className必须为全名，也就是得包含包名，比如，cn.netjava.pojo.UserInfo;
	-   Object obj=c.newInstance();//创建对象的实例
	-    获得构造函数的方法
	-   Constructor getConstructor(Class[] params)//根据指定参数获得public构造器
	-  Constructor[] getConstructors()//获得public的所有构造器
	-   Constructor getDeclaredConstructor(Class[] params)//根据指定参数获得public和非public的构造器
	-  Constructor[] getDeclaredConstructors()//获得public的所有构造器
	-   获得类方法的方法
	-   Method getMethod(String name, Class[] params),根据方法名，参数类型获得方法
	- Method[] getMethods()//获得所有的public方法
    - Method getDeclaredMethod(String name, Class[] params)//根据方法名和参数类型，获得public和非public的方法
	- Method[] getDeclaredMethods()//获得所以的public和非public方法
	- 获得类中属性的方法
	- Field getField(String name)//根据变量名得到相应的public变量
	- Field[] getFields()//获得类中所以public的方法
	-  Field getDeclaredField(String name)//根据方法名获得public和非public变量
	-  Field[] getDeclaredFields()//获得类中所有的public和非public方法
- Java的反射非常强大，传递class， 可以动态的生成该类、取得这个类的所有信息，包括里面的属性、方法以及构造函数等，甚至可以取得其父类或父接口里面的内容。
	- obj.getClass().getDeclaredMethods();//取得obj类中自己定义的方法， 包括私有的方法。
	- obj.getClass().getMethods();//取得obj类中自己定义的方法及继承过来的方法， 但私有方法得不到。

### 说说你对Java注解的理解
- 请[参照](https://www.zhihu.com/question/47449512?sort=created)
-  java 1.5开始引入了注解和反射，正确的来说注解是反射的一部分，没有反射，注解无法正常使用，但离开注解，反射依旧可以使用，因此来说，反射的定义应该包含注解才合理一些。
-  注解的功能分：
	- 1、编写文档：通过代码里标识的元数据生成文档【生成文档doc文档】
	- 2、代码分析：通过代码里标识的元数据对代码进行分析【使用反射】
	- 3、编译检查：通过代码里标识的元数据让编译器能够实现基本的编译检查【Override】
- 元注解：
	- 1. Retention：这个元注解表示一个注解会被保留到什么时候
	- 2、Documented： 当一个注解被@Documented元注解所修饰时，那么无论在哪里使用这个注解，都会被Javadoc工具文档化、
	- 3、Inherited：表明被修饰的注解类型是自动继承的。如果你想让一个类和它的子类都包含某个注解，就可以使用@Inherited来修饰这个注解。也就是说，假设Parent类是Child类的父类，那么我们若用被@Inherited元注解所修饰的某个注解对Parent类进行了修饰，则相当于Child类也被该注解所修饰了。
	-  4. Target：这个元注解说明了被修饰的注解的应用范围，也就是被修饰的注解可以用来注解哪些程序元素

### 说说你对依赖注入的理解
- 请[参照](https://www.jianshu.com/p/ba7dabe61bbe)
- 请[参照](https://www.zhihu.com/question/48427693?answer_deleted_redirect=true)
- 假设类A因功能F需要调用类B，传统的程序中，我们就会去new一个类B的对象，因而类A就会依赖类于类B，这就是说如果类B不存在，则类A也就无法使用。而使用依赖注入以后，类A只需要去调用实现功能F接口的一个实现类，这个实现类可能是类B,C等等，具体调用谁是有Spring的配置文件决定的，这样类A就不再依赖于类B。
- 我们可以这样理解控制反转：资源不是由使用资源的双方进行管理，而是由不使用资源的第三方（即Spring容器）进行管理
- 好处
	- 资源集中管理，实现资源的可配置与易管理
	-（**解耦**） 降低使用资源双方的依赖程度
- Spring Ioc与工厂模式的区别
	- 如果用户需求发生变化，要把Chinese类修改一下。那么前一种工厂模式，就要更改Factory类的方法，并且重新编译布署。而IoC只需 要将class属性改变一下，并且由于IoC利用了Java反射机制，这些对象是**动态生成**的，这时我们就可以热插拨Chinese对象（不必把原程序停止 下来重新编译布署）
	- 注意，IoC的灵活性是有代价的：设置步骤麻烦、生成对象的方式不直观、反射比正常生成对象在效率上慢一点。因此使用IoC要看有没有必要，我认为比较通用的判断方式是：用到工厂模式的地方都可以考虑用IoC模式。
	- 关于IoC的低侵入性。什么是低侵入性？如果你用过Struts或EJB就会发现，要继承一些接口或类，才能利用它们的框架开发。这样，系统就被绑定在Struts、EJB上 了，对系统的可移植性产生不利的影响。如果代码中很少涉及某一个框架的代码，那么这个框架就可以称做是一个低侵入性的框架。

### 说一下泛型原理，并举例说明
- 类型擦除:由于类型擦除，在运行期都是相同的对象，返回true'
```java
public static void main(String[] args) {
    ArrayList<String> arrayList1=new ArrayList<String>();
    arrayList1.add("abc");
    ArrayList<Integer> arrayList2=new ArrayList<Integer>();
    arrayList2.add(123);
 System.out.println(arrayList1.getClass()==arrayList2.getClass());
}
```
<<深入理解jvm>>p311
- 如下代码不能编译通过
```java
public class GenericTypes {
    public static void method(List<String> list){
        System.out.println("invoke method(List<String> list");
    }
    public static void method(List<Integer> list){
        System.out.println("invoke method(List<Integer> list");
    }
}
```
- 如下只有在jdk1.6中才可以输出
- invoke method(List<String> list)
- invoke method(List<Integer> list)
```java
public class GenericTypes {
    public static String method(List<String> list){
        System.out.println("invoke method(List<String> list");
        return "";
    }
    public static int method(List<Integer> list){
        System.out.println("invoke method(List<Integer> list");
        return 1;
    }
    public static void main(String[] args) {
        method(new ArrayList<String>());
        method(new ArrayList<Integer>());
    }
}
```
	

### String为什么要设计成不可变的？
- 请[参照](http://blog.csdn.net/renfufei/article/details/16808775)
- 1. 字符串常量池的需要(设计考虑,)
```java
String s1= "ab" + "cd";
String s2= "abc" + "d";
System.out.println(s1==s2);	//这两个相等，因为都在常量池里，
String a = "abc";
String b = new String("abc");
System.out.println(a==b);//这两个不等，因为一个存放在堆里，一个存放在常量池  
```

- 2. 允许String对象缓存HashCode(效率优化)
Java中String对象的哈希码被频繁地使用, 比如在hashMap 等容器中。
字符串不变性保证了hash码的唯一性,因此可以放心地进行缓存.这也是一种性能优化手段,意味着不必每次都去计算新的哈希码. 在String类的定义中有如下代码:
- 3. 安全性(安全性)
String被许多的Java类(库)用来当做参数,例如 网络连接地址URL,文件路径path,还有反射机制所需要的String参数等, 假若String不是固定不变的,将会引起各种安全隐患。

### Object类的equal和hashCode方法重写，为什么？
请[参照](http://blog.csdn.net/shiyanming1223/article/details/6893401)
- 1、 为什么要重载equal方法？
因为Object的equal方法默认是两个对象的引用的比较，意思就是指向同一内存,地址则相等，否则不相等；如果你现在需要利用对象里面的值来判断是否相等，则重载equal方法。
- 2、 为什么重载hashCode方法？
一般的地方不需要重载hashCode，只有当类需要放在HashTable、HashMap、HashSet等等hash结构的集合时才会重载hashCode，那么为什么要重载hashCode呢？就HashMap来说，好比HashMap就是一个大内存块，里面有很多小内存块，小内存块里面是一系列的对象，可以利用hashCode来查找小内存块hashCode%size(小内存块数量)，所以当equal相等时，hashCode必须相等，而且如果是object对象，必须重载hashCode和equal方法。
- 3、 为什么equals()相等，hashCode就一定要相等，而hashCode相等，却不要求equals相等?
	- 1、因为是按照hashCode来访问小内存块，所以hashCode必须相等。
	- 2、HashMap获取一个对象是比较key的hashCode相等和equal为true。
之所以hashCode相等，却可以equal不等，就比如ObjectA和ObjectB他们都有属性name，那么hashCode都以name计算，所以hashCode一样，但是两个对象属于不同类型，所以equal为false
- 4、 为什么需要hashCode?
	- 1、 通过hashCode可以很快的查到小内存块。
	- 2、 通过hashCode比较比equal方法快，当get时先比较hashCode，如果hashCode不同，直接返回false。

### 并发集合了解哪些？

### java的集合以及集合之间的继承关系
请[参照](http://blog.csdn.net/sdhgood/article/details/38849477)

### HashMap如何put数据（从HashMap源码角度讲解）？
请[参照](https://github.com/szfst/learn-java-project/tree/master/tulingInterview/src/hashmap)

### ArrayMap和HashMap的对比

### HashTable实现原理

### ConcurrentHashMap的实现原理
- 请[参照](http://www.importnew.com/26049.html)
- 其实可以看出JDK1.8版本的ConcurrentHashMap的数据结构已经接近HashMap，相对而言，ConcurrentHashMap只是增加了同步的操作来控制并发，从JDK1.7版本的ReentrantLock+Segment+HashEntry，到JDK1.8版本中synchronized+CAS+HashEntry+红黑树（**在没有hash冲突的时候直接使用cas，在有hash冲突的时候，用synchronized锁住整个链表**，当链表的长度大于8的时候使用红黑树）
- 在JDK1.8版本中，对于size的计算，在扩容和addCount()方法就已经有处理了，JDK1.7是在调用size()方法才去计算，其实在并发集合中去计算size是没有多大的意义的，因为size是实时在变的，只能计算某一刻的大小，但是某一刻太快了，人的感知是一个时间段，所以并不是很精确。
- JDK1.8的实现降低锁的粒度，JDK1.7版本锁的粒度是基于Segment的，包含多个HashEntry，而JDK1.8锁的粒度就是HashEntry（首节点）
- JDK1.8版本的数据结构变得更加简单，使得操作也更加清晰流畅，因为已经使用synchronized来进行同步，所以不需要分段锁的概念，也就不需要Segment这种数据结构了，由于粒度的降低，实现的复杂度也增加了
- JDK1.8使用红黑树来优化链表，基于长度很长的链表的遍历是一个很漫长的过程，而红黑树的遍历效率是很快的，代替一定阈值的链表，这样形成一个最佳拍档
- JDK1.8为什么使用内置锁synchronized来代替重入锁ReentrantLock，我觉得有以下几点：
	- 因为粒度降低了，在相对而言的低粒度加锁方式，synchronized并不比ReentrantLock差，在粗粒度加锁中ReentrantLock可能通过Condition来控制各个低粒度的边界，更加的灵活，而在低粒度中，Condition的优势就没有了
	- JVM的开发团队从来都没有放弃synchronized，而且基于JVM的synchronized优化空间更大，使用内嵌的关键字比使用API更加自然
	- 在大量的数据操作下，对于JVM的内存压力，基于API的ReentrantLock会开销更多的内存，虽然不是瓶颈，但是也是一个选择依据

### HashSet与HashMap怎么判断集合元素重复？
- hashmap通过equal和hashcode
- 而hashset通过hashmap来实现
```java
public class HashSet<E>
    extends AbstractSet<E>
    implements Set<E>, Cloneable, java.io.Serializable
{
    private transient HashMap<E,Object> map;
    // Dummy value to associate with an Object in the backing Map
    private static final Object PRESENT = new Object();
    public HashSet() {
    map = new HashMap<E,Object>();
    }
    public boolean contains(Object o) {
    return map.containsKey(o);
    }
    public boolean add(E e) {
    return map.put(e, PRESENT)==null;
    }
}
```

### java什么是深拷贝和浅拷贝
- 请[参照](https://www.oschina.net/translate/java-copy-shallow-vs-deep-in-which-you-will-swim)
- 浅拷贝：对象的浅拷贝会对“主”对象进行拷贝，但不会复制主对象里面的对象。"里面的对象“会在原来的对象和它的副本之间共享。Person copyPerson= new Person(beforePerson)
- 深拷贝：对象里面所有的数据都被拷贝了，两份数据
- 要创建一个真正的深拷贝，就需要我们一直这样拷贝下去，一直覆盖到 Person 对象所有的内部元素, 最后只剩下原始的类型以及“不可变对象（Immutables）”。让我们观察下如下这个 Street 类以获得更好的理解:
```java
public class Street {
    private String name;
    private int number;

    public Street(Street otherStreet){
         this.name = otherStreet.name;
         this.number = otherStreet.number;
    }
}
```
Street 对象有两个实体变量组成 – String 类型的 name 以及 int 类型的 number。int  类型的 number 是一个原始类型，并非对象。它只是一个简单的值，不能共享, 因此在创建第二个实体变量时，我们可以自动创建一个独立的拷贝。String 是一个不可变对象（Immutable）。简言之，不可变对象也是对象，可一旦创建好了以后就再也不能被修改了。因此，你可以不用为其创建深拷贝就能对其进行共享。

### 手写链表逆序代码(leetcode206)
```java
        public ListNode reverseList(ListNode head) {
            ListNode pre = null;
            ListNode cur = head;
            while(cur!=null){
                ListNode next = cur.next;

                cur.next=pre;
                pre = cur;
                cur = next;
            }
            return pre;
        }
```

### 讲一下对树，B+树的理解
- [参考](http://blog.csdn.net/zhangbo_0323/article/details/50156357)
-  B+树是B-树的变体，也是一种多路搜索树
-  非叶子结点的子树指针与关键字个数相同；
-  非叶子结点的子树指针P[i]，指向关键字值属于[K[i], K[i+1])的子树
（B-树是开区间）
- **为所有叶子结点增加一个链指针**
- 所有关键字都在叶子结点出现
- 特性：
	- 所有关键字都出现在叶子结点的链表中（稠密索引），且链表中的关键字恰好是有序的
	- 不可能在非叶子结点命中
	- 非叶子结点相当于是叶子结点的索引（稀疏索引），叶子结点相当于是存储（关键字）数据的数据层；
	- 更适合文件索引系统

![avatar](http://p.blog.csdn.net/images/p_blog_csdn_net/manesking/5.JPG)

### 讲一下对图的理解

### 判断单链表成环与否？
- 定义两个指针p, q，其中p每次向前移动一步，q每次向前移动两步，所以就成p为慢指针，q为快指针。 
那么如果单链表存在环，则p和q进入环后一定会在某一点相遇，因为进入环后就会一直循环下去，否则q将首先遇到null，就说明不存在环。[参考](http://blog.csdn.net/fu908323236/article/details/78205462)
```java
  private static boolean isCircle(Node head) {
        if (head == null) {
            return false;
        }
        //定义两个指针为同一起点
        Node n1 = head;   //慢指针
        Node n2 = head;   //快指针
        //只要有环的话，这个循环条件就绝对会满足，如果没有环的话，到了最后总不满足
        while(n2.next != null && n2.next.next != null) {
            n1 = n1.next; //n1一次走一步
            n2 = n2.next.next; //n2一次走两步
            if (n1 == n2) {  //如果成环，总会有一点n1==n2
                return true;
            }
        }
        return false;
    }
```
- 判断环的长度
相遇两次，步数相减（假设同一个节点开始）[参考](https://www.jianshu.com/p/6ff4f6cef1d0)

### 合并多个单有序链表（假设都是递增的）
- 合并两个链表
```java
 public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode l3 = head;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                l3.next = l2;
                l2 = l2.next;
            } else {
                l3.next = l1;
                l1 = l1.next;
            }
            l3 = l3.next;
        }
        l3.next = l1 == null ? l2 : l1;
        return head.next;
    }
``` 
- 合并多个：
通过队列实现，记得实现comparator
```java
       PriorityQueue<ListNode> p = new PriorityQueue<>((o1, o2) -> o1.val<o2.val?-1:1);
       for(ListNode item:lists){
           if(item!=null){
               p.add(item);
           }
       }
       ListNode head = new ListNode(0);
       ListNode cur = head;
       while(!p.isEmpty()){
           ListNode peek = p.remove();
           cur.next = peek;
           if(peek.next!=null){
               p.add(peek.next);
           }
           cur=cur.next;
       }
       return head.next;
```
