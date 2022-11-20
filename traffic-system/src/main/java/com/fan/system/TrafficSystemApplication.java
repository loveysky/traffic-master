package com.fan.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fan.system.mapper")
public class TrafficSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrafficSystemApplication.class, args);
    }

}
