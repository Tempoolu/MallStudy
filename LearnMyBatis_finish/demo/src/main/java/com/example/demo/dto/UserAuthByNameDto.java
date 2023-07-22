package com.example.demo.dto;

import com.example.demo.mbg.model.User;
import com.example.demo.mbg.model.UserAuth;

public class UserAuthByNameDto extends User {
    private UserAuth userAuth;

    public UserAuth getUserAuth() {
        return userAuth;
    }

    public void setUserAuth(UserAuth userAuth) {
        this.userAuth = userAuth;
    }
}
