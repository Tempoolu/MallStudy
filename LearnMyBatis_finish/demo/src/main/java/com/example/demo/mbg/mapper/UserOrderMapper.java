package com.example.demo.mbg.mapper;

import com.example.demo.mbg.model.UserOrder;
import com.example.demo.mbg.model.UserOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserOrderMapper {
    long countByExample(UserOrderExample example);

    int deleteByExample(UserOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserOrder row);

    int insertSelective(UserOrder row);

    List<UserOrder> selectByExample(UserOrderExample example);

    UserOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") UserOrder row, @Param("example") UserOrderExample example);

    int updateByExample(@Param("row") UserOrder row, @Param("example") UserOrderExample example);

    int updateByPrimaryKeySelective(UserOrder row);

    int updateByPrimaryKey(UserOrder row);
}