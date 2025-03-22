package com.yunqi.myeshop.controller;

import com.yunqi.myeshop.entity.user.User;
import com.yunqi.myeshop.entity.userdto.*;
import com.yunqi.myeshop.service.implementation.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServices userServices;

    @GetMapping("/{user_id}")
    public UserDetailDto getUserByUserId(@PathVariable int user_id) {
        return userServices.getUserByUserId(user_id);
    }
    @GetMapping
    public List<UserDetailDto> getUsers() {
        return userServices.getAllUsers();
    }
    @PostMapping
    public int createUser(@RequestBody User user) {
        return userServices.createUser(user);
    }
    @DeleteMapping("/{id}")
    public int deleteUserById(@PathVariable int user_id) {
        return userServices.deleteUserByUserId(user_id);
    }
}
