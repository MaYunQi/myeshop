package com.yunqi.myeshop.service.implementation;

import com.yunqi.myeshop.entity.user.Account;
import com.yunqi.myeshop.entity.user.User;
import com.yunqi.myeshop.entity.user.UserAggregate;
import com.yunqi.myeshop.entity.user.Wallet;
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
    public UserAggregate getUserAggregateByUserId(int user_id) {
        User user=userServices.getUserByUserId(user_id);
        Account account=accountServices.getAccountByUserId(user_id);
        Wallet wallet=walletServices.getWalletByAccountId(account.getAccount_id());
        userAggregate=new UserAggregate(user,wallet,account);
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
