package com.test.dao;

import com.test.vo.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


@Component
public interface  BookDao extends JpaRepository<Book, Integer> {

}
