package com.test.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@WebListener

public class MyHttpSessionListener implements HttpSessionListener  {
    public static AtomicInteger count = new AtomicInteger(0);   //记录在线的用户数量

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        log.info("新用户上线了");
        int i = count.incrementAndGet();
        httpSessionEvent.getSession().setAttribute("count", i);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        log.info("用户下线了");
        int i = count.decrementAndGet();
        httpSessionEvent.getSession().setAttribute("count", i);
    }
}