package com.test.day;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAsp {
    private final static Logger logger = LoggerFactory.getLogger(LogAsp.class);

    // 声明一个切入点
    @Pointcut("execution(public * com.test.day.TestService.*(..))")
    public void aspect() {
    }

    @Before("aspect()")
    public void beforeAdvice(){
        logger.info("前置通知。。。");
    }

    @After("aspect()")
    public void afterAdvice(){
        logger.info("后置通知。。。");
    }

    @AfterReturning(pointcut = "aspect()", returning="retVal")
    public void afterReturningAdvice(Object retVal){
        logger.info("返回后通知。。。" );
    }



    @AfterThrowing(pointcut = "aspect()", throwing = "ex")
    public void AfterThrowingAdvice(Exception ex){
        logger.info("抛出异常后通知...");
    }

    @Around("aspect()")
    public Object aroundAdvice(ProceedingJoinPoint pjp){
        Object obj = null;
        try {
            Object[] args = pjp.getArgs();// 得到方法所需的参数
            logger.info("环绕通知:前置...");
            //明确调用业务层方法
            obj = pjp.proceed(args);
            logger.info("环绕通知:后置...");
            return obj;
        } catch (Throwable throwable) {
//            logger.info("环绕通知:异常...");
            throw new RuntimeException(throwable);
        }finally {
//            logger.info("环绕通知:最终...");
        }
    }

}
