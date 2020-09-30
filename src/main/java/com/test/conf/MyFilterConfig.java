package com.test.conf;

import com.test.filter.MyFilter;
import org.apache.logging.log4j.core.filter.TimeFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * myfilter配置
 */
@Configuration
public class MyFilterConfig {
    @Bean
    public FilterRegistrationBean timeFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        MyFilter myFilter = new MyFilter();
        filterRegistrationBean.setFilter(myFilter);
        List<String> urls = new ArrayList<>();
        urls.add("/book/*");
        filterRegistrationBean.setUrlPatterns(urls);//配置过滤规则
        return filterRegistrationBean;
    }
}
