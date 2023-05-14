package pl.aifer.bank;

public class Main {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    public static final int INITIAL_AMOUNT = 20_000;
    public static final int SUM = 6_000;

    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.deposit(INITIAL_AMOUNT);
        while (true){
            try {
                withdraw(bankAccount, SUM);
            } catch (LimitException e) {
                System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
                failedWithdraw(bankAccount, (int) e.getRemainingAmount());
                break;
            }
        }
        System.out.println("Остаток на счете = " + bankAccount.getAmount());
    }

    private static void withdraw(BankAccount bankAccount, int sum) throws LimitException {
        System.out.println(String.format("Остаток на счете = %.2f; сумма к снятию %d",
                bankAccount.getAmount(),
                sum));
        bankAccount.withdraw(sum);
    }

    private static void failedWithdraw(BankAccount bankAccount, int sum) {
        Exception throwable = null;
        try {
            withdraw(bankAccount, sum);
        } catch (LimitException e) {
            throwable = e;
        } finally {
            if (throwable != null) {
                throw new RuntimeException("Произошла непредвиденная ошибка", throwable);
            }
        }
    }
}