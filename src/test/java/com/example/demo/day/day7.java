package com.example.demo.day;


/**
 * 算法
 */
public class day7 {
    /**
     * 爬楼梯
     * @param a 楼体数量
     * @param b 最大步伐
     * @return c 方案数
     */
    public static int test1(int a, int b){//递归方式

        if(a == 0){
            return 1;
        }
        int c = 0;

        if(a >= b){
            for (int i = 1; i <= b; i++) {
                c+=test1(a-i, b);
            }
        }else{
            c+=test1(a, a);

        }

        return c;
    }


    public static int test2(int a){//斐波那契数列
        if(a == 1 || a == 2){
            return a;
        }

        int n1 = 1;
        int n2 = 2;
        for (int i = 3; i <= a; i++) {
            int temp = n2;
            n2 = n1 + n2;
            n1 = temp;
        }

        return n2;
    }


    public static void main(String[] args) {
//        int a = 5;
//        int b = 2;
//        int i = test2(a);
//        System.out.println(i);
//        new bb().test();

//        aa aa = new cc();
    }




}
