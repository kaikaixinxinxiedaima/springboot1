package com.example.demo;

import com.test.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import com.test.entity.Book;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ShardingTest {
    @Autowired
    private BookService bookService;
    @PostConstruct
    void init() {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }

    @Test
    public void test(){
        for (int i = 0; i < 10; i++) {
            Book book = new Book();

            book.setBookName("测试"+i);
            book.setBookContent("测试");
            book.setAuthor("sj");
            book.setCreateTime(new Date());
            book.setUpdateTime(new Date());
            bookService.save(book);
        }
    }


}
