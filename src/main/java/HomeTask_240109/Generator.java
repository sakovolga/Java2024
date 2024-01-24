package HomeTask_240109;

import com.github.javafaker.Faker;

import java.util.*;

public class Generator {
    private static final Faker FAKER = new Faker();
    private static final Random RANDOM = new Random();


    //    public static <T> Set<T> generate(Team<T> team){
//        T Participant
//        team.addNewParticipant();
//    }
//    public static ArrayList<Pupil> generatePupilTeam(Team<Pupil> team){
//        do{
//            team.addNewParticipant(new Pupil(FAKER.name().name(), RANDOM.nextInt(11)));
//        } while (team.getParticipantList().size() != 4);
//        return (ArrayList<Pupil>)team.getParticipantList();
//    }
//    public static ArrayList<TeenAger> generateTeenAgerTeam(Team<TeenAger> team){
//        do{
//            team.addNewParticipant(new TeenAger(FAKER.name().name(), RANDOM.nextInt(15)));
//        } while (team.getParticipantList().size() != 4);
//        return (ArrayList<TeenAger>)team.getParticipantList();
//    }
//    public static Team<Adult> generateAdultTeam(){
//        return new Team<Adult>()
//
//    }
    public static <T extends Participant> Team<T> generateTeam(T participant) {
        Team<T> team = new Team<>(FAKER.team().name());
        ArrayList<T> teamList = new ArrayList<>();
        do {
            if (participant instanceof Adult) {
                teamList.add((T) new Adult(FAKER.name().name(), RANDOM.nextInt(72) + 19));
            } else if (participant instanceof Pupil) {
                teamList.add((T) new Pupil(FAKER.name().name(), RANDOM.nextInt(6) + 6));
            } else if (participant instanceof TeenAger) {
                teamList.add((T) new Pupil(FAKER.name().name(), RANDOM.nextInt(7) + 12));
            }
        } while (teamList.size() != 4);
        team.setParticipantList(teamList);
        return team;
    }

    //    public static <T extends Participant> List<Team<T>> generate25(T t){
//        List<Team<Participant>> pupilTeamList = new ArrayList<>();
//        for (int i = 0; i < 25; i++){
//            pupilTeamList.add(generateTeam(t));
//        }
//        return (List<Team<T>>) pupilTeamList;
//    }
    public static <T extends Participant> List<Team<T>> generate25(T t) {
        List<Team<T>> teamList = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            teamList.add(generateTeam(t));
        }
        return teamList;
    }

//        if (participant instanceof Adult){
//            team.setParticipantList();
//            generateAdultTeam();
//        }

}
