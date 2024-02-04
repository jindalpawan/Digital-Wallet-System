package com.machinecoding.digitalwallet.service;

import com.machinecoding.digitalwallet.dao.WalletDao;
import com.machinecoding.digitalwallet.entity.Transaction;
import com.machinecoding.digitalwallet.entity.Wallet;
import com.machinecoding.digitalwallet.entity.dto.OfferCashbackDto;
import com.machinecoding.digitalwallet.enums.StatementSort;
import com.machinecoding.digitalwallet.service.offerservice.FactoryOfferService;
import com.machinecoding.digitalwallet.service.offerservice.OfferService;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

import static com.machinecoding.digitalwallet.utils.WalletServiceUtils.validateAccountBalance;
import static com.machinecoding.digitalwallet.utils.WalletServiceUtils.validationCheck;
import static com.machinecoding.digitalwallet.utils.WalletServiceUtils.validationCheckTopUp;
import static java.time.ZoneOffset.UTC;

public class WalletService {

    private static WalletDao walletDao;
    private static WalletService walletService;
    private static FactoryOfferService factoryOfferService;

    private WalletService() {
    }

    public static WalletService getInstance() {
        if (walletService == null) {
            walletDao = WalletDao.getInstance();
            walletService = new WalletService();
            factoryOfferService = FactoryOfferService.getInstance();
        }
        return walletService;
    }

    public void createWallet(String name, BigDecimal amount) {
        Wallet wallet = new Wallet(name, amount);
        this.walletDao.getAccountMap().put(wallet.getAccountNumber(), wallet);
        System.out.println("Wallet Account created for user " + name + " with account number " + wallet.getAccountNumber());
    }

    public void topUpWallet(String userAccountNumber, BigDecimal amount) {
        addBalance(userAccountNumber, amount, "Top-up");
    }

    public void addCashbackIntoWallet(String userAccountNumber, BigDecimal amount) {
        addBalance(userAccountNumber, amount, "Cashback");
    }

    public void addBalance(String userAccountNumber, BigDecimal amount, String msg) {
        if (!validationCheckTopUp(this.walletDao, userAccountNumber, amount)) {
            return;
        }
        Wallet userAccount = this.walletDao.getAccountMap().get(userAccountNumber);
        Transaction transaction = new Transaction(userAccountNumber, null, amount, LocalDateTime.now(UTC), msg);
        userAccount.setBalance(userAccount.getBalance().add(amount));
        userAccount.getTransactions().add(transaction);
        System.out.println("Transaction Successful");
    }

    public void amountTransfer(String sender, String receiver, BigDecimal transferAmount) {
        if (!validationCheck(this.walletDao, sender, receiver, transferAmount)) {
            return;
        }

        Wallet senderAccount = this.walletDao.getAccountMap().get(sender);
        if (!validateAccountBalance(senderAccount.getBalance(), transferAmount)) {
            return;
        }

        Wallet receiverAccount = this.walletDao.getAccountMap().get(receiver);
        Transaction transaction = new Transaction(sender, receiver, transferAmount, LocalDateTime.now(UTC),
                "Amount Transfer");
        senderAccount.setBalance(senderAccount.getBalance().subtract(transferAmount));
        receiverAccount.setBalance(receiverAccount.getBalance().add(transferAmount));
        senderAccount.getTransactions().add(transaction);
        receiverAccount.getTransactions().add(transaction);
        OfferCashbackDto offerCashbackDto = new OfferCashbackDto(senderAccount, receiverAccount, senderAccount,
                transferAmount);
        BigDecimal senderCashbackAmount = new BigDecimal(0);
        for(OfferService offerService: factoryOfferService.getOfferServiceList()){
            senderCashbackAmount = senderCashbackAmount.add(offerService.getOfferCashback(offerCashbackDto));
        }
        addCashbackIntoWallet(sender, senderCashbackAmount);
        System.out.println("Transfer Successful");
    }

    public void statement(String walletAccountNumber, StatementSort sort, String receiverId) {
        Wallet wallet = this.walletDao.getAccountMap().get(walletAccountNumber);
        if (wallet == null) {
            System.out.println("Invalid Account Number");
            return;
        }
        System.out.println("Account state for account number: " + walletAccountNumber);
        System.out.println("Your Transaction History: ");
        Set<Transaction> transactions = wallet.getTransactions();
        if (StringUtils.hasLength(receiverId)) {
            transactions =
                    transactions.stream().filter(tran -> tran.getReceiver().equals(receiverId))
                            .collect(Collectors.toSet());
        }
        switch (sort) {
            case Amount:
                transactions.stream().sorted(Comparator.comparing(Transaction::getAmount))
                        .toList()
                        .forEach(System.out::println);
                break;
            case DateTime:
                transactions.stream().sorted(Comparator.comparing(Transaction::getDatetime))
                        .toList()
                        .forEach(System.out::println);
                break;
            case None:
                transactions.forEach(System.out::println);
                break;
        }
    }

    public void walletDaoOverview() {
        for (String accNum : walletDao.getAccountMap().keySet()) {
            System.out.println("Current wallet account balance for account number " + accNum + ": "
                    + walletDao.getAccountMap().get(accNum).getBalance());
        }
    }

    public void userWalletBalance(String walletAccountNum) {
        Wallet wallet = this.walletDao.getAccountMap().get(walletAccountNum);
        if (wallet == null) {
            System.out.println("Invalid Account Number");
            return;
        }
        System.out.println("Current wallet account balance: " + wallet.getBalance());
    }
}