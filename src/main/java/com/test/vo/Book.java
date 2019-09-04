package com.test.vo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "book")
public class Book implements Serializable {
    @Id
    @GeneratedValue
    private Integer Id;

    @Column(length=100)
    private String bookName;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Book(Integer Id,String bookName) {
        this.Id = Id;
        this.bookName = bookName;
    }
}
