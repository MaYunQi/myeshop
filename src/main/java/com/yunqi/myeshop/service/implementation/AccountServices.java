package com.yunqi.myeshop.service.implementation;

import com.yunqi.myeshop.entity.user.Account;
import com.yunqi.myeshop.mapper.AccountMapper;
import com.yunqi.myeshop.service.interfaces.IAccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountServices implements IAccountServices {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Account getAccountByAccountId(int account_id) {
        return accountMapper.getAccountByAccountId(account_id);
    }

    @Override
    public Account getAccountByUserId(int user_id)
    {
        return accountMapper.getAccountByUserId(user_id);
    }
    @Override
    public List<Account> getAllAccounts() {
        return accountMapper.getAllAccounts();
    }

    @Override
    public int updateAccount(Account account) {
        account.setUpdated_at(LocalDateTime.now());
        return accountMapper.updateAccount(account);
    }

    @Override
    public int registerAccount(Account account) {
        account.setCreated_at(LocalDateTime.now());
        return accountMapper.insertAccount(account);
    }

    @Override
    public int deleteAccountByAccountId(int account_id) {
        return accountMapper.deleteAccountByAccountId(account_id);
    }
}
