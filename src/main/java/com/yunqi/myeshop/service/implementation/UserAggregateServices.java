package com.yunqi.myeshop.service.implementation;

import com.yunqi.myeshop.entity.user.UserAggregate;
import com.yunqi.myeshop.entity.userdto.AccountDetailDto;
import com.yunqi.myeshop.entity.userdto.UserDetailDto;
import com.yunqi.myeshop.entity.userdto.WalletDetailDto;
import com.yunqi.myeshop.service.interfaces.IUserAggregateServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserAggregateServices implements IUserAggregateServices {

    private UserAggregate userAggregate;
    @Autowired
    private AccountServices accountServices;
    @Autowired
    private WalletServices walletServices;
    @Autowired
    private UserServices userServices;

    @Override
    @Cacheable(key="#user_id",value = "userCache")
    public UserAggregate getUserAggregateByAccountId(int account_id) {
        userAggregate.setAccount(accountServices.getAccountByAccountId(account_id));
        userAggregate.setWallet(walletServices.getWalletByAccountId(account_id));
        userAggregate.setUser(userServices.getUserByAccountId(account_id));
        return userAggregate;
    }
}
