/*
package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.controller.BookController;
import com.test.entity.Book;
import com.test.mapper.BookMapper;
//import com.test.rabbitmq.RabbitConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot1ApplicationTests {
    private static Logger logger = LoggerFactory.getLogger(Springboot1ApplicationTests.class);

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void test() {

        List<Book> books = new LinkedList<>();

        for (int i = 20; i < 40; i++) {
            books.add(new Book(i,"西游记" + " - 卷（"+i+")"));
        }

        bookMapper.insertList(books);
*/
/*
        for (int i = 0; i < 20; i++) {
            bookMapper.insertSelective(new Book(i,"西游记" + " - 卷（"+i+")"));
        }
*//*


        logger.info("插入成功-----------------");

        // TODO 分页 + 排序 this.userMapper.selectAll() 这一句就是我们需要写的查询，有了这两款插件无缝切换各种数据库
        final PageInfo<Object> pageInfo = PageHelper.startPage(1, 10).setOrderBy("id desc").doSelectPageInfo(() -> this.bookMapper.selectAll());
        logger.info("[lambda写法] - [分页信息] - [{}]", pageInfo.toString());

        PageHelper.startPage(1, 10).setOrderBy("id desc");
        final PageInfo<Book> userPageInfo = new PageInfo<Book>(this.bookMapper.selectAll());
        logger.info("[普通写法] - [{}]", userPageInfo);
        List<Book> bookList = userPageInfo.getList();

        bookList.forEach(book -> {
            logger.info("[查询数据集合] - [{}]", JSONObject.toJSON(book));
        });


    }

    @Test
    public void test1() {

       */
/* for (int i = 0; i < 100; i++) {
            rabbitTemplate.convertAndSend(RabbitConfig.DEFAULT_BOOK_QUEUE, "生产者一" + i);
        }*//*


    }

}
*/
