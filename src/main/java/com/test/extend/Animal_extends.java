package com.test.extend;

public class Animal_extends {
    static class Animal {
        private String color;

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        void eat() {
            System.out.println("animal : eat");
        }
    }

    static class Dog extends Animal {
        void eat() {
            System.out.println("dog : eat");
        }
        void eatTest() {
            this.eat();   // this 调用自己的方法
            super.eat();  // super 调用父类方法
        }
    }

    public static void main(String[] args) {
//        Animal a = new Animal();
//        a.eat();
//
//        Dog d = new Dog();
//        d.eatTest();
//        System.out.println(d.getColor());


//        Animal a = new Dog();  // 向上转型
//        a.eat();               // 调用的是 Cat 的 eat
//        Dog c = (Dog)a;        // 向下转型
//        c.eatTest();        // 调用的是 Cat 的 work

        Animal a1 = new Animal();
        Dog c1 = (Dog)a1;        // 向下转型

        c1.eat();
    }
}
