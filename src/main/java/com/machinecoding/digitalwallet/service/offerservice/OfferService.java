package com.machinecoding.digitalwallet.service.offerservice;


import com.machinecoding.digitalwallet.entity.dto.OfferCashbackDto;

import java.math.BigDecimal;

public interface OfferService {
    BigDecimal getOfferCashback(OfferCashbackDto info);
}
