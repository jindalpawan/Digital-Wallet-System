package com.machinecoding.digitalwallet.utils;

public class AccountUtils {

    private void AccountUtils(){}
    private static int nextAccountNumber = 1;
    public static synchronized String getNextAccountNumber() {
        String accountNumber = Integer.toString(nextAccountNumber);
        int accountNumLength = accountNumber.length();
        StringBuilder a = new StringBuilder();
        a.append("0".repeat(Math.max(0, 16 - accountNumLength)));
        nextAccountNumber++;
        return a.append(accountNumber).toString();
    }
}
