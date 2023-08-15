package com.example.demo.elasticsearch.repository;

import com.example.demo.elasticsearch.document.EsProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EsProductRepository extends ElasticsearchRepository<EsProduct, Long> {

    // 继承ElasticsearchRepository，拥有一些基本的数据操作方式，同时定义了衍生查询方法
    // es可以根据名称自动完成接口
    Page<EsProduct> findByNameOrSubTitleOrKeywords(String name, String subTitle, String keywords, Pageable page);
}
