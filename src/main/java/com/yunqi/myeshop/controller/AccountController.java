package com.yunqi.myeshop.controller;

import com.yunqi.myeshop.entity.user.Account;
import com.yunqi.myeshop.service.implementation.AccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/accounts")
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
        return accountServices.registerAccount(account);
    }
    @PutMapping
    public int updateAccount(@RequestBody Account account) {
        return accountServices.updateAccount(account);
    }
    @DeleteMapping("/{id}")
    public int deleteAccountByAccountId(@PathVariable int account_id) {
        return accountServices.deleteAccountByAccountId(account_id);
    }
}
