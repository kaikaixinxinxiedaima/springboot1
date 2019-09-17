package com.test.aop;

import com.test.annotation.MoreDataSource;
import com.test.dataSource.DataSourceHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class DataSourceAspect {
    @Pointcut("@annotation(com.test.annotation.MoreDataSource)")
    public void aspect() {
    }

    @Before("aspect()")
    public void doBefore(JoinPoint point) throws Throwable {
        final MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Method method = methodSignature.getMethod();
        MoreDataSource mzDataSource = method.getAnnotation(MoreDataSource.class);
        if (method.getDeclaringClass().isInterface()) {
            method = point.getTarget().getClass().getMethod(method.getName(), method.getParameterTypes());
        }
        mzDataSource = method.getAnnotation(MoreDataSource.class);
        if (null != mzDataSource) {
            DataSourceHolder.setDataSourceKey(mzDataSource.name());
        }

        System.out.println("数据源切换：" + DataSourceHolder.getDataSourceKey());
    }

    @After("aspect()")
    public void doAfter() {
        DataSourceHolder.clearDataSourceKey();
    }
}
