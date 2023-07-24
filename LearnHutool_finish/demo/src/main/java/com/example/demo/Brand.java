package com.example.demo;

import lombok.Data;

@Data
public class Brand {
    private long id;
    private String name;
    private int status;

    public void setId(long id) {
        this.id = id;
    }
}
