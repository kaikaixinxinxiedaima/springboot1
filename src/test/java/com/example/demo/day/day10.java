package com.example.demo.day;

import java.util.concurrent.locks.ReentrantLock;

public class day10 {
    static class A{

        static {
            System.out.println(1);
        }

        public A(){
            System.out.println(2);
        }
    }

    static class B extends A{

        static {
            System.out.println("a");
        }

        public B(){
            System.out.println("b");
        }
    }

    public static void main(String[] args) {
//        A ab = new B();
//        ab = new B();


    }

    public static void test1(){
        final String a = "";

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(a);
            }
        }).start();


    }
}
