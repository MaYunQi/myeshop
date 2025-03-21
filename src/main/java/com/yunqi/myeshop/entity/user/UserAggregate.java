package com.yunqi.myeshop.entity.user;

import lombok.Data;

@Data
public class UserAggregate {
    private User user;
    private Wallet wallet;
    private Account account;

    public UserAggregate() {}

    public UserAggregate(User user, Wallet wallet, Account account) {
        this.user = user;
        this.wallet = wallet;
        this.account = account;
    }
}
