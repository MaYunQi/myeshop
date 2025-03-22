package com.yunqi.myeshop.service.implementation;

import com.yunqi.myeshop.entity.user.Wallet;
import com.yunqi.myeshop.mapper.WalletMapper;
import com.yunqi.myeshop.service.interfaces.IWalletServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    public Wallet getWalletByWalletUId(String uid) {
        return walletMapper.findWalletByWalletUId(uid);
    }

    @Override
    public List<Wallet> getAllWallets() {
        return walletMapper.findAllWallets();
    }

    @Override
    public BigDecimal getWalletBalanceByWalletUId(String wallet_uid)
    {
        return walletMapper.findWalletBalanceByWalletUId(wallet_uid);
    }

    @Override
    public int createWallet(int account_id) {
        Wallet wallet = new Wallet();
        wallet.setAccount_id(account_id);
        wallet.setBalance(new BigDecimal(0));
        int retryCounter=3;
        while(retryCounter>0){
            wallet.setWallet_uid(UUID.randomUUID().toString());
            try{
                return walletMapper.insertWallet(wallet);
            }
            catch(Exception e){
                retryCounter--;
            }
        }
        return 0;
    }

    @Override
    public int withdraw(String wallet_uid, BigDecimal amount) {
        ///Withdraw to third party. Not to implemented
        return 0;
    }

    @Override
    public int deposit(String wallet_uid, BigDecimal amount) {
        //Deposit from third party. Not to implemented
        return 0;
    }
    @Transactional
    @Override
    public int pay(String wallet_uid, String to_wallet_uid, BigDecimal amount) {
        BigDecimal fromBalance = walletMapper.findWalletBalanceByWalletUId(wallet_uid);
        if(fromBalance==null)
            return -2;
        if(fromBalance.compareTo(amount)==-1)
            return -1;
        fromBalance=fromBalance.subtract(amount);
        BigDecimal toBalance = walletMapper.findWalletBalanceByWalletUId(to_wallet_uid);
        if(toBalance==null)
            return -3;
        toBalance=toBalance.add(amount);

        int resultOfFrom=walletMapper.updateWallet(wallet_uid,fromBalance);
        int resultOfTo=walletMapper.updateWallet(to_wallet_uid,toBalance);
        if(resultOfFrom==1 && resultOfTo==1)
            return 1;
        else {
            StringBuffer sb=new StringBuffer();
            sb.append("Failed to update wallet balance!");
            sb.append(" From wallet Uid:"+wallet_uid);
            sb.append(" To wallet Uid:"+to_wallet_uid);
            sb.append(" Balance:"+fromBalance);
            sb.append(" Timestamp:"+ LocalDateTime.now());
            throw new RuntimeException(sb.toString());
        }
    }

    @Transactional
    @Override
    public int transfer(String wallet_uid, String to_wallet_uid, BigDecimal amount) {
        return pay(wallet_uid, to_wallet_uid, amount);
    }

    @Override
    public int receiveWithAmount(String wallet_uid, String from_wallet_uid, BigDecimal amount) {
        return 0;
    }

    @Override
    public int receive(String wallet_uid, String from_wallet_uid) {
        return 0;
    }

    @Override
    public int deleteWallet(int wallet_id) {
        return walletMapper.deleteWalletByWalletId(wallet_id);
    }
}
