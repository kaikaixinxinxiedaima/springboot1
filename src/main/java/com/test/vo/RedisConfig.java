package com.test.vo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
@ConfigurationProperties(prefix = "spring.redis")//前缀
public class RedisConfig {
    private String host;

    private String password;

    private int port;

    private int timeout;

   private HashMap<String,String> cluster;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public HashMap<String, String> getCluster() {
        return cluster;
    }

    public void setCluster(HashMap<String, String> cluster) {
        this.cluster = cluster;
    }


}
