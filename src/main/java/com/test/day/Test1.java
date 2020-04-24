package com.test.day;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(value = SpringJUnit4ClassRunner.class) //在使用所有注释前必须使用@RunWith(SpringJUnit4ClassRunner.class),让测试运行于spring测试环境
@TestPropertySource("classpath:test.properties")//用来指定加载的Spring配置文件的位置,会加载默认配置文件
@SpringBootTest
public class Test1 {

    @Value("${test.name}")
    private String name;


    @Test
    public void test(){
        System.out.println("name："+name);

    }

}


//    docker run -d --name gitlab-runner --restart always -v /srv/gitlab-runner/config:/etc/gitlab-runner -v /var/run/docker.sock:/var/run/docker.sock gitlab/gitlab-runner:1.11.1
//
//
//    docker run --rm -v /srv/gitlab-runner/config:/etc/gitlab-runner gitlab/gitlab-runner register \
//        --non-interactive \
//        --executor "docker" \
//        --docker-image alpine:latest \
//        --url "http://192.0.0.167/" \
//        --registration-token "-cX28vzzdPtzFp9pYcor" \
//        --description "docker-runner" \
//        --tag-list "docker,aws" \
//        --run-untagged="true" \
//        --locked="false" \
//        --access-level="not_protected"
