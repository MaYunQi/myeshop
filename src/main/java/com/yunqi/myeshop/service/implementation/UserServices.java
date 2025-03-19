package com.yunqi.myeshop.service.implementation;

import com.yunqi.myeshop.entity.user.User;
import com.yunqi.myeshop.mapper.UserMapper;
import com.yunqi.myeshop.service.interfaces.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServices implements IUserServices {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByUserId(int user_id) {
        return userMapper.findUserByUserId(user_id);
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.findAllUsers();
    }

    @Override
    public int createUser(User user) {
        setGenderOfUser(user);
        return userMapper.insertUser(user);
    }

    @Override
    public int updateUser(User user) {
        setGenderOfUser(user);
        return userMapper.updateUser(user);
    }

    @Override
    public int deleteUserByUserId(int user_id) {
        return userMapper.deleteUserByUserId(user_id);
    }

    private void setGenderOfUser(User user)
    {
        String gender=user.getGender();
        if(gender.equals("male")||gender.equals("男"))
            user.setGender("Male");
        else if(gender.equals("female")||gender.equals("'女"))
            user.setGender("Female");
        else
            user.setGender("Other");
    }
}
