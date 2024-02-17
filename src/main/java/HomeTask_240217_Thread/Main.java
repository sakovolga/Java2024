package HomeTask_240217_Thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        AtomicLong sum = new AtomicLong(0);

        List<Integer> list = new ArrayList<>(100000);
        for (int i = 0; i < 100000; i++) {
            list.add(i);
        }

        Thread t1 = new Thread(() ->{
            for (int i = 0; i < list.size() / 4; i++){
                sum.addAndGet(list.get(i));
            }
        });
        Thread t2 = new Thread(() ->{
            for (int i = list.size() / 4; i < list.size() / 2; i++){
                sum.addAndGet(list.get(i));
            }
        });
        Thread t3 = new Thread(() ->{
            for (int i = list.size() / 2; i < list.size()*3/4; i++){
                sum.addAndGet(list.get(i));
            }
        });
        Thread t4 = new Thread(() ->{
            for (int i = list.size()*3/4; i < list.size(); i++){
                sum.addAndGet(list.get(i));
            }
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
