package com.test.controller;

import java.util.Arrays;
import java.util.Random;

/**
 * 快速排序
 */
public class QuickSort {
    public static void main(String[] args) {
        int arr[] = new int[5];

        for (int i = 0; i < arr.length; i++) {
            Random random = new Random();
            arr[i] = random.nextInt(10);

        }

        System.out.println("排序前数组:" + Arrays.toString(arr));

        quickSort(arr,0,arr.length-1);

        System.out.println("排序后数组:" + Arrays.toString(arr));
    }

    //快速排序开始
    public static void quickSort(int[] arr,int left,int right){
        //该值定义了从哪个位置开始分割序列
        int pivot = 0;

        if(left < right){
            //partition方法对序列进行排序
            pivot = partition(arr, left, right);

            //分割两个序列继续进行快排操作
            quickSort(arr, left, pivot - 1);

            quickSort(arr, pivot + 1, right);
        }

    }

    private static int partition(int[] arr, int left, int right) {
        //取每个序列的第一个值作为基准值
        int pivotkey = arr[left];

        while (left < right) {
            //从序列的右边开始往左遍历，直到找到小于基准值的元素
            while (right > left && arr[right] >= pivotkey) {
                right--;
            }

            //将元素直接赋予给左边第一个，即pivotkey所在的位置
            arr[left] = arr[right];

            //a[right] = pivotkey;
            //从序列的左边边开始往右遍历，直到找到大于基准值的元素
            while (right > left && arr[left] <= pivotkey) {
                left++;
            }

            //此时的a[right]<pivotkey,已经被赋予到左边，所以可以将元素赋予给a[right]
            arr[right] = arr[left];
        }

        //最后的left是基准值所在的位置
        arr[left] = pivotkey;

        return left;
    }
}
