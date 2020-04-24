package com.test.day;

public class ExamplesFactory {
    //实例工厂
    public TestService getTestService(){
        return new TestService();
    }
}
