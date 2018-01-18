package jvm;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

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

    @Override
    public String toString() {
        return "MyClassLoader{" +
                "path='" + path + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    //获取我们.class文件的字节数组
    private byte[] readClassFileToByteArray(String name) {
        InputStream is = null;
        byte[] returnData = null;
        //windows 环境
        name = name.replaceAll("\\.","/");
        String filePath = this.path+name+".class";
        File file = new File(filePath);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            is = new FileInputStream(file);
            int tmp = 0;
            while((tmp = is.read()) != -1){
                os.write((tmp));
            }
            returnData = os.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                is.close();
                os.close();
            }catch (Exception e2){

            }
        }




        return returnData;
    }

}
