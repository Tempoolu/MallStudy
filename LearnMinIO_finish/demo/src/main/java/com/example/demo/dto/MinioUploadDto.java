package com.example.demo.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class MinioUploadDto {
    // 文件访问url
    private String url;
    // 文件名
    private String name;
}
