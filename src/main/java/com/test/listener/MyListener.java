package com.test.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * 监听器类
 */
@Component
public class MyListener implements ApplicationListener<MyEvent> {
    @Override
    public void onApplicationEvent(MyEvent myEvent) {
        System.out.println("唐僧大喊：" + myEvent.getSource() + "赶快收衣服喽！");
    }

}