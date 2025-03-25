package com.yunqi.myeshop.mapper;

import com.yunqi.myeshop.entity.user.Wallet;
import com.yunqi.myeshop.entity.userdto.*;
import org.apache.ibatis.annotations.*;
import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface WalletMapper {

    @Insert("INSERT INTO tb_wallets (account_id, wallet_uid, balance) VALUES ( #{account_id}," +
            "#{wallet_uid},#{balance})")
    @Options(useGeneratedKeys = true,keyProperty = "wallet_id")
    int insertWallet(Wallet wallet);

    @Select("SELECT wallet_uid, balance FROM tb_wallets WHERE wallet_id=#{wallet_id}")
    WalletDetailDTO findWalletByWalletId(int wallet_id);

    @Select("SELECT wallet_uid, balance FROM tb_wallets WHERE wallet_uid=#{wallet_uid}")
    WalletDetailDTO findWalletByWalletUId(String wallet_uid);

    @Select("SELECT wallet_uid, balance FROM tb_wallets WHERE account_id=#{account_id}")
    WalletDetailDTO findWalletByAccountId(int account_id);

    @Select("SELECT wallet_uid, balance FROM tb_wallets")
    List<WalletDetailDTO> findAllWallets();

    @Select("SELECT balance FROM tb_wallets where wallet_id=#{wallet_id}")
    BigDecimal findWalletBalanceByWalletId(int wallet_id);
    @Select("SELECT balance FROM tb_wallets where wallet_uid=#{wallet_uid}")
    BigDecimal findWalletBalanceByWalletUId(String wallet_uid);

    @Update("UPDATE tb_wallets SET balance=#{balance} WHERE wallet_uid=#{wallet_uid}")
    int updateWallet(String wallet_uid, BigDecimal balance);

    @Delete("DELETE  FROM tb_wallets WHERE wallet_id=#{wallet_id}")
    int deleteWalletByWalletId(int wallet_id);
}
