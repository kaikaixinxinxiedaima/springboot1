package com.example.demo.day;

import com.test.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class day4 {
    public static <E extends List> void print(E arr){
        arr.forEach(a -> {
            System.out.println(a);
        });

    }


    public static void main(String[] args) {
        ArrayList arr = new ArrayList();
        arr.add("1111");
        print(arr);

    }
}
