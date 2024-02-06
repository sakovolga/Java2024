package HomeTask_240202.task1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println(mapOfMembers());
        System.out.println(getTheMostPopularFirstLitter());
    }

    //    Написать и протестировать методы которые парсят файл 1.txt и...
    public static Map<String, String> getMap() {
        try (BufferedReader bf = new BufferedReader(new FileReader("1.txt"))) {
            return bf.lines()
                    .collect(Collectors.toMap(e -> e.split(" - ")[0], el -> el.split(" - ")[1]));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //            - Метод для создания мапы, где ключ - первая буква имени, а значение - количество таких имен
//    public static Map<Character, IllegalAccessError> mapOfMembers() {
//        Map<String, String> map = getMap();
//
//
//        try  {
//            map.values().stream()
//                    .collect(Collectors.groupingBy(text -> {
//                        Pattern pattern = Pattern.compile("\\A(\\w)");
//                        Matcher matcher = pattern.matcher(text);
//                        String str =  matcher.group();
//                        return str;},
//                            map.values().stream().map(line -> {
//                                Pattern pattern1 = Pattern.compile("\\b" + s)
//                            })
//
//                            ));
//
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public static Map<Character, Integer> mapOfMembers() {
        Map<Character, Integer> resultMap = new HashMap<>();
        Map<String, String> map = getMap();

        resultMap = map.values().stream()
                .map(String::trim)
                .map(text -> {
                    Pattern pattern = Pattern.compile("\\b(\\w)");
                    Matcher matcher = pattern.matcher(text);
                    if (matcher.find()) {
                        return matcher.group().charAt(0);
                    } else {
                        return null;
                    }
                })
                .collect(Collectors.groupingBy(ch -> ch, Collectors.summingInt(ch -> 1)));

        return resultMap;
    }

    // - Метод для нахождения самой часто встречающейся первой буквы в именах
    public static Character getTheMostPopularFirstLitter() {
        Map<String, String> map = getMap();

        return map.values().stream()
                .map(String::trim)
                .map(text -> {
                    Pattern pattern = Pattern.compile("\\b(\\w)");
                    Matcher matcher = pattern.matcher(text);
                    if (matcher.find()) {
                        return matcher.group().charAt(0);
                    } else {
                        return null;
                    }
                })
                .collect(Collectors.groupingBy(ch -> ch, Collectors.summingInt(ch -> 1)))
                .entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse(null);
    }
// - Метод для создания списка номеров телефонов, где каждый номер преобразован в числовой формат
// - Метод для группировки имен по длине имени
// - Метод для поиска уникальных фамилий
// - Метод для создания списка имен, отсортированного в обратном алфавитном порядке
// - Метод для преобразования данных в формат имя=номер
// - Метод для расчета средней длины имени
// - Метод для создания карты, где ключ - номер телефона, а значение - имя
// - Метод для поиска контактов с максимальной и минимальной длиной имени
}
