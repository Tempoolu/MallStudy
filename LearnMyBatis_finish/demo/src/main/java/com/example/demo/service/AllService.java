package com.example.demo.service;

import com.example.demo.dto.GenderGroupDto;
import com.example.demo.dto.OrdersByUserName2Dto;
import com.example.demo.dto.UserAuthByNameDto;
import com.example.demo.mbg.model.SysRole;
import com.example.demo.mbg.model.User;

import java.util.List;

public interface AllService {
    void createSysRole(SysRole sysRole);

    List<SysRole> getAllSysRoles();

    List<User> getUserByNameOrder(String gender);

    String getRolenameByUserName(String username);

    List<GenderGroupDto> getGenderGroupByRolename(String rolename);

    OrdersByUserName2Dto getOrdersByUsername(String username);

    UserAuthByNameDto getUserAuthByName(String username);
}
