package it.unibo.inheritance.impl;

import it.unibo.inheritance.api.AccountHolder;
import it.unibo.inheritance.api.BankAccount;

public class StrictBankAccount implements BankAccount {

    private static final double ATM_TRANSACTION_FEE = 1;
    private static final double MANAGEMENT_FEE = 5;
    public static final double TRANSACTION_FEE = 0.1;

    private final AccountHolder holder;
    private double balance;
    private int transactions;

    public StrictBankAccount(final AccountHolder accountHolder, final double balance) {
        this.holder = accountHolder;
        this.balance = balance;
        this.transactions = 0;
    }

    private void transactionOp(final int id, final double amount) {
        if (checkUser(id)) {
            this.balance += amount;
            this.incrementTransactions();
        }
    }

    @Override
    public void deposit(final int id, final double amount) {
        this.transactionOp(id, amount);
    }
    
    // Esegue il prelievo solo se il saldo è sufficiente (lo verifica col metodo in fondo 'isWithdrawAllowed'
    @Override
    public void withdraw(final int id, final double amount) {
        if (isWithdrawAllowed(amount)) {
            this.transactionOp(id, -amount);
        }
    }

    @Override
    public void depositFromATM(final int id, final double amount) {
        this.deposit(id, amount - ATM_TRANSACTION_FEE);
    }

    @Override
    public void withdrawFromATM(final int id, final double amount) {
        this.withdraw(id, amount + ATM_TRANSACTION_FEE);
    }

    private void incrementTransactions() {
        this.transactions++;
    }

    @Override
    public AccountHolder getAccountHolder() {
        return this.holder;
    }

    @Override
    public double getBalance() {
        return this.balance;
    }

    @Override
    public int getTransactionsCount() {
        return this.transactions;
    }

    @Override
    public void chargeManagementFees(final int id) {
        final double feeAmount = MANAGEMENT_FEE + this.transactions * TRANSACTION_FEE;

        if (checkUser(id) && isWithdrawAllowed(feeAmount)) {
            this.balance -= feeAmount;
            this.transactions = 0;
        }
    }

    private boolean checkUser(final int id) {
        return this.holder.getUserID() == id;
    }

    // Verifica il saldo prima di fare il prelievo. Se è true esegue il metodo 'withdraw'.
    private boolean isWithdrawAllowed(final double amount) { 
        return this.balance >= amount;
    }
}