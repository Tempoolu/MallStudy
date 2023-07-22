package com.example.demo.dto;

import com.example.demo.mbg.model.UserOrder;

import java.util.List;

public class OrdersByUserName2Dto {

    private Integer id;
    private String username;
    private List<OrderDto> orderList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<OrderDto> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderDto> orderList) {
        this.orderList = orderList;
    }
}
