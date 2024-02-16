package HomeTask_240109;

import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {
    public static HashMap<Team<Participant>, Integer> getAllTeamsMap(
            HashMap<Team<Participant>, Integer> pupil,
            HashMap<Team<Participant>, Integer> teenager,
            HashMap<Team<Participant>, Integer> adult) {
        HashMap<Team<Participant>, Integer> allTeamsMap = new HashMap<>(pupil);
        allTeamsMap.putAll(teenager);
        allTeamsMap.putAll(adult);
        System.out.println(allTeamsMap.size());
        return allTeamsMap;
    }

    //    1. Найти команду с максимальными баллами:
    public static <T extends Participant> Team<Participant> findTeamWithMaxPoints(
            HashMap<Team<Participant>, Integer> map) {
        return map.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .get()
                .getKey();
    }

    //    2. Подсчет общего количества баллов:
    public static int findSumOfPoints(HashMap<Team<Participant>, Integer> map)
            throws MapOfTeamIsEmptyExeption {
        if (map.isEmpty()) {
            throw new MapOfTeamIsEmptyExeption("Нет ни одной команды!");
        }
        return map.values().stream()
                .reduce(Integer::sum)
                .get();
    }

    //    3. Список команд без баллов:
    public static List<Team<Participant>> getTeamsWithoutPoints(
            HashMap<Team<Participant>, Integer> map) {
        return map.entrySet().stream()
                .filter(e -> e.getValue() == 0)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    //    4. Средний возраст участников в каждой команде:
    public static void getAverageAgeOfEachTeam(HashMap<Team<Participant>, Integer> map) {
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
    public static List<Team<Participant>> getTeamsWithPointMoreThenAverage(HashMap<Team<Participant>, Integer> map) {
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
    public static <T extends Participant> HashMap<Team<Participant>, Integer> sortHashMap
    (HashMap<Team<Participant>, Integer> map) {
        return map.entrySet().stream()
                .sorted(new Comparator<Map.Entry<Team<Participant>, Integer>>() {
                    @Override
                    public int compare(Map.Entry<Team<Participant>, Integer> o1, Map.Entry<Team<Participant>, Integer> o2) {
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
    public static <T extends Participant> List<Team<Participant>> getTeamsOfCertainCategory
    (HashMap<Team<Participant>, Integer> map, Class<T> participantClass) {
        return map.keySet().stream()
                .filter(team ->
                        team.getParticipantList().stream()
                                .allMatch(participantClass::isInstance))
                .toList();
    }

    //    8. Команды с победами над определенной командой: Определить команды, которые выиграли у заданной команды.
    public static void getWinnersOfCertainTeam(HashMap<Team<Participant>, Integer> map) {
        // Выбираем произвольную команду
        System.out.println(8);
        Team<Participant> team = map.entrySet().stream()
                .findFirst()
                .map(Map.Entry::getKey)
                .orElse(null);
        System.out.println("Команды, победившие у команды " + team.getTeamName() + ": ");
        try {
            team.getGameList().stream()
                    .filter(game -> game.getMyPoints() == 0)
                    .map(Game::getRival)
                    .forEach(System.out::println);
        } catch (NullPointerException e) {
            System.out.println("Команда не сыграла ни одной игры");
        }
    }

    //    9. Самый молодой участник среди всех команд:
    public static Participant getYoungestParticipant(HashMap<Team<Participant>, Integer> map) {
        return map.keySet().stream()
                .flatMap(team -> team.getParticipantList().stream())
                .min(Comparator.comparing(Participant::getAge))
                .orElse(null);
    }

    //    10. Самая опытная команда: Определить команду с наибольшим суммарным возрастом участников.
    public static Team<Participant> findTheMostExperiencedTeam
    (HashMap<Team<Participant>, Integer> map) throws ParticipantAgeIsNull {
        Team<Participant> experiencedTteam = map.keySet().stream()
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
    public static List<Team<Participant>> getAdeRange(HashMap<Team<Participant>, Integer> map, int minAge, int maxAge) {
        return map.keySet().stream()
                .filter(team -> team.getParticipantList().stream().
                        allMatch(participant ->
                                participant.getAge() > minAge && participant.getAge() < maxAge))
                .toList();
    }

    //    12. Имена участников по убыванию возраста:
    public static Map<String, Integer> getOrderByAge(HashMap<Team<Participant>, Integer> map) {
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
    public static String getGreatestAgeRange(HashMap<Team<Participant>, Integer> map) {
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
    public static void getTeamPairWithEqualsSummaryAge(HashMap<Team<Participant>, Integer> map) {
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
    public static void getAverageAgeForEachCategory(HashMap<Team<Participant>, Integer> map) {
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
    public static List<Team<Participant>> getTeamsWithIncreasedPoints(HashMap<Team<Participant>, Integer> map) {
        System.out.println(16);
        return map.keySet().stream()
                .filter(team -> team.getGameList().stream().allMatch(e -> e.getMyPoints() != 0))
                .toList();
    }

    //    17. Выявить команды, которые не имеют проигрышей. - это то же самое, что предыдущий метод
    //    18. Список команд, которые имели ничейные результаты с заданной командой.
    public static void getTeamsWithDraw(HashMap<Team<Participant>, Integer> map) {
        System.out.println(18);
        // Выбираем произвольную команду
        Team<Participant> team = map.entrySet().stream()
                .findFirst()
                .map(Map.Entry::getKey)
                .orElse(null);
        System.out.println("Команды, которые имели ничейные результаты с командой " + team + ": ");
        try {
            // Ищем команды с ничейными результатами
            team.getGameList().stream()
                    .filter(game -> game.getMyPoints() == 5)
                    .map(Game::getRival)
                    .forEach(System.out::println);
        } catch (NullPointerException e) {
            System.out.println("Команда ни разу не играла");
        }

    }

    //    19. Вывести результаты всех игр между двумя конкретными командами.
    public static void showResultsOfTwoTeams(List<Team<Participant>> list) {
        System.out.println(19);
        Team<Participant> team1 = list.get(0);
        Team<Participant> team2 = list.get(1);
        team1.getGameList().stream()
                .filter(games -> games.getRival() == team2)
                .forEach(entry -> System.out.println("Команда " + team1.getTeamName() +
                        " играла с командой " + team2.getTeamName() + " с результатом " + entry.getMyPoints()
                        + ":" + entry.getRivalPoints()));
    }

    //    20. Сравнить две команды по средним баллам и среднему возрасту участников.
    public static void compareTwoTeams(HashMap<Team<Participant>, Integer> map) {
        System.out.println(20);
        List<Map.Entry<Team<Participant>, Integer>> listOfTwoTeams = map.entrySet().stream()
                .limit(2)
                .toList();
        Team<Participant> team1 = listOfTwoTeams.get(0).getKey();
        Team<Participant> team2 = listOfTwoTeams.get(1).getKey();
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

    public static double getAverageAge(Team<Participant> team) {
        return team.getParticipantList().stream()
                .mapToDouble(Participant::getAge)
                .average()
                .orElse(Double.NaN);
    }

    //    21. Найти команды, в которых все участники имеют уникальные имена.
    public static void getTeamsWithUniqueParticipantName(HashMap<Team<Participant>, Integer> map) {
        System.out.println(21);
        System.out.println("Команды, в которых все участники имеют уникальные имена:");
        map.keySet().stream()
                .filter(team -> {
                    List<String> nameList = team.getParticipantList().stream()
                            .map(participant -> getFirstName(participant.getName()))
                            .distinct()
                            .toList();
                    return nameList.size() == 4;
                })
                .forEach(team -> System.out.println(team.getTeamName()));
    }

    public static String getFirstName(String fullName) {
        Pattern pattern = Pattern.compile("Dr\\. |Mr\\. |Mrs\\. |Ms\\. ");
        Matcher matcher = pattern.matcher(fullName);
        String stringWithoutPrefix = matcher.replaceAll("");
        return stringWithoutPrefix.split(" ")[0];
    }

    //    22. Определить команды с самой длинной последовательностью побед.
    public static void getTeamsWithLongestWinningStreak(HashMap<Team<Participant>, Integer> map) {
        System.out.println(22);
        System.out.println("Kоманды с самой длинной последовательностью побед:");
        map.keySet().stream()
                .collect(Collectors.groupingBy(Solution::getLongestWinningStreak))
                .entrySet().stream()
                .filter(entry -> {
                    int max = map.keySet().stream().
                            map(Solution::getLongestWinningStreak).max(Integer::compareTo).get();
                    return entry.getKey() == max;
                })
                .forEach(System.out::println);
    }
    public static int getLongestWinningStreak(Team<Participant> team) {
        List<Game> gameList = team.getGameList();
        int longestWinningStreak = 0;
        int winningStreak = 0;
        boolean win = false;
        for (int i = 0; i < gameList.size(); i++) {
            if (gameList.get(i).getMyPoints() == 10) {
                winningStreak++;
                win = true;
                if (i == gameList.size() - 1 && winningStreak > longestWinningStreak) {
                    longestWinningStreak = winningStreak;
                }
            } else {
                win = false;
                if (winningStreak > longestWinningStreak) {
                    longestWinningStreak = winningStreak;
                }
                winningStreak = 0;
            }
        }
        return longestWinningStreak;
    }
//    23. Найти команды с наибольшим количеством ничьих результатов.
public static void getTeamsWithBiggestNumberOfDraw(HashMap<Team<Participant>, Integer> map) {
    System.out.println(23);
    System.out.println("Kоманды с наибольшим количеством ничьих результатов:");
    map.keySet().stream()
            .collect(Collectors.groupingBy(Solution::getNumberOfDraw))
            .entrySet().stream()
            .filter(entry -> {
                int max = map.keySet().stream().
                        map(Solution::getNumberOfDraw).max(Integer::compareTo).get();
                return entry.getKey() == max;
            })
            .forEach(System.out::println);
}
    public static int getNumberOfDraw(Team<Participant> team){
        return (int)team.getGameList().stream()
                .filter(game -> game.getMyPoints() == 5)
                .count();

    }
//    24. Выявить команды, которые показали наибольшее улучшение баллов к концу сезона.
//    25. Создать комплексный отчет, включающий средний возраст команды, общее количество баллов, наибольшую победную серию,
//    и сравнение с другими командами.
    public static void complexReport(HashMap<Team<Participant>, Integer> map){
        Team<Participant> team = map.keySet().stream().findFirst().orElse(null);
        System.out.println("Комплексный отчет команды " + team.getTeamName() + ":");
        double averageAge = team.getParticipantList().stream()
                .mapToDouble(Participant::getAge)
                .average()
                .orElse(Double.NaN);
        System.out.println("Средний возраст команды: " + averageAge);
        int points = map.get(team);
        System.out.println("Общее количество баллов: " + points);
        System.out.println("Наибольшая победная серия: " + getLongestWinningStreak(team));
        System.out.println("Команда заняла " + getTeamPlace(map, team) + " место в своей группе" );
    }
    public static int getTeamPlace(HashMap<Team<Participant>, Integer> map, Team<Participant> team){
        Class<? extends Participant> participantClass = team.getParticipantList().get(0).getClass();
        return (int) map.entrySet().stream()
                .filter(entry -> entry.getKey().getParticipantList().get(0).getClass() == participantClass)
                .map(Map.Entry::getValue)
                .filter(points -> points >= map.get(team))
                .count();
    }
}
