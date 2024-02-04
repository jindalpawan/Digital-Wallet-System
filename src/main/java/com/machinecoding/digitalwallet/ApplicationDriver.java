package com.machinecoding.digitalwallet;

import com.machinecoding.digitalwallet.enums.StatementSort;
import com.machinecoding.digitalwallet.service.WalletService;

import java.math.BigDecimal;
import java.util.Scanner;

import static com.machinecoding.digitalwallet.utils.ConstantVariables.ACCOUNT_BALANCE;
import static com.machinecoding.digitalwallet.utils.ConstantVariables.ACCOUNT_STATEMENT;
import static com.machinecoding.digitalwallet.utils.ConstantVariables.Amount;
import static com.machinecoding.digitalwallet.utils.ConstantVariables.CREATE_WALLET;
import static com.machinecoding.digitalwallet.utils.ConstantVariables.DateTime;
import static com.machinecoding.digitalwallet.utils.ConstantVariables.EXIT;
import static com.machinecoding.digitalwallet.utils.ConstantVariables.None;
import static com.machinecoding.digitalwallet.utils.ConstantVariables.OVERVIEW;
import static com.machinecoding.digitalwallet.utils.ConstantVariables.TOPUP_AMOUNT;
import static com.machinecoding.digitalwallet.utils.ConstantVariables.TRANSFER_AMOUNT;

public class ApplicationDriver {

    public static void main(String[] args) {
        WalletService wService = WalletService.getInstance();
        Scanner sc = new Scanner(System.in);

        try {
            outerFlow:
            while (true) {
                baseMenu();

                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case CREATE_WALLET:
                        createWallet(sc, wService);
                        break;

                    case TRANSFER_AMOUNT:
                        transferAmount(sc, wService);
                        break;

                    case TOPUP_AMOUNT:
                        topUpAmount(sc, wService);
                        break;

                    case ACCOUNT_STATEMENT:
                        accountStatement(sc, wService);
                        break;

                    case ACCOUNT_BALANCE:
                        accountBalance(sc, wService);
                        break;

                    case OVERVIEW:
                        wService.walletDaoOverview();
                        break;
                    case EXIT:
                        System.out.println("Application Terminated");
                        break outerFlow;

                    default:
                        System.out.println("Wrong choice. Reenter please");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            sc.close();
        }
    }

    private static void createWallet(Scanner sc, WalletService wService) {
        System.out.println("Enter name");
        String userName = sc.nextLine();
        System.out.println("Enter initial amount");
        BigDecimal amount = sc.nextBigDecimal();
        wService.createWallet(userName, amount);
    }

    private static void topUpAmount(Scanner sc, WalletService wService) {
        System.out.println("Enter account number");
        String sender = sc.nextLine();
        System.out.println("Enter amount");
        BigDecimal amount1 = sc.nextBigDecimal();
        wService.topUpWallet(sender,amount1);
    }

    private static void transferAmount(Scanner sc, WalletService wService) {
        System.out.println("Enter Sender wallet account number");
        String sender = sc.nextLine();
        System.out.println("Enter Receiver wallet account number");
        String receiver = sc.nextLine();
        System.out.println("Enter amount");
        BigDecimal amount1 = sc.nextBigDecimal();
        wService.amountTransfer(sender, receiver, amount1);
    }

    private static void accountStatement(Scanner sc, WalletService wService) {
        System.out.println("Enter wallet account number");
        String userAccount = sc.nextLine();
        String receiverId = statementFilter(sc);
        wService.statement(userAccount, statementSorting(sc), receiverId);
    }

    private static void accountBalance(Scanner sc, WalletService wService) {
        System.out.println("Enter wallet account number");
        wService.userWalletBalance(sc.nextLine());
    }

    private static void baseMenu() {
        System.out.println("\nChoose:");
        System.out.println("1. Register user or Create wallet");
        System.out.println("2. Transfer Amount");
        System.out.println("3. Top-up Amount");
        System.out.println("4. Account Statement");
        System.out.println("5. Current Account Balance");
        System.out.println("6. Overview");
        System.out.println("7. Exit");
        System.out.println("Enter your choice");
    }

    private static StatementSort statementSorting(Scanner sc) {
        StatementSort sorting = null;
        innerFlow: while(true){
            System.out.println("\nWant to sort wallet account statement based on below parameter:");
            System.out.println("1. Date");
            System.out.println("2. Amount");
            System.out.println("3. None");
            System.out.println("Enter your choice");
            int ch = sc.nextInt();
            switch (ch) {
                case DateTime:
                    sorting = StatementSort.DateTime;
                    break innerFlow;

                case Amount:
                    sorting = StatementSort.Amount;
                    break innerFlow;

                case None:
                    sorting = StatementSort.None;
                    break innerFlow;

                default:
                    System.out.println("Wrong choice. Reenter please");
                    break;
            }
        }
        return sorting;
    }

    private static String statementFilter(Scanner sc) {
        String receiverId = null;
        innerFlow: while(true){
            System.out.println("\nWant to filter statement based on receiver user:");
            System.out.println("1. Yes");
            System.out.println("2. No");
            System.out.println("Enter your choice");
            int ch = sc.nextInt();
            switch (ch) {
                case DateTime:
                    System.out.println("Enter receiver account number");
                    receiverId = sc.nextLine();
                    break innerFlow;

                case Amount:
                    break innerFlow;

                default:
                    System.out.println("Wrong choice. Reenter please");
                    break;
            }
        }
        return receiverId;
    }
}
