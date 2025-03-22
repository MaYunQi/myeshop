package com.yunqi.myeshop.service.interfaces;

import com.yunqi.myeshop.entity.userdto.*;

import java.util.List;

public interface IAccountServices {
    AccountDetailDto getAccountByAccountId(int account_id);
    List<AccountDetailDto> getAllAccounts();
    int changePassword(ChangePwdDto changePwdDto);
    int changeEmail(ChangeEmailDto changeEmailDto);
    int changePhoneNumber(ChangePhoneNoDto changePhoneNoDto);
    int changeUsername(ChangeUnameDto changeUnameDto);
    int registerAccount(AccountRegisterDto account);
    int deleteAccountByAccountId(int account_id);
    int loginByUsername(LoginByUsernameDto loginByUsernameDto);
}
