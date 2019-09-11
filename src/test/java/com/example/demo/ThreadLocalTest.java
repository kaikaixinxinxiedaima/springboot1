package com.example.demo;

/**
 * ThreadLocal创建的变量只能被当前线程访问
 */
public class ThreadLocalTest {
    static class T1 implements Runnable{
        private static Integer num = new Integer(0);

        @Override
        public void run() {
            setNum();
        }

        public static void setNum() {
            for (int i = 0; i < 500; i++) {
                num ++;
            }
            System.out.println(num);
        }


        public static void main(String[] args) throws InterruptedException {
            T1 t1 = new T1();

            Thread thread1 = new Thread(t1);
            Thread thread2 = new Thread(t1);

            thread1.start();
            thread2.start();
        }


    }


    static class T2 implements Runnable{
        private static ThreadLocal<Integer> num = new ThreadLocal<Integer>() {
            public Integer initialValue() {
                return 0;
            }
        };

        @Override
        public void run() {
            setNum();
        }

        public static void setNum() {
            Integer integer = 0;
            for (int i = 0; i < 500; i++) {
                integer ++;
            }
            num.set(integer);

            System.out.println(num.get());
        }


        public static void main(String[] args) throws InterruptedException {
            T2 t2 = new T2();

            Thread thread1 = new Thread(t2);
            Thread thread2 = new Thread(t2);

            thread1.start();
            thread2.start();

        }
    }
}
