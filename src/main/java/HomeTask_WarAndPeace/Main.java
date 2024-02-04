package HomeTask_WarAndPeace;

import java.io.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        countWords("WarAndPeace");
    }

    public static void countWords(String readFrom) {
        try (BufferedReader bf = new BufferedReader(new FileReader(readFrom))) {
            StringBuilder contentBuilder = new StringBuilder();
            bf.lines().forEach(line -> contentBuilder.append(line).append("\n"));
            String text = contentBuilder.toString();
            Map<String, Long> words = new HashMap<>();
            Pattern pattern = Pattern.compile("\\b[а-яА-Я]+\\b");
            Matcher matcher = pattern.matcher(text);
            while (matcher.find()) {
                words.put(matcher.group(), 0L);
            }
            for (Map.Entry<String, Long> entry : words.entrySet()) {
                Pattern pattern1 = Pattern.compile("\\b" + entry.getKey() + "\\b");
                Matcher matcher1 = pattern1.matcher(text);
                long count = 0;
                while (matcher1.find()) {
                    count++;
                }
                words.put(entry.getKey(), count);
            }
            LinkedHashMap<String, Long> sortedWords = words.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getValue))
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                            (oldValue, newValue) -> oldValue, LinkedHashMap::new));
            System.out.println(sortedWords);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
