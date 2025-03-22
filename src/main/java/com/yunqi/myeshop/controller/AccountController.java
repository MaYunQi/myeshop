package com.yunqi.myeshop.controller;

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
    public AccountDetailDto getAccountByAccountId(@PathVariable int account_id) {
        return accountServices.getAccountByAccountId(account_id);
    }
    @GetMapping
    public List<AccountDetailDto> getAllAccounts() {
        return accountServices.getAllAccounts();
    }
    @PostMapping
    public int registerAccount(@RequestBody AccountRegisterDto account) {
        if(ParameterValidator.isEmailValid(account.getEmail())
                &&ParameterValidator.isPhoneNumberValid(account.getPhone_number())
                &&ParameterValidator.isPasswordValid(account.getPassword_hash()))
            return accountServices.registerAccount(account);
        return -1;
    }
    @PostMapping("/changepwd")
    public int changePassword(@RequestBody ChangePwdDto changePwdDto) {
        if(!ParameterValidator.isPasswordValid(changePwdDto.getPassword()))
            return -1;
        changePwdDto.setUpdated_at(LocalDateTime.now());
        return accountServices.changePassword(changePwdDto);
    }
    @PostMapping("/changeuname")
    public int changeUsername(@RequestBody ChangeUnameDto changeUnameDto) {
        changeUnameDto.setUpdated_at(LocalDateTime.now());
        return accountServices.changeUsername(changeUnameDto);
    }
    @PostMapping("/changephone")
    public int changePhoneNumber(@RequestBody ChangePhoneNoDto changePhoneNoDto) {
        if(!ParameterValidator.isPhoneNumberValid(changePhoneNoDto.getPhone_number()))
            return -1;
        changePhoneNoDto.setUpdated_at(LocalDateTime.now());
        return accountServices.changePhoneNumber(changePhoneNoDto);
    }
    @PostMapping("/changeemail")
    public int changeEmail(@RequestBody ChangeEmailDto changeEmailDto)
    {
        if(!ParameterValidator.isEmailValid(changeEmailDto.getEmail()))
            return -1;
        changeEmailDto.setUpdated_at(LocalDateTime.now());
        return accountServices.changeEmail(changeEmailDto);
    }
    @DeleteMapping("/{id}")
    public int deleteAccountByAccountId(@PathVariable int account_id) {
        return accountServices.deleteAccountByAccountId(account_id);
    }
}
