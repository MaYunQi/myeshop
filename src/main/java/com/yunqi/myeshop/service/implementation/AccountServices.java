package com.yunqi.myeshop.service.implementation;

import com.yunqi.myeshop.entity.userdto.*;
import com.yunqi.myeshop.mapper.AccountMapper;
import com.yunqi.myeshop.service.interfaces.IAccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AccountServices implements IAccountServices {

    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AccountDetailDto getAccountByAccountId(int account_id) {
        return accountMapper.findAccountByAccountId(account_id);
    }

    @Override
    public int loginByUsername(LoginByUsernameDto loginByUsernameDto) {
        String encryptedPwd = passwordEncoder.encode(loginByUsernameDto.getPassword());
        loginByUsernameDto.setPassword(encryptedPwd);
        //To be implemented
        return 0;
    }

    @Override
    public List<AccountDetailDto> getAllAccounts() {
        return accountMapper.findAllAccounts();
    }

    @Override
    public int changePassword(ChangePwdDto changePwdDto) {
        String encryptedPwd = passwordEncoder.encode(changePwdDto.getPassword());
        changePwdDto.setPassword(encryptedPwd);
        return accountMapper.updateAccountPasswordHash(changePwdDto);
    }

    @Override
    public int changeUsername(ChangeUnameDto changeUnameDto) {
        if(doesUsernameExist(changeUnameDto.getUsername()))
            return -1;
        return accountMapper.updateAccountUsername(changeUnameDto);
    }

    @Override
    public int changeEmail(ChangeEmailDto changeEmailDto) {
        String new_email = changeEmailDto.getEmail();
        if(doesEmailExist(new_email))
            return -1;
        return accountMapper.updateAccountEmail(changeEmailDto);
    }

    @Override
    public int changePhoneNumber(ChangePhoneNoDto changePhoneNoDto) {
        if(doesPhoneNumberExist(changePhoneNoDto.getPhone_number()))
            return -1;
        return accountMapper.updateAccountPhoneNumber(changePhoneNoDto);
    }

    /**
     * Insert a new account into database
     * @param account
     * @return Return value is greater than 0 if username,phone number or email already exists in database.
     * 0 for fail to insert, 1 for successfully inserted.
     */
    @Override
    public int registerAccount(AccountRegisterDto account) {
        String encryptedPwd= passwordEncoder.encode(account.getPassword_hash());
        account.setPassword_hash(encryptedPwd);
        String username=account.getUsername();
        String email=account.getEmail();
        String phone_number=account.getPhone_number();
        int validity=checkValidityOfAccountDetailsForRegister(username,email,phone_number);
        if(validity!=0)
            return validity;
        int retryCounter=3;
        while(retryCounter>0){
            account.setAccount_uid(UUID.randomUUID().toString());
            account.setCreated_at(LocalDateTime.now());
            try{
                return accountMapper.insertAccount(account);
            }
            catch(Exception e){
                retryCounter--;
            }
        }
        return 0;
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
