package com.yunqi.myeshop.service.interfaces;

import com.yunqi.myeshop.entity.user.User;
import com.yunqi.myeshop.entity.userdto.UserDetailDto;

import java.util.List;

public interface IUserServices {
    UserDetailDto getUserByAccountId(int account_id);
    UserDetailDto getUserByUserId(int user_id);
    List<UserDetailDto> getAllUsers();
    int createUser(User user);
    int deleteUserByUserId(int user_id);
}
