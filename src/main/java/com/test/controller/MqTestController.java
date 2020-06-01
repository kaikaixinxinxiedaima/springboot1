//package com.test.controller;
//
//import com.alibaba.fastjson.JSONObject;
//import com.test.annotation.MoreDataSource;
//import com.test.entity.Book;
//import com.test.rabbitmq.FanoutRabbitConfig;
//import com.test.rabbitmq.vo.ChatMsg;
//import com.test.service.BookService;
//import com.test.websocket.WebSocketServer;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.Serializable;
//import java.util.Date;
//
////import com.test.rabbitmq.TopicRabbitConfig;
//
//@Controller
//@RequestMapping("/testmq")
//public class MqTestController {
//    private static Logger logger = LoggerFactory.getLogger(MqTestController.class);
//
//    @Autowired
//    private  RabbitTemplate rabbitTemplate; //rabbitTemplate是springboot 提供的默认实现
//
//
//    @RequestMapping("")
//    public String index(){
//        return "mq/mq_test";
//    }
//
//
//    @RequestMapping(value="/send")
//    @ResponseBody
//    public void defaultMessage(String message, String from, String to) {
//        ChatMsg chatMsg = new ChatMsg();
//        chatMsg.setFrom(from);
//        chatMsg.setTo(to);
//        chatMsg.setContent(message);
//        chatMsg.setDate(new Date());
//       rabbitTemplate.convertAndSend("fanoutExchange", "", JSONObject.toJSONString(chatMsg));
//    }
//
//    @RequestMapping(value="/rabbit2")
//    @ResponseBody
//    public void defaultMessage2() {
//
//        for (int i = 0; i < 10; i++) {
//            rabbitTemplate.convertAndSend("exchange","dev.book.topic.manual.queue", "生产者一 " + i);
//        }
//    }
//
//    @RequestMapping(value="/rabbit3")
//    @ResponseBody
//    public void fanoutMessage3() {
//
//        for (int i = 0; i < 5; i++) {
//            rabbitTemplate.convertAndSend("fanoutExchange","", "生产者一 " + i);
//        }
//    }
//
//}
