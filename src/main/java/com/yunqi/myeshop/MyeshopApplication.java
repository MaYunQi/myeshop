package com.yunqi.myeshop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class MyeshopApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyeshopApplication.class, args);
    }
}
