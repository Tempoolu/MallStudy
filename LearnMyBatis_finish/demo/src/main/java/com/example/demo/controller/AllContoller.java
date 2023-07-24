package com.example.demo.controller;

import com.example.demo.dto.GenderGroupDto;
import com.example.demo.dto.OrdersByUserName2Dto;
import com.example.demo.dto.UserAuthByNameDto;
import com.example.demo.mbg.model.SysRole;
import com.example.demo.mbg.model.User;
import com.example.demo.service.AllService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@Api(tags = "AllControllerApi")
public class AllContoller {

    @Autowired
    private AllService allService;

    @PostMapping("sysrole/create")
    public void createSysRole(@RequestBody SysRole sysRole) {
        allService.createSysRole(sysRole);
    }


    @ApiOperation("Get all system role")
    @GetMapping("sysrole/getall")
    public List<SysRole> getAllSysRoles() {
        return allService.getAllSysRoles();
    }

    @GetMapping("user/get_user_by_gender")
    public List<User> getAllFemaleByNameOrder(@RequestParam("gender") String gender) {
        return allService.getUserByNameOrder(gender);
    }

    @GetMapping("user/get_role_by_name")
    public String getRolenameByUserName(@RequestParam("username") String username) {
        return allService.getRolenameByUserName(username);
    }

    @GetMapping("user/get_gender_group_by_rolename")
    public List<GenderGroupDto> getGenderGroupByRolename(@RequestParam("rolename") String rolename) {
        return allService.getGenderGroupByRolename(rolename);
    }

    @GetMapping("user/get_orders_by_username")
    public OrdersByUserName2Dto getOrdersByUsername(@RequestParam("username") String username) {
        return allService.getOrdersByUsername(username);
    }

    @GetMapping("user/get_userauth_by_username")
    public UserAuthByNameDto getUserAuthByName(@RequestParam("username") String username) {
        return allService.getUserAuthByName(username);
    }
}
