package com.test.conf;


import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "config.jwt")
@Data
@Getter
public class JwtConfig {
    public static String secret;
    public static Integer expire;
    public static String header;


    public static String getSecret() {
        return secret;
    }

    public static void setSecret(String secret) {
        JwtConfig.secret = secret;
    }

    public static Integer getExpire() {
        return expire;
    }

    public static void setExpire(Integer expire) {
        JwtConfig.expire = expire;
    }

    public static String getHeader() {
        return header;
    }

    public static void setHeader(String header) {
        JwtConfig.header = header;
    }
}
