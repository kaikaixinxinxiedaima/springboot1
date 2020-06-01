package com.test.entity;

import com.test.annotation.Init;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name="book")
@Data
public class Book implements Serializable ,Cloneable{


    /**
     * PS:@GeneratedValue注解的strategy属性提供四种值:
     *
     * -AUTO主键由程序控制, 是默认选项 ,不设置就是这个
     *
     * -IDENTITY 主键由数据库生成, 采用数据库自增长, Oracle不支持这种方式
     *
     * -SEQUENCE 通过数据库的序列产生主键, MYSQL  不支持
     *
     * -Table 提供特定的数据库产生主键, 该方式更有利于数据库的移植
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @Init("测试书名")
    private String bookName;

    private String bookContent;

    private String author;

    private Date createTime;

    private Date updateTime;



    public Book() {

    }

    public Book(Integer id,String bookName) {
        this.id = id;
        this.bookName = bookName;
    }

    @Override
    public Book clone() throws CloneNotSupportedException {
        return (Book)super.clone();
    }

}
