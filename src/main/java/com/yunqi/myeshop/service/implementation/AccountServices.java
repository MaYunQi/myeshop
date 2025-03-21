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
        return accountMapper.findAccountByAccountId(account_id);
    }

    @Override
    public Account getAccountByUserId(int user_id)
    {
        return accountMapper.findAccountByUserId(user_id);
    }
    @Override
    public List<Account> getAllAccounts() {
        return accountMapper.findAllAccounts();
    }

    @Override
    public int updateAccount(Account account) {
        account.setUpdated_at(LocalDateTime.now());
        return accountMapper.updateAccount(account);
    }

    /**
     * Insert a new account into database
     * @param account
     * @return -1 if username,phone number or email already exists in database.
     * 0 for fail to insert, 1 for successfully inserted.
     */
    @Override
    public int registerAccount(Account account) {
        String username=account.getUsername();
        String email=account.getEmail();
        String phone_number=account.getPhone_number();
        if(doesUsernameExist(username)||doesEmailExist(email)||doesPhoneNumberExist(phone_number))
            return -1;
        account.setCreated_at(LocalDateTime.now());
        return accountMapper.insertAccount(account);
    }

    @Override
    public int deleteAccountByAccountId(int account_id) {
        return accountMapper.deleteAccountByAccountId(account_id);
    }

    /**
     * Test if username exists in database
     * @param username
     * @return True if exists, otherwise false
     */
    private boolean doesUsernameExist(String username) {
        String uname= accountMapper.findUsernameByUsername(username);
        return uname!=null;
    }
    private boolean doesPhoneNumberExist(String phone_number) {
        String phoneNumber= accountMapper.findPhoneNumberByPhoneNumber(phone_number);
        return phoneNumber!=null;
    }
    private boolean doesEmailExist(String emai) {
        String Email= accountMapper.findEmailByEmail(emai);
        return Email!=null;
    }
}
