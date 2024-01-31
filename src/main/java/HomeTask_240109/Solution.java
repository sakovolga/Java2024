package HomeTask_240109;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {
    public static <T extends Participant> HashMap<Team<? extends Participant>, Integer> getAllTeamsMap(
            HashMap<Team<? extends Participant>, Integer> pupil,
            HashMap<Team<? extends Participant>, Integer> teenager,
            HashMap<Team<? extends Participant>, Integer> adult) {
        HashMap<Team<? extends Participant>, Integer> allTeamsMap = new HashMap<>(pupil);
        allTeamsMap.putAll(teenager);
        allTeamsMap.putAll(adult);
        System.out.println(allTeamsMap.size());
        return allTeamsMap;
    }

    //    1. Найти команду с максимальными баллами:
    public static <T extends Participant> Team<? extends Participant> findTeamWithMaxPoints(
            HashMap<Team<? extends Participant>, Integer> map) {
        return map.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .get()
                .getKey();
    }

    //    2. Подсчет общего количества баллов:
    public static int findSumOfPoints(HashMap<Team<? extends Participant>, Integer> map)
            throws MapOfTeamIsEmptyExeption {
        if (map.isEmpty()) {
            throw new MapOfTeamIsEmptyExeption("Нет ни одной команды!");
        }
        return map.values().stream()
                .reduce(Integer::sum)
                .get();
    }

    //    3. Список команд без баллов:
    public static List<Team<? extends Participant>> getTeamsWithoutPoints(
            HashMap<Team<? extends Participant>, Integer> map) {
        return map.entrySet().stream()
                .filter(e -> e.getValue() == 0)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    //    4. Средний возраст участников в каждой команде:
    public static void getAverageAgeOfEachTeam
    (HashMap<Team<? extends Participant>, Integer> map) {
        map.keySet()
                .forEach(team -> {
                    System.out.print(team);
                    double averageAge = team.getParticipantList().stream()
                            .mapToDouble(Participant::getAge)
                            .average()
                            .orElse(Double.NaN);
                    System.out.println(": " + averageAge);
                });
    }

    //    5. Команды с баллами выше среднего:
    public static List<Team<? extends Participant>> getTeamsWithPointMoreThenAverage
    (HashMap<Team<? extends Participant>, Integer> map) {
        double average = map.values().stream()
                .mapToDouble(Integer::doubleValue)
                .average()
                .orElse(Double.NaN);
        return map.entrySet().stream()
                .filter(entry -> entry.getValue() > average)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    //    6. Сортировка команд по баллам:
    public static <T extends Participant> HashMap<Team<? extends Participant>, Integer> sortHashMap
    (HashMap<Team<? extends Participant>, Integer> map) {
        return map.entrySet().stream()
                .sorted(new Comparator<Map.Entry<Team<? extends Participant>, Integer>>() {
                    @Override
                    public int compare(Map.Entry<Team<? extends Participant>, Integer> o1, Map.Entry<Team<? extends Participant>, Integer> o2) {
                        return o2.getValue().compareTo(o1.getValue());
                    }
                })
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new));
    }

    //    7. Команды с определенной категорией участников: Вывести команды, где все участники принадлежат к одной категории
//            (например, только Adult).
    public static <T extends Participant> List<Team<? extends Participant>> getTeamsOfCertainCategory
    (HashMap<Team<? extends Participant>, Integer> map, Class<T> participantClass) {
        return map.keySet().stream()
                .filter(team ->
                        team.getParticipantList().stream()
                                .allMatch(participantClass::isInstance))
                .toList();
    }

//    8. Команды с победами над определенной командой: Определить команды, которые выиграли у заданной команды.

    //    9. Самый молодой участник среди всех команд:
    public static Participant getYoungestParticipant(HashMap<Team<? extends Participant>, Integer> map) {
        return map.keySet().stream()
                .flatMap(team -> team.getParticipantList().stream())
                .min(Comparator.comparing(Participant::getAge))
                .orElse(null);
    }

    //    10. Самая опытная команда: Определить команду с наибольшим суммарным возрастом участников.
    public static Team<? extends Participant> findTheMostExperiencedTeam
    (HashMap<Team<? extends Participant>, Integer> map) throws ParticipantAgeIsNull {
        Team<? extends Participant> experiencedTteam = map.keySet().stream()
                .collect(Collectors.toMap(
                        team -> team,
                        team -> team.getParticipantList().stream()
                                .map(Participant::getAge)
                                .reduce(Integer::sum)
                                .orElse(0)))
                .entrySet()
                .stream().max(Comparator.comparing(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse(null);
        if (experiencedTteam == null) {
            throw new ParticipantAgeIsNull("Возраст участников не может быть равен 0!");
        } else return experiencedTteam;
    }

    //    11. Команды с участниками в определенном возрастном диапазоне:
    public static List<Team<? extends Participant>> getAdeRange
    (HashMap<Team<? extends Participant>, Integer> map, int minAge, int maxAge) {
        return map.keySet().stream()
                .filter(team -> team.getParticipantList().stream().
                        allMatch(participant ->
                                participant.getAge() > minAge && participant.getAge() < maxAge))
                .toList();
    }

    //    12. Имена участников по убыванию возраста:
    public static Map<String, Integer> getOrderByAge(HashMap<Team<? extends Participant>, Integer> map) {
        return map.keySet().stream()
                .flatMap(team -> team.getParticipantList().stream())
                .collect(Collectors.toMap(Participant::getName,
                        Participant::getAge))
                .entrySet().stream()
                .sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new));
    }

    //    13. Найти команду с наибольшим разбросом возрастов участников.
    public static String getGreatestAgeRange(HashMap<Team<? extends Participant>, Integer> map) {
        return Objects.requireNonNull(map.keySet().stream()
                        .collect(Collectors.toMap(Team::getTeamName,
                                team -> {
                                    int maxAge = team.getParticipantList().stream().
                                            map(Participant::getAge).max(Integer::compareTo).orElse(0);
                                    int minAge = team.getParticipantList().stream().
                                            map(Participant::getAge).min(Integer::compareTo).orElse(0);
                                    return maxAge - minAge;
                                }))
                        .entrySet().stream()
                        .max(Comparator.comparing(Map.Entry::getValue))
                        .orElse(null))
                .getKey();
    }

    //    14. Найти все пары команд, чьи участники имеют одинаковый суммарный возраст.
    public static void getTeamPairWithEqualsSummaryAge(HashMap<Team<? extends Participant>, Integer> map) {
        map.keySet().stream()
                .collect(Collectors.toMap(Team::getTeamName,
                        team -> team.getParticipantList().stream().
                                map(Participant::getAge)
                                .reduce(Integer::sum)
                                .orElse(0)
                ))
                .entrySet().stream()
                .collect(Collectors.groupingBy(Map.Entry::getValue))
                .entrySet().stream()
                .filter(e -> e.getValue().size() > 1)
                .forEach(System.out::println);
    }

    //    15. Вычислить средний балл для команд в каждой категории участников (Adult, Teenager, Pupil).
    public static void getAverageAgeForEachCategory(HashMap<Team<? extends Participant>, Integer> map) {
        map.entrySet().stream()
                .collect(Collectors.groupingBy(
                        entry -> {
                            Participant participant = entry.getKey().getParticipantList().stream().findFirst().orElse(null);
                            if (participant instanceof Pupil) {
                                return "Pupil";
                            }
                            if (participant instanceof TeenAger) {
                                return "TeenAger";
                            }
                            if (participant instanceof Adult) {
                                return "Adult";
                            }
                            return null;
                        },
                        Collectors.averagingDouble(Map.Entry::getValue)
                ))
                .forEach((category, averageScore) -> System.out.println(category + ": " + averageScore));
    }

//    16. Найти команды, чьи баллы улучшались с каждой игрой.

    //    17. Выявить команды, которые не имеют проигрышей.
//    18. Список команд, которые имели ничейные результаты с заданной командой.
//    19. Вывести результаты всех игр между двумя конкретными командами.
//    20. Сравнить две команды по средним баллам и среднему возрасту участников.
    public static void compareTwoTeams(HashMap<Team<? extends Participant>, Integer> map) {
        List<Map.Entry<Team<? extends Participant>, Integer>> listOfTwoTeams = map.entrySet().stream()
                .limit(2)
                .toList();
        Team<? extends Participant> team1 = listOfTwoTeams.get(0).getKey();
        Team<? extends Participant> team2 = listOfTwoTeams.get(1).getKey();
        double team1Average = (double) (listOfTwoTeams.get(0).getValue()) / 24;
        double team2Average = (double) (listOfTwoTeams.get(1).getValue()) / 24;
        System.out.println("Сравнение команд " + team1 + " и " + team2 + " по средним баллам и возрасту:");
        if (team1Average > team2Average) {
            System.out.println("У команды " + team1 + " средний балл выше, чем у команды "
                    + team2 + " (" + team1Average + " > " + team2Average + ")");
        } else if (team1Average < team2Average) {
            System.out.println("У команды " + team1 + " средний балл ниже, чем у команды "
                    + team2 + " (" + team1Average + " < " + team2Average + ")");
        } else {
            System.out.println("У команды " + team1 + " средний балл такой же, как у команды "
                    + team2 + " (" + team1Average + " = " + team2Average + ")");
        }
        if (getAverageAge(team1) > getAverageAge(team2)) {
            System.out.println("У команды " + team1 + " средний возраст выше, чем у команды "
                    + team2 + " (" + getAverageAge(team1) + " > " + getAverageAge(team2) + ")");
        } else if (getAverageAge(team1) < getAverageAge(team2)) {
            System.out.println("У команды " + team1 + " средний возраст меньше, чем у команды "
                    + team2 + " (" + getAverageAge(team1) + " < " + getAverageAge(team2) + ")");
        } else {
            System.out.println("У команды " + team1 + " средний возраст такой же, как у команды "
                    + team2 + " (" + getAverageAge(team1) + " = " + getAverageAge(team2) + ")");
        }
    }

    public static double getAverageAge(Team<? extends Participant> team) {
        return team.getParticipantList().stream()
                .mapToDouble(Participant::getAge)
                .average()
                .orElse(Double.NaN);
    }
//    Найти команды, в которых все участники имеют уникальные имена.
//    Определить команды с самой длинной последовательностью побед.
//    Найти команды с наибольшим количеством ничьих результатов.
//    Выявить команды, которые показали наибольшее улучшение баллов к концу сезона.
//    Создать комплексный отчет, включающий средний возраст команды, общее количество баллов, наибольшую победную серию,
//    и сравнение с другими командами.
}
