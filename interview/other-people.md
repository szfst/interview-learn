#### ����ģʽ
- ����ģʽ
```java
/**
 * ����ģʽ
 * ����ʵ������װ��ʱ���д���
 */
 //����������ʼ������ʱ����
@ThreadSafe
public class SingletonExample2 {
    // ˽�й��캯��
    private SingletonExample2() {
    }
    // ��������
    private static SingletonExample2 instance = new SingletonExample2();
    // ��̬�Ĺ�������
    public static SingletonExample2 getInstance() {
        return instance;
    }
}
//--------------------------------------
//����һ����̬���ʼ��
@ThreadSafe
public class SingletonExample6 {
    // ˽�й��캯��
    private SingletonExample6() {
    }
    // ��������
    private static SingletonExample6 instance = null;
    static {
        instance = new SingletonExample6();
    }
    // ��̬�Ĺ�������
    public static SingletonExample6 getInstance() {
        return instance;
    }
    public static void main(String[] args) {
        System.out.println(getInstance().hashCode());
        System.out.println(getInstance().hashCode());
    }
}
```
- ����ģʽ���������� volatile + ˫�ؼ����� -> ��ָֹ�����ţ�
```java
@ThreadSafe
public class SingletonExample5 {
    // ˽�й��캯��
    private SingletonExample5() {
    }
    // 1��memory = allocate() ���������ڴ�ռ�
    // 2��ctorInstance() ��ʼ������
    // 3��instance = memory ����instanceָ��շ�����ڴ�
    // �������� volatile + ˫�ؼ����� -> ��ָֹ������
    private volatile static SingletonExample5 instance = null;

    // ��̬�Ĺ�������
    public static SingletonExample5 getInstance() {
        if (instance == null) { // ˫�ؼ�����        // B
            synchronized (SingletonExample5.class) { // ͬ����
                if (instance == null) {
                    instance = new SingletonExample5(); // A - 3
                }
            }
        }
        return instance;
    }
```
- enumö�ٷ�ʽ
```java
/**
 * ö��ģʽ���ȫ
 */
@ThreadSafe
@Recommend
public class SingletonExample7 {
    // ˽�й��캯��
    private SingletonExample7() {
    }
    public static SingletonExample7 getInstance() {
        return Singleton.INSTANCE.getInstance();
    }
    private enum Singleton {
        INSTANCE;
        private SingletonExample7 singleton;
        // JVM��֤�����������ֻ����һ��
        Singleton() {
            singleton = new SingletonExample7();
        }
        public SingletonExample7 getInstance() {
            return singleton;
        }
    }
}
```