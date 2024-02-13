package HomeTask_240209_Mock.taski2;

public interface TransactionRepository {
    /**
     * Обрабатывает транзакцию с указанной суммой.
     * @param amount сумма транзакции.
     * @return true, если транзакция успешно обработана, иначе false.
     */
    boolean processTransaction(double amount);
}
