package com.dubbo;



public class HelloDubboImpl implements HelloDubbo {
    @Override
    public String sayHello(String msg) {
        return " from server ,I am : " + msg;
    }
}
