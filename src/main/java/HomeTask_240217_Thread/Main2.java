package HomeTask_240217_Thread;

import java.util.ArrayList;
import java.util.List;

public class Main2 {
    private static long sum = 0;
    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = new ArrayList<>(100000);
        for (int i = 0; i < 100000; i++) {
            list.add(i);
        }

        Thread t1 = new Thread(() -> {
            long localSum = 0;
            for (int i = 0; i < list.size() / 4; i++) {
                synchronized (lock) {
                    sum += list.get(i);
                }            }

        });
        Thread t2 = new Thread(() -> {
            long localSum = 0;
            for (int i = 0; i < list.size() / 4; i++) {
                synchronized (lock) {
                    sum += list.get(i);
                }            }

        });
        Thread t3 = new Thread(() -> {
            long localSum = 0;
            for (int i = 0; i < list.size() / 4; i++) {
                synchronized (lock) {
                    sum += list.get(i);
                }            }

        });
        Thread t4 = new Thread(() -> {
            long localSum = 0;
            for (int i = 0; i < list.size() / 4; i++) {
                synchronized (lock) {
                    sum += list.get(i);
                }            }

        });


        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();

        System.out.println(sum);
    }
}

