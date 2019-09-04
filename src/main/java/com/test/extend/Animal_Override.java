package com.test.extend;

public class Animal_Override {
    static class Animal {
        void eat() {
            System.out.println("animal : eat");
        }
    }

    static class Dog extends Animal {
        void eat() {
            System.out.println("dog : eat");
        }

        void run() {
            System.out.println("dog : run");
        }
    }

    public static void main(String[] args) {
        Animal a = new Animal(); // Animal 对象
        Dog b = new Dog(); // Dog 对象
        Animal b1 = new Dog(); // Dog 对象

        a.eat();// 执行 Animal 类的方法

        b.run();//执行 Dog 类的方法

        b1.eat();//执行 Dog 类的方法
    }
}
