package com.example.demo.day2;

public class day3 {

    public static String rev(String str){
        char[] strBytes = str.toCharArray();
        int length = strBytes.length;
        int start = 0;
        int end = 0;

        while (end <= length){
            if(end == length || strBytes[end] == ' '){
                jh(strBytes, start, end -1);
                start = end + 1;
            }
            end++;
        }



        return new String(strBytes);

    }

    public static void jh(char[] strBytes, int start, int end){
        while (start < end){
            char strByte = strBytes[start];
            strBytes[start] = strBytes[end];
            strBytes[end] = strByte;
            start++;
            end--;
        }
    }

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


    public static void main(String[] args) {
        String a = "abc1 abc2 abc3";
        System.out.println(rev(a));
    }
}
