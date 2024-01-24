package HomeTask_240109;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Handler {

    public static <T extends Participant> HashMap<Team<T>, Integer> play(List<Team<T>> list){
        HashMap<Team<T>, Integer> playersMap = playEachOther(list);
        while (!isUnique(playersMap)){
            HashMap<Team<T>, Integer> doublePlayersMap = new HashMap<>(getOneDoubleTeam(playersMap));
            playersMap = playAnotherOneTour(playersMap, doublePlayersMap);
        }
        return sortHashMap1(playersMap);
    }
    public static <T extends Participant> void printTableOfGames(HashMap<Team<T>, Integer> map){
        for (Map.Entry entry: map.entrySet()){
            System.out.println(entry);
        }
    }
    public static <T extends Participant> HashMap<Team<T>, Integer> getOneDoubleTeam(Map<Team<T>, Integer> map) {

        Map<Team<T>, Integer> map1 =  map.entrySet().stream()
                .collect(Collectors.groupingBy(Map.Entry::getValue))
                .entrySet()
                .stream().filter(value -> value.getValue().size() > 1)
                .limit(1)
                .flatMap(entry -> entry.getValue().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        HashMap<Team<T>, Integer> map2 = new HashMap<>(map1);
        return map2;
    }
    public static <T extends Participant> boolean isUnique(HashMap<Team<T>, Integer> map){
        int count = (int)map.values().stream()
                .distinct()
                .count();
        return count == map.size();
    }
    public static <T extends Participant> HashMap<Team<T>, Integer>  playAnotherOneTour
            (HashMap<Team<T>, Integer> map, HashMap<Team<T>, Integer> doublePlayersMap){
        List<Team<T>> list = doublePlayersMap.keySet().stream().toList();
        for (int i = 0; i < list.size()-1; i++){
            for (int j = i + 1; j < list.size(); j++){
                int point = list.get(i).rePlay(list.get(j));
                if (point == 0.01){
                    int temp = map.get(list.get(i));
                    temp = temp + 1;
                    map.put(list.get(i), temp);
                }
                else {
                    int temp = map.get(list.get(j));
                    temp = temp + 1;
                    map.put(list.get(j), temp);
                }
//                else if (point == 0.005){
//                    double temp = map.get(list.get(j));
//                    temp = temp + 0.005;
//                    map.put(list.get(j), temp);
//                    double temp1 = map.get(list.get(i));
//                    temp1 = temp1 + 0.005;
//                    map.put(list.get(i), temp1);
//                }
            }
        }
        return map;
    }
public static <T extends Participant> HashMap<Team<T>, Integer>  playEachOther(List<Team<T>> list){
    HashMap<Team<T>, Integer> points = new HashMap<>();
    for (int i = 0; i < list.size(); i++){
        points.put(list.get(i), 0);
    }

    for (int i = 0; i < list.size(); i++){
        for (int j = i + 1; j < list.size(); j++){
            int point = list.get(i).play(list.get(j));
            if (point == 10){
                int temp = points.get(list.get(i));
                temp = temp + 10;
                points.put(list.get(i), temp);
            }
            else if (point == 0){
                int temp = points.get(list.get(j));
                temp = temp + 10;
                points.put(list.get(j), temp);
            }
            else if (point == 5){
                int temp = points.get(list.get(j));
                temp = temp + 5;
                points.put(list.get(j), temp);
                int temp1 = points.get(list.get(i));
                temp1 = temp1 + 5;
                points.put(list.get(i), temp1);
            }
        }
    }
//    for (Map.Entry<Team, Double> entry: points.entrySet()){
//        System.out.println(entry);
//    }
    ;
//    System.out.println("----------Отсортировано--------");
//    HashMap<Team, Double> sortedMap = sortHashMap1(points);
//    System.out.println(sortedMap);
//    return sortedMap;
        return sortHashMap1(points);
}
    public static <T extends Participant> HashMap<Team<T>, Integer> sortHashMap1(HashMap<Team<T>, Integer> map){
        return map.entrySet().stream()
                .sorted(new Comparator<Map.Entry<Team<T>, Integer>>() {
                    @Override
                    public int compare(Map.Entry<Team<T>, Integer> o1, Map.Entry<Team<T>, Integer> o2) {
                        return o2.getValue().compareTo(o1.getValue());
                    }
                })
                .collect(Collectors.toMap(Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new));
    }
//public static void sortHashMap(HashMap<Team, Double> map){
//    if (map.size() != 0){
//        double max = 0;
//        for (Map.Entry<Team, Double> entry : map.entrySet()) {
//            if (entry.getValue() >= max)
//                max = entry.getValue();
//        }
//        Team tempTeam = findKeyByValue(map, max);
//        map.remove(tempTeam);
//        System.out.println(tempTeam + " = " + max);
//        sortHashMap(map);
//    }
//}
//public static Team findKeyByValue(HashMap<Team, Double> map, double value){
//    for (Map.Entry<Team, Double> entry: map.entrySet()){
//        if (entry.getValue() == value)
//            return entry.getKey();
//    }
//    return null;
//}
//    public static HashMap<Team, Double> findAllKeyByValue(HashMap<Team, Double> map, double value){
//        HashMap <Team, Double> newMap = new HashMap<>();
//        for (Map.Entry<Team, Double> entry: map.entrySet()){
//            if (entry.getValue() == value)
//                newMap.put(entry.getKey(), value);
//        }
//        return newMap;
//    }
//public static void replay(HashMap<Team, Double> map){
//    if ()
//}
}
