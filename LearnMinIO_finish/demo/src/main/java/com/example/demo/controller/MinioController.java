package com.example.demo.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.example.demo.common.api.CommonResult;
import com.example.demo.dto.BucketPolicyConfigDto;
import com.example.demo.dto.MinioUploadDto;
import io.minio.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/minio")
public class MinioController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MinioController.class);
    @Value("${minio.endpoint}")
    private String ENDPOINT;
    @Value("${minio.bucketName}")
    private String BUCKET_NAME;
    @Value("${minio.accessKey}")
    private String ACCESS_KEY;
    @Value("${minio.secretKey}")
    private String SECRET_KEY;

    @PostMapping("/upload")
    public CommonResult upload(@RequestPart("file") MultipartFile file) {
        try {
            // 创建一个minio的java客户端
            MinioClient minioClient = MinioClient.builder()
            .endpoint(ENDPOINT).credentials(ACCESS_KEY, SECRET_KEY).build();
            boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(BUCKET_NAME).build());
            if (isExist) {
                LOGGER.info("Bucket existed!");
            } else {
                // 创建存储桶并设置只读权限
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(BUCKET_NAME).build());
                BucketPolicyConfigDto bucketPolicyConfigDto = createBucketPolicyConfigDto(BUCKET_NAME);
                SetBucketPolicyArgs setBucketPolicyArgs = SetBucketPolicyArgs.builder()
                        .bucket(BUCKET_NAME)
                        .config(JSONUtil.toJsonStr(bucketPolicyConfigDto))
                        .build();
                minioClient.setBucketPolicy(setBucketPolicyArgs);
            }
            String filename = file.getOriginalFilename();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            // 设置从存储对象名称
            String objectName = sdf.format(new Date()) + "/" +filename;
            // 使用putObject上传一个文件到存储桶里
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .bucket(BUCKET_NAME)
                    .object(objectName)
                    .contentType(file.getContentType())
                    .stream(file.getInputStream(), file.getSize(), ObjectWriteArgs.MIN_MULTIPART_SIZE).build();
            minioClient.putObject(putObjectArgs);
            LOGGER.info("Upload successfully");
            MinioUploadDto minioUploadDto = new MinioUploadDto();
            minioUploadDto.setName(filename);
            minioUploadDto.setUrl(ENDPOINT+"/"+BUCKET_NAME+"/"+objectName);
            return CommonResult.success(minioUploadDto);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("Upload error: {}", e.getMessage());
        }
        return CommonResult.failed();
    }

    // 创建存储桶的访问策略，设置为只读权限
    private BucketPolicyConfigDto createBucketPolicyConfigDto(String bucketName) {
        BucketPolicyConfigDto.Statement statement = BucketPolicyConfigDto.Statement.builder()
                .Effect("Allow")
                .Principal("*")
                .Action("s3:GetObject")
                .Resource("arn:aws:s3:::"+bucketName+"/*.**").build();
        return BucketPolicyConfigDto.builder()
                .Version("2012-10-17")
                .Statement(CollUtil.toList(statement))
                .build();
    }

    @PostMapping("/delete")
    public CommonResult delete(@RequestParam("objectName") String objectName) {
        try {
            MinioClient minioClient = MinioClient.builder()
                    .endpoint(ENDPOINT)
                    .credentials(ACCESS_KEY, SECRET_KEY)
                    .build();
            minioClient.removeObject(
                    RemoveObjectArgs.builder().bucket(BUCKET_NAME).object(objectName).build()
            );
            return CommonResult.success(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonResult.failed();
    }
}
