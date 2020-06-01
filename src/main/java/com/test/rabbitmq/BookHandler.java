//package com.test.rabbitmq;
//
//
//import com.alibaba.fastjson.JSONObject;
//import com.rabbitmq.client.AMQP;
//import com.rabbitmq.client.Channel;
//import com.test.rabbitmq.vo.ChatMsg;
//import com.test.websocket.WebSocketServer;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
///**
// * BOOK_QUEUE 消费者
// */
//@Component
//public class BookHandler {
//
//    private static final Logger logger = LoggerFactory.getLogger(BookHandler.class);
//
///*    *
//     * <p> TODO 该方案是 spring-boot-data-amqp 默认的方式,不太推荐。具体推荐使用  listenerManualAck()</p>
//     * 默认情况下,如果没有配置手动ACK, 那么Spring Data AMQP 会在消息消费完毕后自动帮我们去ACK
//     * 存在问题：如果报错了,消息不会丢失,但是会无限循环消费,一直报错,如果开启了错误日志很容易就吧磁盘空间耗完
//     * 解决方案：手动ACK,或者try-catch 然后在 catch 里面讲错误的消息转移到其它的系列中去
//     * spring.rabbitmq.listener.simple.acknowledge-mode=manual
//     * <p>
//     *
//     * @param text 监听的内容
//     * deliveryTag:该消息的index
//     * multiple：是否批量.true:将一次性ack所有小于deliveryTag的消息。*/
//
//    @RabbitListener(queues = {FanoutRabbitConfig.DEFAULT_BOOK_QUEUE})
//    public void listenerAutoAck(String text, Message message, Channel channel) {
//
//        // TODO 如果手动ACK,消息会被监听消费,但是消息在队列中依旧存在,如果 未配置 acknowledge-mode 默认是会在消费完毕后自动ACK掉
//        final long deliveryTag = message.getMessageProperties().getDeliveryTag();
//        try {
//            logger.info("[消费者一监听的消息] - [{}]", text);
//            new WebSocketServer().sendMqMessage(text);
//
//            // TODO 通知 MQ 消息已被成功消费,可以ACK了
//            channel.basicAck(deliveryTag, false);
//        } catch (IOException e) {
//            try {
//                // TODO 处理失败,重新压入MQ
//                channel.basicRecover();
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
//        }
//    }
//
////    @RabbitListener(queues = {FanoutRabbitConfig.MANUAL_BOOK_QUEUE})
////    public void listenerManualAck(String text, Message message, Channel channel) {
////        logger.info("[消费者二监听的消息] - [{}]",text);
////        try {
////            // TODO 通知 MQ 消息已被成功消费,可以ACK了
////            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
////        } catch (IOException e) {
////            // TODO 如果报错了,那么我们可以进行容错处理,比如转移当前消息进入其它队列
////        }
////    }
//}
