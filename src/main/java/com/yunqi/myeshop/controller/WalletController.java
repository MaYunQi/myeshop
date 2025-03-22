package com.yunqi.myeshop.controller;

import com.yunqi.myeshop.entity.user.Wallet;
import com.yunqi.myeshop.entity.userdto.WalletDetailDto;
import com.yunqi.myeshop.service.implementation.WalletServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/wallet")
public class WalletController {
    @Autowired
    private WalletServices walletServices;

    @GetMapping("/{wallet_id}")
    public WalletDetailDto getWalletByWalletId(@PathVariable int wallet_id) {
        return walletServices.getWalletByWalletId(wallet_id);
    }
    @GetMapping
    public List<WalletDetailDto> getWallets() {
        return walletServices.getAllWallets();
    }
    @PostMapping
    public int createWallet(@RequestBody int account_id) {
        return walletServices.createWallet(account_id);
    }
    @DeleteMapping
    public int deleteWallet(@RequestBody Wallet wallet) {
        return walletServices.deleteWallet(wallet.getWallet_id());
    }
}
