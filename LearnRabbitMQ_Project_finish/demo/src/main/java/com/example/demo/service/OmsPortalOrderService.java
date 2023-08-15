package com.example.demo.service;

import com.example.demo.dto.OrderParam;
import org.springframework.transaction.annotation.Transactional;

public interface OmsPortalOrderService {
    @Transactional
    void generateOrder(OrderParam orderParam);

    @Transactional
    void cancelOrder(Long orderId);
}
