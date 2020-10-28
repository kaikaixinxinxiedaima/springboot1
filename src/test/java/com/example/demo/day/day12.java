package com.example.demo.day;


import com.alibaba.fastjson.JSONArray;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * 剑指offer
 * 第1天
 */
public class day12 {
    /**
     * 在一个长度为 n 的数组里的所有数字都在 0 到 n-1 的范围内。数组中某些数字是重复的，
     * 但不知道有几个数字是重复的，也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
     *
     * Input:
     * {2, 3, 1, 0, 2, 5}
     *
     * Output:
     * 2
     *
     * 解题思路：
     * 对于这种数组元素在 [0, n-1] 范围内的问题，可以将值为 i 的元素调整到第 i 个位置上进行求解。
     * 以 (2, 3, 1, 0, 2, 5) 为例，遍历到位置 4 时，该位置上的数为 2，但是第 2 个位置上已经有一个 2 的值了，因此可以知道 2 重复
     */

    public static int test1(){
        int[] arr = {2, 3, 1, 0, 2, 5};
        int result = -1;

        for (int i = 0; i < arr.length ; i++) {
            while (arr[i] != i){
                if(arr[i] == arr[arr[i]]){
                    return arr[i];
                }

                int t = arr[i];
                arr[i] = arr[t];
                arr[t] = t;
            }
        }

        return result;
    }


    /**
     * 给定一个二维数组，其每一行从左到右递增排序，从上到下也是递增排序。给定一个数，判断这个数是否在该二维数组中。
     * Consider the following matrix:
     * [
     *   [1,   4,  7, 11, 15],
     *   [2,   5,  8, 12, 19],
     *   [3,   6,  9, 16, 22],
     *   [10, 13, 14, 17, 24],
     *   [18, 21, 23, 26, 30]
     * ]
     *
     * Given target = 5, return true.
     * Given target = 20, return false.
     */

    public static boolean test2(int target){
        boolean result = false;
        int arr[][] = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };

        int row = arr.length;
        int col = arr[0].length;

        //从右上角开始
        int r = 0;
        int c = col - 1;

        while (r <= row -1 && c >= 0){
            if(target == arr[r][c]){
                return true;
            }else if (target > arr[r][c]){
                r++;
            }else{
                c--;
            }
        }

