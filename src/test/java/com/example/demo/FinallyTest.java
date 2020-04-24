package com.example.demo;

import com.google.common.base.Supplier;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Function;

public class FinallyTest {
    public static void main(String[] args) throws Exception {

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("测试线程");
            }
        }).start();


        new Thread(() -> {
            System.out.println("lambda测试线程");
        }).start();

        /*-------------------------------------*/

        List<String> list = Arrays.asList("aa", "bbb", "ccc", "ddddd");
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });

        System.out.println(list.toString());

        Collections.sort(list, (a,b)-> b.length() - a.length());
        System.out.println(list.toString());

        /*--------------------------------*/
        Supplier<String> stringSupplier = (Supplier<String>)() -> "哈哈";
        String s = stringSupplier.get();
        System.out.println(s);

        /*-------------------------------*/
        //无参无返回值
        Runnable r = () -> {
            System.out.println("hello");
        };

        r.run();
        /*--------------------------------*/
        //无参有返回值
        Callable c = new Callable() {
            @Override
            public Object call() throws Exception {
                return "hello";
            }
        };

        Callable c1 = () -> {
            return "hello";
        };

        Callable c2 = () -> "hello";

        System.out.println(c.call());
        System.out.println(c1.call());
        System.out.println(c2.call());
        /*------------------------------------*/
        //有参有返回值
        Function<Integer, Integer> f = a -> {
            int count = 0;
            for (int i = 0; i <= a; i++) {
                count += i;
            }
            return count;
        };

        System.out.println(f.apply(10));

        BiFunction<Integer, Integer, Integer> bf = (a, b) -> a + b;

        System.out.println(bf.apply(10, 20));
        /*--------------------------*/



    }
}

