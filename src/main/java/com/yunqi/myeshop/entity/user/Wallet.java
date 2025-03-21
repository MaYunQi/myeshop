package com.yunqi.myeshop.entity.user;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Wallet {
    private int wallet_id;
    private int account_id;
    private String wallet_uid;
    private BigDecimal balance;
}
