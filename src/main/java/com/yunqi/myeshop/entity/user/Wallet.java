package com.yunqi.myeshop.entity.user;

import java.math.BigDecimal;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class Wallet {
    private int wallet_id;
    private int account_id;
    private UUID wallet_uid;
    private BigDecimal balance;

    public int getWallet_id() {
        return wallet_id;
    }

    public void setWallet_id(int wallet_id) {
        this.wallet_id = wallet_id;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public UUID getWallet_uid() {
        return wallet_uid;
    }

    public void setWallet_uid(UUID wallet_uid) {
        this.wallet_uid = wallet_uid;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "wallet_id=" + wallet_id +
                ", account_id=" + account_id +
                ", wallet_uid=" + wallet_uid +
                ", balance=" + balance +
                '}';
    }
}
