package com.yunqi.myeshop.entity.user;

import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Wallet implements Serializable {
    private int wallet_id;
    private int account_id;
    private String wallet_uid;
    private BigDecimal balance;

    public Wallet() {}
}
