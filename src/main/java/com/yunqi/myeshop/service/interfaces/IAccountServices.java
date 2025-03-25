package com.yunqi.myeshop.service.interfaces;

import com.yunqi.myeshop.entity.userdto.*;

import java.util.List;
import java.util.Optional;

public interface IAccountServices {
    AccountDetailDTO getAccountByAccountId(int account_id);
    List<AccountDetailDTO> getAllAccounts();
    int changePassword(ChangePwdDTO changePwdDto);
    int changeEmail(ChangeEmailDTO changeEmailDto);
    int changePhoneNumber(ChangePhoneNoDTO changePhoneNoDto);
    int changeUsername(ChangeUnameDTO changeUnameDto);
    int registerAccount(AccountRegisterDTO account);
    int deleteAccountByAccountId(int account_id);
    Optional<String> loginByUsername(LoginByUsernameDTO loginByUsernameDto);
}
