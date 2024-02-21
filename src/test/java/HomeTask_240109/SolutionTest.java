package HomeTask_240109;

import HomeTask_240109.util.EntityCreator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {
    Solution sl = new Solution();

    @Test
    void getAllTeamsMap() {
    }

    @Test
    void findTeamWithMaxPointsTest() {
        String teamExpected = "Montana zebras";
        String teamActual = sl.findTeamWithMaxPoints(EntityCreator.getMapWithGameResults()).getTeamName();
        assertEquals(teamExpected, teamActual);
    }

    @Test
    void findSumOfPointsPositiveTest() {
        HashMap<Team<Participant>, Integer> map = EntityCreator.getMapWithGameResults();
        try {
            assertEquals(160, sl.findSumOfPoints(map));
        } catch (MapOfTeamIsEmptyExeption e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void findSumOfPointsNegativeTest() {
        HashMap<Team<Participant>, Integer> map = new HashMap<>();
        assertThrows(MapOfTeamIsEmptyExeption.class, () -> sl.findSumOfPoints(map));
    }

    @Test
    void getTeamsWithoutPoints() {
        Team<Participant> team1 = EntityCreator.getTeamList().get(1);
        Team<Participant> team2 = EntityCreator.getTeamList().get(6);
        List<Team<Participant>> expected = List.of(team1, team2);
        List<Team<Participant>> actual = sl.getTeamsWithoutPoints(EntityCreator.getMapWithGameResults());
        assertEquals(expected, actual);
    }

    @Test
    void getAverageAgeOfEachTeamTest() {
        HashMap<Team<Participant>, Integer> map = EntityCreator.getMapWithGameResults();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        sl.getAverageAgeOfEachTeam(map);
        String expectedOutput = "New Hampshire chickens: 4.75\nAlabama sorcerors: 4.5\n" +
                "Wyoming druids: 5.0\nGeorgia spiders: 10.0\nSouth Dakota chimeras: 13.0\n" +
                "California prophets: 12.75\nKentucky gooses: 38.5\n" +
                "Virginia griffins: 36.25\nMontana zebras: 40.75\n";
        assertEquals(expectedOutput, outContent.toString());
        System.setOut(System.out);
    }

    @Test
    void getTeamsWithPointMoreThenAverageTest() {
        List<Team<Participant>> teamList = EntityCreator.getTeamList();
                List<Team<Participant>> expected = List.of(
                        teamList.get(2),
                        teamList.get(3),
                        teamList.get(4),
                        teamList.get(7),
                        teamList.get(8)
                );
        List<Team<Participant>> actual = sl.getTeamsWithPointMoreThenAverage(EntityCreator.getMapWithGameResults());
        assertEquals(expected, actual);
    }

    @Test
    void sortHashMapTest() {
        HashMap<Team<Participant>, Integer> map = EntityCreator.getMapWithGameResults();
        List<Team<Participant>> teamList = EntityCreator.getTeamList();
        Map<Team<Participant>, Integer> expected = new LinkedHashMap<>();
        expected.put(teamList.get(8), 38);
        expected.put(teamList.get(4), 30);
        expected.put(teamList.get(3), 25);
        expected.put(teamList.get(7), 25);
        expected.put(teamList.get(2), 20);
        expected.put(teamList.get(5), 12);
        expected.put(teamList.get(0), 10);
        expected.put(teamList.get(1), 0);
        expected.put(teamList.get(6), 0);
        Map<Team<Participant>, Integer> actual = sl.sortHashMap(map);
        assertEquals(expected, actual);
    }
    @Test
    void getTeamsOfCertainCategory() {
        List<Team<Participant>> expected = EntityCreator.getTeenagerTeamList();
        List<Team<Participant>> actual = sl.getTeamsOfCertainCategory(EntityCreator.getMapWithGameResults(), TeenAger.class);
        assertEquals(expected, actual);
    }
    @Test
    void getWinnersOfCertainTeamTest() {
        List<Team<Participant>> expected = List.of(
                EntityCreator.getAdultTeamList().get(1),
                EntityCreator.getAdultTeamList().get(2)
        );
        List<Team<Participant>> actual = sl.getWinnersOfCertainTeam(EntityCreator.getMapWithGameResults(),
                                                                    EntityCreator.getAdultTeamList().get(0));
        assertTrue(expected.size() == actual.size() && expected.containsAll(actual)
                                                            && actual.containsAll(expected));
    }
    @Test
    void getYoungestParticipant() {
    }

    @Test
    void findTheMostExperiencedTeam() {
    }

    @Test
    void getAdeRange() {
    }

    @Test
    void getOrderByAge() {
    }

    @Test
    void getGreatestAgeRange() {
    }

    @Test
    void getTeamPairWithEqualsSummaryAge() {
    }

    @Test
    void getAverageAgeForEachCategory() {
    }

    @Test
    void getTeamsWithIncreasedPoints() {
    }

    @Test
    void getTeamsWithDraw() {
    }

    @Test
    void showResultsOfTwoTeams() {
    }

    @Test
    void compareTwoTeams() {
    }

    @Test
    void getAverageAge() {
    }

    @Test
    void getTeamsWithUniqueParticipantName() {
    }

    @Test
    void getFirstName() {
    }

    @Test
    void getTeamsWithLongestWinningStreak() {
    }

    @Test
    void getLongestWinningStreak() {
    }

    @Test
    void getTeamsWithBiggestNumberOfDraw() {
    }

    @Test
    void getNumberOfDraw() {
    }

    @Test
    void complexReport() {
    }

    @Test
    void getTeamPlace() {
    }
}