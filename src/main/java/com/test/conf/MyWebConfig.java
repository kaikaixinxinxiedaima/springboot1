//package com.test.conf;
//
//import com.test.listener.MyHttpSessionListener;
//import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import javax.servlet.http.HttpSessionListener;
//
///**
// * Linstener配置
// */
//@Configuration
//public class MyWebConfig implements WebMvcConfigurer {
//    @Bean
//    public ServletListenerRegistrationBean<HttpSessionListener> listenerRegist() {
//        ServletListenerRegistrationBean<HttpSessionListener> srb = new ServletListenerRegistrationBean();
//        srb.setListener(new MyHttpSessionListener());
//        System.out.println("-------------------listener注册-------------------");
//        return srb;
//    }
//}