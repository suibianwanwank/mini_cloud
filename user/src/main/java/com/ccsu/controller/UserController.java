package com.ccsu.controller;

import com.ccsu.pojo.User;
import com.ccsu.service.UserService;
import com.ccsu.service.UserServiceImpl;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

    @Resource
    UserService userService;

    @RequestMapping("user")
    public void user(){
        userService.userUse();
        System.out.println("controller,user");
    }

    @RequestMapping("user1/{id}")
    public void getUser(@PathVariable Integer id){
        User user = userService.getUser(id);
        System.out.println(user);
        System.out.println("controller,user");
    }

}
