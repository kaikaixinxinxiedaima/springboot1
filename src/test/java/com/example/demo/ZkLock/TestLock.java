package com.example.demo.ZkLock;

import com.test.lock.ZkLock;

public class TestLock {
    private static int num = 1;
    private static ZkLock zkLock;

    static {
        zkLock = new ZkLock("stock_zk_lock");
    }


    static class StockThread implements Runnable{

        @Override
        public void run() {
            try {
                //加锁
                zkLock.lock();

                boolean b = ReduceStock();

                if(b){
                    System.out.println(Thread.currentThread().getName() + "库存减少成功");
                }else{
                    System.out.println(Thread.currentThread().getName() + "库存减少失败");
                }
            }finally {
                //解锁
                zkLock.unlock();
            }

        }
    }

    public static boolean ReduceStock(){
        if(num > 0){
            num--;
            return true;
        }else{
            return false;
        }
    }

    public static void main(String[] args) {

        for (int i = 1; i < 5; i++) {
            new Thread(new StockThread(),"线程" + i + " ").start();
        }
    }
}
