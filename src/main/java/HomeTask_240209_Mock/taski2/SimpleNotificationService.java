package HomeTask_240209_Mock.taski2;

public class SimpleNotificationService implements NotificationService {

    @Override
    public void sendPaymentNotification(String message) {
        System.out.println("Notification sent: " + message);
    }
}