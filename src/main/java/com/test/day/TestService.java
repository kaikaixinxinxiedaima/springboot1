package com.test.day;

import org.springframework.stereotype.Service;

public class TestService {
    public void test(){
        System.out.println("测试方法");
        int i = 1 / 0;
    }
}
