# Design with abstract classes

Creare da zero una `abstract class AbstractBankAccount implements BankAccount`:

- Deve contenere tutti gli elementi comuni a tutte le implementazioni di 'BankAccount'.
- Tutti i campi devono essere private.
- La classe deve esporre come public tutti i metodi di 'BankAccount', e soltanto quelli.
- La classe deve definire un metodo protected abstract chiamato 'boolean isWithDrawAllowed(double)', che restituisce true se è possibile prelevare dal conto bancario l'importo passato come parametro.
- La classe deve definire un metodo protected abstract chiamato 'double computeFee()', che restituisce l'importo attuale delle spese di gestione (senza modificare lo stato di 'AbstractBankAccount').
Creare da zero una classe 'ClassicBankAccount extends AbstractBankAccount', con lo stesso comportamento di 'SimpleBankAccount'.

Creare da zero una classe 'RestrictedBankAccount extends AbstractBankAccount', con lo stesso comportamento di 'StrictBankAccount'.

Infine: nel codice di AbstractBankAccount withdraw() e chargeManagementFees() non utilizzavano ancora isWithDrawAllowed() e computeFee(). Devi collegarli, altrimenti le due sottoclassi non avranno davvero comportamenti differenti.

Secondo te, questo design è migliore o peggiore rispetto a quello precedente? Perché?
- DESIGN PRECEDENTE: SimpleBankAccount e StrictBankAccount contenevano separatamente gran parte degli stessi metodi e campi.
- NUOVO DESIGN: AbstractBankAccount raccoglie tutto ciò che hanno in comune, mentre ClassicBankAccount e              RestrictedBankAccount implementano soltanto le differenze.