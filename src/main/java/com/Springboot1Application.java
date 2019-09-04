package com;

//import org.mybatis.spring.annotation.MapperScan;
import com.test.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

import java.util.Date;

//exclude = DataSourceAutoConfiguration.class
@SpringBootApplication(exclude=DataSourceAutoConfiguration.class)
@MapperScan("com.test.mapper")//将项目中对应的mapper类的路径加进来就可以了
@EnableScheduling //开启定时任务
public class Springboot1Application extends SpringBootServletInitializer {

    private static Logger logger = LoggerFactory.getLogger(Springboot1Application.class);

    public static void main(String[] args) {
        //java.lang.IllegalStateException: availableProcessors is already set to [4], rejecting [4] 解决
        //System.setProperty("es.set.netty.runtime.available.processors", "false");

        SpringApplication.run(Springboot1Application.class, args);
        logger.info("项目启动成功！~~~~~~~~~~~~~~~~~ {}",DateUtils.getCurrentDateMillTime());
    }

    @Override//为了打包springboot项目
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
}
