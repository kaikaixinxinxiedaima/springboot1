package com.test.controller;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;
import java.util.Stack;

//算法
public class Algorithm123 {
    /**
     * 冒泡排序
     */
    static class Q1{
        public static void sort(int[] arr){
            if(arr == null || arr.length == 0){
                return;
            }

            int length = arr.length;

            for (int i = 0; i < length-1; i++) {
                for (int j = 0; j < length-1-i; j++) {
                    if(arr[j] > arr[j+1]){
                        int temp = arr[j];
                        arr[j] = arr[j+1];
                        arr[j+1] = temp;
                    }
                }
            }
        }


        public static void main(String[] args) {
            int[] arr = {2,5,7,9,3,2,9,10};

            System.out.println(JSONObject.toJSONString(arr));
            sort(arr);
            System.out.println(JSONObject.toJSONString(arr));

        }

    }

    /**
     * 快速排序
     */
    static class Q2{
        /**
         * @param arr 排序数组
         * @param start 开始位置
         * @param end 结束位置
         */
        public static void quickSort(int[] arr,int start,int end){
            if(start < end){
                int i,j,k;
                i = start;
                j = end;
                k = arr[i];

                while (i < j){
                    while (i < j && arr[j] > k){
                        j--;
                    }
                    if(i < j){
                        arr[i++] = arr[j];
                    }
                    while (i < j && arr[i] < k){
                        i++;
                    }
                    if(i < j){
                        arr[j--] = arr[i];
                    }
                }

                arr[i] = k;
                quickSort(arr,start,i-1);
                quickSort(arr,i+1,end);

            }
        }

        public static void main(String[] args) {
            int[] arr = {12,2,5,7,9,3,2,9,10};

            System.out.println(JSONObject.toJSONString(arr));
            quickSort(arr,0,arr.length-1);
            System.out.println(JSONObject.toJSONString(arr));

        }



    }

    /**
     * 用两个栈来实现一个队列，完成队列的Push和Pop操作
     */
    static class Q3{
        static Stack<Integer> stack1 = new Stack<Integer>();
        static Stack<Integer> stack2 = new Stack<Integer>();

        public static void push(int node) {
            stack1.push(node);
        }

        public static int pop() {
            if(stack1.empty() && stack2.empty()){
                throw new RuntimeException("Queue is empty!");
            }
            if(stack2.empty()){
                while(!stack1.empty()){
                    stack2.push(stack1.pop());
                }
            }
            return stack2.pop();
        }

        public static void main(String[] args) {
            push(1);
            push(2);
            push(3);
            System.out.println(pop());
            System.out.println(pop());
            System.out.println(pop());
        }
    }

    /**
     * 现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
     */
    static class Q4{
        public static int Fibonacci(int n) {
            int a=1,b=1,c=0;
            if(n==1||n==2){
                return 1;
            }else{
                for (int i=3;i<=n;i++){
                    c=a+b;
                    a=b;
                    b=c;
                }
                return c;
            }
        }

        public static void main(String[] args) {
            System.out.println(Fibonacci(5));
        }
    }

    /**
     * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
     * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0
     */
    static class Q5{
        public static int MoreThanHalfNum_Solution(int [] array) {
            if(array == null || array.length == 0){
                return 0;
            }

            int count = 0;
            Arrays.sort(array);
            int num = array[array.length / 2];
            for (int i : array) {
                if(num == i){
                    count ++;
                }
            }

            if(count<=(array.length/2)){
                num=0;
            }
            return num;
        }

        public static void main(String[] args) {
            int[] arr = {3,4,5,6,7,5,4,3,4,3,4,3,43,3,3,3,3,3,3,3,3};
            System.out.println(MoreThanHalfNum_Solution(arr));
        }
    }

    /**
     * 在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置, 如果没有则返回 -1（需要区分大小写）.
     */
    static class Q6{
        public static int FirstNotRepeatingChar(String str) {
            for (int i = 0; i < str.length(); i++) {
                if(str.indexOf(String.valueOf(str.charAt(i))) == str.lastIndexOf(String.valueOf(str.charAt(i)))){
                    return i;
                }
            }
            return -1;
        }

        public static void main(String[] args) {
            String a = "aAa";
            System.out.println(FirstNotRepeatingChar(a));
        }
    }


}
