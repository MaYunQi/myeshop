package com.yunqi.myeshop.mapper;

import com.yunqi.myeshop.entity.user.Account;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface AccountMapper {
    @Insert("INSERT INTO tb_accounts (user_id,username,password_hash,email,phone_number,created_at,updated_at,last_login_at)" +
            "VALUES (#{user_id},#{username},#{password_hash},#{email},#{phone_number},#{created_at},#{updated_at},#{last_login_at})")
    @Options(useGeneratedKeys = true,keyProperty = "account_id")
    int insertAccount(Account account);

    @Select("SELECT * FROM tb_accounts WHERE account_id=#{account_id}")
    Account getAccountByAccountId(int account_id);

    @Select("SELECT * FROM tb_accounts")
    List<Account> getAllAccounts();

    @Select("SELECT * FROM tb_accounts WHERE user_id=#{user_id}")
    Account getAccountByUserId(int user_id);

    @Update("UPDATE  tb_accounts SET user_id=#{user_id},username=#{username},password_hash=#{password_hash},email=#{email}," +
            "phone_number=#{phone_number},updated_at=#{updated_at} WHERE account_id=#{account_id}")
    int updateAccount(Account account);

    @Delete("DELETE FROM tb_accounts WHERE account_id=#{account_id}")
    int deleteAccountByAccountId(int account_id);

    @Delete("DELETE FROM tb_accounts WHERE user_id=#{user_id}")
    int deleteAllAccountsByUserId(int user_id);
}
