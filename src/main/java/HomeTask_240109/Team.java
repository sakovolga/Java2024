package HomeTask_240109;

import java.util.*;

public class Team<T extends Participant> {
    private String teamName;
    private List<T> participantList = new ArrayList<>();
    private ArrayList<Map.Entry<Team<? extends Participant>, Integer>> gameList;
    public Team(String teamName) {
        this.teamName = teamName;
    }

    public ArrayList<Map.Entry<Team<? extends Participant>, Integer>> getGameList() {
        return gameList;
    }

    public void setGameList(Team<? extends Participant> team, Integer points) {
        this.gameList.add(Map.entry(team, points));
    }

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

    public int play(Team<? extends Participant> secondTeam) {
        int randomDigit = new Random().nextInt(3);
        if (randomDigit == 1) {
            return 10;   //Победа
        }
        if (randomDigit == 0) {
            return 0;   //Проигрыш
        } else return 5;  //Ничья
    }

    public int rePlay(Team<? extends Participant> secondTeam) {
        int randomDigit = new Random().nextInt(2);
        if (randomDigit == 1) {
            return 1;   //Победа
        } else return 0;   //Проигрыш
    }
}
