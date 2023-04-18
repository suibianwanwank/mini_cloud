package com.ccsu.service;

import com.ccsu.mapper.UserMapper;
import com.ccsu.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

//    public

    @Override
    public User getUser(Integer id) {
        User user = userMapper.getUserById(id);
        System.out.println("获取user:"+user);
        return user;
    }

    public String userUse(){
        System.out.println("调用userUse方法");
        return "sda";
    }

}
