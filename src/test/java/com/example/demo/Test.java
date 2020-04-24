package com.example.demo;


import com.alibaba.druid.sql.visitor.functions.Char;
import org.hibernate.annotations.Synchronize;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

public class Test {
    public static void main(String[] args) throws Exception {
        String filePath = "D://work//项目管理//开发环境//IDEA/11111.7z";
        String outFilePath = "D://work//项目管理//开发环境//IDEA/22222.7z";

//        FileInputStream fileInputStream = new FileInputStream(filePath);
//        FileOutputStream fileOutputStream = new FileOutputStream(outFilePath);
//
//        byte[] bytes = new byte[2014];
//        int length = 0;
//        while ((length = fileInputStream.read(bytes)) > 0){
//            fileOutputStream.write(bytes, 0, length);
//        }
//
//        fileInputStream.close();
//        fileOutputStream.close();

//        FileChannel fileChannel = new FileInputStream(filePath).getChannel();
//        FileChannel outFileChannel = new FileOutputStream(outFilePath).getChannel();
//
//        for (long i = fileChannel.size(); i > 0;) {
//            long transfer = fileChannel.transferTo(fileChannel.position(), i, outFileChannel);
//            i -= transfer;
//        }


//        test(filePath, outFilePath);


        Singleton singleton = Singleton.getSingleton();
        Singleton singleton1 = Singleton.getSingleton();

        System.out.println(singleton == singleton1);

    }

    private static void test(String... path) {
        Arrays.stream(path).forEach(System.out::println);

        System.out.println(path.toString());
    }



}

/*
Java 8 对接口做了进一步的增强。
    a. 在接口中可以添加使用 default 关键字修饰的非抽象方法。即：默认方法（或扩展方法）
    b. 接口里可以声明静态方法，并且可以实现。
 */

@FunctionalInterface
interface testInterface{
    static void a(){
        System.out.println(111111);
    }
    default void a1(){
        System.out.println(111111);
    }

    void insert();
}

/*
    双重校验锁
 */

class Singleton{
    private static volatile Singleton singleton = null;
    private Singleton(){

    }

    public static Singleton getSingleton(){
        if(singleton == null){
            synchronized (Singleton.class){
                if(singleton == null){
                    singleton = new Singleton();
                }
            }

        }

        return singleton;
    }
}