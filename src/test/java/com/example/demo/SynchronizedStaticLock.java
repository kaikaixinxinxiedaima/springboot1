package com.example.demo;

/**
 * 比较复杂的一种情况，
 * 同时访问静态synchronized方法和非静态synchronized方法
 * 结果：因为锁不同，所以相互之前不影响
 */
public class SynchronizedStaticLock {
    static class Lock1 implements Runnable{
        static Lock1 lock = new Lock1();

        @Override
        public void run() {
            if(Thread.currentThread().getName().equals("Thread-0")){
                test1();
            }else{
                test2();
            }
        }


        public synchronized static void test1(){
            System.out.println("静态synchronized方法，我是：" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "运行结束");
        }

        public synchronized void test2(){
            System.out.println("非静态synchronized方法，我是：" + Thread.currentThread().getName());
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
