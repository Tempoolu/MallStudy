package com.example.demo.mbg.mapper;

import com.example.demo.mbg.model.SysUserRole;
import com.example.demo.mbg.model.SysUserRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserRoleMapper {
    long countByExample(SysUserRoleExample example);

    int deleteByExample(SysUserRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysUserRole row);

    int insertSelective(SysUserRole row);

    List<SysUserRole> selectByExample(SysUserRoleExample example);

    SysUserRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") SysUserRole row, @Param("example") SysUserRoleExample example);

    int updateByExample(@Param("row") SysUserRole row, @Param("example") SysUserRoleExample example);

    int updateByPrimaryKeySelective(SysUserRole row);

    int updateByPrimaryKey(SysUserRole row);
}