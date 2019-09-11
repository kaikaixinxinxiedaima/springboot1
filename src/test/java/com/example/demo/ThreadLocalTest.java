package com.example.demo;

public class ThreadLocalTest {
    static class T1 implements Runnable{
        private static ThreadLocal<Integer> num = new ThreadLocal<>();

        @Override
        public void run() {
            Integer integer = num.get();
            for (int i = 0; i < 500; i++) {
                integer ++;
            }
            num.set(integer);
        }

        public static int getNum() {
            return num.get();
        }

        public static void main(String[] args) throws InterruptedException {
            T1 t1 = new T1();
            T1 t2 = new T1();

            Thread thread1 = new Thread(t1);
            Thread thread2 = new Thread(t2);

            thread1.start();
            thread2.start();

            while (thread1.isAlive() || thread2.isAlive()){

            }

            System.out.println(t1.getNum());

        }


    }
}
