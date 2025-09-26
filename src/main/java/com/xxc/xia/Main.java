package com.xxc.xia;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * desc..
 * @author xxc
 * @version 2025/9/24 0:05
 */
@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableTransactionManagement
@MapperScan(basePackages = { "com.xxc.xia.mapper" })
@Slf4j
public class Main {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Main.class, args);
        log.info("swagger addr: http://localhost:{}/swagger-ui/index.html",
            applicationContext.getEnvironment().getProperty("server.port"));
    }
}