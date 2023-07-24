package com.example.demo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.example.demo.model.Student;
import com.example.demo.service.RedisService;
import com.example.demo.service.StudentService;
import com.example.demo.congfig.RedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private RedisService redisService;
    @Value("Student:")
    private  String Student_Key;

    @CachePut(value = RedisConfig.REDIS_KEY_DATABASE, key="'Student:'+#student.id")
    @Override
    public Student insert(Student student) {
        Map<String, Object> stuMap = BeanUtil.beanToMap(student);
        redisService.hSetAll(Student_Key + student.getId(), stuMap);
        System.out.println("hello");
        return student;
    }
}
