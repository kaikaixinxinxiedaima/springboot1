package com.example.demo.day;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class day2 {

    @FunctionalInterface
    interface Test{
        String print(String str);
    }

    @FunctionalInterface
    interface Math{
        Double add(Double a, Double b);
    }


    public static void Print(String s,Test test){
        System.out.println("hello "+test.print(s));
    }

    public static void Print1(Double a, Double b ,Math math){
        System.out.println(a + " + " + b + " = " + math.add(a, b));
    }


    public static void main(String[] args) {
        String a = "张三";


//        Print(a,new Test() {
//            @Override
//            public String print(String str) {
//                return str;
//            }
//        });
//
//
//        Print(a, x -> x);
//        Print1(1d,2d, (x, y) -> x + y);
//
//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//        day2.a a1 = new a();
//        for (int i = 0; i < 2; i++) {
//            executorService.execute(new Runnable() {
//                @Override
//                public void run() {
//                    if(Thread.currentThread().getName().equals("pool-1-thread-2")){
//                            a1.test2();
//                    }else{
//                            a1.test();
//                    }
//                }
//            });
//        }


//        Class<day2> day2Class = day2.class;
//        try {
//            Constructor<day2> declaredConstructor = day2Class.getDeclaredConstructor();
//            day2 day2 = declaredConstructor.newInstance();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        Method[] methods = day2Class.getDeclaredMethods();
//        for (Method method : methods) {
//            System.out.println(method.getName());
//        }
//
//        Iterator<Map.Entry<Object, Object>> iterator = new HashMap<>().entrySet().iterator();


//        for (int i = 1; i < 10; i++) {
//            for (int j = 1; j <= i; j++) {
//                System.out.print(j + " * " + i + " = " + i*j);
//                if((i*j+"").length() == 1){
//                    System.out.print("  |");
//                }else{
//                    System.out.print(" |");
//                }
//            }
//            System.out.println(" ");
//        }
//
//        System.out.println("");
//
//        for (int i = 9; i >= 1; i--) {
//            for (int j = 1; j <= i; j++) {
//                System.out.print(j + " * " + i + " = " + i*j);
//                if((i*j+"").length() == 1){
//                    System.out.print("  |");
//                }else{
//                    System.out.print(" |");
//                }
//            }
//            System.out.println(" ");
//        }


/*        int a1 = 0;

        while (true){
            if(++a1 > 5){
                break;
            }else{
                System.out.println(a1);
            }
        }*/

        int[] arr = {3,4,5,6,7,8,9};
        int search = 6;
        int searchIndex = biSearch(arr, search);
        System.out.println(searchIndex);
    }


    public static int biSearch(int[] arr,int search){
        int start = 0;
        int end = arr.length -1;
        int mid;
        while (start <= end){
            mid = (start + end) / 2;
            if(arr[mid] == search){
                return mid;
            }else if(arr[mid] < search){
                start = mid + 1;
            }else{
                end = mid - 1;
            }

        }
        return -1;
    }


    static class a extends Thread{
        public  synchronized void test(){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("haha");
        }

        public  void test2(){
            System.out.println("hehe");
        }
    }


}
