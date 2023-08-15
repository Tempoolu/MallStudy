package com.example.demo.controller;

import com.example.demo.elasticsearch.document.EsProduct;
import com.example.demo.service.EsProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/esProduct")
public class EsProductController {
    @Autowired
    private EsProductService esProductService;

    @PostMapping ("/importAll")
    public int importAll() {
        return esProductService.importAll();
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        esProductService.delete(id);
    }

    @PostMapping("/delete/batch")
    public void delete(@RequestParam("ids") List<Long> ids) {
        esProductService.delete(ids);
    }

    @PostMapping("/create/{id}")
    public EsProduct create(@RequestParam("id") Long id) {
        EsProduct esProduct = esProductService.create(id);
        if (esProduct == null) {
            throw new RuntimeException();
        }
        return esProduct;
    }

    @GetMapping("/search/simple")
    public Page<EsProduct> search(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false, defaultValue = "0") Integer pageNum,
            @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        Page<EsProduct> esProductPage =  esProductService.search(keyword, pageNum, pageSize);
        return esProductPage;
    }

}
