package com.test.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 监听器类
 */
@Component
public class MyListener2 {
    @EventListener
    public void say(MyEvent event){
        System.out.println("猴哥大喊：" + event.getSource() + "赶快收衣服喽！");
    }
}
