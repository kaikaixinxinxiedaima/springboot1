package com.example.demo.day;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class day8 {

    public static void sort(int[] arr){
        if(arr == null || arr.length == 0){
            return;
        }


        int length = arr.length;

        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - 1 - i; j++) {
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }

    }


    public static void quickSort(int[] arr, int start, int end){
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
            quickSort(arr, start, i-1);
            quickSort(arr, i+1, end);
        }




    };


    public static void main(String[] args) {
        int[] arr = {2,3,5,6,3,2,12,2};
//        sort(arr);
        quickSort(arr,0,arr.length-1);
        System.out.println(JSONArray.toJSONString(arr));

        String c = new StringBuffer("").reverse().toString().charAt(0)+"";
    }

    public class Singleton{
        private volatile Singleton singleton;

        private Singleton(){};

        public Singleton getSingleton(){
            if(singleton == null){
                synchronized (Singleton.class){
                    if(singleton == null){
                        synchronized (Singleton.class){
                            singleton = new Singleton();
                        }
                    }
                }
            }

            return singleton;
        }
    }


}
