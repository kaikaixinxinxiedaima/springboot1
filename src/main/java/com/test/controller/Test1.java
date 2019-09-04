package com.test.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.test.entity.Book;
import com.test.util.DateUtils;
import com.test.util.ShortUrl;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class Test1 {
    public static void main(String[] args) {
        int a = 60;
        int b = 13;

        System.out.println(a&b);
        System.out.println(a|b);
        System.out.println(a<<2);
        System.out.println(a>>2);
        System.out.println(a>>>2);//只有右移，空位补零
    }


    /**
     * 文件读写
     */
    static class FilrWrite{
        /**
         * 字节流读取文件
         */

        public static void InputStream() throws Exception {
            File file = new File("E:\\aaa.txt");
            InputStream inputStream = new FileInputStream(file);
            int k;
            StringBuffer str = new StringBuffer();
            byte[] bytes = new byte[inputStream.available()];

            if((k = inputStream.read(bytes)) != -1){
                str = str.append(new String(bytes));
            }

            inputStream.close();
            System.out.println(str);
        }

        /**
         * 字节流写入文件
         */

        public static void OuputStream() throws Exception {
            File file = new File("E:\\aaa.txt");
            OutputStream outputStream = new FileOutputStream(file);

            String text = "明天也是晴天";
            outputStream.write(text.getBytes());
            outputStream.close();
            InputStream();

        }

        /**
         * 字符流读取文件
         */
        public static void FileReader() throws Exception {
            File file = new File("E:\\aaa.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            StringBuffer str = new StringBuffer();
            String temp = "";
            while ((temp = bufferedReader.readLine()) != null){
                str = str.append(temp);
            }

            fileReader.close();
            bufferedReader.close();

            System.out.println(str);

        }

        /**
         * 字符流写入文件
         */
        public static void FileWriter() throws Exception {
            File file = new File("E:\\aaa.txt");
            FileWriter writer = new FileWriter(file);

            String text = "后天会更好";
            writer.write(text);
            writer.flush();//写入
            writer.close();//在关闭字符流时会强制性地将缓冲区中的内容进行输出

            FileReader();

        }



        public static void main(String[] args) throws Exception {
//            InputStream();

//            OuputStream();

//            FileReader();

            FileWriter();

            HashMap hashMap = new HashMap();
            hashMap.put(null,null);
            hashMap.put(null,null);

            Hashtable hashtable = new Hashtable();


            BigDecimal bigDecimal = new BigDecimal("1");
            BigDecimal add = bigDecimal.add(new BigDecimal(1));

            System.out.println(add);

            System.out.println(8<<1);


        }
    }


    static class Q11{

        static Stack<Integer> stack1 = new Stack<Integer>();
        static Stack<Integer> stack2 = new Stack<Integer>();


        public static void push(int node) throws Exception{
            stack1.push(node);

        }

        public static int pop() throws Exception{
            if(stack1.isEmpty() && stack2.isEmpty()){
                throw new RuntimeException("队列为空");
            }

            if(stack2.isEmpty()){
                while (!stack1.isEmpty()){
                    stack2.push(stack1.pop());
                }
            }

            return stack2.pop();

        }

        public static void a(){

        }

        public static Book b (){
            Book book = new Book();

            try {
                book.setBookName("1");
                return book;
            }catch (Exception e){
                book.setBookName("2");
                return book;
            }finally {
                book.setBookName("3");

            }
        }

        public static void main(String[] args) throws Exception {
            /*Thread[] ths = new Thread[3];
            for (int i = 0; i < 3; i++) {
                ths[i] = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int j = 0; j < 5; j++) {
                            System.out.print(j);
                        }
                        System.out.print(" ");
                    }
                });
            }
            for (Thread th : ths) {
                th.start();
            }*/


//            System.out.println( new String("111") == new String("111"));
//            System.out.println( new String("111").hashCode() == new String("111").hashCode());

            System.out.println("2".compareTo("2"));

        }

    }



    static class p{
        static int a = 0;
         int b = 0;

        private void test(){
             System.out.println("父类");
         }

        public static void main(String[] args) {
            System.out.println("哈哈");
        }
    }

    static class c extends p{

        private void test(){
            System.out.println("子类");
        }

        /*public static void main(String[] args) throws Exception {
            p.main(new String[2]);
            Book book = Book.class.getConstructor().newInstance();
            System.out.println(JSONObject.toJSONString(book));
        }*/

        public static void main(String[] args) {
//            p p = new c();
//            p.test();

            List list = new ArrayList();

            Book book = new Book();
            Map map = new HashMap();

            Set set = new HashSet();
            set.add(1);
            set.add(1);

            map.put(book,111);
            map.replace(book,222);

            Collections.synchronizedList(list);
            System.out.println(map.toString());


        }
    }


    static class listFile{
        public static void readFile(File[] files){
            if(files == null){
                return;
            }

            for (File file : files) {
                if(file.isFile()){
                    System.out.println(file.getName());
                }else if (file.isDirectory()){
                    readFile(file.listFiles());
                }
            }
        }

        public static void main(String[] args) {
//            File file = new File("E://");
//            readFile(file.listFiles());

            String a = "";
            String[] shortText = ShortUrl.ShortText("lcchuguo/p/4823425.html");
            for (String string : shortText) {
                a+=string;
            }
            System.out.println(a);
        }
    }



    static class LazySingleton{
        private LazySingleton instance;

        private LazySingleton(){

        }

        public LazySingleton getInstance(){
            if (instance == null){
                instance = new LazySingleton();
            }
            return instance;
        }
    }


    //计算器实现
    static class Math{
        public static synchronized double getResult(double numberA,double numberB,String operateType){
            double result = 0d;

            switch (operateType){
                case "+" :
                    result = numberA + numberB;
                    break;
                case "-" :
                    result = numberA - numberB;
                    break;
                case "*" :
                    result = numberA * numberB;
                    break;
                case "/" :
                    result = numberA / numberB;
            }

            return result;

        }

        public static void main(String[] args) {
           /* try {
                while (true){
                    Scanner scanner = new Scanner(System.in);

                    System.out.println("请输入数字A：");
                    String strA = scanner.nextLine();
                    System.out.println("请输入运算符：");
                    String type = scanner.nextLine();
                    System.out.println("请输入数字B：");
                    String strB = scanner.nextLine();

                    double result = Math.getResult(Double.valueOf(strA), Double.valueOf(strB), type);

                    System.out.println("结果：" + result);
                }
            } catch ( Exception e) {
                System.out.println("输入有误");
            }*/

          /* List<Double> list = new LinkedList<>();
           list.add(12d);
           list.add(22d);

            Collections.sort(list, new Comparator<Double>() {
                public int compare(Double arg0, Double arg1) {
                    return arg1.compareTo(arg0);
                }
            });
            System.out.println(list.toString());*/

//
//          String a = "111111";
//
//            System.out.println(a.length() > 5 ? a.substring(0,4) : a);





                double a = 9.998;
                double b = 2.998;
                double v = a / b;

                System.out.println(v);


        }
    }


    interface   a{
        static final int a = 1;
        void test();
    }
    interface   b{
        void test1();
    }


    interface c1 extends a,b{

    }


    static  class  aaaaaa{
        public static void main(String[] args) {
            Map map = new LinkedHashMap();

            for (int i = 0; i < 10; i++) {
                map.put(i+"",i+"");
            }

            int size = 10;
            int cyclesNum = 2;
            int num;
            int remainder = size%cyclesNum;

            if(size != 0) {
                num = (int)size/cyclesNum;
                if (size < cyclesNum) {
                    num += 1;
                } else {
                    if(remainder != 0) num += 1;
                }
                int count = 0;
                int listSize = cyclesNum;
                if(num == 1){//数据单分组
                    //保存数据
                    test(map);

                }else if(num > 1){//数据多分组
                    int a = 0;
                    HashMap map1 = new HashMap();

                    Iterator<Map.Entry<String, List<Map<String, Object>>>>  iterator = map.entrySet().iterator();
                    while (iterator.hasNext()){
                        Map.Entry<String, List<Map<String, Object>>> entry = iterator.next();
                        String key = entry.getKey();
                        System.out.println(key);


                        if(a >= cyclesNum){
                            test(map1);
                            a = 0;
                            map1.clear();
                            map1.put(entry.getKey(),entry.getValue());
                            a++;
                        }else{
                            map1.put(entry.getKey(),entry.getValue());
                            a++;
                        }


                    }

                    if(map1 != null && map1.size() > 0){
                        test(map1);
                    }
                }
            }
        }

        static void test(Map map){
            Iterator<Map.Entry<String, List<Map<String, Object>>>> iterator = map.entrySet().iterator();

            while (iterator.hasNext()){
                Map.Entry<String, List<Map<String, Object>>> next = iterator.next();
                System.out.println(next.toString());
            }
        }
    }


    static  class  aaaaaa1{
        public static void main(String[] args) {
            List<Date> list = new LinkedList<>();
//            list.add(DateUtils.addMonth(new Date(),-1));
//            list.add(DateUtils.addMonth(new Date(),-2));
//            list.add(DateUtils.addMonth(new Date(),0));

            System.out.println(test(list));

            Collections.sort(list, new Comparator<Date>() {
                public int compare(Date arg0, Date arg1) {
                    //按照kd1的更新时间进行降序排列
                    if(arg1.before(arg0)){
                        return -1;
                    }
                    if(arg1 == arg0){
                        return 0;
                    }
                    return 1;
                }
            });

            System.out.println(test(list));



        }


        public static String test(List<Date> list){
            String str = "";

            for (Date date : list) {
                str += DateUtils.dateToString(date,"yyyy-MM-dd HH:mm:ss") +" ";
            }
            return str;
        }
    }


    static  class  aaaaaa12 implements Runnable{

        volatile int a = 0;

        public static void main(String[] args) throws Exception {
//            for (int i = 0; i < 10; i++) {
//
//                Thread thread = new Thread(new aaaaaa12());
//                thread.start();
//            }

            List<String> list = new LinkedList<>();
            list.add("1");
            list.add("2");
            list.add("3");
            List<String> list1 = new LinkedList<>();

            list1.add(new String("4"));
            list1.addAll(list);
            list1.addAll(list);

            System.out.println(list1.toString());

        }

        public synchronized void test() throws InterruptedException {
            Thread.sleep(1000);
            int i = new Random().nextInt(10);
            a += i;
            System.out.println(a+"-"+DateUtils. getCurrentDateMillTime());
        }

        @Override
        public void run() {
            try {
                test();
            } catch (InterruptedException e) {
                e.printStackTrace();
                e.printStackTrace();
            }
        }
    }



    //双重检验锁
    static class Singleton{
        private static volatile Singleton singleton;

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

}
