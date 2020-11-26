package com.example.demo.day;

import com.alibaba.fastjson.JSONArray;

import java.util.HashMap;
import java.util.Map;

public class day17 {
    /**
     * @author  fanchunying
     * @create  2020/11/24 9:50
     * @desc
     * //给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * //
     * // 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
     * //
     * // 示例:
     * //
     * // 给定 nums = [2, 7, 11, 15], target = 9
     * //
     * //因为 nums[0] + nums[1] = 2 + 7 = 9
     * //所以返回 [0, 1]
     * //
     **/
    public static int[] twoSum(int[] arr, int target){
        if(arr == null || arr.length == 0){
            return null;
        }
        Map<Integer,Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], i);
        }

        for (int i = 0; i < arr.length; i++) {
            int sub = target - arr[i];

            if(map.containsKey(sub) && map.get(sub) != i){
                return new int[] {i, map.get(sub)};
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(JSONArray.toJSONString(twoSum(new int[]{1,2,2,4,5,4}, 4)));
    }
}
