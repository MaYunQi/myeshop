package com.yunqi.myeshop.service.interfaces;

import com.yunqi.myeshop.entity.user.Wallet;
import java.math.BigDecimal;
import java.util.List;

public interface IWalletServices {
    Wallet getWalletByAccountId(int account_id);
    Wallet getWalletByWalletId(int wallet_id);
    Wallet getWalletByWalletUId(String uid);
    BigDecimal getWalletBalanceByWalletUId(String wallet_uid);
    List<Wallet> getAllWallets();
    int createWallet(int account_id);
    int withdraw(String wallet_uid, BigDecimal amount);
    int deposit(String wallet_uid, BigDecimal amount);
    int pay(String wallet_uid, String to_wallet_uid,BigDecimal amount);
    int transfer(String wallet_uid,String to_wallet_uid, BigDecimal amount);
    int receiveWithAmount(String wallet_uid, String from_wallet_uid, BigDecimal amount);
    int receive(String wallet_uid, String from_wallet_id);
    int deleteWallet(int wallet_id);
}
