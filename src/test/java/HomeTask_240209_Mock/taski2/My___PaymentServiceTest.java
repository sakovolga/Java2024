package HomeTask_240209_Mock.taski2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MyPaymentServiceTest {
    @InjectMocks
    PaymentService ps;
    @Mock
    SimpleNotificationService sns;
    @Mock
    SimpleTransactionRepository str;

    @Test
    void makePaymentPositiveTest() {
        boolean expected = true;
        Mockito.when(str.processTransaction(10)).thenReturn(true);
        boolean actual = ps.makePayment(10);
        Assertions.assertEquals(expected, actual);
        Mockito.verify(sns).sendPaymentNotification(Mockito.anyString());
        Mockito.verify(str).processTransaction(10);
    }


    @Test
    void refundPaymentTest() {
//        Mockito.when(str.processTransaction(10.0)).thenReturn(true);
        ps.refundPayment(-10.0);
        Mockito.verify(str).processTransaction(10.0);
        Mockito.verify(sns).sendPaymentNotification(Mockito.anyString());
    }


    @Test
    void adjustPaymentScenario1Test() {
        Mockito.when(str.processTransaction(Mockito.anyDouble())).thenReturn(true).thenReturn(true);
        ps.adjustPayment(-76, 7);
        Mockito.verify(str, Mockito.times(2)).processTransaction(Mockito.anyDouble());
        Mockito.verify(sns, Mockito.times(2)).sendPaymentNotification(Mockito.anyString());
    }
    @Test
    void adjustPaymentScenario2Test() {
        Mockito.when(str.processTransaction(Mockito.anyDouble())).thenReturn(true).thenReturn(false);
        ps.adjustPayment(-76, -7);
        Mockito.verify(str, Mockito.times(2)).processTransaction(Mockito.anyDouble());
        Mockito.verify(sns, Mockito.times(2)).sendPaymentNotification(Mockito.anyString());
    }
    @Test
    void adjustPaymentScenario3Test() {
        Mockito.when(str.processTransaction(Mockito.anyDouble())).thenReturn(false);
        ps.adjustPayment(76, 7);
        Mockito.verify(str).processTransaction(Mockito.anyDouble());
        Mockito.verify(sns).sendPaymentNotification(Mockito.anyString());
    }
    @Test
    void verifyPaymentTest(){
        Assertions.assertTrue(ps.verifyPayment(Mockito.anyDouble()));
        Mockito.verify(sns).sendPaymentNotification(Mockito.anyString());
    }
    @Test
    void cancelPaymentTest(){
        ps.cancelPayment(Mockito.anyDouble());
        Mockito.verify(str).processTransaction(Mockito.anyDouble());
        Mockito.verify(sns).sendPaymentNotification(Mockito.anyString());
    }
    @Test
    void freezeTransactionTest(){
        ps.freezeTransaction(Mockito.anyDouble());
        Mockito.verify(sns).sendPaymentNotification(Mockito.anyString());
    }
    @Test
    void unfreezeTransactionTest(){
        ps.unfreezeTransaction(Mockito.anyDouble());
        Mockito.verify(sns).sendPaymentNotification(Mockito.anyString());
    }
    @Test
    void schedulePaymentTest(){
        Assertions.assertTrue(ps.schedulePayment(5, Mockito.anyString()));
        Mockito.verify(sns).sendPaymentNotification(Mockito.anyString());
    }
    @Test
    void confirmPaymentTest(){
        ps.confirmPayment(Mockito.anyDouble());
        Mockito.verify(sns).sendPaymentNotification(Mockito.anyString());
    }
    @Test
    void declinePaymentTest(){
        ps.declinePayment(Mockito.anyDouble());
        Mockito.verify(sns).sendPaymentNotification(Mockito.anyString());
    }
}