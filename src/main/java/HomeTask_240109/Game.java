package HomeTask_240109;

public class Game {
    private Team<Participant> rival;
    private int myPoints;
    private int rivalPoints;

    public Game(Team<Participant> rival, int myPoints, int rivalPoints) {
        this.rival = rival;
        this.myPoints = myPoints;
        this.rivalPoints = rivalPoints;
    }

    public Team<Participant> getRival() {
        return rival;
    }

    public int getMyPoints() {
        return myPoints;
    }

    public int getRivalPoints() {
        return rivalPoints;
    }

    @Override
    public String toString() {
        return "Game{" +
                "rival=" + rival.getTeamName() +
                ", myPoints=" + myPoints +
                ", rivalPoints=" + rivalPoints +
                '}';
    }
}
