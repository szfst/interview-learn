
### 滴滴优点
- SimpleDateFormat的特点：
	- 内部维护一个Calendar，存在线程不安全的情况
	- https://www.cnblogs.com/zemliu/archive/2013/08/29/3290585.html
	- http://blog.csdn.net/zdp072/article/details/41044059
- 秒杀
	- http://blog.csdn.net/luomingkui1109/article/details/77432192 
- netty工作原理：
http://blog.csdn.net/excellentyuxiao/article/details/53390408
- http协议 
- 秒杀，库存不为负数的做法
- mysql和mongodb的区别
- 堆
- netty的流程
- haspmap的原理
- es，mongodb的聚合
- 线程同步的方法
###  你我金融
- Thread方法：
- 方法名称可以和构造方法相同
- 不能用来修饰interface的有：private，protected，static
- 链接jdbc
```java
Connection connection = DriverManager.getConnection("url", "username", "password");
        Statement statement = connection.createStatement();
        statement.execute("select * from tabl where j = 0");
```
```java
Class.forName("com.mysql.jdbc.Driver");
Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/message", "root", "password");
PreparedStatement preparedStatement = connection.prepareStatement("select id from message");
ResultSet resultSet = preparedStatement.executeQuery();
while (resultSet.next()){
	resultSet.getString("id");
}
```
- 如下程序，在同一个文件里面，编译报错：
```java
class Base {
    public Base() {
        System.out.println("base");
    }
}
public class Alpha extends Base {
    public static void main(String[] args) {
        new Alpha();
        new Base();
    }
}
```
- java 基本类型：boolean，byte，char，short，int，float，double，long
- String 是类或者对象，是常量
- round：四舍五入（加上0.5之后向下取整）；ceil：天花板也就是向上取整；
floor：地板，也就是向下取整
- switch(A),括号中A的取值可以是byte、short、int、char、String，还有枚举类型,不能作用在long上面
- UML类图：依赖关系，聚合关系，组合关系，关联关系，继承关系http://blog.csdn.net/a19881029/article/details/8957441
- 排序算法java实现(插入排序)
```
public static void InsertSort(int[] arr)
{
    int i, j;
    int n = arr.Length;
    int target;
    //假定第一个元素被放到了正确的位置上
    //这样，仅需遍历1 - n-1
    for (i = 1; i < n; i++)
    {
        j = i;
        target = arr[i];
        while (j > 0 && target < arr[j - 1])
        {
            arr[j] = arr[j - 1];
            j--;
        }
        arr[j] = target;
    }
}
```
- socket编程
	- 客户端：
```java
socket = new Socket("xxx.xxx.xxx.xxx", 10000);
in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
out = new PrintWriter(socket.getOutputStream(),true);
BufferedReader line = new BufferedReader(new InputStreamReader(System.in));
out.println(line.readLine());
line.close();
out.close();
in.close();
socket.close();
```
### 四格互联
- Rocketmq和kafka的区别，为什么要用kafka
- mybatis的原理
- rpc的理解，dobble
- mybatis实现分库分表
- abstract
	- abstract 可以修饰方法、类，但是不能修饰变量。
	- abstract 修饰的方法不能有大括号。
- 哪个不是response对象的方法 （getParameter不是）
	- setContentType（String ct）；
	- setHeader（String hdname，String hdValue）；
	- getParameter（）；
	- sendError（int errorCode）
- Set<Integer> set = new LinkedHashSet<Integer>(10)：有序的，初始化为10，后面可以动态增加数量 
- 输出结果为aaa bbb
```java
public class Test {
    private void test(){
        int x = 1;
        switch (x){
            case 1:{
                System.out.println("aaa");
            }
            case 2:{
                System.out.println("bbb");break;
            }
            case 3:
                System.out.println("ccc");break;
        }
    }
    public static void main(String[] args) {
        new Test().test();
    }
}
```
- throw（在方法体内抛出异常） 和throws（在方法的声明处）
- spring bean同一个配置文件id不能相同，但是不同文件可以相同
- 分布式session的理解
### 萨摩耶金服
- 这个公司好坑，面试官没做准备
- spring IOC 好处：
	- 解耦：为什么解耦
	- 方便
	- 高效
- spring AOP 好处：
	- 声明式事务在此展开
	- 注点分离：将问题细化为单独部分，即可以理解为不可再分割的组件，如上边的日志组件和支付组件
	- 1：降低模块的耦合度
	- 2：使系统容易扩展
	- 3：设计决定的迟绑定：使用AOP,设计师可以推迟为将来的需求作决定，因为它
可以把这种需求作为独立的方面很容易的实现。
	- 4：更好的代码复用性
- 什么时候用联合索引什么时候用单个索引
- 为什么联合索引第一个不走，后面的都不走了
	- b+tree索引就是按顺序的，如果前面找不到，后面就找不到了。
- spring cloud 如何做服务注册发现，实现原理。 
	- https://zhuanlan.zhihu.com/p/24829766
	- http://blog.csdn.net/jrn1012/article/details/77837671
### 自己看的
- 分布式领域CAP理论，
	- Consistency(一致性), 数据一致更新，所有数据变动都是同步的
	- Availability(可用性), 好的响应性能
	- Partition tolerance(分区容忍性) 可靠性
	- 定理：任何分布式系统只可同时满足二点，没法三者兼顾。
	- 忠告：架构师不要将精力浪费在如何设计能满足三者的完美分布式系统，而是应该进行取舍。
	- mybatis原理
