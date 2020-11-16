package com.test.service;


import com.test.entity.Book;
import com.test.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class BookService {

    @Autowired
    private BookMapper bookMapper;

    public Book findById(Integer id){
        Book book = bookMapper.selectByPrimaryKey(id);
        return book;
    }

    public List<Book> findByKeyWord(String keyword){
        List<Book> books = bookMapper.findByKeyWord(keyword);
        return books;
    }

    @Transactional
    public Book updateById(Integer id){
        Book book = bookMapper.selectByPrimaryKey(id);
        book.setBookName("haha");
        bookMapper.updateByPrimaryKey(book);
        int a = 1/0;
        return book;
    }

    @Transactional
    public void save(Book book){
        bookMapper.insert(book);
    }
}
