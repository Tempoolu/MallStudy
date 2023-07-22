package com.example.demo.mbg.mapper;

import com.example.demo.mbg.model.SysRole;
import com.example.demo.mbg.model.SysRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysRoleMapper {
    long countByExample(SysRoleExample example);

    int deleteByExample(SysRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SysRole row);

    int insertSelective(SysRole row);

    List<SysRole> selectByExample(SysRoleExample example);

    SysRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") SysRole row, @Param("example") SysRoleExample example);

    int updateByExample(@Param("row") SysRole row, @Param("example") SysRoleExample example);

    int updateByPrimaryKeySelective(SysRole row);

    int updateByPrimaryKey(SysRole row);
}