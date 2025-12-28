package com.nd.mongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class}) 排除默认加载的数据源
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MongodbCartApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongodbCartApplication.class, args);
    }
}
