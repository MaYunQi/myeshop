package com.yunqi.myeshop.entity.userdto;

import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class WalletDetailDTO implements Serializable {
    private String wallet_uid;
    private BigDecimal balance;
    public WalletDetailDTO() {}
}
