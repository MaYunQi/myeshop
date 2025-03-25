package com.yunqi.myeshop.controller;

import com.yunqi.myeshop.entity.userdto.*;
import com.yunqi.myeshop.service.implementation.AccountServices;
import com.yunqi.myeshop.util.ParameterValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountServices accountServices;

    @GetMapping("/{account_id}")
    public AccountDetailDTO getAccountByAccountId(@PathVariable int account_id) {
        return accountServices.getAccountByAccountId(account_id);
    }
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<AccountDetailDTO> getAllAccounts() {
        return accountServices.getAllAccounts();
    }
    @PostMapping("/register")
    public int registerAccount(@RequestBody AccountRegisterDTO account) {
        if(ParameterValidator.isEmailValid(account.getEmail())
                &&ParameterValidator.isPhoneNumberValid(account.getPhone_number())
                &&ParameterValidator.isPasswordValid(account.getPassword()))
            return accountServices.registerAccount(account);
        return -1;
    }
    @PostMapping("/changepwd")
    public int changePassword(@RequestBody ChangePwdDTO changePwdDto) {
        if(!ParameterValidator.isPasswordValid(changePwdDto.getPassword()))
            return -1;
        changePwdDto.setUpdated_at(LocalDateTime.now());
        return accountServices.changePassword(changePwdDto);
    }
    @PostMapping("/changeuname")
    public int changeUsername(@RequestBody ChangeUnameDTO changeUnameDto) {
        changeUnameDto.setUpdated_at(LocalDateTime.now());
        return accountServices.changeUsername(changeUnameDto);
    }
    @PostMapping("/changephone")
    public int changePhoneNumber(@RequestBody ChangePhoneNoDTO changePhoneNoDto) {
        if(!ParameterValidator.isPhoneNumberValid(changePhoneNoDto.getPhone_number()))
            return -1;
        changePhoneNoDto.setUpdated_at(LocalDateTime.now());
        return accountServices.changePhoneNumber(changePhoneNoDto);
    }
    @PostMapping("/changeemail")
    public int changeEmail(@RequestBody ChangeEmailDTO changeEmailDto)
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
