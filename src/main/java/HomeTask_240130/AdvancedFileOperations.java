package HomeTask_240130;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdvancedFileOperations {

    public static void main(String[] args) throws IOException {
        countWordsInFile();
        findTxtFilesInDirectory();
        copyLastParagraphUsingRandomAccess();
    }

    // Усложненное Задание 1: Работа с классом File

    /**
     * Написать метод, который читает файл "example.txt",
     * подсчитывает количество слов в файле и записывает это количество
     * в новый файл "word_count.txt". Использовать BufferedReader и BufferedWriter
     * для эффективной работы с текстом.
     */
    public static void countWordsInFile() throws IOException {
        try(BufferedReader bf = new BufferedReader(new FileReader("prog.txt"));
            BufferedWriter bw = new BufferedWriter(new FileWriter("word_count.txt"))){
            long wordCount = bf.lines()
                    .flatMap(line -> Arrays.stream(line.split(" ")))
                    .count();
            bw.write(String.valueOf(wordCount));
        } catch (IOException e){
            System.err.println("Ошибка ввода-вывода");
        }

    }

    // Усложненное Задание 2: Работа с классом Path

    /**
     * Написать метод, который ищет все файлы с расширением ".txt"
     * в текущей директории и её поддиректориях. Результаты поиска
     * (пути к файлам) записать в файл "found_files.txt".
     */
    public static void findTxtFilesInDirectory() throws IOException {
        Path path = Path.of("/home/olga");
        try (BufferedWriter bf = new BufferedWriter(new FileWriter("found_files.txt"))){
            Files.find(path, 5, ((paths, basicFileAttributes) ->
                    paths.toString().endsWith(".txt") && basicFileAttributes.isRegularFile()))
                    .forEach(p -> {
                        try {
                            bf.write(p.toString() + "\n");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        }
    }

    // Усложненное Задание 3: Работа с классом RandomAccessFile

    /**
     * Написать метод, который открывает файл "example.txt"
     * с использованием RandomAccessFile, переходит к последнему абзацу
     * в файле и копирует его содержимое в новый файл "last_paragraph.txt".
     */
    public static void copyLastParagraphUsingRandomAccess() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("last_paragraph.txt"))){
            RandomAccessFile raf = new RandomAccessFile("prog.txt", "rw");
            raf.seek(0);
            List<Long> list = new ArrayList<>();
            int readByte;
            while ((readByte = raf.read()) != -1) {
                if (readByte == 10){
                    list.add(raf.getFilePointer());
                }
            }
            raf.seek(list.get(list.size()-1));
            bw.write(String.valueOf(raf.readLine()));
            raf.close();
        } catch (IOException e){
            System.out.println("Ошибка ввода-вывода");
        }
    }
}
