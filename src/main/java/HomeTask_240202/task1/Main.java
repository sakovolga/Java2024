package HomeTask_240202.task1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

    }

    //    Написать и протестировать методы которые парсят файл 1.txt и...
    public static Map<String, String> getMap() {
        try (BufferedReader bf = new BufferedReader(new FileReader("1.txt"))) {
            return bf.lines()
                    .collect(Collectors.toMap(e -> e.split(" - ")[0], el -> el.split(" - ")[1]));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //            - Метод для создания мапы, где ключ - первая буква имени, а значение - количество таких имен
//    public static Map<Character, IllegalAccessError> mapOfMembers() {
//        Map<String, String> map = getMap();
//        Pattern pattern = Pattern.compile("\\A(\\w)");
//
//        try  {
//            map.values().stream()
//                    .collect(Collectors.groupingBy(text -> {
//                        Matcher matcher = pattern.matcher(text);
//                        return matcher.group();},
//                            ));
//
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
// - Метод для нахождения самой часто встречающейся первой буквы в именах
// - Метод для создания списка номеров телефонов, где каждый номер преобразован в числовой формат
// - Метод для группировки имен по длине имени
// - Метод для поиска уникальных фамилий
// - Метод для создания списка имен, отсортированного в обратном алфавитном порядке
// - Метод для преобразования данных в формат имя=номер
// - Метод для расчета средней длины имени
// - Метод для создания карты, где ключ - номер телефона, а значение - имя
// - Метод для поиска контактов с максимальной и минимальной длиной имени
}
