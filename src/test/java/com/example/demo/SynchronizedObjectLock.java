package com.example.demo;


/**
 * 对象锁
 *      1、普通方法
 *      2、同步代码块(this/锁对象)
 */
public class SynchronizedObjectLock {
    static class Lock1 implements Runnable{
        static Lock1 lock = new Lock1();
        Object lockObj = new Object();

        @Override
        public void run() {
            synchronized (this){
                System.out.println("对象锁的代码块形式，我是：" + Thread.currentThread().getName());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "运行结束");
            }

        }

        public static void main(String[] args) {
            Thread thread = new Thread(lock);
            Thread thread1 = new Thread(lock);
            thread.start();
            thread1.start();

            while (thread.isAlive() || thread1.isAlive()){

            }

            System.out.println("The End");
        }
    }

    static class Lock2 implements Runnable{
        static Lock2 lock = new Lock2();
        @Override
        public void run() {
            test();
        }

        public synchronized void test(){
            System.out.println("对象锁的普通方法修饰符形式，我是：" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "运行结束");
        }

        public static void main(String[] args) {
            Thread thread = new Thread(lock);
            Thread thread1 = new Thread(lock);
            thread.start();
            thread1.start();

            while (thread.isAlive() || thread1.isAlive()){

            }

            System.out.println("The End");
        }
    }
}
