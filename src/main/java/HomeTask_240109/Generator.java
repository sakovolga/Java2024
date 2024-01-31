package HomeTask_240109;

import com.github.javafaker.Faker;

import java.util.*;
import java.util.stream.Stream;

public class Generator {
    private static final Faker FAKER = new Faker();
    private static final Random RANDOM = new Random();

    public static <T extends Participant> Team<T> generateTeam(Class<T> clazz) {
        Team<T> team = new Team<>(FAKER.team().name());
        ArrayList<T> teamList = new ArrayList<>();
        do {
            if (clazz == Adult.class) {
                teamList.add((T) new Adult(FAKER.name().name(), RANDOM.nextInt(72) + 19));
            } else if (clazz == Pupil.class) {
                teamList.add((T) new Pupil(FAKER.name().name(), RANDOM.nextInt(6) + 6));
            } else if (clazz == TeenAger.class) {
                teamList.add((T) new TeenAger(FAKER.name().name(), RANDOM.nextInt(7) + 12));
            }
        } while (teamList.size() != 4);
        team.setParticipantList(teamList);
        return team;
    }

    public static <T extends Participant> List<Team<T>> generate25(Class<T> clazz) {
        List<Team<T>> teamList = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            teamList.add(generateTeam(clazz));
        }
        return teamList;
    }

    public static List<List<Team<? extends Participant>>> generate75() {
        List<Team<Pupil>> pupilTeamList;
        List<Team<TeenAger>> teenAgerTeamList;
        List<Team<Adult>> adultTeamList;
        List<String> listWithoutDoubleTeamName;
        List<String> listWithoutDoubleParticipantName;
        do {
            pupilTeamList = generate25(Pupil.class);
            teenAgerTeamList = generate25(TeenAger.class);
            adultTeamList = generate25(Adult.class);
            List<Team<? extends Participant>> listOf75Teams = new ArrayList<>(pupilTeamList);
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
        List<List<Team<? extends Participant>>> listListOf75Teams = new ArrayList<>();
        listListOf75Teams.add((List<Team<? extends Participant>>) (List<?>) pupilTeamList);
        listListOf75Teams.add((List<Team<? extends Participant>>) (List<?>) teenAgerTeamList);
        listListOf75Teams.add((List<Team<? extends Participant>>) (List<?>) adultTeamList);
        return listListOf75Teams;
    }
}
