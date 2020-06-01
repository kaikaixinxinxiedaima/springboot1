package com.example.demo.kafka;

import com.test.es.BookRepository;
import com.test.es.EsBook;
import com.test.kafka.KafkaProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class KafkaTest {
    @Autowired
    KafkaProducer kafkaProducer;


    @Test
    public void test(){
        kafkaProducer.sendLog("测试消息");
    }
}
