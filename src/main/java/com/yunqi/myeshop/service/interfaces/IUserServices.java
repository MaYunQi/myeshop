package com.yunqi.myeshop.service.interfaces;

import com.yunqi.myeshop.entity.user.User;
import com.yunqi.myeshop.entity.userdto.UserDetailDTO;

import java.util.List;

public interface IUserServices {
    UserDetailDTO getUserByAccountId(int account_id);
    UserDetailDTO getUserByUserId(int user_id);
    List<UserDetailDTO> getAllUsers();
    int createUser(User user);
    int deleteUserByUserId(int user_id);
}
