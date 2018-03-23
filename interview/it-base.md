[TOC]
##### 一、java char能否存储一个中文字
- java中的char是两个字节的，所以可以用来存储中文(一个中文也是两个字节)，而在c语言中char只是一个字节
- Java编译器默认使用Unicode编码，因此2字节可以表示所有字符。

##### java中==和equals和hashCode的区别
- http://blog.csdn.net/hla199106/article/details/46907725
```java
int int1 = 12;
Integer Integer1 = new Integer(12);
Integer Integer2 = new Integer(12);
Integer Integer3 = new Integer(127);
Integer b1 = 127;
String s1 = "str";
String s2 = "str";
String str1 = new String("str");
String str2 = new String("str");

System.out.println("int1==Integer1:" + (int1 == Integer1));//Integer会自动拆箱为int，所以为true
System.out.println("Integer1==Integer2:" + (Integer1 == Integer2));//不同对象，在内存存放地址不同，所以为false
System.out.println("Integer3==b1:" + (Integer3 == b1));//Integer3指向new的对象地址，b1指向缓存中127地址，地址不同，所以为false

System.out.println("s1==s2:" + (s1 == s2));//true
System.out.println("s1==str1:" + (s1 == str1));//false
System.out.println("str1==str2:" + (str1 == str2));//false
```
- 初学者可以这样理解，hashCode方法实际上返回的就是对象存储的物理地址（**实际可能并不是**）。
- 1、如果两个对象equals，Java运行时环境会认为他们的hashcode一定相等。 
- 2、如果两个对象不equals，他们的hashcode有可能相等。 
- 3、如果两个对象hashcode相等，他们不一定equals。 
- 4、如果两个对象hashcode不相等，他们一定不equals
##### int、char、long各占多少字节数
- 1字节： byte , boolean
- 2字节： short , char
- 4字节： int , float
- 8字节： long , double
- 注：1字节(byte)=8位(bits)
##### utf-8编码中的中文占几个字节
- Unicode/GBK： 中文2字节
- UTF-8： 中文通常3字节，在拓展B区之后的是4字节
- 综上，中文字符在编码中占用的字节数一般是2-4个字节。
```java
System.out.println("中".getBytes("UTF-8").length);//3
System.out.println("中中".getBytes("UTF-8").length);//6
System.out.println("中".getBytes("GBK").length); //2
System.out.println("中中".getBytes("GBK").length);//4
```
##### int与integer的区别
https://www.cnblogs.com/guodongdidi/p/6953217.html
##### 谈谈对java多态的理解
- 三要素：继承、重写、父类引用指向子类对象http://blog.csdn.net/woshiermingerming/article/details/52324861
- 多态分为两种（ https://www.cnblogs.com/liujinhong/p/6003144.html）
	- a. 编译时多态：方法的重载；
	- b. 运行时多态：JAVA运行时系统根据调用该方法的实例的类型来决定选择调用哪个方法则被称为运行时多态。（我们平时说得多的事运行时多态，所以多态主要也是指运行时多态）
- 多态的定义：指允许不同类的对象对同一消息做出响应。即同一消息可以根据发送对象的不同而采用多种不同的行为方式。（发送消息就是函数调用）
- 实现多态的技术称为：动态绑定（dynamic binding），是指在执行期间判断所引用对象的实际类型，根据其实际的类型调用其相应的方法。
- 多态的作用：**消除类型之间的耦合关系**。
-  多态的好处：
	-  1.可替换性（substitutability）。多态对已存在代码具有可替换性。例如，多态对圆Circle类工作，对其他任何圆形几何体，如圆环，也同样工作。
	- 2.可扩充性（extensibility）。多态对代码具有可扩充性。增加新的子类不影响已存在类的多态性、继承性，以及其他特性的运行和操作。实际上新加子类更容易获得多态功能。例如，在实现了圆锥、半圆锥以及半球体的多态基础上，很容易增添球体类的多态性。
	- 3.接口性（interface-ability）。多态是超类通过方法签名，向子类提供了一个共同接口，由子类来完善或者覆盖它而实现的。如图8.3 所示。图中超类Shape规定了两个实现多态的接口方法，computeArea()以及computeVolume()。子类，如Circle和Sphere为了实现多态，完善或者覆盖这两个接口方法。
	- 4.灵活性（flexibility）。它在应用中体现了灵活多样的操作，提高了使用效率。
	- 5.简化性（simplicity）。多态简化对应用软件的代码编写和修改过程，尤其在处理大量对象的运算和操作时，这个特点尤为突出和重要。
##### String、StringBuffer、StringBuilder区别
##### 什么是内部类？内部类的作用
- 内部类（ Inner Class ）就是定义在另外一个类里面的类。与之对应，包含内部类的类被称为外部类
- 内部类的主要作用如下：
	- 内部类提供了更好的封装，可以把内部类隐藏在外部类之内，不允许同一个包中的其他类访问该类
	- 内部类的方法可以直接访问外部类的所有数据，包括私有的数据
	- 内部类所实现的功能使用外部类同样可以实现，只是有时使用内部类更方便
- 内部类可分为以下几种
	- 成员内部类
	- 静态内部类
	- 方法内部类
	- 匿名内部类
