package com.yunqi.myeshop.entity.userdto;

import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class WalletDetailDto implements Serializable {
    private String wallet_uid;
    private BigDecimal balance;
    public WalletDetailDto() {}
}
