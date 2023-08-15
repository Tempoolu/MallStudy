package com.example.demo.controller;

import com.example.demo.dto.OrderParam;
import com.example.demo.service.OmsPortalOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OmsPortalOrderController {
    @Autowired
    private OmsPortalOrderService service;

    @PostMapping("/generate")
    public void generateOrder(@RequestBody OrderParam orderParam) {
        service.generateOrder(orderParam);
    }
}
