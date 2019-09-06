package com.example.demo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class VolatileTest {
    static class T1{
       private int num = 0;

       public void add(){
           try {
               Thread.sleep(100);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           synchronized (this){
               num++;
           }
       }

       public int getNum() {
           return num;
       }

       public static void main(String[] args) {
           final T1 volatileTest = new T1();
           for (int i = 0; i < 500; i++) {
               new Thread(new Runnable() {
                   @Override
                   public void run() {
                       volatileTest.add();
                   }
               }).start();
           }

           while (Thread.activeCount() > 2){
               Thread.yield();
           }

           System.out.println("num:" + volatileTest.getNum());
       }
   }

    static class T2{
        //可重入锁
        private Lock lock = new ReentrantLock();
        private int num = 0;

        public void add(){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            lock.lock();

            try{
                num++;
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }

        public int getNum() {
            return num;
        }

        public static void main(String[] args) {
            final T2 volatileTest = new T2();
            for (int i = 0; i < 500; i++) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        volatileTest.add();
                    }
                }).start();
            }

            while (Thread.activeCount() > 2){
                Thread.yield();
            }

            System.out.println("num:" + volatileTest.getNum());
        }
    }


    static class T3 implements Runnable{
        static int a = 0;
        static T3 t = new T3();
        public static void main(String[] args) throws Exception {
            Thread thread = new Thread(t);
            Thread thread1 = new Thread(t);
            thread.start();
            thread1.start();
            thread.join();
            thread1.join();
            System.out.println(a);
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000000; i++) {
                a++;
            }
        }
    }
}
