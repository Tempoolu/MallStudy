package com.example.demo.dto;

import com.example.demo.mbg.model.User;
import com.example.demo.mbg.model.UserOrder;

import java.util.List;

public class OrdersByUserNameDto extends User {
    private List<UserOrder> orderList;

    public List<UserOrder> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<UserOrder> orderList) {
        this.orderList = orderList;
    }
}
