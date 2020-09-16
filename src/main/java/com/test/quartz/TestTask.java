package com.test.quartz;

public class TestTask extends Task{

    @Override
    public void run() {
        System.out.println("测试定时任务----");

    }
}
