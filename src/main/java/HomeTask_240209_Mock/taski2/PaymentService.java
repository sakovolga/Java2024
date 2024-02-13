package HomeTask_240209_Mock.taski2;

public class PaymentService {
    private final SimpleNotificationService notificationService;
    private final SimpleTransactionRepository transactionRepository;

    public PaymentService(SimpleNotificationService notificationService, SimpleTransactionRepository transactionRepository) {
        this.notificationService = notificationService;
        this.transactionRepository = transactionRepository;
    }

    public boolean makePayment(double amount) {
        boolean result = transactionRepository.processTransaction(amount);
        if (result) {
            notificationService.sendPaymentNotification("Payment successful for amount: " + amount);
        } else {
            notificationService.sendPaymentNotification("Payment failed for amount: " + amount);
        }
        return result;
    }

    public void refundPayment(double amount) {
        boolean result = transactionRepository.processTransaction(-amount);
        if (result) {
            notificationService.sendPaymentNotification("Refund successful for amount: " + amount);
        } else {
            notificationService.sendPaymentNotification("Refund failed for amount: " + amount);
        }
    }

    public void adjustPayment(double originalAmount, double newAmount) {
        boolean refundResult = transactionRepository.processTransaction(-originalAmount);
        if (refundResult) {
            notificationService.sendPaymentNotification("Adjustment refund successful for amount: " + originalAmount);
            boolean paymentResult = transactionRepository.processTransaction(newAmount);
            if (paymentResult) {
                notificationService.sendPaymentNotification("Adjustment payment successful for amount: " + newAmount);
            } else {
                notificationService.sendPaymentNotification("Adjustment payment failed for amount: " + newAmount);
            }
        } else {
            notificationService.sendPaymentNotification("Adjustment refund failed for amount: " + originalAmount);
        }
    }

    public boolean verifyPayment(double amount) {
        notificationService.sendPaymentNotification("Payment verified for amount: " + amount);
        return true;
    }

    public void cancelPayment(double amount) {
        boolean refundResult = transactionRepository.processTransaction(-amount);
        if (refundResult) {
            notificationService.sendPaymentNotification("Payment cancellation successful for amount: " + amount);
        } else {
            notificationService.sendPaymentNotification("Payment cancellation failed for amount: " + amount);
        }
    }

    public void freezeTransaction(double amount) {
        notificationService.sendPaymentNotification("Transaction for amount: " + amount + " is frozen.");
    }

    public void unfreezeTransaction(double amount) {
        notificationService.sendPaymentNotification("Transaction for amount: " + amount + " is unfrozen.");
    }

    public boolean schedulePayment(double amount, String date) {
        notificationService.sendPaymentNotification("Payment for amount: " + amount + " is scheduled for " + date + ".");
        return true;
    }

    public void confirmPayment(double amount) {
        notificationService.sendPaymentNotification("Payment for amount: " + amount + " is confirmed.");
    }

    public void declinePayment(double amount) {
        notificationService.sendPaymentNotification("Payment for amount: " + amount + " is declined.");
    }
}
