package HomeTask_240109;

import java.util.*;

import com.github.javafaker.Faker;

import static HomeTask_240109.Generator.*;

public class Main<T extends Participant> {
    private static final Faker FAKER = new Faker();
    private static final Random RANDOM = new Random();

    public static void main(String[] args) throws MapOfTeamIsEmptyExeption, ParticipantAgeIsNull {

//        Pupil p1 = new Pupil();
//        TeenAger t1 = new TeenAger();
//        Adult a1 = new Adult();

//        List<Team<Pupil>> pupilTeamList = generate25(Pupil.class);
//        List<Team<TeenAger>> teenAgerTeamList = generate25(TeenAger.class);
//        List<Team<Adult>> adultTeamList = generate25(Adult.class);
        List<List<Team<? extends Participant>>> listOf75Team = Generator.generate75();
        List<Team<? extends Participant>> pupilTeamList = listOf75Team.get(0);
        List<Team<? extends Participant>> teenAgerTeamList = listOf75Team.get(1);
        List<Team<? extends Participant>> adultTeamList = listOf75Team.get(2);


        System.out.println("----Таблица игр младшей группы----");
        HashMap<Team<? extends Participant>, Integer> pupilMap = Handler.play(pupilTeamList);
//        HashMap<Team<T>, Integer> pupilMapT = pupilMap;
        Handler.printTableOfGames(pupilMap);
        System.out.println("----Таблица игр подростков----");
        HashMap<Team<? extends Participant>, Integer> teenagerMap = Handler.play(teenAgerTeamList);
        Handler.printTableOfGames(teenagerMap);
        System.out.println("----Таблица игр взрослых----");
        HashMap<Team<? extends Participant>, Integer> adultMap = Handler.play(adultTeamList);
        Handler.printTableOfGames(adultMap);

        HashMap<Team<? extends Participant>, Integer> allTeamsMap = Solution.getAllTeamsMap(pupilMap, teenagerMap, adultMap);
        //    1. Найти команду с максимальными баллами:
        System.out.println("Команда с максимальными баллами: " + Solution.findTeamWithMaxPoints(allTeamsMap));
        //    2. Подсчет общего количества баллов:
        System.out.println("Общее количество баллов: " + Solution.findSumOfPoints(allTeamsMap));
        //    3. Список команд без баллов:
        System.out.println("Список команд без баллов: " + Solution.getTeamsWithoutPoints(allTeamsMap));
        //    4. Средний возраст участников в каждой команде:
        System.out.println("Средний возраст участников в каждой команде:");
        Solution.getAverageAgeOfEachTeam(allTeamsMap);
        //    5. Команды с баллами выше среднего:
        System.out.println("Команды с баллами выше среднего:");
        System.out.println(Solution.getTeamsWithPointMoreThenAverage(allTeamsMap));
        //    6. Сортировка команд по баллам:
        System.out.println("Сортировка команд по баллам:");
        System.out.println(Solution.sortHashMap(allTeamsMap));
        //    7. Команды с определенной категорией участников:
        //    Вывести команды, где все участники принадлежат к одной категории
        //    (например, только Adult).
        System.out.println("Команды, где все участники принадлежат к одной категории:");
        System.out.println(Solution.getTeamsOfCertainCategory(allTeamsMap, Adult.class));

        //    9. Самый молодой участник среди всех команд:
        System.out.println("Самый молодой участник среди всех команд: " + Solution.getYoungestParticipant(allTeamsMap));
        //    10. Самая опытная команда: Определить команду с наибольшим суммарным возрастом участников.
        System.out.println("Самая опытная команда: " + Solution.findTheMostExperiencedTeam(allTeamsMap));
        //    11. Команды с участниками в определенном возрастном диапазоне:
        System.out.println("Команды с участниками в возрастном диапазоне от 35 до 90 лет: " + Solution.getAdeRange(allTeamsMap, 35, 90));
        //    12. Имена участников по убыванию возраста:
        System.out.println("Имена участников по убыванию возраста: " + Solution.getOrderByAge(allTeamsMap));
        //    13. Найти команду с наибольшим разбросом возрастов участников.
        System.out.println("Команда с наибольшим разбросом возрастов участников: " + Solution.getGreatestAgeRange(allTeamsMap));
        //    14. Найти все пары команд, чьи участники имеют одинаковый суммарный возраст.
        System.out.println("Пары команд, чьи участники имеют одинаковый суммарный возраст: ");
        Solution.getTeamPairWithEqualsSummaryAge(allTeamsMap);
        //    15. Вычислить средний балл для команд в каждой категории участников (Adult, Teenager, Pupil).
        System.out.println("Средний балл для команд в каждой категории участников:");
        Solution.getAverageAgeForEachCategory(allTeamsMap);

        //    20. Сравнить две команды по средним баллам и среднему возрасту участников.
        Solution.compareTwoTeams(allTeamsMap);

    }
}

