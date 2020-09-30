package com.test.lock;


import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * redis实现分布式锁
 * 基于setnx lua
 */
@Component
public class RedisLock {
    private static ResourceLoader resourceLoader = new DefaultResourceLoader();
    private static final int DEFAULT_TIME_OUT = 10; // 30 s
    private static final int DEFAULT_RELOCK_TIME = 1; // 10 s
    private static RedisSerializer<String> argsSerializer  = new StringRedisSerializer();
    private static RedisSerializer<String> resultSerializer = new StringRedisSerializer();
    private static RedisTemplate<String, String> redisTemplate;
    private static Redisson redisson;

    /**
     * 解决静态属性使用@Autowired注入
     * @param redisTemplate
     */
    @Autowired
    public RedisLock(RedisTemplate<String, String> redisTemplate, Redisson redisson){
        this.redisTemplate = redisTemplate;
        this.redisson = redisson;
    }

    //获取锁
    public static boolean myTryLock(String lockKey, String lockValue){
        //获取lua脚本
        DefaultRedisScript lockRedisScript =new DefaultRedisScript<>();
        lockRedisScript.setLocation(new ClassPathResource("script/lock.lua"));
        // 这个值类型要跟lua返回值类型一致才行，否则就会报 java.lang.IllegalStateException
        lockRedisScript.setResultType(Long.class);
        List<String> keys = Collections.singletonList(lockKey);
        Object result = redisTemplate.execute(lockRedisScript, argsSerializer, resultSerializer, keys, lockValue, String.valueOf(DEFAULT_TIME_OUT));
        if("1".equals(String.valueOf(result))){
            return true;
        }
        return false;
    }

    //释放锁
    public static boolean myUnLock(String lockKey, String lockValue){
        //获取lua脚本
        DefaultRedisScript unLockRedisScript =new DefaultRedisScript<>();
        unLockRedisScript.setLocation(new ClassPathResource("script/unLock.lua"));
        // 这个值类型要跟lua返回值类型一致才行，否则就会报 java.lang.IllegalStateException
        unLockRedisScript.setResultType(Long.class);
        List<String> keys = Collections.singletonList(lockKey);
        Object result = redisTemplate.execute(unLockRedisScript, argsSerializer, resultSerializer, keys, lockValue);
        if("1".equals(String.valueOf(result))){
            return true;
        }
        return false;
    }

    public static void autoWatchDog(String lockKey, String lockValue){
        new WatchDog(lockKey, lockValue).start();
    }


    //实现自动续约，看门狗
    @Data
    static class WatchDog extends Thread{
        //业务是否执行完
        private boolean businessDone;
        private String lockKey;
        private String lockValue;

        public WatchDog(String lockKey, String lockValue){
            this.lockKey = lockKey;
            this.lockValue = lockValue;
        }

        @Override
        public void run() {
            ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
            while(!businessDone){
                service.schedule(()->{
                    //获取lua脚本
                    DefaultRedisScript lockRedisScript =new DefaultRedisScript<>();
                    lockRedisScript.setLocation(new ClassPathResource("script/lock.lua"));
                    // 这个值类型要跟lua返回值类型一致才行，否则就会报 java.lang.IllegalStateException
                    lockRedisScript.setResultType(Long.class);
                    List<String> keys = Collections.singletonList(lockKey);
                    Object result = redisTemplate.execute(lockRedisScript, argsSerializer, resultSerializer, keys, lockValue, String.valueOf(DEFAULT_TIME_OUT));
                    if(!"1".equals(String.valueOf(result))){
                        businessDone = true;
                    }
                },RedisLock.DEFAULT_RELOCK_TIME, TimeUnit.SECONDS);
            }
        }
    }






    //根据key获取value
    public static String myGetValueByKey(String lockKey){
        return redisTemplate.opsForValue().get(lockKey);
    }


    //redisson获取锁
    public static RLock redissonGetLock(String lockKey){
        return redisson.getLock(lockKey);
    }

    //redisson释放锁
    public static void redissonUnLock(RLock rLock){
        rLock.unlock();
    }

}
