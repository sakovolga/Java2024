package HomeTask_240125.taski;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    /**
     * Создайте объекты класса Book для каждой строки.
     * Используйте Stream API для преобразования строк в объекты.
     * Сериализуйте список книг в файл с использованием ObjectOutputStream.
     */
    public static void main(String[] args) throws RuntimeException {
        try (BufferedReader reader = new BufferedReader(new FileReader("taski.txt"));
             ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("taski.ser"))) {
            reader.lines()
                    .forEach(s -> {
                        int id = Integer.parseInt(s.split(", ")[0]);
                        String title = s.split(", ")[1];
                        String author = s.split(", ")[2];
                        int year = Integer.parseInt(s.split(", ")[3]);
                        double price = Integer.parseInt(s.split(", ")[4]);
                        try {
                            outputStream.writeObject(new Book(id, title, author, year, price));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class Main1 {

    /**
     * Десериализуйте список книг из файла,
     * созданного в предыдущем задании, используя ObjectInputStream.
     * Используйте Stream API для фильтрации книг, например, по автору или году издания.
     * Результаты сохраните в новый сериализованный файл
     */
    public static void main(String[] args) {
        List<Book> bookList = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("taski.ser"));
             ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("taski2.ser"))) {
            //listBooks.add((Book) inputStream.readObject());
            while (true) {
                try {
                    bookList.add((Book) inputStream.readObject()); //Deserealization
                } catch (EOFException e) {
                    // Обработка конца файла
                    break;
                }
            }

        bookList.stream()
                .filter(book -> book.getYear()>1922)
                .forEach(book -> {
                    try {
                        outputStream.writeObject(book);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
//        System.out.println(bookList);

    }
}

class Main2 {

    /**
     * Отфильтруйте книги с ценой выше определенной суммы. Сериализуйте отфильтрованный список книг в файл.
     */
    public static void main(String[] args) {
        List<Book> bookList = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("taski.ser"));
             ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("taski3.ser"))) {
            while (true) {
                try {
                    bookList.add((Book) inputStream.readObject()); //Deserealization
                } catch (EOFException | ClassNotFoundException e) {
                    // Обработка конца файла
                    break;
                }
            }
            bookList.stream()
                    .filter(book -> book.getPrice() > 100)
                    .forEach(book -> {
                        try {
                            outputStream.writeObject(book);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

class Main3 {

    /**
     * Агрегируйте данные, например, подсчитайте среднюю цену книг по каждому автору. Сериализуйте результаты в файл.
     */
    public static void main(String[] args) {
//        try (FileInputStream fis = new FileInputStream("data.txt");
//             Scanner scanner = new Scanner(fis);
//             ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("authorAveragePrice.ser"))) {
//
//            Map<String, Double> authorAveragePrice = scanner.useDelimiter("\\A").next().lines()
//                    .skip(1)
//                    .map(line -> line.split(", "))

        List<Book> bookList = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("taski.ser"));
             ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("taski4.ser"))) {
            while (true) {
                try {
                    bookList.add((Book) inputStream.readObject()); //Deserealization
                } catch (EOFException | ClassNotFoundException e) {
                    // Обработка конца файла
                    break;
                }
            }
            outputStream.writeObject(bookList.stream()
                    .collect(Collectors.groupingBy(Book::getAuthor, Collectors.averagingDouble(Book::getPrice))));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}