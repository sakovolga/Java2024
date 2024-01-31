package HomeTask_240130;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileOperationExamples {

    public static void main(String[] args) throws IOException {
//        copyFileUsingFileStreams();
        copyFileUsingPath();
//        copyFileUsingRandomAccessFile();
    }

    // Задание 1: Работа с классом File

    /**
     * Написать метод, который создает файл с именем
     * "example.txt" в текущей директории, затем считывает
     * этот файл с использованием FileInputStream и записывает
     * его содержимое в новый файл "copy_example.txt" с использованием FileOutputStream.
     */
    public static void copyFileUsingFileStreams() throws IOException {
        File file = new File("example.txt");
        file.createNewFile();
        if (file.exists()) {
            try (FileInputStream inputStream = new FileInputStream("example.txt");
                 FileOutputStream outputStream = new FileOutputStream("copy_example.txt")) {
                outputStream.write(inputStream.read());
            }
        }


    }

    // Задание 2: Работа с классом Path

    /**
     * Написать метод, который использует Path и Files
     * для копирования файла "example.txt" в новый файл "copy_example_path.txt".
     * Проверить, существует ли файл "example.txt" перед копированием.
     */


    public static void copyFileUsingPath() throws IOException {

        File file = new File("example.txt");
        Path path = file.toPath();
        if (file.exists()) {
            try {
                Files.copy(path, Path.of("copy_example_path.txt"));
            } catch (IOException e){
                System.err.println("Ошибка при копировании файла: " + e.getMessage());
        }
    }
}

// Задание 3: Работа с классом RandomAccessFile

/**
 * Написать метод, который открывает файл "example.txt"
 * с использованием RandomAccessFile, читает первые 20 байт и записывает
 * их в новый файл "random_access_copy.txt".
 */


public static void copyFileUsingRandomAccessFile() throws IOException {
    try (RandomAccessFile accessFile = new RandomAccessFile("example.txt", "rw");
         BufferedWriter writer = new BufferedWriter(new FileWriter("random_access_copy.txt"))) {

        byte[] buffer = new byte[20];
        accessFile.read(buffer, 0, 20);
        writer.write(new String(buffer));
    }
}
}
