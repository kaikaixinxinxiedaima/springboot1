package com.example.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {

    static class T1 implements Runnable{

        @Override
        public void run() {
            if(Thread.currentThread().isDaemon()){
                System.out.println("当前守护线程："+Thread.currentThread().getName());

            }else{
                System.out.println("当前线程："+Thread.currentThread().getName());
            }
        }


        public static void main(String[] args) throws InterruptedException {

            ExecutorService executor = Executors.newFixedThreadPool(5);

            for (int i = 0; i < 10; i++) {
                T1 t1 = new T1();
                executor.execute(t1);
            }

            executor.shutdown();

            ////判断是否所有的线程已经运行完
            while (!executor.isTerminated()) {
            }

            System.out.println("所有线程执行完毕");
        }


    }
}


//administrator/git123456!


//admin/admin