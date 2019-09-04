package com.test.extend;

/**
 * 多态
 */
public class Polymorphism {
    static class Animal {
        void eat() {
            System.out.println("animal : eat");
        }
    }

    static class Dog extends Animal {
        void eat() {
            System.out.println("dog : eat");
        }
    }

    static class Pig extends Animal {
        void eat() {
            System.out.println("pig : eat");
        }
    }

    public static void show(Animal a)  {
        // 类型判断
        if (a instanceof Dog)  {  // 猫做的事情
            Dog c = (Dog)a;
            c.eat();
        } else if (a instanceof Pig) { // 狗做的事情
            Pig c = (Pig)a;
            c.eat();
        }
    }

    public static void main(String[] args) {
        show(new Dog());

        show(new Dog());
    }
}
