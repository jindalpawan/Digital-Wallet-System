package com.machinecoding.digitalwallet.service.offerservice.impl;

import com.machinecoding.digitalwallet.entity.Wallet;
import com.machinecoding.digitalwallet.entity.dto.OfferCashbackDto;
import com.machinecoding.digitalwallet.service.offerservice.OfferService;

import java.math.BigDecimal;

public class FirstTransactionCashback implements OfferService {

    private static FirstTransactionCashback firstTransactionCashback;

    private void FirstTransactionCashback(){}

    public static FirstTransactionCashback getInstance(){
        if(firstTransactionCashback == null){
            firstTransactionCashback = new FirstTransactionCashback();
        }
        return firstTransactionCashback;
    }

    @Override
    public BigDecimal getOfferCashback(OfferCashbackDto info) {
        BigDecimal cashbackAmount = BigDecimal.valueOf(0);
        if (firstTransaction(info.getUser())) {
            cashbackAmount = cashbackAmount.add(BigDecimal.valueOf(info.getTransferAmount().intValue())
                    .divide(BigDecimal.TEN));
        }
        return cashbackAmount;
    }

    private static boolean firstTransaction(Wallet sender) {
        return sender.getTransactions().size() <= 1;
    }
}
