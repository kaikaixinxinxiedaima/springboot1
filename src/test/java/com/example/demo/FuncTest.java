package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class FuncTest {
    public static void main(String[] args) {
        //静态方法引用
        Function<String, String> f = a -> Fun.staticUp(a);
        Function<String, String> f1 = Fun::staticUp;

        System.out.println("大写：" + f.apply("aaaaa"));
        System.out.println("大写：" + f1.apply("bbbbb"));

        //实例方法引用
        Function<String, String> f2 = a -> new Fun().up(a);
        Function<String, String> f3 = new Fun()::up;

        System.out.println("大写：" + f2.apply("ccccc"));
        System.out.println("大写：" + f3.apply("ddddd"));

        //对象方法引用
        BiFunction<Fun, String, String> f4 = (fun, a) -> new Fun().upCase(a);
        BiFunction<Fun, String, String> f5 = Fun::upCase;

        System.out.println("大写：" + f4.apply(new Fun(), "eeee"));
        System.out.println("大写：" + f5.apply(new Fun(), "ffff"));

        //构造方法引用
        Supplier<Fun> s = () -> new Fun();
        Supplier<Fun> s1 = Fun::new;
        Supplier<List> s2 = ArrayList::new;

        s.get();
        s1.get();
        s2.get();

    }
}

class Fun{
    public static String staticUp(String a){
        return a.toUpperCase();
    }

    public String up(String a){
        return a.toUpperCase();
    }

    public String upCase(String a){
        return a.toUpperCase();
    }

    public Fun(){
        System.out.println("new Fun()");
    }
}
