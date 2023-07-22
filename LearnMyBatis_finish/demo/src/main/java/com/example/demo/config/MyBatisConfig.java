package com.example.demo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

// MBG生成的文件放在mapper包下，自定义的放在dao包下
@Configuration
@MapperScan({"com.example.demo.mbg.mapper", "com.example.demo.dao"})
public class MyBatisConfig {
}