##### 抽象类和接口区别
- 抽象类只能单继承,接口能多实现
- 抽象类是一个类,可以被任意权限修饰符修饰,静态和非静态属性，final和非final属性，可以有抽象方法和非抽象方法；接口只能被public,final修饰,只能有静态方法,即使没有显示的声明，而且是不可修改的
-  抽象的事物不同:   抽象类是对类的抽象,接口是对行为的抽象； 抽象类是对整个类抽象,包括属性,行为；接口是对类的行为(局部)进行抽象；
-  定义的时候,定义抽象类和接口的思想不同； 设计抽象类是自下而上的过程,我子类需要,所以我定义抽象类；设计接口是自上而下的过程,我接口规范某一行为,我某类需要这个行为,我实现某接口；
-  调用者使用动机不同,实现接口是为了使用他规范的某一个行为；
 继承抽象类是为了使用这个类属性和行为.
##### 抽象类的意义
 - 规范子类的行为
 - 封装子类中重复内容（成员变量和方法）
 - 为子类提供一个公共的类型
##### 抽象类与接口的应用场景
##### 抽象类是否可以没有方法和属性
可以
##### 接口的意义
- 接口是一种规范，规范行为
- 不同的类不同的实现
##### 泛型中extends和super的区别
- <? extends T> 表示上界通配符 它表示T以及T的子类， 类型最高是T
- <? super T> 表示下界通配符 它表示T以及T的超类，类型最高可到Object ，最低是T，可以存数据，但是只能取出Object的数据
##### 父类的静态方法能否被子类重写
- http://blog.sina.com.cn/s/blog_ac37d2530102wq6n.html
- 父类的静态方法是**不能**被子类重写的，其实重写只能适用于实例方法，不能用于静态方法，对于上面这种静态方法而言，我们应该称之为隐藏(如果引用是父类，调用则显示父类的，如果引用是子类，调用静态方法则显示子类的静态方法)。  Java静态方法形式上可以重写，但从本质上来说不是Java的重写。因为静态方法只与类相关，不与具体实现相关。声明的是什么类，则引用相应类的静态方法(本来静态无需声明，可以直接引用)。并且static方法不是后期绑定的，它在编译期就绑定了。换句话说，这个方法不会进行多态的判断，只与声明的类有关
#####进程和线程的区别
- http://blog.csdn.net/zhou753099943/article/details/51771220、
- 进程是资源的分配和调度的一个独立单元，而线程是CPU调度的基本单元
##### final，finally，finalize的区别
http://blog.csdn.net/cyl101816/article/details/67640843
##### java序列化的方式
- 1、Java原生序列化：Java原生序列化方法即通过Java原生流(InputStream和OutputStream之间的转化)的方式进行转化。需要注意的是JavaBean实体类必须实现Serializable接口，否则无法序列化
- 2、Json序列化：Json序列化一般会使用jackson包，通过ObjectMapper类来进行一些操作，比如将对象转化为byte数组或者将json串转化为对象。现在的大多数公司都将json作为服务器端返回的数据格式。比如调用一个服务器接口，通常的请求为xxx.json?a=xxx&b=xxx的形式
- 3、FastJson序列化：fastjson 是由阿里巴巴开发的一个性能很好的Java 语言实现的 Json解析器和生成器。特点：速度快，测试表明fastjson具有极快的性能，超越任其他的java json parser。功能强大，完全支持java bean、集合、Map、日期、Enum，支持范型和自省。无依赖，能够直接运行在Java SE 5.0以上版本 
支持Android。使用时候需引入FastJson第三方jar包
- 4、ProtoBuff序列化：ProtocolBuffer是一种轻便高效的结构化数据存储格式，可以用于结构化数据序列化。适合做数据存储或 RPC 数据交换格式。可用于通讯协议、数据存储等领域的语言无关、平台无关、可扩展的序列化结构数据格式。
优点：跨语言；序列化后数据占用空间比JSON小，JSON有一定的格式，在数据量上还有可以压缩的空间。
缺点：它以二进制的方式存储，无法直接读取编辑，除非你有 .proto 定义，否则无法直接读出 Protobuffer的任何内容。
其与thrift的对比：两者语法类似，都支持版本向后兼容和向前兼容，thrift侧重点是构建跨语言的可伸缩的服务，支持的语言多，同时提供了全套RPC解决方案，可以很方便的直接构建服务，不需要做太多其他的工作。 Protobuffer主要是一种序列化机制，在数据序列化上进行性能比较，Protobuffer相对较好。ProtoBuff序列化对象可以很大程度上将其压缩，可以大大减少数据传输大小，提高系统性能。对于大量数据的缓存，也可以提高缓存中数据存储量。原始的ProtoBuff需要自己写.proto文件，通过编译器将其转换为java文件，显得比较繁琐。百度研发的jprotobuf框架将Google原始的protobuf进行了封装，对其进行简化，仅提供序列化和反序列化方法。其实用上也比较简洁，通过对JavaBean中的字段进行注解就行，不需要撰写.proto文件和实用编译器将其生成.java文件，百度的jprotobuf都替我们做了这些事情了。
5、总结：
- jprotobuf序列化耗时：7ms; 总大小：148
- jprotobuf反序列化耗时：0ms
- java serialize: 6ms; 总大小：420
- java deserialize: 1ms
- json serialize: 37ms; 总大小：341
- json deserialize: 27ms
- fastJson serialize: 173ms; 总大小：269
- fastJson serialize: 35ms
##### Serializable 和Parcelable 的区别
Android中实现序列化有两个选择：一是实现Serializable接口（是JavaSE本身就支持的），一是实现Parcelable接口（是Android特有功能，效率比实现Serializable接口高效，可用于Intent数据传递，也可以用于进程间通信（IPC））
##### 静态属性和静态方法是否可以被继承？是否可以被重写？以及原因？
##### 静态内部类的设计意图
##### 成员内部类、静态内部类、局部内部类和匿名内部类的理解，以及项目中的应用
##### 闭包和局部内部类的区别
##### string 转换成 integer的方式及原理