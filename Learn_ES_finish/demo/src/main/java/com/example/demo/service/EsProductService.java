package com.example.demo.service;

import com.example.demo.elasticsearch.document.EsProduct;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EsProductService {

//    // 从数据库导入所有商品到es
    int importAll();

    // 删除商品
    void delete(Long id);

    // 创建商品
    EsProduct create(Long id);

    // 批量删除商品
    void delete(List<Long> ids);

    // 根据关键字搜索名称或副标题
    Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize);

//    // 根据关键字搜索名称或副标题复合查询
//    Page<EsProduct> search(String keyword, Long brandId, Long productCategoryId, Integer pageNum, Integer pageSize,Integer sort);
//
//    // 根据商品id推荐相关商品
//    Page<EsProduct> recommend(Long id, Integer pageNum, Integer pageSize);
//
//    // 搜索关键词相关品牌/分类/属性
//    EsProductRelatedInfo searchRelatedInfo(String keyword);
}
