package com.test.controller;

import com.alibaba.fastjson.JSONObject;
import com.test.entity.Book;
import com.test.service.BookService;
import com.test.websocket.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

//import com.test.rabbitmq.TopicRabbitConfig;

@Controller
@RequestMapping("/webSocket")
public class WebSocketController {
    private static Logger logger = LoggerFactory.getLogger(WebSocketController.class);

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    //页面请求
    @GetMapping("/socket/{from}")
    public ModelAndView socket(@PathVariable String from) {
        ModelAndView mav=new ModelAndView("mq/mq_test");
        mav.addObject("from", from);
        return mav;
    }


    //推送数据接口
    @ResponseBody
    @RequestMapping("/push/{cid}")
    public String pushToWeb(@PathVariable String cid,String message) {
        JSONObject jsonObject = new JSONObject();
        try {
            WebSocketServer.sendInfo(message,cid);
           /* String date = WebSocketController.format.format(new Date());
            String mes = message+ " (" + date + ")";
            jsonObject.put("mes",mes);
            jsonObject.put("sender",cid);*/
            return jsonObject.toString();
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(cid+"#"+e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        int a = 0;
        for (;;a++) {
            System.out.println(a);
            if(a > 100){
                break;
            }
        }
    }

}
