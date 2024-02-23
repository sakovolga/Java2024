package HomeTask_240222;

import java.util.concurrent.Phaser;

public class Bus {
    public static void main(String[] args) {
        Phaser phaser = new Phaser();
        phaser.register();
        new Thread(new Pupil(phaser, "Петя Петров")).start();
        new Thread(new Pupil(phaser, "Ваня Иванов")).start();
        new Thread(new Pupil(phaser, "Вася Васильев")).start();
        new Thread(new Pupil(phaser, "Семен Семенов")).start();

        phaser.arriveAndAwaitAdvance();
        System.out.println("Все в автобусе, поехали");
        phaser.arriveAndAwaitAdvance();
        System.out.println("Автобус пустой");

        phaser.arriveAndDeregister();
    }
}
class Pupil implements Runnable{
    Phaser phaser;
    String name;

    public Pupil(Phaser phaser, String name) {
        this.phaser = phaser;
        this.name = name;
        phaser.register();
    }

    @Override
    public void run() {
        System.out.println("Ученик " + name + " заходит в автобус");
        phaser.arriveAndAwaitAdvance();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Ученик " + name + " выходит из автобуса");
        phaser.arriveAndDeregister();
    }
}