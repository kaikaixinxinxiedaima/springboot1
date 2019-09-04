package com.test.rabbitmq;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 创建消息队列
 * @Configuration 启动容器+@Bean注册Bean，@Bean下管理bean的生命周期
 * @Bean 标注在方法上(返回某个实例的方法)，等价于spring的xml配置文件中的<bean>，作用为：注册bean对象
 *       *** bean name为方法名 ****
 */

/*
@Configuration
public class FanoutRabbitConfig {

    public static final String DEFAULT_BOOK_QUEUE = "dev.book.fanout.a.queue";
    public static final String MANUAL_BOOK_QUEUE = "dev.book.fanout.b.queue";

    @Bean
    public Queue queueMessageA() {
        // 第一个是 QUEUE 的名字,第二个是消息是否需要持久化处理
        return new Queue(DEFAULT_BOOK_QUEUE, true);
    }

    @Bean
    public Queue queueMessageB() {
        // 第一个是 QUEUE 的名字,第二个是消息是否需要持久化处理
        return new Queue(MANUAL_BOOK_QUEUE, true);
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    Binding bindingExchangeMessage(Queue queueMessageA, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueMessageA).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeMessages(Queue queueMessageB, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueMessageB).to(fanoutExchange);
    }
}
*/
