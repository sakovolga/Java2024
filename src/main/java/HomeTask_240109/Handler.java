package HomeTask_240109;

import java.util.*;
import java.util.stream.Collectors;

public class Handler {

    public static HashMap<Team<Participant>, Integer> play(List<Team<Participant>> list) {
        HashMap<Team<Participant>, Integer> playersMap = playEachOther(list);
        while (!isUnique(playersMap)) {
            HashMap<Team<Participant>, Integer> doublePlayersMap = new HashMap<>(getOneDoubleTeam(playersMap));
            playersMap = playAnotherOneTour(playersMap, doublePlayersMap);
        }
        return sortHashMap1(playersMap);
    }

    public static void printTableOfGames(HashMap<Team<Participant>, Integer> map) {
        map.entrySet().forEach(System.out::println);
    }

    public static HashMap<Team<Participant>, Integer> getOneDoubleTeam
            (Map<Team<Participant>, Integer> map) {
        Map<Team<Participant>, Integer> map1 = map.entrySet().stream()
                .collect(Collectors.groupingBy(Map.Entry::getValue))
                .entrySet()
                .stream().filter(value -> value.getValue().size() > 1)
                .limit(1)
                .flatMap(entry -> entry.getValue().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return new HashMap<>(map1);
    }

    public static boolean isUnique(HashMap<Team<Participant>, Integer> map) {
        return map.values().stream().distinct().count() == map.size();
    }

    public static HashMap<Team<Participant>, Integer> playAnotherOneTour
            (HashMap<Team<Participant>, Integer> map, HashMap<Team<Participant>, Integer> doublePlayersMap) {
        List<Team<Participant>> list = doublePlayersMap.keySet().stream().toList();
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                Team<Participant> player1 = list.get(i);
                Team<Participant> player2 = list.get(j);
                int point = player1.rePlay(player2);
                if (point == 1) {
                    map.put(player1, map.get(player1) + 1);
                    logWinReplayGame(player1, player2);
                } else {
                    map.put(player2, map.get(player2) + 1);
                    logWinReplayGame(player2, player1);
                }
            }
        }
        return map;
    }

    public static HashMap<Team<Participant>, Integer> playEachOther(List<Team<Participant>> list) {
        HashMap<Team<Participant>, Integer> points = new HashMap<>();
        for (Team<Participant> team : list) {
            points.put(team, 0);
        }
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                Team<Participant> player1 = list.get(i);
                Team<Participant> player2 = list.get(j);
                int point = player1.play(player2);
                if (point == 10) {
                    points.put(player1, points.get(player1) + 10);
                    logWinBasicGame(player1, player2);
                } else if (point == 0) {
                    points.put(player2, points.get(player2) + 10);
                    logWinBasicGame(player2, player1);
                } else if (point == 5) {
                    points.put(player2, points.get(player2) + 5);
                    points.put(player1, points.get(player1) + 5);
                    logDrawGame(player1, player2);
                }
            }
        }
        return sortHashMap1(points);
    }
    static public void logWinBasicGame(Team<Participant> winner, Team<Participant> loser){
        winner.setGameList(new Game(loser, 10, 0));
        loser.setGameList(new Game(winner, 0, 10));
    }
    static public void logWinReplayGame(Team<Participant> winner, Team<Participant> loser){
        winner.setGameList(new Game(loser, 1, 0));
        loser.setGameList(new Game(winner, 0, 1));
    }
    static public void logDrawGame(Team<Participant> player1, Team<Participant> player2){
        player1.setGameList(new Game(player2, 5, 5));
        player2.setGameList(new Game(player1, 5, 5));
    }


    public static HashMap<Team<Participant>, Integer> sortHashMap1
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

//    public static void printParticipants(HashMap<Team<Participant>, Integer> map){
//        map.keySet().stream()
//                .flatMap(team -> team.getParticipantList().stream())
//                .forEach(participant -> System.out.println(participant));
//    }

}
