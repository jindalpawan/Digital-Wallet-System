package com.machinecoding.digitalwallet.utils;


import com.machinecoding.digitalwallet.dao.WalletDao;

import java.math.BigDecimal;

public class WalletServiceUtils {

    private void WalletServiceUtils(){}

    public static boolean validationCheckTopUp(WalletDao dao, String sender,
                                               BigDecimal transferAmount) {
        if (transferAmount.compareTo(new BigDecimal(0.0)) <= 0) {
            System.out.println("Transfer amount too low");
            return false;
        }
        if (!dao.getAccountMap().containsKey(sender)) {
            System.out.println("Invalid Sender account number");
            return false;
        }
        return true;
    }

    public static boolean validationCheck(WalletDao dao, String sender, String receiver, BigDecimal transferAmount) {
        if (sender.equals(receiver)) {
            System.out.println("Sender and Receiver cannot be same.");
            return false;
        }
        if (transferAmount.compareTo(new BigDecimal(0.0)) <= 0) {
            System.out.println("Transfer amount too low");
            return false;
        }
        if (!dao.getAccountMap().containsKey(sender)) {
            System.out.println("Invalid Sender account number");
            return false;
        }
        if (!dao.getAccountMap().containsKey(receiver)) {
            System.out.println("Invalid Receiver account number");
            return false;
        }
        return true;
    }

    public static boolean validateAccountBalance(BigDecimal currentBalance, BigDecimal transferAmount){
        if (currentBalance.compareTo(transferAmount) < 0) {
            System.out.println("Insufficient Balance");
            return false;
        }
        return true;
    }
}
