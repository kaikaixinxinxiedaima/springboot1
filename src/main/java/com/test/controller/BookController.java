package com.test.controller;

import com.alibaba.fastjson.JSONObject;
import com.test.annotation.MoreDataSource;
import com.test.entity.Book;
//import com.test.rabbitmq.TopicRabbitConfig;
import com.test.service.BookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/book")
public class BookController {
    private static Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Serializable> redisCacheTemplate;

    @Autowired
    private  RabbitTemplate rabbitTemplate; //rabbitTemplate是springboot 提供的默认实现



    /**
     * 查询图书
     * @return
     */
//    @RequiresRoles(value = {"admin"}, logical = Logical.AND)
//    @RequiresPermissions(value={"book:list"},logical=Logical.OR)
    @RequestMapping(value="/list/{id}")
    @ResponseBody
    public Book list(@PathVariable(value = "id") Integer id){
        Book book = bookService.findById(id);

        stringRedisTemplate.opsForValue().set("test","测试");

        redisCacheTemplate.opsForValue().set("book",book);

        final Book book1 = (Book) redisCacheTemplate.opsForValue().get("book");

//        logger.info(stringRedisTemplate.opsForValue().get("test"));
//
//        logger.info("[对象缓存结果] - [{}]", book1);
//
//        logger.info("[对象缓存结果] - [{}]", JSONObject.toJSON(book1).toString());

        return book;
    }

    @ApiOperation(value = "根据id查询图书",notes = "采用restfull风格",httpMethod = "GET")
    @ApiImplicitParam(name = "id",value = "主键",dataType="int",required = true)
    @RequestMapping(value="/list2/{id}")
    @ResponseBody
    @MoreDataSource(name = "master")
    public Book list2(@PathVariable(value = "id") Integer id){
        Book book = bookService.findById(id);
        return book;
    }

    @RequestMapping(value="/update/{id}")
    @ResponseBody
    @MoreDataSource
    public Book update(@PathVariable(value = "id") Integer id){
        Book book = bookService.updateById(id);
        return book;
    }

    @RequestMapping(value="/update2/{id}")
    @ResponseBody
    public Book update2(@PathVariable(value = "id") Integer id){
        Book book = bookService.updateById(id);
        return book;
    }

    @RequestMapping(value="/listToCache")
    @ResponseBody
    public JSONObject listToCache(HttpServletRequest request){
        Book book = bookService.findById(1);

        JSONObject sessionObj = new JSONObject();

        sessionObj.put("sessionId：", request.getSession().getId());
        sessionObj.put("服务器端口：", request.getServerPort());

        //向session中保存数据
        request.getSession().setAttribute("book",JSONObject.toJSON(book).toString());

        return sessionObj;
    }


    @RequestMapping(value="/listFromCache")
    @ResponseBody
    public JSONObject listFromCache(HttpServletRequest request){
        //从session获取数据
        String bookString = (String) request.getSession().getAttribute("book");

        Book book = JSONObject.parseObject(bookString, Book.class);

        JSONObject sessionObj = new JSONObject(true);

        sessionObj.put("sessionId：", request.getSession().getId());
        sessionObj.put("服务器端口：", request.getServerPort());

        sessionObj.put("book",JSONObject.toJSON(book).toString());

        return sessionObj;
    }



    @RequestMapping(value="/rabbit")
    @ResponseBody
    public void defaultMessage() {

        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("exchange","dev.book.topic.default.queue", "生产者一 " + i);
        }
    }

    @RequestMapping(value="/rabbit2")
    @ResponseBody
    public void defaultMessage2() {

        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("exchange","dev.book.topic.manual.queue", "生产者一 " + i);
        }
    }

    @RequestMapping(value="/rabbit3")
    @ResponseBody
    public void fanoutMessage3() {

        for (int i = 0; i < 5; i++) {
            rabbitTemplate.convertAndSend("fanoutExchange","", "生产者一 " + i);
        }
    }

}
