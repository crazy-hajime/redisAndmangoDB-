package com.nd.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
* 1.创建maven项目
* 2.删除src目录，创建模块
* 3.引入依赖
* 4.配置文件
* 5.创建启动类
* 6.创建目录dao,service,controller
*
*
* */
@SpringBootApplication
public class RedisCrudApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisCrudApplication.class, args);
    }
}
