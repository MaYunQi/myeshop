package com.yunqi.myeshop.service.interfaces;

import com.yunqi.myeshop.entity.user.Account;
import com.yunqi.myeshop.entity.userdto.ChangePhoneNoDto;
import com.yunqi.myeshop.entity.userdto.ChangePwdDto;
import com.yunqi.myeshop.entity.userdto.ChangeUnameDto;

import java.util.List;

public interface IAccountServices {
    Account getAccountByAccountId(int account_id);
    List<Account> getAllAccounts();
    int changePasswordHash(ChangePwdDto changePwdDto);
    int changeEmail(int account_id, String new_email);
    int changePhoneNumber(ChangePhoneNoDto changePhoneNoDto);
    int changeUsername(ChangeUnameDto changeUnameDto);
    int updateAccount(Account account);
    int registerAccount(Account account);
    int deleteAccountByAccountId(int account_id);
}
