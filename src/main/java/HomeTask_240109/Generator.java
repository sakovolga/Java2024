package HomeTask_240109;

import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.Stream;

public class Generator {
    private static final Faker FAKER = new Faker();
    private static final Random RANDOM = new Random();

    public static <T extends Participant> Team<Participant> generateTeam(Class<T> clazz) {
        Team<Participant> team = new Team<>(FAKER.team().name());
        ArrayList<Participant> teamList = new ArrayList<>();
        do {
            if (clazz == Adult.class) {
                teamList.add(new Adult(FAKER.name().name(), RANDOM.nextInt(72) + 19, CardCen.generateCard()));
            } else if (clazz == Pupil.class) {
                teamList.add(new Pupil(FAKER.name().name(), RANDOM.nextInt(6) + 6, CardCen.generateCard()));
            } else if (clazz == TeenAger.class) {
                teamList.add(new TeenAger(FAKER.name().name(), RANDOM.nextInt(7) + 12, CardCen.generateCard()));
            }
        } while (teamList.size() != 4);
        team.setParticipantList(teamList);
        return team;
    }

    public static <T extends Participant> List<Team<Participant>> generate25(Class<T> clazz) {
        List<Team<Participant>> teamList = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            teamList.add(generateTeam(clazz));
        }
        return teamList;
    }


    public static List<List<Team<Participant>>> generate75() {
        List<Team<Participant>> pupilTeamList;
        List<Team<Participant>> teenAgerTeamList;
        List<Team<Participant>> adultTeamList;
        List<String> listWithoutDoubleTeamName;
        List<String> listWithoutDoubleParticipantName;
        do {
            pupilTeamList = generate25(Pupil.class);
            teenAgerTeamList = generate25(TeenAger.class);
            adultTeamList = generate25(Adult.class);
            List<Team<Participant>> listOf75Teams = new ArrayList<>(pupilTeamList);
            listOf75Teams.addAll(teenAgerTeamList);
            listOf75Teams.addAll(adultTeamList);
            listWithoutDoubleTeamName = listOf75Teams.stream()
                    .map(Team::getTeamName)
                    .distinct()
                    .toList();
            listWithoutDoubleParticipantName = listOf75Teams.stream()
                    .flatMap(team -> team.getParticipantList().stream())
                    .map(Participant::getName)
                    .distinct()
                    .toList();
        } while ((listWithoutDoubleTeamName.size() != 75 || listWithoutDoubleParticipantName.size() != 300));
        List<List<Team<Participant>>> listListOf75Teams = new ArrayList<>();
        listListOf75Teams.add((List<Team<Participant>>) pupilTeamList);
        listListOf75Teams.add((List<Team<Participant>>) teenAgerTeamList);
        listListOf75Teams.add((List<Team<Participant>>)adultTeamList);
        return listListOf75Teams;
    }
}
