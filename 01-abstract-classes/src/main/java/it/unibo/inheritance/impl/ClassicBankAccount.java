package it.unibo.inheritance.impl;

import it.unibo.inheritance.api.AccountHolder;

public class ClassicBankAccount extends AbstractBankAccount {

    public ClassicBankAccount(final AccountHolder accountHolder, final double balance) {
        super(accountHolder, balance);
    }

    //Questa classe che estende 'AbstractBankAccount' è obbligata a implementare i 2 metodi astratti.
    //La classe deve avere lo stesso comportamento di 'SimpleBankAccount'.

    protected boolean isWithdrawAllowed(double amount) { //guardo come è implementato il metodo 'withdraw' di 'SimpleBankAccount'.
        return true; //restituisce sempre true perchè in 'SimpleBankAccount' se il conto va in rosso (ammontare negativo) il prelievo non si blocca.
    }

    protected double computeFee() { //guardo come è implementato il metodo 'chargeManagementFees' di 'SimpleBankAccount'.
        return 5; //MANAGEMENT_FEE in 'SimpleBankAccount' vale 5. Il metodo di la restituisce una valore, quindi qui devo restituire il valore previsto.
    }
}
