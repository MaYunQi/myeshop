package com.yunqi.myeshop.service.interfaces;

import com.yunqi.myeshop.entity.user.Wallet;
import java.util.List;
import java.util.UUID;

public interface IWalletServices {
    Wallet getWalletByAccountId(int account_id);
    Wallet getWalletByWalletId(int wallet_id);
    Wallet getWalletByWalletUId(UUID uid);
    List<Wallet> getAllWallets();
    int addWallet(Wallet wallet);
    int updateWallet(Wallet wallet);
    int deleteWallet(int wallet_id);
}
