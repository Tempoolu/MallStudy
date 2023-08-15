package com.example.demo.nosql.document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;
import java.util.Date;

@Data
@EqualsAndHashCode
@Document
public class MemberReadHistory {
    @Id
    private String id;

    @Indexed
    private Long memberId;

    @Indexed
    private Long productId;

    private String memberNickname;
    private String memberIcon;
    private String productName;
    private String productPic;
    private String productSubTitle;
    private String productPrice;
    private Date createTime;
}
