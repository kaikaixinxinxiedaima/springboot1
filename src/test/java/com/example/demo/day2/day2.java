package com.example.demo.day2;

import com.example.demo.day.day14;

import java.util.Arrays;

public class day2 {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }


    public static TreeNode test(int[] pre, int[] in){
        if(pre.length == 0 || in.length == 0){
            return null;
        }
        TreeNode root = new TreeNode(pre[0]);

        for (int i = 0; i < in.length; i++) {
            if(root.val == in[i]){
                root.left = test(Arrays.copyOfRange(pre, 1, i+1), Arrays.copyOfRange(in, 0, i+1));
                root.right = test(Arrays.copyOfRange(pre, i, pre.length), Arrays.copyOfRange(in, i+1, in.length));
            }
        }

        return root;
    }



    public static void main(String[] args) {

    }

}
