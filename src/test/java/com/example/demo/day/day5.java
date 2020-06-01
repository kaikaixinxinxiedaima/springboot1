package com.example.demo.day;

import java.util.LinkedHashMap;
import java.util.Map;

public class day5 {

    public static void main(String[] args) {
        Map<String, String> a = new LinkedHashMap<>();

        Map<String, String> i = test(a);

        System.out.println(i);
    }

    public static Map<String, String> test(Map<String, String> a){
        try {
            a.put("a", "a");
            return a;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            a.put("a", "b");
        }

        return null;
    }

}

