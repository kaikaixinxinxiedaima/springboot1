package com.test.day;

public class StaticFactory {
    //静态工厂
    public static final TestService getTestService(){
        return new TestService();
    }
}
