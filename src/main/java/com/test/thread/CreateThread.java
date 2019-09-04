package com.test.thread;

import java.util.concurrent.*;

public class CreateThread {
    //第一种方式
    static class T1 implements Runnable{

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("线程1运行--" + i);
                try {
                    if(i == 5){
                        Thread.yield();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


    //第二种方式
    static class T2 extends Thread{

        public void run() {

            for (int i = 0; i < 10; i++) {
                System.out.println("线程2运行--" + i);
            }
        }
    }


    //第三种方式 , 又返回值的线程
    static class T3 implements Callable<Integer>{

        @Override
        public Integer call() throws Exception {
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName()+" "+i);
            }
            return null;
        }
    }


    public static void main(String[] args) {
//        Thread one = new Thread(new T1(), "one");
//        one.start();
//
//        T2 two = new T2();
//        two.start();
//
//        T3 t3 = new T3();
//        FutureTask<Integer> ft = new FutureTask<>(t3);
//        Thread three = new Thread(ft, "又返回值线程");
//        three.start();


        //线程池
        ExecutorService executorService =
                new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS,
                        new ArrayBlockingQueue<Runnable>(5));
        executorService.execute(new T1());
    }
}