        return result;
    }


    /**
     * 从尾到头打印链表
     * 从尾到头反过来打印出每个结点的值。
     */

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class ListNode{
        private int val;
        private ListNode next;

        ListNode(int val){
            this.val = val;
        }
    }

    //递归方式
    public static ArrayList<Integer> test3(ListNode listNode){

        ArrayList<Integer> arrayList = new ArrayList<>();
        if(listNode != null){
            arrayList.addAll(test3(listNode.getNext()));
            arrayList.add(listNode.getVal());
        }

        return arrayList;
    }

    //运用栈
    public static ArrayList<Integer> test3_1(ListNode listNode){

        ArrayList<Integer> arrayList = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        while (listNode != null){
            stack.add(listNode.getVal());
            listNode = listNode.getNext();
        }

        while (!stack.empty())
            arrayList.add(stack.pop());

        return arrayList;
    }


    /**
     * 根据二叉树的前序遍历和中序遍历的结果，重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     *
     * 前：3 9 20 15 7
     * 中：9 3 15 20 7
     * 输出：https://uploadfiles.nowcoder.com/files/20190616/124213_1560686577165_31d9adce-2af8-4754-8386-0aabb4e500b0.png
     */
    static class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }

    public static TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if(pre == null || in == null || pre.length == 0 || in.length == 0){
            return null;
        }

        TreeNode treeNode = new TreeNode(pre[0]);

        for (int i = 0; i < in.length; i++) {
            if(in[i] == pre[0]){
                treeNode.left = reConstructBinaryTree(Arrays.copyOfRange(pre,1, i + 1), Arrays.copyOfRange(in,0, i + 1));
                treeNode.right = reConstructBinaryTree(Arrays.copyOfRange(pre,i + 1, pre.length), Arrays.copyOfRange(in,i + 1, in.length));
            }
        }

        return treeNode;
    }

    /*----------------------用于直观打印二叉树 start-------------------------*/

    // 用于获得树的层数
    public static int getTreeDepth(TreeNode root) {
        return root == null ? 0 : (1 + Math.max(getTreeDepth(root.left), getTreeDepth(root.right)));
    }


    private static void writeArray(TreeNode currNode, int rowIndex, int columnIndex, String[][] res, int treeDepth) {
        // 保证输入的树不为空
        if (currNode == null) return;
        // 先将当前节点保存到二维数组中
        res[rowIndex][columnIndex] = String.valueOf(currNode.val);

        // 计算当前位于树的第几层
        int currLevel = ((rowIndex + 1) / 2);
        // 若到了最后一层，则返回
        if (currLevel == treeDepth) return;
        // 计算当前行到下一行，每个元素之间的间隔（下一行的列索引与当前元素的列索引之间的间隔）
        int gap = treeDepth - currLevel - 1;

        // 对左儿子进行判断，若有左儿子，则记录相应的"/"与左儿子的值
        if (currNode.left != null) {
            res[rowIndex + 1][columnIndex - gap] = "/";
            writeArray(currNode.left, rowIndex + 2, columnIndex - gap * 2, res, treeDepth);
        }

        // 对右儿子进行判断，若有右儿子，则记录相应的"\"与右儿子的值
        if (currNode.right != null) {
            res[rowIndex + 1][columnIndex + gap] = "\\";
            writeArray(currNode.right, rowIndex + 2, columnIndex + gap * 2, res, treeDepth);
        }
    }


    public static void show(TreeNode root) {
        if (root == null) System.out.println("EMPTY!");
        // 得到树的深度
        int treeDepth = getTreeDepth(root);

        // 最后一行的宽度为2的（n - 1）次方乘3，再加1
        // 作为整个二维数组的宽度
        int arrayHeight = treeDepth * 2 - 1;
        int arrayWidth = (2 << (treeDepth - 2)) * 3 + 1;
        // 用一个字符串数组来存储每个位置应显示的元素
        String[][] res = new String[arrayHeight][arrayWidth];
        // 对数组进行初始化，默认为一个空格
        for (int i = 0; i < arrayHeight; i ++) {
            for (int j = 0; j < arrayWidth; j ++) {
                res[i][j] = " ";
            }
        }

        // 从根节点开始，递归处理整个树
        // res[0][(arrayWidth + 1)/ 2] = (char)(root.val + '0');
        writeArray(root, 0, arrayWidth/ 2, res, treeDepth);

        // 此时，已经将所有需要显示的元素储存到了二维数组中，将其拼接并打印即可
        for (String[] line: res) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < line.length; i ++) {
                sb.append(line[i]);
                if (line[i].length() > 1 && i <= line.length - 1) {
                    i += line[i].length() > 4 ? 2: line[i].length() - 1;
                }
            }
            System.out.println(sb.toString());
        }
    }
    /*----------------------用于直观打印二叉树 end--------------------------*/


    public static void main(String[] args) {
//        int i = test1();
//        System.out.println(i);
//        boolean b = test2(2);
//        System.out.println(b);

//        ListNode l1 = new ListNode(1);
//        ListNode l2 = new ListNode(2);
//        ListNode l3 = new ListNode(3);
//        l1.setNext(l2);
//        l2.setNext(l3);
//
//        ArrayList<Integer> arrayList = test3(l1);
//        ArrayList<Integer> arrayList1 = test3_1(l1);
//        System.out.println(JSONArray.toJSONString(arrayList1));


        int [] pre = {3,9,20,15,7};
        int [] in = {9,3,15,20,7};

        TreeNode treeNode = reConstructBinaryTree(pre, in);
        show(treeNode);

    }







}
