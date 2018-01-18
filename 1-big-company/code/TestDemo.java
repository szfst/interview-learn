package jvm;

public class TestDemo {
    public TestDemo() {
        System.out.println("a demo"+ TestDemo.class.getClassLoader());
    }

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
}
