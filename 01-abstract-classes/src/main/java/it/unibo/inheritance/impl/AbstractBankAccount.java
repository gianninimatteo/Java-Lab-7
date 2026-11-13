package it.unibo.inheritance.impl;

import it.unibo.inheritance.api.AccountHolder;
import it.unibo.inheritance.api.BankAccount;

public abstract class AbstractBankAccount implements BankAccount {
    
    private static final double ATM_TRANSACTION_FEE = 1;
    private static final double MANAGEMENT_FEE = 5;

    private final AccountHolder holder;
    private double balance;
    private int transactions;

    public AbstractBankAccount(final AccountHolder accountHolder, final double balance) {
        this.holder = accountHolder;
        this.balance = balance;
        this.transactions = 0; //transactions deve partire sempre da 0 quando viene creato un nuovo conto.
    }

    //Metodo esposto di 'BankAccount'
    @Override
    public void chargeManagementFees(final int id) { 
        /*
         * Riduce il bilancio del conto di un ammontare pari alle spese di gestione
         */
        if (checkUser(id)) {
            this.balance -= AbstractBankAccount.MANAGEMENT_FEE;
            resetTransactions();
        }
    }

    protected boolean checkUser(final int id) {
        return this.getAccountHolder().getUserID() == id;
    }

    protected void resetTransactions() {
        this.transactions = 0;
    }

    //Metodo esposto di 'BankAccount'
    @Override
    public void deposit(final int id, final double amount) {
        /*
         * Incrementa il numero di transazioni e aggiunge amount al totale del
         * conto Nota: il deposito va a buon fine solo se l'id utente
         * corrisponde
         */
        this.transactionOp(id, amount);
    }

    private void transactionOp(final int id, final double amount) {
        if (checkUser(id)) {
            this.balance += amount;
            this.incrementTransactions();
        }
    }

    protected void incrementTransactions() {
        this.transactions++;
    }

    //Metodo esposto di 'BankAccount'
    @Override
    public void depositFromATM(final int id, final double amount) {
        /*
         * Incrementa il numero di transazioni e aggiunge amount al totale del
         * conto detraendo le spese (costante ATM_TRANSACTION_FEE) relative
         * all'uso dell'ATM (bancomat) Nota: il deposito va a buon fine solo se
         * l'id utente corrisponde
         */
        this.deposit(id, amount - AbstractBankAccount.ATM_TRANSACTION_FEE);
    }

    //Metodo esposto di 'BankAccount'
    @Override
    public AccountHolder getAccountHolder() {
        return holder;
    }

    //Metodo esposto di 'BankAccount'
    @Override
    public double getBalance() {
        return this.balance;
    }

    //Metodo esposto di 'BankAccount'
    @Override
    public int getTransactionsCount() {
        return this.transactions;
    }

    //Metodo esposto di 'BankAccount'
    @Override
    public void withdraw(final int id, final double amount) {
        /*
         * Incrementa il numero di transazioni e rimuove amount al totale del
         * conto. Note: - Il conto puo' andare in rosso (ammontare negativo) -
         * Il prelievo va a buon fine solo se l'id utente corrisponde
         */
        this.transactionOp(id, -amount);
    }

    //Metodo esposto di 'BankAccount'
    @Override
    public void withdrawFromATM(final int id, final double amount) {
        /*
         * Incrementa il numero di transazioni e rimuove amount + le spese
         * (costante ATM_TRANSACTION_FEE) relative all'uso dell'ATM (bancomat)
         * al totale del conto. Note: - Il conto puo' andare in rosso (ammontare
         * negativo) - Il prelievo va a buon fine solo se l'id utente
         * corrisponde
         */
        this.withdraw(id, amount + AbstractBankAccount.ATM_TRANSACTION_FEE);
    }

    // 2 METODI ASTRATTI
    // Un metodo abstract non ha mai il corpo { }: dichiara soltanto la firma.

    protected abstract boolean isWithDrawAllowed(double amount); //restituisce true se è possibile prelevare l'importo passato come parametro.

    protected abstract double computeFee(); //restituisce l'importo attuale delle spese di gestione
}
