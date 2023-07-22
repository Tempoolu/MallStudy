package com.example.demo.dao;

import com.example.demo.dto.GenderGroupDto;
import com.example.demo.dto.OrdersByUserName2Dto;
import com.example.demo.dto.UserAuthByNameDto;

import java.util.List;

public interface AllDao {
    String getRolenameByUserName(String username);

    List<GenderGroupDto> getGenderGroupByRolename(String rolename);

    OrdersByUserName2Dto getOrdersByUsername(String username);

    UserAuthByNameDto getUserAuthByName(String username);
}
