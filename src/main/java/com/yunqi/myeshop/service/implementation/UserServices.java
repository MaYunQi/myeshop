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
    //@Cacheable(key="#user_id",value = "userCache")
    public User getUserByUserId(int user_id) {
        return userMapper.findUserByUserId(user_id);
    }

    @Override
    public List<User> getAllUsers() {
        return userMapper.findAllUsers();
    }
    /**
     * Insert new user into database
     * @param user
     * @return -1 indicates the id number is invalid or the id number exists.
     * 0 indicates fail to insert user into database, otherwise successfully inserted.
     */
    @Override
    public int createUser(User user) {
        String idNumber = user.getId_number();
        if(doesIdNumberExist(idNumber))
            return -1;
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
    /**
     * Format the gender feild of instance.
     * @param user
     */
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
    /**
     * Test if the id number exists in database
     * @param idNumber
     * @return True if id number exists, false otherwise.
     */
    private boolean doesIdNumberExist(String idNumber) {
        int user_id=userMapper.findUserIdByIDNumber(idNumber);
        return user_id!=0;
    }
}
