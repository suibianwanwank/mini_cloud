package com.ccsu.mapper;


import com.ccsu.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    public User getUserById(Integer id);
}
