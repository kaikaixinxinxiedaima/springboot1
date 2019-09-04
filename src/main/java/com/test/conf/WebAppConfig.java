package com.test.conf;

import com.test.interceptor.LogInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 配置拦截器，拦截规则
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

    /**
     * addPathPatterns 配置需要拦截的请求路径，excludePathPatterns配置不需要拦截的请求路径。
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor()).addPathPatterns ("/**");
        super.addInterceptors(registry);
    }
}