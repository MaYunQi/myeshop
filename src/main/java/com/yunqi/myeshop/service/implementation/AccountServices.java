package com.yunqi.myeshop.service.implementation;

import com.yunqi.myeshop.entity.user.Account;
import com.yunqi.myeshop.entity.userdto.ChangePhoneNoDto;
import com.yunqi.myeshop.entity.userdto.ChangePwdDto;
import com.yunqi.myeshop.entity.userdto.ChangeUnameDto;
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
    public List<Account> getAllAccounts() {
        return accountMapper.findAllAccounts();
    }

    @Override
    public int changePasswordHash(ChangePwdDto changePwdDto) {
        return accountMapper.updateAccountPasswordHash(changePwdDto);
    }

    @Override
    public int changeUsername(ChangeUnameDto changeUnameDto) {
        if(doesUsernameExist(changeUnameDto.getUsername()))
            return -1;
        return accountMapper.updateAccountUsername(changeUnameDto);
    }

    @Override
    public int changeEmail(int account_id, String new_email) {
        if(doesEmailExist(new_email))
            return -1;
        LocalDateTime updated_at = LocalDateTime.now();
        return accountMapper.updateAccountEmail(account_id, new_email, updated_at);
    }

    @Override
    public int changePhoneNumber(ChangePhoneNoDto changePhoneNoDto) {
        if(doesPhoneNumberExist(changePhoneNoDto.getPhone_number()))
            return -1;
        return accountMapper.updateAccountPhoneNumber(changePhoneNoDto);
    }

    @Override
    public int updateAccount(Account account) {
        int current_account_id = account.getAccount_id();
        String username = account.getUsername();
        String email = account.getEmail();
        String phone_number = account.getPhone_number();
        int validity=checkValidityOfAccountDetailsForUpdate(current_account_id,username,email,phone_number);
        if(validity!=0)
            return validity;
        account.setUpdated_at(LocalDateTime.now());
        return accountMapper.updateAccount(account);
    }

    /**
     * Insert a new account into database
     * @param account
     * @return Return value is greater than 0 if username,phone number or email already exists in database.
     * 0 for fail to insert, 1 for successfully inserted.
     */
    @Override
    public int registerAccount(Account account) {
        String username=account.getUsername();
        String email=account.getEmail();
        String phone_number=account.getPhone_number();
        int validity=checkValidityOfAccountDetailsForRegister(username,email,phone_number);
        if(validity!=0)
            return validity;
        account.setCreated_at(LocalDateTime.now());
        return accountMapper.insertAccount(account);
    }

    @Override
    public int deleteAccountByAccountId(int account_id) {
        return accountMapper.deleteAccountByAccountId(account_id);
    }

    /**
     * Test if username, email, phone number exist in database.
     * @param username
     * @param email
     * @param phone_number
     * @return
     * 0(0b000) All don't exist
     * 1(0b001) Username exists
     * 2(0b010) email exists
     * 4(0b100) phone number exists
     * 3(0b011) username,email exist
     * 5(0b101) phone number, username exist
     * 6(0b110) phone number, email exist
     * 7(0b111) All exist
     */
    private int checkValidityOfAccountDetailsForRegister(String username, String email, String phone_number) {
        int validityState=0;
        if(username!=null)
            validityState|=0b001;
        if(email!=null)
            validityState|=0b010;
        if(phone_number!=null)
            validityState|=0b100;
        return validityState;
    }

    /**
     * Test if updated username, email, phone number exist in database.
     * @param originalAccountId
     * @param username
     * @param email
     * @param phone_number
     * @return
     * 0(0b000) All don't exist
     * 1(0b001) Username exists within different account
     * 2(0b010) email exists within different account
     * 4(0b100) phone number exists within different account
     * 3(0b011) username,email exist within different account
     * 5(0b101) phone number, username exist within different account
     * 6(0b110) phone number, email exist within different account
     * 7(0b111) All exist within different account
     */
    private int checkValidityOfAccountDetailsForUpdate(int originalAccountId,String username, String email, String phone_number) {
        int validityState=0;
        int id_by_uname=accountMapper.findAccountIdByUsername(username);
        int id_by_email=accountMapper.findAccountIdByEmail(email);
        int id_by_phone=accountMapper.findAccountIdByPhoneNo(phone_number);
        if(originalAccountId!=id_by_uname)
            validityState|=0b001;
        if(id_by_email!=id_by_phone)
            validityState|=0b010;
        if(id_by_phone!=id_by_email)
            validityState|=0b100;
        return validityState;
    }
    private boolean doesUsernameExist(String new_username) {
        int id_by_uname=accountMapper.findAccountIdByUsername(new_username);
        return id_by_uname!=0;
    }
    private boolean doesEmailExist(String new_email) {
        int id_by_email=accountMapper.findAccountIdByEmail(new_email);
        return id_by_email!=0;
    }
    private boolean doesPhoneNumberExist(String new_phone_number) {
        int id_by_phone=accountMapper.findAccountIdByPhoneNo(new_phone_number);
        return id_by_phone!=0;
    }
}
