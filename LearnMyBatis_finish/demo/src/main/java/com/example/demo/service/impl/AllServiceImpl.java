package com.example.demo.service.impl;

import com.example.demo.dao.AllDao;
import com.example.demo.dto.GenderGroupDto;
import com.example.demo.dto.OrdersByUserName2Dto;
import com.example.demo.dto.UserAuthByNameDto;
import com.example.demo.mbg.mapper.SysRoleMapper;
import com.example.demo.mbg.mapper.UserMapper;
import com.example.demo.mbg.model.*;
import com.example.demo.service.AllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AllServiceImpl implements AllService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AllDao allDao;

    @Override
    public void createSysRole(SysRole sysRole) {
        sysRoleMapper.insert(sysRole);
    }

    @Override
    public List<SysRole> getAllSysRoles() {
        return sysRoleMapper.selectByExample(new SysRoleExample());
    }

    @Override
    public List<User> getUserByNameOrder(String gender) {
        UserExample orderExample = new UserExample();
        UserExample.Criteria criteria = orderExample.createCriteria();

        // 设定条件
        criteria.andGenderEqualTo(gender);

        // 设定排序根据
        orderExample.setOrderByClause("username");

        return userMapper.selectByExample(orderExample);
    }

    @Override
    public String getRolenameByUserName(String username) {

        // 根据username获取rolename，用subquery
        return allDao.getRolenameByUserName(username);
    }

    @Override
    public List<GenderGroupDto> getGenderGroupByRolename(String rolename) {

        // #选择特定rolename，group by性别，并计算count。返回的是自己定义的一个dto类
        return allDao.getGenderGroupByRolename(rolename);
    }

    public OrdersByUserName2Dto getOrdersByUsername(String username) {
        return allDao.getOrdersByUsername(username);
    }

    public UserAuthByNameDto getUserAuthByName(String username) {return allDao.getUserAuthByName(username);}
}
