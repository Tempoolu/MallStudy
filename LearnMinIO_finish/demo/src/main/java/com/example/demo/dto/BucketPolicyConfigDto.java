package com.example.demo.dto;

import jdk.jfr.DataAmount;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
@Builder
public class BucketPolicyConfigDto {
    // 是存储桶访问策略的封装类，在这里创建存储桶时，对应匿名用户设置了只读权限
    private String Version;
    private List<Statement> Statement;

    @Data
    @EqualsAndHashCode
    @Builder
    public static class Statement {
        private String Effect;
        private String Principal;
        private String Action;
        private String Resource;
    }
}
