package com.yunqi.myeshop.entity.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class UserAggregate {
    private User user;
    private Wallet wallet;
    private Account account;

    @Autowired
    public UserAggregate(User user, Wallet wallet, Account account) {
        this.user = user;
        this.account = account;
        this.wallet = wallet;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "UserAggregate{" +
                "user=" + user +
                ", wallet=" + wallet +
                ", account=" + account +
                '}';
    }
}
