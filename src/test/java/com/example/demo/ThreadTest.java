package com.example.demo;

public class ThreadTest {
    private int pubCount = 1;
    private int result = 0;
    private boolean ready = false;

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            ThreadTest threadTest = new ThreadTest();
            RunnableDemo R1 = threadTest.new RunnableDemo(true);
            Thread thread1 = new Thread(R1);
            thread1.start();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            RunnableDemo R2 = threadTest.new RunnableDemo(false);
            Thread thread2 = new Thread(R2);
            thread2.start();
        }



    }

    public synchronized void read(){
        if(ready){
            result = pubCount * 3;
        }
        System.out.println("result："+result);
    }
    public synchronized void write(){
        pubCount = 2;
        ready = true;
    }


    private class RunnableDemo implements Runnable{
        private boolean flag;
        public RunnableDemo(boolean flag){
            this.flag = flag;
        }
        @Override
        public void run() {
            if(flag){
                write();
            }else{
                read();
            }
        }
    }
}


