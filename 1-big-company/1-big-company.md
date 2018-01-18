网页地址:http://blog.csdn.net/u011676417/article/details/69076759
##### 一、concurrent：
- 1、volatile：
	- 保证可见性，禁止指令重排序，**但是不能保证可见性**
- 2、transient：
	- Java的serialization提供了一种持久化对象实例的机制。当持久化对象时，可能有一个特殊的对象数据成员，我们不想用serialization机制来保存它。为了在一个特定对象的一个域上关闭serialization，可以在这个域前加上关键字transient
##### 二、java虚拟机：
- GC
	- 对象什么时候会被GC:
		- 引用计数算法：给对象添加一个引用计数器，每当有一个地方引用它的时候，计数器就加1，当失效的时候就减1；任何时刻计数器为0的对象就是不可能再被时候的；优点是简单，缺点：objA.instance=objB,objB.instance=objA,实际上这两个对象不能使用了，但还是相互有引用，虚拟机不能回收它们；虚拟机不是通过引用记数法判断对象存活的；
		- 可达性分析算法
			- 基本思路：通过一系列称为“GC Roots"的对象作为起始点，重这些节点开始向下搜索，搜索所走过的路径称为引用链(Refrence Chain),当一个对象到GC Roots没有任何引用链相连（用图论的话来说，就是从GC Roots到这个对象不可达）时，则证明这个对象是不可用的。
			- java语言中：可以作为GC Roots的对象包括以下几种：
				- 虚拟机栈（栈帧中的本地变量表）中引用的对象
				- 方法去中类静态属性所引用的对象
				- 方法区中常量引用的对象
				- 本地方法栈中JNI(即一般说的native方法)引用的对象
	    - 引用：
		    - 强引用：只要强引用存在，垃圾收集器永远不会回收掉被引用的对象
		    - 软引用（soft Reference）：在系统将要发生内存溢出异常之前，将会把这些对象列入回收返回之中进行第二次回收。如果这次回收还没有足够的内存，才会抛出内存溢出异常。jdk1.2以后才有。
		    - 弱引用：被弱引用关联的对象只能生存到下一次垃圾回收发生之前，当垃圾收集器工作时，无论当前内存时候足够，都会回收弱引用。jdk1.2以后才有。
		    - 虚引用：一个对象是否有虚引用的存在，完全不会对其生产时间构成影响，也无法通过虚引用来取得一个对象实例。为一个对象设置虚引用的唯一目的就是能够在这个对象被回收时收到一个通知。  jdk1.2以后才有。
		- 没有被标记的对象，也不会立刻被回收，如果有finalize()方法，系统会将它加入到F-queue中，在finalize方法调用的时候他可以让别的引用再指向它，这样就不会被回收，但是finalize()方法只被系统执行一次，再次没有引用不会被调用了
	- GC算法：
		-  标记清理（Mark-Sweep）
			-  首先标记出所有需要回收的对象，在标记完成后统一回收所有被标记的对象，是一种基础的回收算法，因为后续的回收算法都是基于此算法的不足而改进得到的
			-  不足：1、效率不高，标记和清除两个过程的效率都不高；2、空间不连续，空间碎片太多后，在程序运行过程中需要分配较大的对象时，无法找到足够的额连续内存而不得不提前触发另一次回收。
		-  复制算法（Copying）
			-  方式：将内存按照容量划分为大小相等的两款，每次只使用其中的一块。当这一块的内存用完了，就将还存活的对象复制到另一块上面，然后再把已使用过的内存空间一次性清理掉。
			-  优点：每次对半个内存回收，内存分配的时候也不用考虑内存碎片的情况；实现简单；
			-  缺点：将内存缩小为原来的一半，在对象存活率较高的情况下要进行很多复制效率低下
			-  现在的商业虚拟机都使用这种回收算法**回收新生代**，适合新生代，两个survivor分别占内存10%，eden占用80%的内存，当存活的对象超过10%，可以用老年担保（handle promotion）
		-  标记-整理算法（Mark-Compact）
			-  方式：在标记-清除算法的后面使用在对对象进行整理
			-  适合老年代
		- 分代收集算法
			- 根据对象存活周期的不同将内存划分为几块。一半是把java堆分为新生代和老年代，这样就可以根据各个年代的特点采用最合适的算法。在新生代中， 每次垃圾回收都会有大量对象死去，只有少量活着，就用复制算法，只需要付出少量存活对象的复制成本就可以完成收集。而老年代中因为对象的存活率高、没有二外的空间对他进行分配担保，就必须使用“标记-清理”或者“标记-整理”算法来进行回收。 
