package com.yunqi.myeshop.entity.user;

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
