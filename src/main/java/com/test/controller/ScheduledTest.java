package com.test.controller;


import com.test.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Async
public class ScheduledTest {
    private static Logger logger = LoggerFactory.getLogger(ScheduledTest.class);

    //@Scheduled(cron = "0/5 * * * * *")
    public void scheduled() throws InterruptedException {
        logger.info("=====>>>>>cron执行-{}",DateUtils.getCurrentDateMillTime());
        Thread.sleep(5000);


    }

    //@Scheduled(fixedRate = 5000)
    public void scheduled1() throws InterruptedException {
        logger.info("=====>>>>>fixedRate执行-{}", DateUtils.getCurrentDateMillTime());
        Thread.sleep(4000);
    }

    //@Scheduled(fixedDelay = 5000)
    public void scheduled2() throws InterruptedException {
        logger.info("=====>>>>>fixedDelay执行-{}",DateUtils.getCurrentDateMillTime());
        Thread.sleep(3000);
    }
}
