package com.yunqi.myeshop.controller;

import com.yunqi.myeshop.entity.user.Wallet;
import com.yunqi.myeshop.entity.userdto.WalletDetailDTO;
import com.yunqi.myeshop.service.implementation.WalletServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/wallet")
@RequiredArgsConstructor
public class WalletController {

    private final WalletServices walletServices;

    @GetMapping("/{wallet_id}")
    public WalletDetailDTO getWalletByWalletId(@PathVariable int wallet_id) {
        return walletServices.getWalletByWalletId(wallet_id);
    }
    @GetMapping
    public List<WalletDetailDTO> getWallets() {
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
