package com.example.demo;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class LambdaAndStreamUseTest {


    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4();
        test5();
    }

    //取属性
    private static void test1() {
        //id集合
        List<Integer> ids = books().stream().map(Book::getId).collect(Collectors.toList());

        System.out.println("id集合：" + ids);

        //拼接id
        String str = books().stream().map(book -> "'" + book.getId() + "'").collect(Collectors.joining(",", "(", ")"));

        System.out.println("拼接id:" + str);
    }

    //价格排序
    private static void test2() {
        //价格排序
        List<Book> bookList = books().stream().sorted((book1, book2) -> Double.compare(book1.getPrice(), book2.getPrice())).collect(Collectors.toList());

//        bookList.forEach(System.out::println);

        //价格+发布日期排序
        Comparator<Book> priceComparator = Comparator.comparingDouble(Book::getPrice);
        Comparator<Book> dateComparator = Comparator.comparing(Book::getPublishDate);

        List<Book> bookList1 = books().stream().sorted(priceComparator.thenComparing(dateComparator.reversed())).collect(Collectors.toList());
        bookList1.forEach(System.out::println);

    }

    //转map
    private static void test3() {
        Map<Integer, Book> bookMap = books().stream().collect(Collectors.toMap(Book::getId, book -> book));

        bookMap.forEach((key, value) -> System.out.println(key+ ":" + value));

    }
    //所有书的平均价
    private static void test4() {
        Double aDouble = books().stream().collect(Collectors.averagingDouble(Book::getPrice));

        System.out.println("平均价：" + aDouble);
    }
    //分组统计
    private static void test5() {
        Map<Double, Long> map = books().stream().collect(Collectors.groupingBy(Book::getPrice, Collectors.counting()));

        System.out.println("按价格统计：" + map);

        Map<String, Double> map1 = books().stream().collect(Collectors.groupingBy(Book::getBookName, Collectors.summingDouble(Book::getPrice)));

        System.out.println("按书名统计总金额：" + map1);

        Map<String, Double> map2 = books().stream().collect(Collectors.groupingBy(Book::getBookName, Collectors.averagingDouble(Book::getPrice)));

        System.out.println("按书名统计平均价格：" + map2);

        Map<String, Optional<Book>> map3 = books().stream().collect(Collectors.groupingBy(Book::getBookName, Collectors.maxBy(Comparator.comparing(Book::getPrice))));

        System.out.println("每种书最贵的价格：" + map3);

    }



    private static List<Book> books() {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(1, "java", 35d, LocalDate.parse("2019-06-06")));
        bookList.add(new Book(2, "js", 35d, LocalDate.parse("2019-06-06")));
        bookList.add(new Book(3, "php", 55d, LocalDate.parse("2019-03-06")));
        bookList.add(new Book(4, "php", 35d, LocalDate.parse("2019-06-06")));
        bookList.add(new Book(5, "html", 45d, LocalDate.parse("2019-06-06")));
        bookList.add(new Book(6, "spring", 85d, LocalDate.parse("2019-07-06")));
        bookList.add(new Book(7, "mybatis", 45d, LocalDate.parse("2019-06-06")));
        bookList.add(new Book(8, "mysql", 35d, LocalDate.parse("2019-08-06")));
        bookList.add(new Book(9, "spring", 55d, LocalDate.parse("2019-06-01")));
        bookList.add(new Book(10, "springboot", 35d, LocalDate.parse("2019-03-06")));

        return bookList;
    }

}


class Book{
    private Integer id;
    private String bookName;
    private Double price;
    private LocalDate publishDate;

    public Book(Integer id, String bookName, Double price, LocalDate publishDate) {
        this.id = id;
        this.bookName = bookName;
        this.price = price;
        this.publishDate = publishDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", price=" + price +
                ", publishDate=" + publishDate +
                '}';
    }
}
