package HomeTask_240109;

import java.util.*;

public class Team<T extends Participant> {
    private String teamName;
    private List<T> participantList = new ArrayList<>();

    public Team(String teamName) {
        this.teamName = teamName;
    }

//    public void addNewParticipant(T participant) {
//        participantList.add(participant);
//    }

    public String getTeamName() {
        return teamName;
    }

    public void setParticipantList(List<T> participantList) {
        this.participantList = participantList;
    }

    public List<T> getParticipantList() {
        return participantList;
    }

    @Override
    public String toString() {
        return teamName;
    }

    public int play(Team<T> secondTeam) {
//        String winner;
        int randomDigit = new Random().nextInt(3);

        if(randomDigit == 1) {
            return 10;   //Победа
        }
        if (randomDigit == 0){
            return 0;   //Проигрыш
        }
        else return 5;  //Ничья
//        System.out.println("WINNER: " + winner + "!!!!!");
    }
    public int rePlay(Team<T> secondTeam) {
        int randomDigit = new Random().nextInt(2);

        if(randomDigit == 1) {
            return 1;   //Победа
        } else return 0;   //Проигрыш
//        } else return 0.005;  //Ничья
    }
}
