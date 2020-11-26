package com.example.demo.day;

import com.alibaba.fastjson.JSONArray;

import java.util.LinkedList;
import java.util.List;

public class day18 {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }


    /**
     * @author  fanchunying
     * @create  2020/11/26 11:08
     * @desc
     * //将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     * // 示例：
     * //
     * // 输入：1->2->4, 1->3->4
     * //输出：1->1->2->3->4->4
     **/


    public static ListNode mergerTwoList(ListNode list1, ListNode list2){
        ListNode head = new ListNode(0);
        ListNode result = head;

        while (list1 != null && list2 != null){
            if(list1.val <= list2.val){
                head.next = list1;
                head = head.next;
                list1 = list1.next;
            }else{
                head.next = list2;
                head = head.next;
                list2 = list2.next;
            }
        }

        if(list1 != null){
            head.next = list1;
        }
        if(list2 != null){
            head.next = list2;
        }

        return result.next;
    }






    /**
     * @author  fanchunying
     * @create  2020/11/26 13:52
     * @desc
     * //给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
     * //
     * // 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     * //
     * // 示例 1:
     * //
     * // 给定数组 nums = [1,1,2],
     * //
     * //函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。
     * //
     * //你不需要考虑数组中超出新长度后面的元素。
     **/
    public static List<Integer> removeTheagain(int[] arr){
        List<Integer> list = new LinkedList<>();
        if(arr == null || arr.length == 0){
            return list;
        }
        //标记
        int i = 0;

        for (int j = 1; j < arr.length; j++) {
            if(arr[j] != arr[i]){
                i++;
                arr[i] = arr[j];
            }
        }
        for (int j = 0; j < i + 1; j++) {
            list.add(arr[j]);
        }

        return list;
        
    }

    /**
     * //实现 strStr() 函数。
     * //
     * // 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如
     * //果不存在，则返回 -1。
     * //
     * // 示例 1:
     * //
     * // 输入: haystack = "hello", needle = "ll"
     * //输出: 2
     */
    public static int strStr(String haystack, String needle){
        if(needle == null){
            return 0;
        }
        int m = haystack.length();
        int n = needle.length();

        for (int i = 0; i <= m - n; i++) {
            int j;
            for (j = 0; j < n; j++) {
                if(needle.charAt(j) != haystack.charAt(i + j)){
                    break;
                }
            }

            if(j == n){
                return i;
            }
        }

        return -1;
    }




    public static void main(String[] args) {
/*        ListNode list1 = new ListNode(1);
        ListNode list12 = new ListNode(3);
        ListNode list13 = new ListNode(4);
        list1.next = list12;
        list12.next = list13;


        ListNode list2 = new ListNode(1);
        ListNode list22 = new ListNode(4);
        ListNode list23 = new ListNode(5);
        ListNode list24 = new ListNode(6);
        ListNode list25 = new ListNode(7);

        list2.next = list22;
        list22.next = list23;
        list23.next = list24;
        list24.next = list25;

        ListNode listNode = mergerTwoList(list1, list2);

        while (listNode != null) {
            System.out.print(listNode.val + " ");
            listNode = listNode.next;
        }*/


/*        int[] arr = {1,2,2,3,3,5,6,8};
        List<Integer> list = removeTheagain(arr);
        System.out.println(JSONArray.toJSONString(list));*/

        System.out.println(strStr("hello", "ll"));

    }

}
