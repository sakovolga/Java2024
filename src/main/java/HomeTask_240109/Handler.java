package HomeTask_240109;

import java.util.*;
import java.util.stream.Collectors;

public class Handler {

    public static HashMap<Team<? extends Participant>, Integer> play(List<Team<? extends Participant>> list) {
        HashMap<Team<? extends Participant>, Integer> playersMap = playEachOther(list);
        while (!isUnique(playersMap)) {
            HashMap<Team<? extends Participant>, Integer> doublePlayersMap = new HashMap<>(getOneDoubleTeam(playersMap));
            playersMap = playAnotherOneTour(playersMap, doublePlayersMap);
        }
        return sortHashMap1(playersMap);
    }

    public static <T extends Participant> void printTableOfGames(HashMap<Team<? extends Participant>, Integer> map) {
        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry);
        }
    }

    public static HashMap<Team<? extends Participant>, Integer> getOneDoubleTeam
            (Map<Team<? extends Participant>, Integer> map) {
        Map<Team<? extends Participant>, Integer> map1 = map.entrySet().stream()
                .collect(Collectors.groupingBy(Map.Entry::getValue))
                .entrySet()
                .stream().filter(value -> value.getValue().size() > 1)
                .limit(1)
                .flatMap(entry -> entry.getValue().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return new HashMap<>(map1);
    }

    public static <T extends Participant> boolean isUnique(HashMap<Team<? extends Participant>, Integer> map) {
        int count = (int) map.values().stream()
                .distinct()
                .count();
        return count == map.size();
    }

    public static <T extends Participant> HashMap<Team<? extends Participant>, Integer> playAnotherOneTour
            (HashMap<Team<? extends Participant>, Integer> map, HashMap<Team<? extends Participant>, Integer> doublePlayersMap) {
        List<Team<? extends Participant>> list = doublePlayersMap.keySet().stream().toList();
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                int point = list.get(i).rePlay(list.get(j));
                if (point == 0.01) {
                    int temp = map.get(list.get(i));
                    temp = temp + 1;
                    map.put(list.get(i), temp);
                } else {
                    int temp = map.get(list.get(j));
                    temp = temp + 1;
                    map.put(list.get(j), temp);
                }
            }
        }
        return map;
    }

    public static HashMap<Team<? extends Participant>, Integer> playEachOther
            (List<Team<? extends Participant>> list) {
        HashMap<Team<? extends Participant>, Integer> points = new HashMap<>();
        for (Team<? extends Participant> team : list) {
            points.put(team, 0);
        }
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                int point = list.get(i).play(list.get(j));
                if (point == 10) {
                    int temp = points.get(list.get(i));
                    temp = temp + 10;
                    points.put(list.get(i), temp);
                    list.get(i).setGameList(list.get(j), 10);
                    list.get(j).setGameList(list.get(i), 0);
                } else if (point == 0) {
                    int temp = points.get(list.get(j));
                    temp = temp + 10;
                    points.put(list.get(j), temp);
                    list.get(j).setGameList(list.get(i), 10);
                    list.get(i).setGameList(list.get(j), 0);
                } else if (point == 5) {
                    int temp = points.get(list.get(j));
                    temp = temp + 5;
                    points.put(list.get(j), temp);
                    int temp1 = points.get(list.get(i));
                    temp1 = temp1 + 5;
                    points.put(list.get(i), temp1);
                    list.get(j).setGameList(list.get(i), 5);
                    list.get(i).setGameList(list.get(j), 5);
                }
            }
        }
        return sortHashMap1(points);
    }

    public static HashMap<Team<? extends Participant>, Integer> sortHashMap1
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

    public static void printParticipants(HashMap<Team<? extends Participant>, Integer> map){
        map.keySet().stream()
                .flatMap(team -> team.getParticipantList().stream())
                .forEach(participant -> System.out.println(participant));
    }

}
