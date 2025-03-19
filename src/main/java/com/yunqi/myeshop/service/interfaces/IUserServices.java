package com.yunqi.myeshop.service.interfaces;

import com.yunqi.myeshop.entity.user.User;
import java.util.List;

public interface IUserServices {
    User getUserByUserId(int user_id);
    List<User> getAllUsers();
    int createUser(User user);
    int updateUser(User user);
    int deleteUserByUserId(int user_id);
}
