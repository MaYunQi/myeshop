package com.yunqi.myeshop.mapper;

import com.yunqi.myeshop.entity.user.User;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO tb_users (first_name,last_name,gender,date_of_birth) VALUES  (#{first_name},#{last_name},#{gender},#{date_of_birth})")
    @Options(useGeneratedKeys = true,keyProperty = "user_id")
    int insertUser(User user);

    @Select("SELECT * FROM tb_users WHERE user_id=#{user_id}")
    User findUserByUserId(int user_id);

    @Select("SELECT * FROM tb_users")
    List<User> findAllUsers();

    @Update("UPDATE  tb_users SET first_name=#{first_name},last_name=#{last_name},gender=#{gender},date_of_birth=#{date_of_birth} WHERE user_id=#{user_id}")
    int updateUser(User user);

    @Delete("DELETE  FROM tb_users WHERE user_id=#{user_id}")
    int deleteUserByUserId(int user_id);
}
