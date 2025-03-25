package com.yunqi.myeshop.entity.user;

import com.yunqi.myeshop.entity.userdto.AccountDetailDTO;
import com.yunqi.myeshop.entity.userdto.UserDetailDTO;
import com.yunqi.myeshop.entity.userdto.WalletDetailDTO;
import lombok.Data;
import java.io.Serializable;

@Data
public class UserAggregate implements Serializable {
    private AccountDetailDTO account;
    private UserDetailDTO user;
    private WalletDetailDTO wallet;

    public UserAggregate() {}

    public UserAggregate(AccountDetailDTO account, UserDetailDTO user, WalletDetailDTO wallet) {
        this.account = account;
        this.user = user;
        this.wallet = wallet;
    }
}
