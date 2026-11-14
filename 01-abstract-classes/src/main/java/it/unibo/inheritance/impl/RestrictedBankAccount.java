package it.unibo.inheritance.impl;

import it.unibo.inheritance.api.AccountHolder;

public class RestrictedBankAccount extends AbstractBankAccount {

    public RestrictedBankAccount(final AccountHolder accountHolder, final double balance) {
        super(accountHolder, balance);
    }

    //Questa classe che estende 'AbstractBankAccount' è obbligata a implementare i 2 metodi astratti.
    //La classe deve avere lo stesso comportamento di 'StrictBankAccount'.

    protected boolean isWithdrawAllowed(double amount) { //guardo come è implementato il metodo 'withdraw' di 'StrictBankAccount'.
        if (getBalance() >= amount) { //verifica il saldo prima di fare il prelievo
            return true; //restituisce true se il saldo è sufficiente
        }
        else {
            return false; ////restituisce false se il saldo non è sufficiente
        }
    }

    protected double computeFee() { //guardo come è implementato il metodo 'chargeManagementFees' di 'StrictBankAccount'.
        return 5 + (getTransactionsCount() * 0.1); //restituisco l'operazione del metodo 'chargeManagementFees' della classe 'StrictBankAccount'. 
        //il campo 'transactions' è privato, quindi richiamo il metodo 'getTransactionsCount()' che recupera il numero di transazioni effettuate.
    }
    
}