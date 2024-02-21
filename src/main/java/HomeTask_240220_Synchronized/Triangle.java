package HomeTask_240220_Synchronized;

public class Triangle {
//    Сборкa автомобиля. Выполняется в трех потоках.
//    Сначала покраска, потом монтаж 4 колес, потом установка 6 электронных устройств.
//    Потом автомобиль сходит с конвейера, и цикл начинается заново.
    public static void main(String[] args) {
        Car car = new Car();
        Painter painter = new Painter(car);
        Mechanic mechanic = new Mechanic(car);
        Electrician electrician = new Electrician(car);
        Thread t1 = new Thread(painter);
        Thread t2 = new Thread(mechanic);
        Thread t3 = new Thread(electrician);
        t1.start();
        t2.start();
        t3.start();
    }
}

class Car {
    int paintingArea = 0;
    int wheel = 0;
    int electronicDevice = 0;

    public synchronized void paintCar() {
        while (paintingArea >= 100) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        paintingArea += 20;
        System.out.println("Покраска " + paintingArea);
        if (paintingArea == 100) {
            notifyAll();
        }
    }

    public synchronized void wheelMounting() {
        while (wheel >= 4 || paintingArea < 100 || electronicDevice != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        wheel++;
        System.out.println("Монтаж колес " + wheel);
        notifyAll();
    }

    public synchronized void electronicMounting() {
        while (electronicDevice >= 6 || wheel != 4 || paintingArea < 100) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        electronicDevice++;
        System.out.println("Монтаж электронного устройства " + electronicDevice);
        if (electronicDevice == 6) {
            wheel = 0;
            paintingArea = 0;
            electronicDevice = 0;
            System.out.println("Конвейер пуст!");
        }
        notifyAll();
    }
}

class Painter implements Runnable{
    Car car;
    public Painter(Car car) {
        this.car = car;
    }
    @Override
    public void run() {
        for (int i = 0; i < 15; i++) {
            car.paintCar();
        }
    }
}
class Mechanic implements Runnable{
    Car car;
    public Mechanic(Car car) {
        this.car = car;
    }
    @Override
    public void run() {
        for (int i = 0; i < 12; i++) {
            car.wheelMounting();
        }
    }
}
class Electrician implements Runnable{
    Car car;
    public Electrician(Car car) {
        this.car = car;
    }
    @Override
    public void run() {
        for (int i = 0; i < 18; i++) {
            car.electronicMounting();
        }
    }
}
