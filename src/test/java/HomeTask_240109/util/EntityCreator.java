package HomeTask_240109.util;

import HomeTask_240109.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EntityCreator {
    public static List<Team<Participant>> getPupilTeamList() {

        List<Team<Participant>> pulilTeamList = new ArrayList<>();

        Team<Participant> participantTeam1 = new Team<>("New Hampshire chickens");
        List<Participant> participantList1 = new ArrayList<>();
        participantList1.add(new Pupil("Ivan Ivanov", 4, "08195816898823970725379"));
        participantList1.add(new Pupil("Leta Douglas", 5, "87206952362190350229170"));
        participantList1.add(new Pupil("Keitha Mante", 6, "49145799790596310129737"));
        participantList1.add(new Pupil("Ozzie Hickle", 4, "69057287216035451125530"));
        participantTeam1.setParticipantList(participantList1);

        Team<Participant> participantTeam2 = new Team<>("Alabama sorcerors");
        List<Participant> participantList2 = new ArrayList<>();
        participantList2.add(new Pupil("Henry Trantow", 5, "18408783970132940528414"));
        participantList2.add(new Pupil("Kimbra Bartoletti", 4, "72960939203345810327640"));
        participantList2.add(new Pupil("Vicente Gottlieb", 4, "56900028871680940627098"));
        participantList2.add(new Pupil("Duncan Rodriguez", 5, "91782182569799690724194"));
        participantTeam2.setParticipantList(participantList2);

        Team<Participant> participantTeam3 = new Team<>("Wyoming druids");
        List<Participant> participantList3 = new ArrayList<>();
        participantList3.add(new Pupil("Ardelle Waters", 5, "77069073188095530427456"));
        participantList3.add(new Pupil("Odette Funk", 6, "42802326018256900925063"));
        participantList3.add(new Pupil("Neil Schaden", 4, "45502074018170580128618"));
        participantList3.add(new Pupil("Fredrick Wilderman", 5, "40852951116911720626280"));
        participantTeam3.setParticipantList(participantList3);

        pulilTeamList.add(participantTeam1);
        pulilTeamList.add(participantTeam2);
        pulilTeamList.add(participantTeam3);

        participantTeam1.setGameList(new Game(participantTeam2, 5, 5));
        participantTeam1.setGameList(new Game(participantTeam3, 5, 5));

        participantTeam2.setGameList(new Game(participantTeam1, 5, 5));
        participantTeam2.setGameList(new Game(participantTeam3, 10, 0));

        participantTeam3.setGameList(new Game(participantTeam1, 5, 5));
        participantTeam3.setGameList(new Game(participantTeam2, 0, 10));

        return pulilTeamList;
    }
    public static List<Team<Participant>> getTeenagerTeamList() {

        List<Team<Participant>> teenagerTeamList = new ArrayList<>();

        Team<Participant> participantTeam1 = new Team<>("Georgia spiders");
        List<Participant> participantList1 = new ArrayList<>();
        participantList1.add(new TeenAger("Wendell Schultz", 11, "09042087854224090527434"));
        participantList1.add(new TeenAger("Arnulfo Paucek", 9, "34630793455922711125221"));
        participantList1.add(new TeenAger("Rolando Heidenreich", 8, "48163394629428790724234"));
        participantList1.add(new TeenAger("Terisa Mills", 12, "97255515426482220125703"));
        participantTeam1.setParticipantList(participantList1);

        Team<Participant> participantTeam2 = new Team<>("South Dakota chimeras");
        List<Participant> participantList2 = new ArrayList<>();
        participantList2.add(new TeenAger("Samual Lang", 13, "14377229098450180626913"));
        participantList2.add(new TeenAger("Lizette Willms", 15, "46657360768472730525360"));
        participantList2.add(new TeenAger("Judie Upton", 11, "77821851762244990128640"));
        participantList2.add(new TeenAger("Terese DuBuque", 13, "87456514042478790129470"));
        participantTeam2.setParticipantList(participantList2);

        Team<Participant> participantTeam3 = new Team<>("California prophets");
        List<Participant> participantList3 = new ArrayList<>();
        participantList3.add(new TeenAger("Trista McClure", 12, "71651155158096240129554"));
        participantList3.add(new TeenAger("Torie Russel", 13, "56857260650853270526284"));
        participantList3.add(new TeenAger("Hilaria McLaughlin", 13, "27319345009681201226113"));
        participantList3.add(new TeenAger("Ed Koch", 13, "18349190002800330129764"));
        participantTeam3.setParticipantList(participantList3);

        teenagerTeamList.add(participantTeam1);
        teenagerTeamList.add(participantTeam2);
        teenagerTeamList.add(participantTeam3);

        participantTeam1.setGameList(new Game(participantTeam2, 5, 5));
        participantTeam1.setGameList(new Game(participantTeam3, 5, 5));

        participantTeam2.setGameList(new Game(participantTeam1, 5, 5));
        participantTeam2.setGameList(new Game(participantTeam3, 10, 0));

        participantTeam3.setGameList(new Game(participantTeam1, 5, 5));
        participantTeam3.setGameList(new Game(participantTeam2, 0, 10));

        return teenagerTeamList;
    }
    public static List<Team<Participant>> getAdultTeamList() {

        List<Team<Participant>> adultTeamList = new ArrayList<>();

        Team<Participant> participantTeam1 = new Team<>("Kentucky gooses");
        List<Participant> participantList1 = new ArrayList<>();
        participantList1.add(new Adult("Elliot Crooks", 35, "94611549792491310229558"));
        participantList1.add(new Adult("Arvilla Collier", 21, "93621709173237620726639"));
        participantList1.add(new Adult("Mrs. Tamar Effertz", 54, "16738972620057160725070"));
        participantList1.add(new Adult("Isreal Tromp", 44, "42230547088355580425640"));
        participantTeam1.setParticipantList(participantList1);

        Team<Participant> participantTeam2 = new Team<>("Virginia griffins");
        List<Participant> participantList2 = new ArrayList<>();
        participantList2.add(new Adult("Santiago Hauck", 36, "71641355377281241227210"));
        participantList2.add(new Adult("Lorinda Hermiston", 31, "48821175133756070525589"));
        participantList2.add(new Adult("Lesha Roberts", 36, "76146178702684000325633"));
        participantList2.add(new Adult("Maya Orn", 42, "04160027033752321225970"));
        participantTeam2.setParticipantList(participantList2);

        Team<Participant> participantTeam3 = new Team<>("Montana zebras");
        List<Participant> participantList3 = new ArrayList<>();
        participantList3.add(new Adult("Jamie Hahn", 36, "87953468786524770725989"));
        participantList3.add(new Adult("Audrey Cremin", 41, "11224789824438880724885"));
        participantList3.add(new Adult("Bobette Kemmer", 21, "05247925128560960129191"));
        participantList3.add(new Adult("Corine Spencer", 65, "94583130106056520324015"));
        participantTeam3.setParticipantList(participantList3);

        adultTeamList.add(participantTeam1);
        adultTeamList.add(participantTeam2);
        adultTeamList.add(participantTeam3);

        participantTeam1.setGameList(new Game(participantTeam3, 0, 10));
        participantTeam1.setGameList(new Game(participantTeam2, 0, 10));

        participantTeam2.setGameList(new Game(participantTeam1, 10, 10));
        participantTeam2.setGameList(new Game(participantTeam3, 5, 5));
        participantTeam2.setGameList(new Game(participantTeam3, 0, 1));

        participantTeam3.setGameList(new Game(participantTeam1, 10, 10));
        participantTeam3.setGameList(new Game(participantTeam2, 5, 5));
        participantTeam3.setGameList(new Game(participantTeam2, 1, 0));

        return adultTeamList;
    }
    public static List<Team<Participant>> getTeamList() {
        List<Team<Participant>> teamList = new ArrayList<>();
        teamList.addAll(getPupilTeamList());
        teamList.addAll(getTeenagerTeamList());
        teamList.addAll(getAdultTeamList());
        return teamList;
    }
    public static HashMap<Team<Participant>, Integer> getMapWithGameResults() {
        HashMap<Team<Participant>, Integer> map = new HashMap<>();
        List<Team<Participant>> teamList = getTeamList();
        map.put(teamList.get(0), 10);
        map.put(teamList.get(1), 15);
        map.put(teamList.get(2), 5);
        map.put(teamList.get(3), 10);
        map.put(teamList.get(4), 15);
        map.put(teamList.get(5), 5);
        map.put(teamList.get(6), 0);
        map.put(teamList.get(7), 15);
        map.put(teamList.get(8), 16);
        return map;
    }

}
