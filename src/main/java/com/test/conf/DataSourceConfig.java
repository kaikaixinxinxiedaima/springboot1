package com.test.conf;

import com.alibaba.druid.support.http.WebStatFilter;
import com.google.common.collect.Maps;
import com.test.annotation.MoreDataSource;
import com.test.dataSource.DataSourceHolder;
import com.test.dataSource.DynamicDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.Map;

//@Configuration
public class DataSourceConfig {
//    @Bean
//    @Primary
//    public DataSource dataSource() {
//        DynamicDataSource resolver = new DynamicDataSource();
//        Map<Object, Object> dataSources = Maps.newHashMap();
//        dataSources.put(MoreDataSource.master, masterDataSource());
//        dataSources.put(MoreDataSource.slave, slaveDataSource());
//        resolver.setTargetDataSources(dataSources);
//        return resolver;
//    }
//
//    @Bean
//    @ConfigurationProperties(prefix="spring.datasource.druid.master")
//    public DataSource masterDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean
//    @ConfigurationProperties(prefix="spring.datasource.druid.slave")
//    public DataSource slaveDataSource() {
//        return DataSourceBuilder.create().build();
//    }

}
