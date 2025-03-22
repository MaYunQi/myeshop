package com.yunqi.myeshop.entity.user;

import com.yunqi.myeshop.entity.userdto.AccountDetailDto;
import com.yunqi.myeshop.entity.userdto.UserDetailDto;
import com.yunqi.myeshop.entity.userdto.WalletDetailDto;
import lombok.Data;
import java.io.Serializable;

@Data
public class UserAggregate implements Serializable {
    private AccountDetailDto account;
    private UserDetailDto user;
    private WalletDetailDto wallet;

    public UserAggregate() {}

    public UserAggregate(AccountDetailDto account, UserDetailDto user, WalletDetailDto wallet) {
        this.account = account;
        this.user = user;
        this.wallet = wallet;
    }
}
