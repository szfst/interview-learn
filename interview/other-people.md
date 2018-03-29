#### 单例模式
- 饿汉模式
```java
/**
 * 饿汉模式
 * 单例实例在类装载时进行创建
 */
 //方法二：初始化对象时创建
@ThreadSafe
public class SingletonExample2 {
    // 私有构造函数
    private SingletonExample2() {
    }
    // 单例对象
    private static SingletonExample2 instance = new SingletonExample2();
    // 静态的工厂方法
    public static SingletonExample2 getInstance() {
        return instance;
    }
}
//--------------------------------------
//方法一、静态块初始化
@ThreadSafe
public class SingletonExample6 {
    // 私有构造函数
    private SingletonExample6() {
    }
    // 单例对象
    private static SingletonExample6 instance = null;
    static {
        instance = new SingletonExample6();
    }
    // 静态的工厂方法
    public static SingletonExample6 getInstance() {
        return instance;
    }
    public static void main(String[] args) {
        System.out.println(getInstance().hashCode());
        System.out.println(getInstance().hashCode());
    }
}
```
- 懒汉模式（单例对象 volatile + 双重检测机制 -> 禁止指令重排）
```java
@ThreadSafe
public class SingletonExample5 {
    // 私有构造函数
    private SingletonExample5() {
    }
    // 1、memory = allocate() 分配对象的内存空间
    // 2、ctorInstance() 初始化对象
    // 3、instance = memory 设置instance指向刚分配的内存
    // 单例对象 volatile + 双重检测机制 -> 禁止指令重排
    private volatile static SingletonExample5 instance = null;

    // 静态的工厂方法
    public static SingletonExample5 getInstance() {
        if (instance == null) { // 双重检测机制        // B
            synchronized (SingletonExample5.class) { // 同步锁
                if (instance == null) {
                    instance = new SingletonExample5(); // A - 3
                }
            }
        }
        return instance;
    }
```
- enum枚举方式
```java
/**
 * 枚举模式：最安全
 */
@ThreadSafe
@Recommend
public class SingletonExample7 {
    // 私有构造函数
    private SingletonExample7() {
    }
    public static SingletonExample7 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }
    private enum Singleton {
        INSTANCE;
        private SingletonExample7 singleton;
        // JVM保证这个方法绝对只调用一次
        Singleton() {
            singleton = new SingletonExample7();
        }
        public SingletonExample7 getInstance() {
            return singleton;
        }
    }
}
```