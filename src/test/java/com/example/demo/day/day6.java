package com.example.demo.day;

import java.util.HashMap;
import java.util.concurrent.*;

public class day6 {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch downLatch = new CountDownLatch(1);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        int threadCount1 = ((ThreadPoolExecutor)executorService).getPoolSize();
        System.out.println("初始："+threadCount1);
        executorService.execute(()->{
            try {
                Thread.sleep(5000);
                System.out.println("1111");
                downLatch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
//        ConcurrentHashMap
//        HashMap
        downLatch.await();
        int threadCount2 = ((ThreadPoolExecutor)executorService).getPoolSize();
        System.out.println("执行完："+threadCount2);

        int a=10;
        int b=Short.MAX_VALUE+1;
        int c=a+b;
        System.out.println(c);

    }
}
