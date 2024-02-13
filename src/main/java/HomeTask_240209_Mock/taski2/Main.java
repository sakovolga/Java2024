package HomeTask_240209_Mock.taski2;

public class Main {
    public static void main(String[] args) {
        PaymentService paymentService = new PaymentService(new SimpleNotificationService(), new SimpleTransactionRepository());

        paymentService.adjustPayment(-112, 13);


    }
}
