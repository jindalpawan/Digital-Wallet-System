package com.machinecoding.digitalwallet.service.offerservice;

import com.machinecoding.digitalwallet.service.offerservice.impl.EqualAccountBalanceCashback;
import com.machinecoding.digitalwallet.service.offerservice.impl.FirstTransactionCashback;

import java.util.ArrayList;
import java.util.List;

public class FactoryOfferService {
    private static List<OfferService> offerServiceList;
    private static FactoryOfferService factoryOfferService;

    private void FactoryOfferService(){}

    public static FactoryOfferService getInstance(){
        if(factoryOfferService == null){
            factoryOfferService = new FactoryOfferService();
            offerServiceList = new ArrayList<>();
            offerServiceList.add(EqualAccountBalanceCashback.getInstance());
            offerServiceList.add(FirstTransactionCashback.getInstance());
        }
        return factoryOfferService;
    }

    public List<OfferService> getOfferServiceList(){
        return offerServiceList;
    }
}
