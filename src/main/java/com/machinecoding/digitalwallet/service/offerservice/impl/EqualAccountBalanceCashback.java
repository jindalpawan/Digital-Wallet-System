package com.machinecoding.digitalwallet.service.offerservice.impl;

import com.machinecoding.digitalwallet.entity.dto.OfferCashbackDto;
import com.machinecoding.digitalwallet.service.offerservice.OfferService;

import java.math.BigDecimal;

public class EqualAccountBalanceCashback implements OfferService {
    private static EqualAccountBalanceCashback equalAccountBalanceCashback;

    private void EqualAccountBalanceCashback(){}

    public static EqualAccountBalanceCashback getInstance(){
        if(equalAccountBalanceCashback == null){
            equalAccountBalanceCashback = new EqualAccountBalanceCashback();
        }
        return equalAccountBalanceCashback;
    }

    @Override
    public BigDecimal getOfferCashback(OfferCashbackDto info) {
        BigDecimal cashbackAmount = BigDecimal.valueOf(0);
        if (info.getSender().getBalance().equals(info.getReceiver().getBalance())) {
            BigDecimal cashbackToAdd = BigDecimal.valueOf(info.getTransferAmount().intValue())
                    .divide(BigDecimal.valueOf(5));
            cashbackAmount = cashbackAmount.add(cashbackToAdd);
        }
        return cashbackAmount;
    }
}
