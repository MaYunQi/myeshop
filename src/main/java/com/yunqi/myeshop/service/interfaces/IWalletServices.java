package com.yunqi.myeshop.service.interfaces;

import com.yunqi.myeshop.entity.userdto.WalletDetailDTO;

import java.math.BigDecimal;
import java.util.List;

public interface IWalletServices {
    WalletDetailDTO getWalletByAccountId(int account_id);
    WalletDetailDTO getWalletByWalletId(int wallet_id);
    WalletDetailDTO getWalletByWalletUId(String uid);
    BigDecimal getWalletBalanceByWalletUId(String wallet_uid);
    List<WalletDetailDTO> getAllWallets();
    int createWallet(int account_id);
    int withdraw(String wallet_uid, BigDecimal amount);
    int deposit(String wallet_uid, BigDecimal amount);
    int pay(String wallet_uid, String to_wallet_uid,BigDecimal amount);
    int transfer(String wallet_uid,String to_wallet_uid, BigDecimal amount);
    int receiveWithAmount(String wallet_uid, String from_wallet_uid, BigDecimal amount);
    int receive(String wallet_uid, String from_wallet_id);
    int deleteWallet(int wallet_id);
}
