package com.example.demo.RedisLock;

import com.test.lock.RedisLock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * 测试redis分布式锁
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestRedisLock {
    final static String lockKey = "jiaobaba";

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public TestRedisLock() {
        //在构造函数上写上这个
        System.setProperty("es.set.netty.runtime.available.processors","false");
    }

    @Test
    public void test() throws InterruptedException {
        CountDownLatch downLatch = new CountDownLatch(20);

        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                do{

                }while (!func());
//                func1();

                downLatch.countDown();
            }).start();
        }

        downLatch.await();

        System.out.println("全部任务执行完毕");
    }

    public boolean func(){
        String lockValue = UUID.randomUUID().toString();
        try{
            //获取锁
            Boolean flag = RedisLock.myTryLock(lockKey, lockValue);//本质上调用的就是setnx
            if(!flag){
                return false;
            }

            //开启续约
            RedisLock.autoWatchDog(lockKey, lockValue);
            System.out.println(lockValue + "获取锁成功，执行业务");

//            try {
//                Thread.sleep(150000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //释放锁
            boolean unLock = RedisLock.myUnLock(lockKey, lockValue);
            if(unLock){
                System.out.println(lockValue + "业务执行完，释放锁");
            }
        }

        //标识获取锁成功
        return true;
    }

    public void func1(){
        RLock rLock = RedisLock.redissonGetLock(lockKey);
        try{
            //加锁
            rLock.lock();
            System.out.println(Thread.currentThread().getName() + "获取锁成功，执行业务");
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //释放锁
            RedisLock.redissonUnLock(rLock);
            System.out.println(Thread.currentThread().getName() + "业务执行完，释放锁");
        }
    }
}
