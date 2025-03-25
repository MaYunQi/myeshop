package com.yunqi.myeshop.service.implementation;

import com.yunqi.myeshop.entity.user.User;
import com.yunqi.myeshop.entity.userdto.UserDetailDTO;
import com.yunqi.myeshop.mapper.UserMapper;
import com.yunqi.myeshop.service.interfaces.IUserServices;
import com.yunqi.myeshop.util.ParameterValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServices implements IUserServices {

    private final UserMapper userMapper;

    @Override
    //@Cacheable(key="#user_id",value = "userCache")
    public UserDetailDTO getUserByUserId(int user_id) {
        return userMapper.findUserByUserId(user_id);
    }

    @Override
    public UserDetailDTO getUserByAccountId(int account_id){
        return userMapper.findUserByAccountId(account_id);
    }
    @Override
    public List<UserDetailDTO> getAllUsers() {
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
        if(!ParameterValidator.isBirthdayValid(user.getDate_of_birth()))
            return -1;
        if(!ParameterValidator.isIdNumberValid(user.getId_number()))
            return -1;
        if(doesIdNumberExist(user.getId_number()))
            return -1;
        setGenderOfUser(user);
        return userMapper.insertUser(user);
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
