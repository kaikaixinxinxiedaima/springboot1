package com.test.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MoreDataSource {
    public static String master = "master";
    public static String slave = "slave";

    String name() default MoreDataSource.slave;
}
