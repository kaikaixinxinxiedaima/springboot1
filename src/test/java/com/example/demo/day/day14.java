package com.example.demo.day;

import com.alibaba.fastjson.JSONArray;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


public class day14 {

    static class ListNode{
        int val;
        ListNode next;

        ListNode(int val){
            this.val = val;
        }
    }

    /**
     * 反转链表(单向链表)
     * @param head
     */
    public static ListNode reverseList(ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        ListNode next = head.next;
        head.next = null;
        ListNode listNode = reverseList(next);
        next.next = head;

        return listNode;

    }


    /**
     * 二叉树的镜像
     */
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) { val = x; }
    }


    public static void Mirror(TreeNode treeNode){
        if(treeNode == null){
            return;
        }

        //交换
        TreeNode temp = treeNode.left;
        treeNode.left = treeNode.right;
        treeNode.right = temp;

        Mirror(treeNode.left);
        Mirror(treeNode.right);
    }


    /**
     * 顺时针打印矩阵
     * 1  2  3  4
     * 5  6  7  8
     * 9  10 11 12
     * 13 14 15 16
     *
     * 打印结果为：1, 2, 3, 4, 8, 12, 16, 15, 14, 13, 9, 5, 6, 7, 11, 10
     * @param int[][] m
     */

    public static List<Integer> printMatrix(int[][] m){
        ArrayList<Integer> arrayList = new ArrayList<>();

        int r1 = 0;
        int r2 = m.length - 1;
        int c1 = 0;
        int c2 = m[0].length -1;

        while (r1 <= r2 && c1 <= c2){
            for (int i = c1; i <= c2; i++) {
                arrayList.add(m[r1][i]);
            }
            for (int i = r1 + 1; i <= r2; i++) {
                arrayList.add(m[i][c2]);
            }

            if(r1 != r2){
                for (int i = c2 - 1; i >= c1; i--) {
                    arrayList.add(m[r2][i]);
                }
            }
            if(c1 != c2){
                for (int i = r2 - 1; i > r1 ; i--) {
                    arrayList.add(m[i][c1]);
                }
            }


            r1++;r2--;c1++;c2--;
        }


        return arrayList;
    }


    /**
     * 二分法
     * @param args
     */

    public static int binarySearch(int[] arr, int key){
        if(arr == null || arr.length == 0){
            return -1;
        }
        int start = 0;
        int end = arr.length - 1;

        while (start <= end){
            int mid = (start + end) / 2;

            if(arr[mid] == key){
                return mid;
            }else if (arr[mid] > key){
                end = mid - 1;
            }else if(arr[mid] < key){
                start = mid + 1;
            }
        }

        return -1;

    }

    public static void main(String[] args) {
//        ListNode listNode1 = new ListNode(1);
//        ListNode listNode2 = new ListNode(2);
//        ListNode listNode3 = new ListNode(3);
//        ListNode listNode4 = new ListNode(4);
//        listNode1.next = listNode2;
//        listNode2.next = listNode3;
//        listNode3.next = listNode4;
//
//        ListNode listNode = reverseList(listNode1);
//
//        while (listNode != null){
//            System.out.println(listNode.val);
//            listNode = listNode.next;
//        }



//        int[][] m = {
//                        {1,2,3,4},
//                        {5,6,7,8},
//                        {9,10,11,12},
//                        {13,14,15,16}
//                    };
//
//
//        List<Integer> list = printMatrix(m);
//        System.out.println(JSONArray.toJSONString(list));



        int[] m = {2,3,4,5,6,7,8};
        int i = binarySearch(m, 3);
        System.out.println(i);
    }


}
