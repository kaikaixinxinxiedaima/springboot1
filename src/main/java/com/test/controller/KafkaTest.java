package com.test.controller;

import com.test.annotation.MoreDataSource;
import com.test.entity.Book;
import com.test.kafka.KafkaProducer;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/kafka")
public class KafkaTest {
    @Autowired
    KafkaProducer kafkaProducer;


    @ApiOperation(value = "kafka测试消息发送",notes = "kafka测试消息发送",httpMethod = "GET")
    @ApiImplicitParam(name = "message",value = "消息",dataType="String",required = true)
    @RequestMapping(value="/send")
    @ResponseBody
    public void send(String message){
        kafkaProducer.sendLog(message);
    }
}
