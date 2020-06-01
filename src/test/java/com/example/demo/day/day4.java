package com.example.demo.day;

import com.test.entity.Book;
import org.joda.time.DateTime;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class day4 {
    public static <E extends List> void print(E arr){
        arr.forEach(a -> {
            System.out.println(a);
        });

    }



    public static void main(String[] args) {
//        ArrayList arr = new ArrayList();
//        arr.add("1111");
//        print(arr);    /*
//        print(arr);     *
//        print(arr);     */
//        System.out.println( DateTime.now().toDate());
    }




    public abstract class abs{
        abstract void test();

        void test1(){

        }
    }

    public class ex extends abs{

        @Override
        void test() {
            System.out.println("ha");
        }
    }

    public interface intf{
        public static final int a = 0;

        void test();
    }

    public abstract class ex1 implements intf{


    }
}
