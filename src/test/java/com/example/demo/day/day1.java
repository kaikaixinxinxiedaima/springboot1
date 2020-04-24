package com.example.demo.day;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class day1 {

    public static void main(String[] args) {
//        new Thread(() -> {
//            System.out.println("线程启动");
//        }).start();
//
//
//        List<Integer> list = new LinkedList<>();
//        list.add(1);
//        list.add(3);
//        list.add(5);
//        list.add(7);
//
//        Collections.sort(list, (a, b) -> b - a);
//
//        System.out.println(list.toString());

//        String test = "aabbcc";

//        System.out.println(test.matches(".*aa.*"));
//
//        System.out.println(Pattern.matches(".*aa.*", test));


//        Pattern p = Pattern.compile(".*aa.*");
//        Matcher matcher = p.matcher(test);
//        System.out.println(matcher.find());

//        System.out.println("11.2".matches("^[-\\+]?[.\\d]*$"));


//        String pattern = ".*aa.*";
//
//
//
//        boolean b = Pattern.matches(pattern, test);
//
//        System.out.println(b);



        String ip="127.0.0.266";
//
////        String pattern = "(\\d{1,3})(\\.(\\d{1,3})){3}";
//        String pattern = "(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})";
//        Pattern p=Pattern.compile(pattern);
//        Matcher m=p.matcher(ip);
//
//        if(m.find()) {
//            System.out.println("整个表达式："+m.group(0));
//            System.out.println("第1组："+m.group(1));
//            System.out.println("第2组："+m.group(2));
//            System.out.println("第3组："+m.group(3));
//            System.out.println("第4组："+m.group(4));
//        }


//        System.out.println(ip.matches("^((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$"));

//        String str = "This is only a" + " simple" + "test";
//
//        String s = str + "111";
//
//
//        Map map = new ConcurrentHashMap<>();
//        map.put(null,null);
//        map.put(null,null);
//        System.out.println(map);


//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 10L, TimeUnit.SECONDS,
//                new LinkedBlockingDeque<>(1000),
//                new ThreadPoolExecutor.AbortPolicy());
//
//        threadPoolExecutor.execute(() -> {
//            System.out.println(111111111);
//        });

//
//        float f1[][] = new float[6][6];
//
//        float []f2[] = new float[6][6];
//
//        float f3[][] = new float[][6];
//
//        float [][]f4 = new float[6][6];
//
//        float [][]f5 = new float[6][];

//        int[][] arr={{12,58},{56,78,41},{0}};
        int[][] arr= new int[1][1];

        for(int i=0;i<arr.length;i++){
            for(int j=0;j<arr[i].length;j++){
                System.out.print(arr[i][j]+"1  ");
            }
            System.out.println();
        }
        System.exit(1);
    }

    String a = "";

    public static void a(){
        new day1().b();
    }

    public void b(){
        String a = this.a;
        a();
    }
    public void day1(){
        String a = this.a;
        String ¥$a = "1";
    }
}

