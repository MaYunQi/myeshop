package com.yunqi.myeshop.service.interfaces;

import com.yunqi.myeshop.entity.user.Account;
import java.util.List;

public interface IAccountServices {
    Account getAccountByAccountId(int account_id);
    Account getAccountByUserId(int user_id);
    List<Account> getAllAccounts();
    int updateAccount(Account account);
    int registerAccount(Account account);
    int deleteAccountByAccountId(int account_id);
}
