package com.kteck;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.kteck.mapper")
public class ProxyApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProxyApplication.class);
    }
}
