package com.yunqi.myeshop.service.interfaces;

import com.yunqi.myeshop.entity.User;

import java.util.List;

public interface IUserServices {
    User getUserById(int id);
    List<User> getAllUsers();
    int createUser(User user);
    int updateUser(User user);
    int deleteUserById(int id);
}
