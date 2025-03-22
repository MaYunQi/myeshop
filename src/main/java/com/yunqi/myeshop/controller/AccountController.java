package com.yunqi.myeshop.controller;

import com.yunqi.myeshop.entity.user.Account;
import com.yunqi.myeshop.entity.userdto.*;
import com.yunqi.myeshop.service.implementation.AccountServices;
import com.yunqi.myeshop.util.ParameterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountServices accountServices;

    @GetMapping("/{account_id}")
    public Account getAccountByAccountId(@PathVariable int account_id) {
        return accountServices.getAccountByAccountId(account_id);
    }
    @GetMapping
    public List<Account> getAllAccounts() {
        return accountServices.getAllAccounts();
    }
    @PostMapping
    public int registerAccount(@RequestBody Account account) {
        String email = account.getEmail();
        String phone_number = account.getPhone_number();
        if(ParameterValidator.isEmailValid(email)&&ParameterValidator.isPhoneNumberValid(phone_number))
            return accountServices.registerAccount(account);
        return -1;
    }
    @PostMapping("/changepwd")
    public int changePassword(@RequestBody ChangePwdDto changePwdDto) {
        changePwdDto.setUpdated_at(LocalDateTime.now());
        return accountServices.changePasswordHash(changePwdDto);
    }
    @PostMapping("/changeusername")
    public int changeUsername(@RequestBody ChangeUnameDto changeUnameDto) {
        changeUnameDto.setUpdated_at(LocalDateTime.now());
        return accountServices.changeUsername(changeUnameDto);
    }
    @PostMapping("/changephoneno")
    public int changePhoneNumber(@RequestBody ChangePhoneNoDto changePhoneNoDto) {
        changePhoneNoDto.setUpdated_at(LocalDateTime.now());
        return accountServices.changePhoneNumber(changePhoneNoDto);
    }

    @PutMapping
    public int updateAccount(@RequestBody Account account) {
        String email = account.getEmail();
        String phone_number = account.getPhone_number();
        if(ParameterValidator.isEmailValid(email)&&ParameterValidator.isPhoneNumberValid(phone_number))
            return accountServices.updateAccount(account);
        return -1;
    }
    @DeleteMapping("/{id}")
    public int deleteAccountByAccountId(@PathVariable int account_id) {
        return accountServices.deleteAccountByAccountId(account_id);
    }

}
