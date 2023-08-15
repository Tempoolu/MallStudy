package com.example.demo.service.impl;

import com.example.demo.component.CancelOrderSender;
import com.example.demo.dto.OrderParam;
import com.example.demo.service.OmsPortalOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OmsPortalOrderServiceImpl implements OmsPortalOrderService {
    private static Logger LOGGER = LoggerFactory.getLogger(OmsPortalOrderServiceImpl.class);

    @Autowired
    private CancelOrderSender sender;
    @Override
    public void generateOrder(OrderParam orderParam) {
        LOGGER.info("process generate order");
        sendDelayMessageCancelOrder(1L);
    }
    public void cancelOrder(Long orderId) {
        LOGGER.info("process cancel order {}", orderId);
    }

    private void sendDelayMessageCancelOrder(Long orderId) {
        long delayTime = 30*1000; //测试用30秒
        sender.sendMessage(orderId, delayTime);
    }


}
