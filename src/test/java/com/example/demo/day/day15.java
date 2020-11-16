package com.example.demo.day;

public class day15 {
    public static void main(String[] args) {
        String display = System.getenv("DISPLAY").substring(1000);
        System.out.println(display);
    }
}
