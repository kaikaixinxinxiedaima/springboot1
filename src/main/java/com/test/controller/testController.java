package com.test.controller;

import com.alibaba.fastjson.JSONObject;
import com.test.annotation.AnnotationFactory;
import com.test.entity.Book;
import com.test.exception.MyException;
import com.test.util.DateUtils;
//import com.test.vo.RedisConf2;
import com.test.vo.RedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.lang.reflect.Constructor;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.DoubleUnaryOperator;
import java.util.stream.IntStream;

@RestController
public class testController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Serializable> redisCacheTemplate;


    @Autowired
    private RedisConfig properties;
//
//    @Autowired
//    private RedisConf2 properties2;
//
//    @Value("${sheshou}")
//    private String sheshou;
//
//
    @RequestMapping("/lol")
    public String test1(){
        return JSONObject.toJSONString(properties.getCluster().get("nodes"));
    }


    public static void main(String[] args) throws Exception {


        char aaaa = '哈';

        int[] arr = {2,4,1,9,7,6};

        int n = arr.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i -1; j++) { // -1是为了防止溢出
                if(arr[j] < arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }

        for (int i = 0; i < arr.length; i++) {
            //System.out.print(arr[i] + " ");
        }
        HashMap hashtable = new HashMap();
        hashtable.put("aa",null);
        hashtable.put("bb",null);
        hashtable.hashCode();

        //System.out.println(hashtable.hashCode());

        //Book book = (Book) Class.forName("com.test.entity.Book").newInstance();

        //Class<Book> bookClass = Book.class;
        //System.out.println(JSONObject.toJSONString(book) + "1111");


        Book book = AnnotationFactory.createBook();

        String _$aaa11 = "aaa";
        long a = 100000L;
        float f1 = 234.5F;
        double d1;
        Double aa = 123D;
        char letter = '嗯';

//        System.out.println(JSONObject.toJSONString(book));

//        System.out.println(FreshJuice.FreshJuiceSize.LARGE);

        int c = 10;

//        System.out.println(++c);
//        System.out.println(++c);


//        int a1 = 60,b1 = 13;

//        System.out.println(a1 > b1 && a1 >b1);


//        switch (1){
//            case 1:
//                System.out.println(1);
//
//            default:
//                System.out.println(0);
//        }

//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
//        System.out.println(simpleDateFormat.format(new Date()));

//        Calendar calendar= Calendar.getInstance();
//        calendar.set(2009, 6 - 1, 12);
//        long timeInMillis = calendar.getTimeInMillis();
//        System.out.println(calendar.get(Calendar.MONTH)+1);


//        char c1;
//        String s;
//        // 使用 System.in 创建 BufferedReader
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("输入字符, 按下 'q' 键退出。");
//        // 读取字符
//        do {
////            c1 = (char) br.read();
//            s =  br.readLine();
//            System.out.println(s);
//        } while (!s.equals("q"));

//        try {
//            File file = new File("E:\\spring.log");
//            FileInputStream inputStream = new FileInputStream(file);
//            InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
//            BufferedReader breader=new BufferedReader(reader);
//
//            StringBuffer sb = new StringBuffer();
//            while (reader.ready()) {
//                sb.append((char) reader.read());
//                // 转成char加到StringBuffer对象中
//            }
//            System.out.println(sb.toString());
//            reader.close();
//            // 关闭读取流
//
//            inputStream.close();
//            // 关闭输入流,释放系统资源
//
//
//
//            File file1 = new File("E:\\aaa.txt");
//
//            FileOutputStream fop = new FileOutputStream(file1);
//            // 构建FileOutputStream对象,文件不存在会自动新建
//
//            OutputStreamWriter writer = new OutputStreamWriter(fop, "UTF-8");
//
//            writer.write(sb.toString());
//
//            writer.flush();
//
//            writer.close();
//
//            fop.close();
//
//
//
//
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }


        //自定义异常

       /* try {
            JSONObject object = new JSONObject(true);
            object.put("code","111");
            object.put("msg","自定义异常");

            throw new MyException(object);

        }catch (Exception e){
            if (e instanceof MyException){
                JSONObject object = ((MyException) e).getObject();
                System.out.println(object);
            }else{
                e.printStackTrace();
            }
        }*/

//         SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//
//        System.out.println(simpleDateFormat.format(new Date()));

       /* String str1 = "hello";
        String str2 = "hello" + new String("");

        System.out.println(str1.equals(str2));
*/

//        String[] a1 = new String[2];
//
//        System.out.println(a1.length);
//
//        int[] a2 = new int[2];
//
//        System.out.println(a2.length);


//        Thread t = new Thread() {
//
//            public void run() {
//                pong();
//            }
//        };
//
//        t.start();
//        System.out.print("ping");
//
//        new Thread(new aa()).start(); Math.pow(2, 31) - 1
//        int aaa = (int)(Math.pow(2, 31) - 1);
//        System.out.println(aaa + 1);
//        System.out.println(aaa > aaa + 1);

