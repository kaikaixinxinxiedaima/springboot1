package com.test.service;


import com.test.entity.Book;
import com.test.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BookService {

    @Autowired
    private BookMapper bookMapper;

    public Book findById(Integer id){
        Book book = bookMapper.selectByPrimaryKey(id);
        return book;
    }
}
