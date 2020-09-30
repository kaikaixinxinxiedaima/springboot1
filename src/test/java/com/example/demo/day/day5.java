package com.example.demo.day;

import com.alibaba.fastjson.JSONArray;
import com.example.demo.ThreadPool;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

public class day5 {

    public static void main(String[] args) {
//        Map<String, String> a = new LinkedHashMap<>();
//
//        Map<String, String> i = test(a);
//
//        System.out.println(i);
//        HashMap<Object, Object> map = new HashMap<>();
//
//        for (int i = 0; i < 12 ; i++) {
//            map.put(i,i);
//        }
//
//        System.out.println(map);
//
//        map.put(12,12);
//        System.out.println(map);

//        ArrayList a = new ArrayList();
//        a.add("1");


//        int[] arr = {2,3,5,6,8,4,3,2,4,7,0};
//        sort(arr, 0 , arr.length -1);
//        System.out.println(JSONArray.toJSON(arr));

//        Integer a = 2000;
//        Integer b = 2000;
//        Integer a1 = 1;
//        Integer b1 = 1;
//        Integer.valueOf(a1);
//
//        System.out.println(a == b);
//        System.out.println(a1 == b1);
                Executors.newFixedThreadPool(2);

        System.out.println(Math.round(1.5));




    }




    public static void test1(){
//        this.test(new HashMap<>());
    }

    public static Map<String, String> test(Map<String, String> a){
        try {
            a.put("a", "a");
            return a;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            a.put("a", "b");
        }
        return null;
    }

    public static void sort(int[] arr, int start, int end){
        if(start < end){
            int i,j,k;
            i = start;
            j = end;
            k = arr[i];

            while(i < j){
                while (i < j && arr[j] > k){
                    j--;
                }
                if (i < j){
                    arr[i++] = arr[j];
                }
                while (i < j && arr[i] < k){
                    i++;
                }
                if (i < j){
                    arr[j--] = arr[i];
                }

            }

            arr[i] = k;
            sort(arr,start,i-1);
            sort(arr,i+1,end);
        }
    }

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


}

