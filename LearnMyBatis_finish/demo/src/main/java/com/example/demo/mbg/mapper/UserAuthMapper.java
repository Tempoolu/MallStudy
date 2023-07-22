package com.example.demo.mbg.mapper;

import com.example.demo.mbg.model.UserAuth;
import com.example.demo.mbg.model.UserAuthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserAuthMapper {
    long countByExample(UserAuthExample example);

    int deleteByExample(UserAuthExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserAuth row);

    int insertSelective(UserAuth row);

    List<UserAuth> selectByExample(UserAuthExample example);

    UserAuth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") UserAuth row, @Param("example") UserAuthExample example);

    int updateByExample(@Param("row") UserAuth row, @Param("example") UserAuthExample example);

    int updateByPrimaryKeySelective(UserAuth row);

    int updateByPrimaryKey(UserAuth row);
}