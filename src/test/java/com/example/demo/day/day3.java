package com.example.demo.day;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class day3 {
    //二分法
    public static int biSearch(int[] arr, int search) {
        int start = 0;
        int end = arr.length - 1;
        int mid;
        while (start <= end) {
            mid = (start + end) / 2;
            if (arr[mid] == search) {
                return mid;
            } else if (arr[mid] < search) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }

        }
        return -1;
    }

    //冒泡
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }

        int length = arr.length;

        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }


    //快排
    public static void quickSort(int[] arr, int start, int end) {
        if(arr == null || arr.length == 0){
            return;
        }

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

    }


    //快排2
    public static List<Integer> quickSort2(Integer[] arr) {
        if(arr == null || arr.length == 0){
            return null;
        }

        List<Integer> leftArr = new LinkedList<>();
        List<Integer> rightArr = new LinkedList<>();

        int q = arr[0];

        for (int i = 0; i < arr.length; i++) {
            if(arr[i] > q){
                rightArr.add(arr[i]);
            }else{
                leftArr.add(arr[i]);
            }
        }

        quickSort2(leftArr.toArray(new Integer[leftArr.size()])).addAll(quickSort2(rightArr.toArray(new Integer[rightArr.size()])));
        return leftArr;
    }


    public static void main(String[] args) {
//        int[] a = {3, 4, 5, 7, 86, 4, 3, 2, 2};
//        Integer[] b = {3, 4, 5, 7, 86, 4, 3, 2, 2};
////        bubbleSort(a);
////        quickSort(a, 0, a.length - 1);
//
//
//        List<Integer> list = quickSort2(b);
//        System.out.println(JSONArray.toJSON(list));


//        String s = JSON.toJSONString(new User());
//        System.out.println(s);


//        int[] a = {3, 4, 5, 7, 86, 124};
//        System.out.println(biSearch(a,7));

        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("a",1);
        hashMap.put("a",1);
        hashMap.put("a",1);
        hashMap.put("a",1);
        hashMap.put("a",1);
        hashMap.put("a",1);
        hashMap.put("a",1);
        hashMap.put("a",1);
        hashMap.get("a");
        String sadsd = "0";

        if(StringUtils.isNotBlank(sadsd) && Double.valueOf(sadsd).doubleValue() != 0){
            System.out.println(111111111);
        }else{
            System.out.println(22222);
        }
    }




    static class User implements Serializable {
        private int id = 1;
        private String name = "张三";

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }




}



