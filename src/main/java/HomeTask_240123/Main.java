package HomeTask_240123;

import HomeTask_240109.MapOfTeamIsEmptyExeption;

import java.io.*;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        filterAndWrite("f.txt", "2.txt");
        groupToFile("f.txt", "3.txt");
        sort("f.txt", "4.txt");
        findMinRang("f.txt", "5.txt");
        getStatistic("f.txt", "6.txt");
        addPrefix("f.txt", "7.txt");
        filterAndAddPostfix("f.txt", "8.txt");
        refactor("f.txt", "9.txt");
        join("f.txt", "10.txt");
    }

    //    2. Отфильтруйте стримы, имеющие рейтинг выше 4.5, и запишите их в новый файл.
    public static void filterAndWrite(String readFrom, String writeTo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(readFrom));
             FileWriter writer = new FileWriter(writeTo)) {
            reader.lines()
                    .filter(s ->
                            Double.parseDouble(s.split(", ")[1]) > 4.5)
                    .forEach(s -> {
                        try {
                            writer.write(s + "\n");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //    3.  Сгруппируйте стримы по названию и запишите количество стримов в каждой группе в новый файл.
    public static void groupToFile(String readFrom, String writeTo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(readFrom));
             FileWriter writer = new FileWriter(writeTo)) {
            reader.lines()
                    .collect(Collectors.groupingBy(s -> s.split(": ")[1].split(", ")[0]))
                    .forEach((key, value) -> {
                        try {
                            writer.write(key + ":" + value.size() + "\n");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //    4. Отсортируйте стримы по рейтингу от наибольшего к наименьшему и запишите результаты в новый файл.
    public static void sort(String readFrom, String writeTo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(readFrom));
             FileWriter writer = new FileWriter(writeTo)) {
            reader.lines()
                    .sorted(new Comparator<String>() {
                        @Override
                        public int compare(String o1, String o2) {
                            return o2.split(", ")[1].compareTo(o1.split(", ")[1]);
                        }
                    })
                    .forEach(e -> {
                        try {
                            writer.write(e + "\n");
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //    5. Найдите стримы с минимальным рейтингом и выведите их в новый файл.
    public static void findMinRang(String readFrom, String writeTo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(readFrom));
             FileWriter writer = new FileWriter(writeTo)) {
            List<String> lines = reader.lines().
                    toList();
            double min = lines.stream()
                    .mapToDouble(e -> Double.parseDouble(e.split(", ")[1]))
                    .min().orElse(0.0);
            lines.stream()
                    .filter(e -> Double.parseDouble(e.split(", ")[1]) == min)
                    .forEach(e -> {
                        try {
                            writer.write(e + "\n");
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //    6. Соберите статистику по рейтингам (минимальный, максимальный, средний) и запишите ее в новый файл.
    public static void getStatistic(String readFrom, String writeTo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(readFrom));
             FileWriter writer = new FileWriter(writeTo)) {
            DoubleSummaryStatistics stats = reader.lines()
                    .map(e -> e.split(", ")[1])
                    .collect(Collectors.summarizingDouble(Double::parseDouble));
            writer.write(stats.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //    7. Измените названия всех стримов, добавив к ним префикс "Stream-"
//    и запишите обновленные названия в новый файл.
    public static void addPrefix(String readFrom, String writeTo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(readFrom));
             FileWriter writer = new FileWriter(writeTo)) {
            reader.lines()
                    .map(e -> e.split("\\[")[0] + "[Stream-" + e.split("\\[")[1])
                    .forEach(e -> {
                        try {
                            writer.write(e + "\n");
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //    8. Отфильтруйте стримы с рейтингом ниже 4.7 и преобразуйте их названия,
//    добавив в конец "- Low Rated". Запишите результаты в новый файл.
    public static void filterAndAddPostfix(String readFrom, String writeTo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(readFrom));
             FileWriter writer = new FileWriter(writeTo)) {
            reader.lines()
                    .filter(e -> (Double.parseDouble(e.split(", ")[1])) < 4.7)
                    .map(e -> e.split("]")[0] + "- Low Rated]" + e.split("]")[1])
                    .forEach(e -> {
                        try {
                            writer.write(e + "\n");
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //    9. Создайте сводку, включающую идентификатор стрима, название и рейтинг,
//    и запишите ее в новый файл в формате "ID: Название - Рейтинг".
    public static void refactor(String readFrom, String writeTo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(readFrom));
             FileWriter writer = new FileWriter(writeTo)) {
            reader.lines()
                    .map(e -> e.split("\\[")[0] + e.split("\\[")[1].split("],")[0] + " -" + e.split("\\[")[1].split("],")[1])
                    .forEach(e -> {
                        try {
                            writer.write(e + "\n");
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //    10. Соберите все названия стримов в одну длинную строку, разделяя их запятыми,
//    и запишите эту строку в новый файл.
    public static void join(String readFrom, String writeTo) {
        try (BufferedReader reader = new BufferedReader(new FileReader(readFrom));
             FileWriter writer = new FileWriter(writeTo)) {
            writer.write(reader.lines()
                    .collect(Collectors.joining(", ")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
