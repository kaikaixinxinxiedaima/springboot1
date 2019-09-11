package com.example.demo;

public class ThreadPriority {
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
            T1 t1 = new T1();

            Thread thread1 = new Thread(t1);
            Thread thread2 = new Thread(t1);
            Thread thread3 = new Thread(t1);
            //设置优先级
//
//            thread1.setPriority(Thread.MIN_PRIORITY);
//            thread2.setPriority(Thread.NORM_PRIORITY);
//            thread3.setPriority(Thread.MAX_PRIORITY);


            //设置守护线程（先设置，后启动）
            thread1.setDaemon(true);
            thread1.start();

            thread2.start();
            thread3.start();
        }


    }
}
