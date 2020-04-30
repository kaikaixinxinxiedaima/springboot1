package com.example.demo.es;


import com.test.es.BookRepository;
import com.test.es.EsBook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EsTest {
    @Autowired
    BookRepository bookRepository;

    public EsTest() {
        //在构造函数上写上这个
        System.setProperty("es.set.netty.runtime.available.processors","false");
    }

    @Test
    public void test(){
        Iterable<EsBook> all = bookRepository.findAll();
        Iterator<EsBook> iterator = all.iterator();

        while (iterator.hasNext()){
            EsBook next = iterator.next();
            System.out.println("---------------"+next);
        }
    }
}
