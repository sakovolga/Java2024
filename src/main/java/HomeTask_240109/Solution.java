package HomeTask_240109;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {
    public static <T extends Participant> HashMap<Team<? extends Participant>, Integer> getAllTeamsMap(
            HashMap<Team<Pupil>, Integer> pupil,
            HashMap<Team<TeenAger>, Integer> teenager,
            HashMap<Team<Adult>, Integer> adult) {
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
        if (map.size() == 0) {
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
        map.keySet().stream()
                .forEach(team -> {
                    System.out.println(team);
                    double averageAge = team.getParticipantList().stream()
                            .mapToDouble(Participant::getAge)
                            .average()
                            .orElse(Double.NaN);
                    System.out.println("Средний возраст: " + averageAge);
                    System.out.println();
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
    public static Participant getYoungestParticipant(HashMap<Team<? extends Participant>, Integer> map){
        return map.keySet().stream()
                .flatMap(team -> team.getParticipantList().stream())
                .min(Comparator.comparing(Participant::getAge))
                .orElse(null);

    }
//    10. Самая опытная команда: Определить команду с наибольшим суммарным возрастом участников.
    public static Team<? extends Participant> findTheMostExperiencedTeam
    (HashMap<Team<? extends Participant>, Integer> map) throws ParticipantAgeIsNull {
        Team<? extends Participant> experiencedTteam =  map.keySet().stream()
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
        if (experiencedTteam == null){
            throw new ParticipantAgeIsNull("Возраст участников не может быть равен 0!");
        } else return experiencedTteam;
    }
//    Команды с участниками в определенном возрастном диапазоне:
//    Имена участников по убыванию возраста:
//
//    Найти команду с наибольшим разбросом возрастов участников.
//    Найти все пары команд, чьи участники имеют одинаковый суммарный возраст.
//    Вычислить средний балл для команд в каждой категории участников (Adult, Teenager, Pupil).
//    Найти команды, чьи баллы улучшались с каждой игрой.
//
//    Выявить команды, которые не имеют проигрышей.
//    Список команд, которые имели ничейные результаты с заданной командой.
//    Вывести результаты всех игр между двумя конкретными командами.
//    Сравнить две команды по средним баллам и среднему возрасту участников.
//
//    Найти команды, в которых все участники имеют уникальные имена.
//    Определить команды с самой длинной последовательностью побед.
//    Найти команды с наибольшим количеством ничьих результатов.
//    Выявить команды, которые показали наибольшее улучшение баллов к концу сезона.
//    Создать комплексный отчет, включающий средний возраст команды, общее количество баллов, наибольшую победную серию,
//    и сравнение с другими командами.
}
