package HomeTask_240209_Mock.taski2;

public class Transaction {
    private final double amount;
    private final boolean isSuccess;

    public Transaction(double amount, boolean isSuccess) {
        this.amount = amount;
        this.isSuccess = isSuccess;
    }

    public double getAmount() {
        return amount;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "amount=" + amount +
                ", isSuccess=" + isSuccess +
                '}';
    }
}
