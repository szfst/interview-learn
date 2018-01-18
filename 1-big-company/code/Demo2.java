package jvm;

public class Demo2 {
    public static void main(String[] args) {
        System.out.println(Demo2.class.getClassLoader());
        ClassLoader classLoader = Demo2.class.getClassLoader();
        while (classLoader !=null){
            System.out.println(classLoader);
            classLoader = classLoader.getParent();
        }
        System.out.println(classLoader);
    }
}
