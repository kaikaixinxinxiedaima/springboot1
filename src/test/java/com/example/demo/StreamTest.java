package com.example.demo;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Stream创建方式
 */
public class StreamTest {
    //数组
    static void gen1(){
        String[] arr = {"a","b","c","d"};
        Stream<String> stream = Stream.of(arr);
    }
    //集合
    static void gen2(){
        List<String> list = Arrays.asList("a", "b", "c", "d");
        Stream<String> stream = list.stream();
    }
    //generate
    static void gen3(){
        Stream<Integer> stream = Stream.generate(() -> 1);
        stream.limit(10).forEach(System.out::println);

    }
    //iterate
    static void gen4(){
        Stream<Integer> stream = Stream.iterate(1, x -> 1);
        stream.limit(10).forEach(System.out::println);
    }
    //其它api
    static void gen5(){
        String str = "qwer";
        IntStream intStream = str.chars();

        //intStream.forEach(x -> System.out.println(x));
        intStream.forEach(System.out::println);
    }
    //获取偶数
    static void gen6(){
        Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9).stream();
        Stream<Integer> stream1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9).stream();
        stream.filter(x -> x%2 == 0).forEach(System.out::println);

        int sum = stream1.filter(x -> x % 2 == 0).mapToInt(x -> x).sum();
        System.out.println("偶数和：" + sum);
    }
    //排序查找
    static void gen7(){
        Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9).stream();

        Optional<Integer> any = stream.filter(x -> x % 2 == 0).sorted((a, b) -> b - a).findAny();
        System.out.println(any.get());
    }
    //1-50 偶数存入新的list
    static void gen8(){
        List<Integer> list = Stream.iterate(1, x -> x + 1).limit(50).filter(x -> x % 2 == 0).collect(Collectors.toList());

        System.out.println(list);
    }
    //去重
    static void gen9(){
        Stream<Integer> stream = Arrays.asList(2, 2, 3, 5, 5, 6, 7, 3, 9).stream();
        Stream<Integer> stream1 = Arrays.asList(2, 2, 3, 5, 5, 6, 7, 3, 9).stream();
        stream.distinct().forEach(System.out::println);

        Set<Integer> set = stream1.collect(Collectors.toSet());
        System.out.println(set);
    }
    //字符串分隔，转成数字求和
    static void gen10(){
        String str = "9,8,7,6";

        int sum = Stream.of(str.split(",")).mapToInt(x -> Integer.valueOf(x)).sum();
        System.out.println(sum);
    }
    //url参数解析成map
    static void gen11(){
        String str = "id=1&name=tom&password=123456";

        Map<String, String> map = Stream.of(str.split("&")).map(x -> x.split("=")).collect(Collectors.toMap(s -> s[0], s -> s[1]));

        System.out.println(map);
    }

    public static void main(String[] args) {
        //StreamTest.gen1();
        //StreamTest.gen2();
        //StreamTest.gen3();
        //StreamTest.gen4();
        //StreamTest.gen5();
        //StreamTest.gen6();
        //StreamTest.gen7();
        //StreamTest.gen8();
        //StreamTest.gen9();
        //StreamTest.gen10();
        StreamTest.gen11();
    }

}
