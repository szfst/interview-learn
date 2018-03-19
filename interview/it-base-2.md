#####哪些情况下的对象会被垃圾回收机制处理掉
https://www.zhihu.com/question/35164211
- 什么时候
	- 能说出新生代、老年代结构，能提出minor gc/full gc （Minor GC
从年轻代空间（包括 Eden 和 Survivor 区域）回收内存被称为 Minor GC。Major GC 是清理老年代。Full GC 是清理整个堆空间—包括年轻代和老年代。）
	- 能说明minor gc/full gc的触发条件、OOM的触发条件，降低GC的调优的策略。 
	- eden满了minor gc
	- 升到老年代的对象大于老年代剩余空间full gc，或者小于时被HandlePromotionFailure参数强制full gc
	- gc与非gc时间耗时超过了GCTimeRatio的限制引发OOM，调优诸如通过NewRatio控制新生代老年代比例，通过MaxTenuringThreshold控制进入老年前生存次数等
- 对什么东西
	- 从gc root开始搜索，搜索不到的对象
	- 补充强引用、弱引用、软引用、幻影引用区别等
	- 从root搜索不到，而且经过第一次标记、清理后，仍然没有复活的对象 （没有在finalize（）方法里面复活的对象）
- 做什么事情
	- 能说出诸如新生代做的是复制清理、from survivor、to survivor是干啥用的、老年代做的是标记清理、标记清理后碎片要不要整理、复制清理和标记清理有有什么优劣势等
	- 还能讲清楚串行、并行（整理/不整理碎片）、CMS等搜集器可作用的年代、特点、优劣势，并且能说明控制/调整收集器选择的方式。 
#####讲一下常见编码方式
utf-8，GBK
#####修改对象A的equals方法的签名，那么使用HashMap存放这个对象实例的时候，会调用哪个equals方法？
- 会调用对象对象的equals方法。
“==”如果是基本类型的话就是看他们的数据值是否相等就可以。
如果是引用类型的话，比较的是栈内存局部变量表中指向堆内存中的指针的值是否相等
“equals”如果对象的equals方法没有重写的话，equals方法和“==”是同一种。
hashcod是返回对象实例内存地址的hash映射。
理论上所有对象的hash映射都是不相同的。
https://www.jianshu.com/p/985534b21089
#####Java中String的了解
- 1.String类是final类是不可以被继承的，并且他的成员方法默认是final方法。java中final方法是不可以被继承的
- 2.String对象一旦被创建就是固定不变的了，对String对象的任何改变都不影响到原对象，相关的任何change操作都会生成新的对象
- 3.每当我们创建字符串常量时，JVM会首先检查字符串常量池，如果该字符串已经存在常量池中，那么就直接返回常量池中的实例引用。如果字符串不存在常量池中，就会实例化该字符串并且将其放到常量池中。由于String字符串的不可变性我们可以十分肯定常量池中一定不存在两个相同的字符
- 静态常量池：即*.class文件中的常量池，class文件中的常量池不仅仅包含字符串(数字)字面量，还包含类、方法的信息，占用class文件绝大部分空间.运行时常量池：则是jvm虚拟机在完成类装载操作后，将class文件中的常量池载入到内存中，并保存在方法区中，我们常说的常量池，就是指方法区中的运行时常量池。
- 4.intern方法使用：一个初始为空的字符串池，它由类String独自维护。当调用 intern方法时，如果池已经包含一个等于此String对象的字符串（用equals(oject)方法确定），则返回池中的字符串。否则，将此String对象添加到池中，并返回此String对象的引用。
#####Java中实现多态的机制是什么？
- http://blog.csdn.net/bornlili/article/details/55213563
- Java实现多态有三个必要条件：继承、重写、向上转型
	- 继承：在多态中必须存在有继承关系的子类和父类。
	- 重写：子类对父类中某些方法进行重新定义，在调用这些方法时就会调用子类的方法。
	- 向上转型：在多态中需要将子类的引用赋给父类对象，只有这样该引用才能够具备技能调用父类的方法和子类的方法。
- 在Java中有两种形式可以实现多态。继承和接口。
- 所以多态机制遵循的原则概括为：当超类对象引用变量引用子类对象时，被引用对象的类型而不是引用变量的类型决定了调用谁的成员方法，但是这个被调用的方法必须是在超类中定义过的，也就是说被子类覆盖的方法，但是它仍然要根据继承链中方法调用的优先级来确认方法，该优先级为：this.show(O)、super.show(O)、this.show((super)O)、super.show((super)O)。
#####如何将一个Java对象序列化到文件里？
- serilization
- fastjson
- protobuf
然后用outputstream写入文件
http://blog.csdn.net/leefengboy/article/details/52724019
#####说说你对Java反射的理解
- http://blog.csdn.net/piaoyi493279486/article/details/45624257
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
#####说说你对Java注解的理解
- https://www.zhihu.com/question/47449512?sort=created
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