package com.yunqi.myeshop.mapper;

import com.yunqi.myeshop.entity.userdto.*;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface AccountMapper {
    @Insert("INSERT INTO tb_accounts (account_uid,username,password_hash,email,phone_number,created_at)" +
            "VALUES (#{account_uid},#{username},#{password},#{email},#{phone_number},#{created_at})")
    @Options(useGeneratedKeys = true,keyProperty = "account_id")
    int insertAccount(AccountRegisterDto account);

    @Select("SELECT account_id FROM tb_accounts WHERE username=#{username} AND password_hash=#{password}")
    int findAccountIdByUnameAndPwd(LoginByUsernameDto loginByUsernameDto);

    @Select("SELECT account_id FROM tb_accounts WHERE username=#{username}")
    String findAccountUIdByUsername(String username);

    @Select("SELECT account_id FROM tb_accounts WHERE email=#{email}")
    String findAccountUIdByEmail(String email);

    @Select("SELECT account_id FROM tb_accounts WHERE phone_number=#{phone_number}")
    String findAccountUIdByPhoneNo(String phone_number);

    @Select("SELECT username, password_hash,last_login_at FROM tb_accounts WHERE username=#{username}")
    LoginByUsernameDto findLoginAccountByUsername(String username);

    @Select("SELECT account_id,account_uid, username,email,phone_number,created_at," +
            "updated_at,last_login_at FROM tb_accounts WHERE account_id=#{account_id}")
    AccountDetailDto findAccountByAccountId(int account_id);

    @Select("SELECT account_id,account_uid, username,email,phone_number,created_at," +
            "updated_at,last_login_at FROM tb_accounts")
    List<AccountDetailDto> findAllAccounts();

    @Update("UPDATE tb_accounts SET last_login_at=#{last_login_at} WHERE account_id=#{account_id}")
    int updateLoginAt(LoginByUsernameDto loginByUsernameDto);

    @Update("UPDATE tb_accounts SET password_hash=#{password}" +
            ",updated_at=#{updated_at} WHERE account_id=#{account_id}")
    int updateAccountPasswordHash(ChangePwdDto changePwdDto);

    @Update("UPDATE tb_accounts SET username=#{username}" +
            ",updated_at=#{updated_at} WHERE account_id=#{account_id}")
    int updateAccountUsername(ChangeUnameDto changeUnameDto);

    @Update("UPDATE tb_accounts SET email=#{email}" +
            ",updated_at=#{updated_at} WHERE account_id=#{account_id}")
    int updateAccountEmail(ChangeEmailDto changeEmailDto);

    @Update("UPDATE tb_accounts SET phone_number=#{phone_number}" +
            ",updated_at=#{updated_at} WHERE account_id=#{account_id}")
    int updateAccountPhoneNumber(ChangePhoneNoDto changePhoneNoDto);

    @Delete("DELETE FROM tb_accounts WHERE account_id=#{account_id}")
    int deleteAccountByAccountId(int account_id);
}
