package com.yunqi.myeshop.mapper;

import com.yunqi.myeshop.entity.user.User;
import com.yunqi.myeshop.entity.userdto.*;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("INSERT INTO tb_users (account_id,first_name,last_name,id_number,gender,date_of_birth) VALUES  " +
            "(#{account_id}, #{first_name},#{last_name},#{id_number},#{gender},#{date_of_birth})")
    @Options(useGeneratedKeys = true,keyProperty = "user_id")
    int insertUser(User user);

    @Select("SELECT user_id FROM tb_users WHERE id_number=#{id_number}")
    int findUserIdByIDNumber(String id_number);

    @Select("SELECT account_id,first_name,last_name,gender,date_of_birth" +
            " FROM tb_users WHERE user_id=#{user_id}")
    UserDetailDTO findUserByUserId(int user_id);

    @Select("SELECT account_id,first_name,last_name,gender,date_of_birth" +
            " FROM tb_users WHERE account_id=#{account_id}")
    UserDetailDTO findUserByAccountId(int account_id);

    @Select("SELECT account_id,first_name,last_name,gender,date_of_birth" +
            " FROM tb_users WHERE user_id=#{user_id}")
    List<UserDetailDTO> findAllUsers();

    @Delete("DELETE FROM tb_users WHERE user_id=#{user_id}")
    int deleteUserByUserId(int user_id);
}