- 类加载机制
	- 类加载器有哪些： 
		- 驱动类加载器（Bootstrap ClassLoader）：c++实现
			- 启动类加载器无法被java程序直接饮用，用户在编写自定义加载器是，如果需要把加载器请求委派给引导类加载器，直接用null代替既可
			- 加载的范围：JAVA_HOME\lib、被-Xbootclasspath参数所制定的路径中的，并且是虚拟机识别的（仅按照文件名识别，如rt.jar，名字不符合的类库即使放到lib目录中也不会被加载）
		- 扩展类加载器（Extension ClassLoader）：
			- 加载的范围：JAVA_HOME\lib\ext目录中的，或者被java.ext.dirs系统变量所指定的路径中的所有类库，开发者可以直接使用扩展类加载器
		- 应用程序类加载器（Application ClassLoader）：系统类加载器（getSystemClassLoader（）返回的类），如果应用程序中没有自定义过自己的类加载器，一般情况下这个就是程序中默认的类加载器 
			- 加载的范围：负责加载用户类路径（classpath）上所指定的类库
	- 这些类加载器的父子关系：启动类加载器<--- 扩展类加载器<--- 应用程序类加载器<--- 自定义类加载器
	- 什么是双亲委派模型：如果一个雷加载器收到了类的加载请求，它首先不会自己尝试去加载这个类，而是把这个请求委派给弗雷加载去去完成，每一个层次的类加载器都是如此，因此所有的加载请求最终都应该传送到启程的启动类加载器中，只有当弗雷加载器反馈自己无法完成这个请求（它搜索范围中没有找到所需的类）时，子加载器才会尝试自己去加载。
	- 双亲委派模型的好处：java类随着它的类加载器一起具备了一种带有优先级的层次关系，对于保证java的稳定运行有很重要的作用。例如类java.lang.Object，它存放在rt.jar中，无论哪一个类加载器要加载这个类，最终都是委派给处于模型最顶端的启动类加载器进行加载，因此Object类在程序的各个类加载器环境中都是同一个类。相反，如果没有使用双亲委派模型，由各个类取自行加载的话，如果用户编写了一个称为java.lang.Objectde 类，并放在程序的ClassPath中，那系统中将会出现多个不同的Object类，java类型提系统最基础的行为也就无法保证，应用程序也会变得一片混乱。可以尝试去编写一个与rt.jar类库中已有类重名的java类，将会发现可以正常变异，但永远无法被加载运行。
	- 如何自定义类加载器：</br>
		- 1、继承ClassLoader
		- 2、重写findClass方法（不重写loadClass方法，调用父类的loadClass方法找不到类就可以调用子类的findClass了）
```java
	
  public class MyClassLoader extends ClassLoader {
    private String path;//加载类的路径
    private String name;//类加载器名称
    public MyClassLoader(ClassLoader parent, String path, String name) {
        super(parent);//显示制定父类加载器
        this.path = path;
        this.name = name;
    }
    public MyClassLoader(String path, String name) {
        super();//让系统类加载器成为该类的父加载器
        this.path = path;
        this.name = name;
    }
    //记载我们自己定义的类，通过我们自定义的这个classloader
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] data = readClassFileToByteArray(name);
        return this.defineClass(name,data,0,data.length);
    }
    //获取我们.class文件的字节数组
    private byte[] readClassFileToByteArray(String name) {
        //TODO执行自己加载类的方法，详细代码见code
        return returnData;
    }
}
//使用
public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        MyClassLoader loader = new MyClassLoader("G:/tmp/","zhangfei");
        //上一个loader作为父加载器
        MyClassLoader wukongloader = new MyClassLoader(loader,"G:/tmp/","wukong");
        //null则代表父类加载器为 BootStrap ClassLoader，父类加载器找不到那个类就调用findClass让子类加载器去加载
//        MyClassLoader wukongloader = new MyClassLoader(null,"G:/tmp/","wukong");
        //双亲委派模型，如果父类存在这个类，则加载父亲的
        Class<?> testDemo = wukongloader.loadClass("jvm.TestDemo");
        //加载自定义的
        Class<?> testDemo1 = wukongloader.loadClass("a.b.TestDemo");
        testDemo.newInstance();
        testDemo1.newInstance();
    }
```
