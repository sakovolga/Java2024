package HomeTask_240109;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
class GeneratorTest {

    public static Stream<Arguments> orderClass() {
        return Stream.of(
                Arguments.of((Pupil.class),
                Arguments.of(Adult.class),
                Arguments.of(TeenAger.class)));
    }
    @ParameterizedTest
    @MethodSource("orderClass")
    void generateTeamTest(Class<Participant> clazz) {
//        Проверка на размер
        Team<Participant> team = Generator.generateTeam(clazz);
        Assertions.assertEquals(4, team.getParticipantList().size());
//        Проверка на соответствие классу
        for (Participant participant : team.getParticipantList()) {
            Assertions.assertTrue(clazz.isInstance(participant));
        }
    }

    @ParameterizedTest
    @MethodSource("orderClass")
    void generate25Test(Class<? extends Participant> clazz) {
//        Проверка на размер
        List<? extends Team<? extends Participant>> teamList = Generator.generate25(clazz);
        Assertions.assertEquals(25, teamList.size());
//        Проверка на соответствие нужному классу
        for (Team<? extends Participant> team : teamList) {
            Assertions.assertTrue(clazz.isInstance(team.getParticipantList().get(0)));
        }
    }

    @Test
    void generate75Test() {
//        Проверка на количество
        List<List<Team<Participant>>> list = Generator.generate75();
        for (List<Team<Participant>> teamList: list){
            assertEquals(25, teamList.size());
        }

//        Проверка на уникальность названий команд
        List<Team<Participant>> oneList = new ArrayList<>();
        for (List<Team<Participant>> teamList : list) {
            oneList.addAll(teamList);
        }
        Set<Team<Participant>> teamSet = new HashSet<>(oneList);
        assertEquals(oneList.size(), teamSet.size());

//      Проверка на уникальность имен участников
        List<Participant> participantList = oneList.stream().
                flatMap(participantTeam -> participantTeam.getParticipantList().stream())
                .toList();
        Set<Participant> participantSet = new HashSet<>(participantList);
        assertEquals(participantList.size(), participantSet.size());
    }
}