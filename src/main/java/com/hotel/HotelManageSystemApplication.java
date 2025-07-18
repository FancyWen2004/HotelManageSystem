package com.hotel;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.hotel.mapper")
@EnableScheduling  // 启用定时任务
@EnableTransactionManagement
public class HotelManageSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelManageSystemApplication.class, args);
    }

}
