package com.machinecoding.digitalwallet.entity.dto;

import com.machinecoding.digitalwallet.entity.Wallet;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class OfferCashbackDto {
    private Wallet sender;
    private Wallet receiver;
    private Wallet user;
    private BigDecimal transferAmount;
}
