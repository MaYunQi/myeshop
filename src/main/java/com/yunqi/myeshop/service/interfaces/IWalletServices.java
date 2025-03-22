package com.yunqi.myeshop.service.interfaces;

import com.yunqi.myeshop.entity.userdto.WalletDetailDto;

import java.math.BigDecimal;
import java.util.List;

public interface IWalletServices {
    WalletDetailDto getWalletByAccountId(int account_id);
    WalletDetailDto getWalletByWalletId(int wallet_id);
    WalletDetailDto getWalletByWalletUId(String uid);
    BigDecimal getWalletBalanceByWalletUId(String wallet_uid);
    List<WalletDetailDto> getAllWallets();
    int createWallet(int account_id);
    int withdraw(String wallet_uid, BigDecimal amount);
    int deposit(String wallet_uid, BigDecimal amount);
    int pay(String wallet_uid, String to_wallet_uid,BigDecimal amount);
    int transfer(String wallet_uid,String to_wallet_uid, BigDecimal amount);
    int receiveWithAmount(String wallet_uid, String from_wallet_uid, BigDecimal amount);
    int receive(String wallet_uid, String from_wallet_id);
    int deleteWallet(int wallet_id);
}
