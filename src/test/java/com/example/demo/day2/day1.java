package com.example.demo.day2;

import com.alibaba.fastjson.JSONArray;

public class day1 {

    public static void test(int[] arr, int start ,int end){
        if(start < end){
            int i,j,k;
            i = start;
            j = end;
            k = arr[i];

            while (i < j){
                while (i < j && arr[i] < k){
                    i++;
                }
                if(i < j){
                    arr[j--] = arr[i];
                }

                while (i < j && arr[j] > k){
                    j--;
                }
                if(i < j){
                    arr[i++] = arr[j];
                }

            }
            arr[i] = k;
            test(arr,start, i-1);
            test(arr,i+1,end);
        }
    }

    public static void main(String[] args) {
        int[] arr = {2,34,32,6,78,4,2,3};
        int i = arr.length - 1;
        test(arr,0,i);

        System.out.println(JSONArray.toJSONString(arr));
    }
}
