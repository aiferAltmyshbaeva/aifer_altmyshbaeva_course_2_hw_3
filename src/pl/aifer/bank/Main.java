package pl.aifer.bank;

import pl.aifer.bank.BankAccount;
import pl.aifer.bank.LimitException;

public class Main {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.deposit(20_000);
        while (true){
            try {
                bankAccount.withdraw(6_000);
            } catch (LimitException e) {
                withdraw(bankAccount, (int) e.getRemainingAmount());
                break;
            }
        }
        System.out.println("bankAccount = " + bankAccount);
    }

    private static void withdraw(BankAccount bankAccount, int sum) {
        try {
            bankAccount.withdraw(sum);
        } catch (LimitException e) {

        }
    }
}