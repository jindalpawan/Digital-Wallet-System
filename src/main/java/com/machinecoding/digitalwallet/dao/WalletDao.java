package com.machinecoding.digitalwallet.dao;

import com.machinecoding.digitalwallet.entity.Wallet;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class WalletDao {

    private Map<String, Wallet> accountMap = new HashMap<>();
    private static WalletDao walletDao;

    private WalletDao() {}

    public static WalletDao getInstance(){
        if(walletDao == null){
            walletDao = new WalletDao();
        }
        return walletDao;
    }
}
