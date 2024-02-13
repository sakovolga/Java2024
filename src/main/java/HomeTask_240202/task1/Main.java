package HomeTask_240202.task1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println(findUniqueLastName());

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

    //  1. Метод для создания мапы, где ключ - первая буква имени, а значение - количество таких имен

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

    // 2. Метод для нахождения самой часто встречающейся первой буквы в именах
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

    // 3. Метод для создания списка номеров телефонов, где каждый номер преобразован в числовой формат
    public static List<Long> getPhoneNumberList() {
        Map<String, String> map = getMap();
        return map.keySet().stream()
                .map(phone -> {
                    Pattern pattern = Pattern.compile("[^0-9]");
                    Matcher matcher = pattern.matcher(phone);
                    return matcher.replaceAll("");
                })
                .map(Long::parseLong)
                .toList();
    }

    // 4. Метод для группировки имен по длине имени
    public static Map<Integer, List<String>> groupByNameLength() {
        Map<String, String> map = getMap();
        return map.values().stream()
                .map(str -> {
                    Pattern pattern = Pattern.compile("Mr\\. |Mrs\\. ");
                    Matcher matcher = pattern.matcher(str);
                    String withoutMr = matcher.replaceAll("");
                    pattern = Pattern.compile("^\\w+");
                    matcher = pattern.matcher(withoutMr);
                    if (matcher.find()) {
                        return matcher.group();
                    }
                    return "";
                })

                .collect(Collectors.groupingBy(String::length));
    }

    // 5. Метод для поиска уникальных фамилий
    public static List<String> findUniqueLastName() {
        Map<String, String> map = getMap();
        return map.values().stream()
                .map(str -> {
                    Pattern pattern = Pattern.compile(" MD| DDS");
                    Matcher matcher = pattern.matcher(str);
                    String withoutMr = matcher.replaceAll("");
                    pattern = Pattern.compile("\\w+\\b$");
                    matcher = pattern.matcher(withoutMr);
                    if (matcher.find()) {
                        return matcher.group();
                    }
                    return "";
                })
                .distinct()
                .toList();
    }
// 6. Метод для создания списка имен, отсортированного в обратном алфавитном порядке
// 7. Метод для преобразования данных в формат имя=номер
// 8. Метод для расчета средней длины имени
// 9. Метод для создания карты, где ключ - номер телефона, а значение - имя
// 10. Метод для поиска контактов с максимальной и минимальной длиной имени
}
