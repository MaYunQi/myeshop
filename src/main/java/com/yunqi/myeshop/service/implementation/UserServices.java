package com.yunqi.myeshop.service.implementation;

import com.yunqi.myeshop.entity.user.User;
import com.yunqi.myeshop.mapper.UserMapper;
import com.yunqi.myeshop.service.interfaces.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
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
        if(!isIdNumberValid(idNumber)||doesIdNumberExist(idNumber))
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
    private boolean isIdNumberValid(String id_number) {
        if(id_number==null||id_number.length()!=18)
            return false;
        String first17=id_number.substring(0,17);
        if(!first17.matches("\\d+"))
            return false;
        char lastChar=id_number.charAt(17);
        if(!(Character.isDigit(lastChar)||lastChar=='X'||lastChar=='x'))
            return false;
        String checkCode = calculateCheckCode(first17);
        char expectedLastChar = checkCode.charAt(0);
        return expectedLastChar == lastChar || (expectedLastChar == 'X' && (lastChar == 'X' || lastChar == 'x'));
    }
    private String calculateCheckCode(String first17) {
        int[] weights = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
        char[] checkCodes = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
        int sum = 0;
        for (int i = 0; i < first17.length(); i++) {
            sum += (first17.charAt(i) - '0') * weights[i];
        }
        int mod = sum % 11;
        return String.valueOf(checkCodes[mod]);
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
