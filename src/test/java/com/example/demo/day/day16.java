package com.example.demo.day;

public class day16 {



    /**
     * @author  fanchunying
     * @create  2020/11/23 14:52
     * @desc 数字在排序数组中出现的次数
     * Input:
     * nums = 1, 2, 3, 3, 3, 3, 4, 6
     * K = 3
     *
     * Output:
     * 4
     **/
    public static int test1(int[] nums, int k){
        int first = binarySearch(nums, k);
        int last = binarySearch(nums, k + 1);
        return (first == nums.length || nums[first] != k) ? 0 : last - first;
    }

    private static int binarySearch(int[] nums, int K) {
        int l = 0, h = nums.length;
        while (l < h) {
            int m = l + (h - l) / 2;
            if (nums[m] >= K)
                h = m;
            else
                l = m + 1;
        }
        return l;
    }

    /**
     * @author  fanchunying
     * @create  2020/11/23 15:01
     * @desc 二叉树的深度
     * 从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
     **/
    /**
     * 二叉树
     */
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) { val = x; }
    }

    public static int TreeDepth(TreeNode root){
        return root == null ? 0 : 1 + (Math.max(TreeDepth(root.left), TreeDepth(root.right)));
    }


    /**
     * @author  fanchunying
     * @create  2020/11/23 15:09
     * @desc    翻转单词顺序列
     *          思路：先旋转每个单词，再旋转整个字符串
     * Input:
     * "I am a student."
     *
     * Output:
     * "student. a am I"
     *
     *
     **/
    public static String reverseSentence(String str){
        int n = str.length();
        char[] chars = str.toCharArray();
        int i = 0, j = 0;

        while (j <= n) {
            if (j == n || chars[j] == ' ') {
                reverse(chars, i, j - 1);
                i = j + 1;
            }
            j++;
        }
        reverse(chars, 0, n - 1);
        return new String(chars);

    }

    private static void reverse(char[] c, int i, int j) {
        while (i < j)
            swap(c, i++, j--);
    }

    private static void swap(char[] c, int i, int j) {
        char t = c[i];
        c[i] = c[j];
        c[j] = t;
    }


    /**
     * @author  fanchunying
     * @create  2020/11/23 15:58
     * @desc  整数翻转
     * //给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
     * //
     * // 示例 1:
     * //
     * // 输入: 123
     * //输出: 321
     **/
    public static int reverse(int x) {
        if(x < -231 || x > 230){
            return 0;
        }
        String xStr = String.valueOf(x);
        char[] chars = xStr.toCharArray();
        int length = xStr.length() - 1;
        int a = 0;

        while (a < length){
            if(chars[a] == '-'){
                a++;
            }else {
                char t = chars[a];
                chars[a] = chars[length];
                chars[length] = t;
                a++;
                length--;
            }
        }

        return Integer.valueOf(new String(chars));
    }


    /**
     * @author  fanchunying
     * @create  2020/11/23 16:39
     * @desc 判断是否是回文数，不转成字符串实现
     **/
    public static String checkPalindromeNumber(int number){
        int t = number;
        int sum = 0;

        while (true){
            int i = number % 10;
            sum = sum * 10 + i;
            number /= 10;
            if(number == 0) break ;
        }

        if(sum == t){
            return "是回文数";
        }
        return "不是回文数";
    }

    public static void test(){
        int a = 0;
    }

    public static void main(String[] args) {
//        System.out.println(reverseSentence("I am a student."));
//        System.out.println(reverse(-123));
        System.out.println(checkPalindromeNumber(123211));
    }
}
