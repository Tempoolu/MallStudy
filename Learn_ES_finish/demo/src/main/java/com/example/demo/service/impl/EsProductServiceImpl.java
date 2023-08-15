package com.example.demo.service.impl;

import com.example.demo.dao.EsProductDao;
import com.example.demo.elasticsearch.document.EsProduct;
import com.example.demo.elasticsearch.repository.EsProductRepository;
import com.example.demo.mbg.mapper.PmsProductMapper;
import com.example.demo.mbg.model.PmsProduct;
import com.example.demo.mbg.model.PmsProductExample;
import com.example.demo.service.EsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class EsProductServiceImpl implements EsProductService {

    @Autowired
    private PmsProductMapper pmsProductMapper;
    @Autowired
    private EsProductDao productDao;
    @Autowired
    private EsProductRepository productRepo;


    public int importAll() {
        List<EsProduct> esProductList = productDao.getAllEsProductList(null);
        Iterable<EsProduct> iterable = productRepo.saveAll(esProductList);
        Iterator<EsProduct> iterator = iterable.iterator();
        int result = 0;
        if (iterator.hasNext()) {
            result++;
            iterator.next();
        }
        return result;
    }
    public void delete(Long id) {
        productRepo.deleteById(id);
    }

    public EsProduct create(Long id) {
        // 从数据库根据id获取EsProduct，将任何一个的信息存入es
        List<EsProduct> esProductList = productDao.getAllEsProductList(id);
        EsProduct result = null;
        if (esProductList.size()>0) {
            EsProduct esProduct = esProductList.get(0);
            result = productRepo.save(esProduct);
        }

        return result;
    }

    public void delete(List<Long> ids) {
        if (ids.size() > 0) {
            productRepo.deleteAllById(ids);
        }
    }

    public Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return productRepo.findByNameOrSubTitleOrKeywords(keyword,keyword, keyword, pageable);
    }
}
