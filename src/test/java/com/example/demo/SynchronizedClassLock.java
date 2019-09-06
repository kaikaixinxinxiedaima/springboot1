package com.example.demo;

/**
 * 类锁
 *     synchronized加载static方法上
 *     synchronized（*.class）
 */
public class SynchronizedClassLock {
    static class Lock1 implements Runnable{
        static Lock1 lock = new Lock1();
        static Lock1 lock2 = new Lock1();

        @Override
        public void run() {
            test();
        }

        public synchronized static void test(){
            System.out.println("对象锁的代码块形式，我是：" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "运行结束");

        }

        public static void main(String[] args) {
            Thread thread = new Thread(lock);
            Thread thread1 = new Thread(lock2);
            thread.start();
            thread1.start();

            while (thread.isAlive() || thread1.isAlive()){

            }

            System.out.println("The End");
        }
    }

    static class Lock2 implements Runnable{
        static Lock2 lock = new Lock2();
        static Lock2 lock2 = new Lock2();

        @Override
        public void run() {
            test();
        }

        public  static void test(){
            synchronized(Lock2.class){
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
            Thread thread1 = new Thread(lock2);
            thread.start();
            thread1.start();

            while (thread.isAlive() || thread1.isAlive()){

            }

            System.out.println("The End");
        }
    }
}
