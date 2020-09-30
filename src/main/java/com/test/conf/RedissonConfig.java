package com.test.conf;

import org.redisson.Redisson;
import org.redisson.config.*;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissonConfig {
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private String port;

    @Bean
    public Redisson redisson(){
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://"+host+":"+port);

        return (Redisson)Redisson.create(config);
    }

}
