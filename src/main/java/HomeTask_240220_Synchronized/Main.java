package HomeTask_240220_Synchronized;

public class Main {
    public static void main(String[] args) {
        MessageBuffer messageBuffer = new MessageBuffer();
        Sender sender = new Sender();
        sender.messageBuffer = messageBuffer;
        Receiver receiver = new Receiver();
        receiver.messageBuffer = messageBuffer;
        Thread t1 = new Thread(sender);
        Thread t2 = new Thread(receiver);
        t1.start();
        t2.start();
    }
}
class MessageBuffer{
    int countMessages = 0;
    public synchronized void putToBuffer(){
        while (countMessages == 5){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        countMessages++;
        System.out.println("putToBuffer " + countMessages);
        notify();
    }
    public synchronized void getFromBuffer(){
        while (countMessages == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        countMessages--;
        System.out.println("getFromBuffer " + countMessages);
        notify();
    }
}
class Sender implements Runnable{
    MessageBuffer messageBuffer;

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            messageBuffer.putToBuffer();
        }
    }
}
class Receiver implements Runnable{
    MessageBuffer messageBuffer;
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            messageBuffer.getFromBuffer();
        }
    }
}
