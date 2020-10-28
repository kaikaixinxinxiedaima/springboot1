package com.example.demo.day;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class day11 {
    public static void main(String[] args) {
        System.out.println(new User("李福裿").getName());
//        AbstractQueuedSynchronizer
    }

    static class User{
        private String name;

        public User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
