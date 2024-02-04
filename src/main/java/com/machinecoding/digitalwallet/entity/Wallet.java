package com.machinecoding.digitalwallet.entity;

import com.machinecoding.digitalwallet.utils.AccountUtils;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

@Data
public class Wallet extends Account implements Serializable {

    @Serial
    private static final long serialVersionUID = -1601025916428105936L;
    private Set<Transaction> transactions;

    public Wallet(String name, BigDecimal balance) {
        super(AccountUtils.getNextAccountNumber(), new User(name), balance);
        this.transactions = new TreeSet<>(Comparator.comparing(Transaction::getDatetime));
    }

    @Override
    public String toString() {
        return "Account Number = " + this.getAccountNumber() + ", Name = " + this.getUser().getName() + ", " +
                "current balance = " + this.getBalance() + ", transactions = " + this.transactions;
    }
}
