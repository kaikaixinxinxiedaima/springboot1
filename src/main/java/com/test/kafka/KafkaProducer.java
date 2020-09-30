//package com.test.kafka;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//public class KafkaProducer {
//    private static Logger logger = LoggerFactory.getLogger(KafkaProducer.class);
//
//    @Autowired
//    private KafkaTemplate kafkaTemplate;
//
//    public void sendLog(String log){
//        logger.info("向kafka中发送消息:"+log);
//        kafkaTemplate.send("topic_log", log);
//    }
//}
