package com.yunqi.myeshop.mapper;

import com.yunqi.myeshop.entity.user.Account;
import com.yunqi.myeshop.entity.userdto.*;
import org.apache.ibatis.annotations.*;
import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface AccountMapper {
    @Insert("INSERT INTO tb_accounts (username,password_hash,email,phone_number,created_at,updated_at,last_login_at)" +
            "VALUES (#{username},#{password_hash},#{email},#{phone_number},#{created_at},#{updated_at},#{last_login_at})")
    @Options(useGeneratedKeys = true,keyProperty = "account_id")
    int insertAccount(Account account);

    @Select("SELECT * FROM tb_accounts WHERE account_id=#{account_id}")
    Account findAccountByAccountId(int account_id);

    @Select("SELECT account_id FROM tb_accounts WHERE username=#{username}")
    int findAccountIdByUsername(String username);

    @Select("SELECT account_id FROM tb_accounts WHERE email=#{email}")
    int findAccountIdByEmail(String email);

    @Select("SELECT account_id FROM tb_accounts WHERE phone_number=#{phone_number}")
    int findAccountIdByPhoneNo(String phone_number);

    @Select("SELECT * FROM tb_accounts")
    List<Account> findAllAccounts();

    @Update("UPDATE tb_accounts SET password_hash=#{password_hash}" +
            ",updated_at=#{updated_at} WHERE account_id=#{account_id}")
    int updateAccountPasswordHash(ChangePwdDto changePwdDto);

    @Update("UPDATE tb_accounts SET username=#{username}" +
            ",updated_at=#{updated_at} WHERE account_id=#{account_id}")
    int updateAccountUsername(ChangeUnameDto changeUnameDto);

    @Update("UPDATE tb_accounts SET email=#{email}" +
            ",updated_at=#{updated_at} WHERE account_id=#{account_id}")
    int updateAccountEmail(int account_id, String new_email, LocalDateTime updated_at);

    @Update("UPDATE tb_accounts SET phone_number=#{phone_number}" +
            ",updated_at=#{updated_at} WHERE account_id=#{account_id}")
    int updateAccountPhoneNumber(ChangePhoneNoDto changePhoneNoDto);

    @Update("UPDATE tb_accounts SET username=#{username},password_hash=#{password_hash},email=#{email}," +
            "phone_number=#{phone_number},updated_at=#{updated_at} WHERE account_id=#{account_id}")
    int updateAccount(Account account);

    @Delete("DELETE FROM tb_accounts WHERE account_id=#{account_id}")
    int deleteAccountByAccountId(int account_id);
}
