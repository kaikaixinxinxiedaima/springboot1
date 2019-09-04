package com.test.exception;

import com.alibaba.fastjson.JSONObject;

public class MyException extends RuntimeException{
    private JSONObject object ;

    public JSONObject getObject() {
        return object;
    }

    public void setObject(JSONObject object) {
        this.object = object;
    }

    public MyException(JSONObject object1){
        this.object = object1;
    }

    public int  MyException(JSONObject object1){
        this.object = object1;
        return 0;
    }

}
