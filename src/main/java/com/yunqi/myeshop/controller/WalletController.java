package com.yunqi.myeshop.controller;

import com.yunqi.myeshop.entity.user.Wallet;
import com.yunqi.myeshop.service.implementation.WalletServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/wallets")
public class WalletController {
    @Autowired
    private WalletServices walletServices;

    @GetMapping("/{wallet_id}")
    public Wallet getWalletByWalletId(@PathVariable int wallet_id) {
        return walletServices.getWalletByWalletId(wallet_id);
    }
    @GetMapping
    public List<Wallet> getWallets() {
        return walletServices.getAllWallets();
    }
    @PostMapping
    public int createWallet(@RequestBody Wallet wallet) {
        return walletServices.addWallet(wallet);
    }
    @PutMapping
    public int updateWallet(@RequestBody Wallet wallet) {
        return walletServices.updateWallet(wallet);
    }
    @DeleteMapping
    public int deleteWallet(@RequestBody Wallet wallet) {
        return walletServices.deleteWallet(wallet.getWallet_id());
    }
}
