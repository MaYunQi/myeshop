package com.yunqi.myeshop.service.implementation;

import com.yunqi.myeshop.entity.user.UserAggregate;
import com.yunqi.myeshop.service.interfaces.IUserAggregateServices;
import org.springframework.beans.factory.annotation.Autowired;
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
    public UserAggregate getUserAggregateByUserId(int user_id) {
        userAggregate.setUser(userServices.getUserByUserId(user_id));
        userAggregate.setAccount(accountServices.getAccountByUserId(user_id));
        userAggregate.setWallet(walletServices.getWalletByAccountId(userAggregate.getAccount().getAccount_id()));
        return userAggregate;
    }

    @Override
    public UserAggregate getUserAggregateByAccountId(int account_id) {
        userAggregate.setAccount(accountServices.getAccountByAccountId(account_id));
        userAggregate.setWallet(walletServices.getWalletByAccountId(account_id));
        userAggregate.setUser(userServices.getUserByUserId(userAggregate.getAccount().getUser_id()));
        return userAggregate;
    }

    @Override
    public UserAggregate getUserAggregateByWalletId(int wallet_id) {
        userAggregate.setWallet(walletServices.getWalletByWalletId(wallet_id));
        userAggregate.setAccount(accountServices.getAccountByAccountId(userAggregate.getAccount().getAccount_id()));
        userAggregate.setUser(userServices.getUserByUserId(userAggregate.getAccount().getUser_id()));
        return userAggregate;
    }
}
