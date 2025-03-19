package com.yunqi.myeshop.service.implementation;

import com.yunqi.myeshop.entity.user.Wallet;
import com.yunqi.myeshop.mapper.WalletMapper;
import com.yunqi.myeshop.service.interfaces.IWalletServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class WalletServices implements IWalletServices {
    @Autowired
    private WalletMapper walletMapper;

    @Override
    public Wallet getWalletByAccountId(int account_id) {
        return walletMapper.findWalletByAccountId(account_id);
    }

    @Override
    public Wallet getWalletByWalletId(int wallet_id) {
        return walletMapper.findWalletByWalletId(wallet_id);
    }

    @Override
    public Wallet getWalletByWalletUId(UUID uid) {
        return walletMapper.findWalletByWalletUId(uid);
    }

    @Override
    public List<Wallet> getAllWallets() {
        return walletMapper.findAllWallets();
    }

    @Override
    public int addWallet(Wallet wallet) {
        return walletMapper.insertWallet(wallet);
    }

    @Override
    public int updateWallet(Wallet wallet) {
        SetWallet(wallet);
        return walletMapper.updateWallet(wallet);
    }

    @Override
    public int deleteWallet(int wallet_id) {
        return walletMapper.deleteWalletByWalletId(wallet_id);
    }

    private void SetWallet(Wallet wallet) {
        BigDecimal balance = wallet.getBalance();
        wallet=walletMapper.findWalletByWalletUId(wallet.getWallet_uid());
        wallet.setBalance(balance);
    }
}
