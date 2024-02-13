package HomeTask_240209_Mock.taski2;

import java.util.ArrayList;
import java.util.List;

public class SimpleTransactionRepository implements TransactionRepository {
    private final List<Transaction> transactions = new ArrayList<>();

    @Override
    public boolean processTransaction(double amount) {
        // Положительная сумма считается успешной транзакцией.
        boolean isSuccess = amount > 0;
        Transaction transaction = new Transaction(amount, isSuccess);
        transactions.add(transaction);

        System.out.println("Transaction processed: Amount = " + amount + ", Success = " + isSuccess);

        return isSuccess;
    }

    public List<Transaction> getAllTransactions() {
        return new ArrayList<>(transactions);
    }
}