//        double s = 0.33333;
//
//        System.out.println(0d/0);


    /*    //todo 创建对象的四种方式

        //1、使用new关键字
        Book book1 = new Book();
        System.out.println(book1);

        //2、使用反射机制
        //使用Class类的newInstance方法
        Book book2 = (Book) Class.forName("com.test.entity.Book").newInstance();
        System.out.println(book2);
        Book book3 = Book.class.newInstance();
        System.out.println(book3);

        //使用Constructor类的newInstance方法
        Constructor<Book> constructor = Book.class.getConstructor();
        Book book4 = constructor.newInstance();
        System.out.println(book4);

        //3、使用clone方法
        //无论何时我们调用一个对象的clone方法，jvm就会创建一个新的对象，将前面对象的内容全部拷贝进去。用clone方法创建对象并不会调用任何构造函数。
        //要使用clone方法，我们需要先实现Cloneable接口并实现其定义的clone方法。

        Book book5 = (Book) book4.clone();
        System.out.println(book5);
        System.out.println("原对象与克隆对象是否相同："+(book4 == book5));

        //4、用对象流来实现 前提是对象必须实现 Serializable
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                new FileOutputStream(filename));
        objectOutputStream.writeObject(book5);
        ObjectInput input=new ObjectInputStream(new FileInputStream(filename));
        Book book6 = (Book) input.readObject();
        System.out.println(filename);
        System.out.println(book6);
        System.out.println("原对象与复制对象是否相同："+(book6 == book5));*/

       /* ArrayList arrayList = new ArrayList();
        System.out.println(arrayList.size());*/


       /* try {
            throw new FileNotFoundException();

        } catch (FileNotFoundException ex) {

            System.out.print("FileNotFoundException!");

        } catch (IOException ex) {

            System.out.print("IOException!");

        } catch (Exception ex) {

            System.out.print("Exception!");

        }*/


        System.out.println("5" + 2);
    }


    private static String filename = Book.class.getResource("").getPath()
            + "/obj.txt";
    static File file = new File(filename);
    static {
        if (!file.exists())
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

    }


    static void pong() {

        System.out.print("pong");

    }

    static class FreshJuice {
        enum FreshJuiceSize{ SMALL, MEDIUM , LARGE }
    }

    static class aa implements Runnable{

        @Override
        public void run() {

        }
    }

    static class NULL{
        public static void haha(){
            System.out.println("haha");
        }

        public static void main(String[] args) {
            ((NULL)null).haha();
        }
    }


    static class HelloA {

        public HelloA() {
            System.out.println("HelloA");
        }

        {
            System.out.println("I'm A class");
        }

        static {
            System.out.println("static A");
        }


    }

    static class HelloB extends HelloA {
        public HelloB() {
            System.out.println("HelloB");
        }

        {
            System.out.println("I'm B class");
        }

        static {
            System.out.println("static B");
        }

        //对象的初始化顺序：
        // （1）类加载之后，按从上到下（从父类到子类）执行被static修饰的语句；
        // （2）当static语句执行完之后,再执行main方法；
        // （3）如果有语句new了自身的对象，将从上到下执行构造代码块、构造器（两者可以说绑定在一起）。
        // （4）static初始化块不能访问非statci成员，也不能调用非static方法，并且只在类加载时执行一次。
        public static void main(String[] args) {
            new HelloB();

            new HelloB();


        }
    }


    static class Example {

        String str = new String("good");

        char[] ch = { 'a', 'b', 'c' };

        public static void main(String args[]) {

//            Example ex = new Example();
//
//            ex.change(ex.str, ex.ch);
//
//            System.out.print(ex.str + " and ");
//
//            System.out.print(ex.ch);

            System.out.println(Example.getValue(2));

        }

        public void change(String str, char ch[]) {

            str = "test ok";

            ch[0] = 'g';

        }

        public static int getValue(int i) {
            int result = 0;
            switch (i) {
                case 1:
                    result = result + i;
                    //break;
                case 2:
                    result = result + i * 2;
                    //break;
                case 3:
                    result = result + i * 3;
                    //break;

            }
            return result;
        }
    }


    /**
     * 下面程序的运行结果是什么？
     * 关于 没有加载到 子类的属性的问题
     * （这里针对 第一次走 父类的构造方法而言）
     * 第一点 可以明确的是 跟 这个变量 是私有定义 没关系
     * 第二点 是 这里第一次 一直是走的父类的构造方法 ，只是因为父类的构造方法里面的调用的某个方法tellName() 被子类重写
     * 所以不会调用父类的 tellName()方法
     * 至于这个tellName()方法里面输出的name属性 是null 是因为这个时候 加载的是一个非静态属性 是 加载的name变量的默认初始值
     * 有关类中的相关 属性 方法 (静态和非静态)的加载顺序，可以看下这个url里面讲解的内容.
     */
    static class Dervied extends Base {
        private String name1 = "dervied";

        public Dervied() {
            tellName();
            printName();
        }

        public void tellName() {
            System.out.println("Dervied tell name: " + name1);
        }

        public void printName() {
            System.out.println("Dervied print name: " + name1);
        }

        public static void main(String[] args){

            new Dervied();
        }

        static {
            System.out.println("Dervied static");
        }
    }
    static class Base {
        static {
            System.out.println("Base static");
        }

        private static String value = "base";
        private String name = "base";

        public Base() {
            tellName();
            printName();
        }

        {
            System.out.println("daimakuai");

        }

        public void tellName() {
            System.out.println("Base tell name: " + name);
        }

        public void printName() {
            System.out.println("Base print name: " + name);
        }
    }
    /******************************************************************/




}
