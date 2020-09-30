//package com.test.kafka;
//
//import com.Springboot1Application;
//import org.apache.kafka.clients.consumer.Consumer;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.support.Acknowledgment;
//import org.springframework.stereotype.Component;
//
//@Component
//public class KafkaConsumer {
//    private static Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
//
//    @KafkaListener(topics = {"topic_log"})
//    public void listen(ConsumerRecord<?, ?> record, Acknowledgment acknowledgment, Consumer<?, ?> consumer) {
//        try {
//            String logStr = (String) record.value();
//            acknowledgment.acknowledge();
//            logger.info("kafka接收消息：{}", logStr);
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.error("kafka消息消费失败：", e);
//        }
//    }
//
//    /**
//     * log4j2 日志
//     * @param record
//     * @param acknowledgment
//     * @param consumer
//     */
//    @KafkaListener(topics = {"kafka-log"})
//    public void log_listen(ConsumerRecord<?, ?> record, Acknowledgment acknowledgment, Consumer<?, ?> consumer) {
//        try {
//            String logStr = (String) record.value();
//            acknowledgment.acknowledge();
//            logger.info("kafka接收消息：{}", logStr);
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.error("kafka消息消费失败：", e);
//        }
//    }
//}
