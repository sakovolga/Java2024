package HomeTask_240109;

import java.util.*;

import com.github.javafaker.Faker;

import static HomeTask_240109.Generator.*;

public class Main<T extends Participant> {
    private static final Faker FAKER = new Faker();
    private static final Random RANDOM = new Random();

    public static void main(String[] args) throws MapOfTeamIsEmptyExeption, ParticipantAgeIsNull {

        List<List<Team<Participant>>> listOf75Team = Generator.generate75();
        List<Team<Participant>> pupilTeamList = listOf75Team.get(0);
        List<Team<Participant>> teenAgerTeamList = listOf75Team.get(1);
        List<Team<Participant>> adultTeamList = listOf75Team.get(2);


        System.out.println("----Таблица игр младшей группы----");
        HashMap<Team<Participant>, Integer> pupilMap = Handler.play(pupilTeamList);
        Handler.printTableOfGames(pupilMap);
        System.out.println("----Таблица игр подростков----");
        HashMap<Team<Participant>, Integer> teenagerMap = Handler.play(teenAgerTeamList);
        Handler.printTableOfGames(teenagerMap);
        System.out.println("----Таблица игр взрослых----");
        HashMap<Team<Participant>, Integer> adultMap = Handler.play(adultTeamList);
        Handler.printTableOfGames(adultMap);
        Solution sl = new Solution();

        HashMap<Team<Participant>, Integer> allTeamsMap = Solution.getAllTeamsMap(pupilMap, teenagerMap, adultMap);
        //    1. Найти команду с максимальными баллами:
        System.out.println(sl.findTeamWithMaxPoints(allTeamsMap));
        //    2. Подсчет общего количества баллов:
        System.out.println("Общее количество баллов: " + sl.findSumOfPoints(allTeamsMap));
        //    3. Список команд без баллов:
        System.out.println("Список команд без баллов: " + sl.getTeamsWithoutPoints(allTeamsMap));
        //    4. Средний возраст участников в каждой команде:
        System.out.println("Средний возраст участников в каждой команде:");
        sl.getAverageAgeOfEachTeam(allTeamsMap);
        //    5. Команды с баллами выше среднего:
        System.out.println("Команды с баллами выше среднего:");
        System.out.println(sl.getTeamsWithPointMoreThenAverage(allTeamsMap));
        //    6. Сортировка команд по баллам:
        System.out.println("Сортировка команд по баллам:");
        System.out.println(sl.sortHashMap(allTeamsMap));
        //    7. Команды с определенной категорией участников:
        //    Вывести команды, где все участники принадлежат к одной категории
        //    (например, только Adult).
        System.out.println("Команды, где все участники принадлежат к категории Adult:");
        System.out.println(sl.getTeamsOfCertainCategory(allTeamsMap, Adult.class));
        //    8. Команды с победами над определенной командой: Определить команды, которые выиграли у заданной команды.
        System.out.println(sl.getWinnersOfCertainTeam(allTeamsMap, pupilTeamList.get(0)));
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
        //    16. Найти команды, чьи баллы улучшались с каждой игрой.
        System.out.println("Команды, чьи баллы улучшались с каждой игрой: " + Solution.getTeamsWithIncreasedPoints(allTeamsMap));
        //    18. Список команд, которые имели ничейные результаты с заданной командой.
        Solution.getTeamsWithDraw(allTeamsMap);
        //    19. Вывести результаты всех игр между двумя конкретными командами.
        Solution.showResultsOfTwoTeams(pupilTeamList);

        //    20. Сравнить две команды по средним баллам и среднему возрасту участников.
        Solution.compareTwoTeams(allTeamsMap);
        //    21. Найти команды, в которых все участники имеют уникальные имена.
        Solution.getTeamsWithUniqueParticipantName(allTeamsMap);
        //    22. Определить команды с самой длинной последовательностью побед.
        Solution.getTeamsWithLongestWinningStreak(allTeamsMap);
        //    23. Найти команды с наибольшим количеством ничьих результатов.
        Solution.getTeamsWithBiggestNumberOfDraw(allTeamsMap);
        //    25. Создать комплексный отчет, включающий средний возраст команды, общее количество баллов, наибольшую победную серию,
        //    и сравнение с другими командами.
        Solution.complexReport(allTeamsMap);

        Handler.printParticipants(allTeamsMap);
        System.out.println("______________");
        Team<Participant> participantTeam1 = new Team<>("New Hampshire chickens");
        Team<Participant> participantTeam2 = new Team<>("Alabama sorcerors");
        Team<Participant> participantTeam3 = new Team<>("Wyoming druids");
        List<Team<Participant>> pulilTeamList = List.of(participantTeam1, participantTeam2, participantTeam3);

        Team<Participant> participantTeam4 = new Team<>("Georgia spiders");
        Team<Participant> participantTeam5 = new Team<>("South Dakota chimeras");
        Team<Participant> participantTeam6 = new Team<>("California prophets");
        List<Team<Participant>> teenagerTeamList = List.of(participantTeam4, participantTeam5, participantTeam6);

        Team<Participant> participantTeam7 = new Team<>("Kentucky gooses");
        Team<Participant> participantTeam8 = new Team<>("Virginia griffins");
        Team<Participant> participantTeam9 = new Team<>("Montana zebras");
        List<Team<Participant>> adultteamList = List.of(participantTeam7, participantTeam8, participantTeam9);

        System.out.println("----Таблица игр младшей группы----");
        HashMap<Team<Participant>, Integer> pMap = Handler.play(pulilTeamList);
        Handler.printTableOfGames(pMap);
        System.out.println("----Таблица игр подростков----");
        HashMap<Team<Participant>, Integer> tMap = Handler.play(teenagerTeamList);
        Handler.printTableOfGames(tMap);
        System.out.println("----Таблица игр взрослых----");
        HashMap<Team<Participant>, Integer> aMap = Handler.play(adultteamList);
        Handler.printTableOfGames(aMap);

    }
}

