package com;

//import org.mybatis.spring.annotation.MapperScan;
import com.test.conf.DataSourceConfig;
import com.test.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

//exclude = DataSourceAutoConfiguration.class
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@MapperScan("com.test.mapper")//将项目中对应的mapper类的路径加进来就可以了
@EnableScheduling //开启定时任务
@Import({DataSourceConfig.class}) //数据源
@EnableTransactionManagement //开启事务管理
public class Springboot1Application extends SpringBootServletInitializer {

    private static Logger logger = LoggerFactory.getLogger(Springboot1Application.class);

    public static void main(String[] args) throws UnknownHostException {
        //java.lang.IllegalStateException: availableProcessors is already set to [4], rejecting [4] 解决
        //System.setProperty("es.set.netty.runtime.available.processors", "false");

        //看这里，加上这句话
        System.setProperty("es.set.netty.runtime.available.processors","false");
        ConfigurableApplicationContext application = SpringApplication.run(Springboot1Application.class, args);
//        logger.info("项目启动成功！~~~~~~~~~~~~~~~~~ {}",DateUtils.getCurrentDateMillTime());

        /*打印sw2*/
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path");

        logger.info("\n----------------------------------------------------------\n\t" +
                "项目启动成功！~~~~~~~~~~~~~~~~~\n\t" +
                "Local: \t\thttp://localhost:" + port + path + "/\n\t" +
                "External: \thttp://" + ip + ":" + port + path + "/\n\t" +
                "swagger-ui: \thttp://" + ip + ":" + port + path + "/swagger-ui.html\n\t" +
                "Doc: \t\thttp://" + ip + ":" + port + path + "/doc.html\n" +
                "----------------------------------------------------------");
    }

    @Override//为了打包springboot项目
    protected SpringApplicationBuilder configure(
            SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
}
