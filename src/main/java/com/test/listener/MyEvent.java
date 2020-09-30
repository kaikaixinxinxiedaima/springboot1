package com.test.listener;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义事件(继承ApplicationEvent)
 */
public class MyEvent  extends ApplicationEvent {
    public MyEvent (Object source) {
        super(source);
    }
}
