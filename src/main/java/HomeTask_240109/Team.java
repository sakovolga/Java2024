package HomeTask_240109;

import lombok.Setter;

import java.util.*;

public class Team<T extends Participant> {
    private final String teamName;
    @Setter
    private List<T> participantList = new ArrayList<>();
    private List<Game> gameList = new ArrayList<>();
//    private ArrayList<Map.Entry<Team<? extends Participant>, Integer>> gameList = new ArrayList<>();
    public Team(String teamName) {
        this.teamName = teamName;
    }

    public List<Game> getGameList() {
        return gameList;
    }

    public void setGameList(Game game) {
        this.gameList.add(game);
    }

    public String getTeamName() {
        return teamName;
    }

    public List<T> getParticipantList() {
        return participantList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team<?> team = (Team<?>) o;
        return Objects.equals(teamName, team.teamName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamName);
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
