package com.test.controller;


import com.test.annotation.jwt.UserLoginToken;
import com.test.util.jwt.JWTUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试jwt
 */

@RestController
@RequestMapping("/jwt")
public class JwtController {
    /**
     * 签名 此签名为 rayfoo 的16位 大写 MD5
     */
    private static String secret;

    /**
     * 默认的过期时间，30分钟
     */
    private static String expire;

    /**
     * token默认的长度
     */
    private static String header;

    @Value("${config.jwt.secret}")
    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Value("${config.jwt.expire}")
    public void setExpire(String expire) {
        this.expire = expire;
    }

    @Value("${config.jwt.header}")
    public void setHeader(String header) {
        this.header = header;
    }

    @RequestMapping("/jwtLogin")
    public String test(String name, String password) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("password", password);
        String token = JWTUtil.getToken(map);
        return token;
    }


    @RequestMapping("/test")
    @UserLoginToken
    public String test1(String name, String password) throws Exception {
        return expire;
    }
}
