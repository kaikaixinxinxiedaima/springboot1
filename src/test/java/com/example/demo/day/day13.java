package com.example.demo.day;

import io.swagger.models.auth.In;
import org.springframework.util.SerializationUtils;

import java.util.Stack;
import java.util.concurrent.CountDownLatch;

public class day13 {

    /**
     * 用两个栈来实现一个队列，完成队列的 Push 和 Pop 操作。
     */

    static class test1{
        static Stack<Integer> in = new Stack<Integer>();
        static Stack<Integer> out = new Stack<Integer>();

        public static void push(Integer a){
            in.push(a);
        }

        public static Integer pop(){
            if(out.isEmpty())
                while (!in.empty())
                    out.push(in.pop());

            if (!out.isEmpty())
                return out.pop();

            return null;
        }

    }


    /**
     * 求斐波那契数列的第 n 项，n <= 39。
     *
     * 0                     n=0
     * 1                     n=1
     * f(n−1)+f(n−2)         n>1
     *
     */
     static class test2{
         //递归方式
         public static int test2_1(int n){
            if (n <= 1)
                return n;

            int[] rst = new int[n + 1];
            rst[1] = 1;

            for (int i = 2; i <= n; i++) {
                rst[i] = rst[i - 1] + rst[i - 2];
            }

            return rst[n];
        }

        //斐波那契数列优化
        public static int test2_2(int n){
            if (n <= 1)
                return n;

            int f1 = 0;
            int f2 = 1;
            int t = 0;

            for (int i = 2; i <= n; i++) {
                t = f2;
                f2 = f1 + f2;
                f1 = t;
            }

            return f2;
        }
    }


    /**
     * 把一根绳子剪成多段，并且使得每段的长度乘积最大
     * 输入: 2
     * 输出: 1
     * 解释: 2 = 1 + 1, 1 × 1 = 1。
     *
     * 输入: 10
     * 输出: 36
     * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
     */

    public static int test3(int n){
        int[] rst = new int[n + 1];
        rst[1] = 1;

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++){
                rst[i] = Math.max(rst[i], j * (i - j));
                rst[i] = Math.max(rst[i], rst[j] * (i - j));
            }
        }

        return rst[n];
    }


    public static void main(String[] args) {

//        test1.push(1);
//        test1.push(2);
//        test1.push(3);
//        test1.push(4);
//
//        for (int i = 0; i < 5; i++) {
//
//            System.out.println(test1.pop());
//        }

//        int i = test2.test2_1(5);
//        System.out.println(i);


//        int i = test3(10);
//        System.out.println(i);

//        CountDownLatch


//        byte[] s = SerializationUtils.serialize("李福");
//        String s1 = new String(s);
//        System.out.println(s1);

//        A a = new A();
//        A a1 = new A();
//        System.out.println(a == a1);

    }



    static class A{
        private A(){

        }
    }
}
