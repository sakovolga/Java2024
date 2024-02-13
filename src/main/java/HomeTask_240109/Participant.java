package HomeTask_240109;

import java.util.Objects;

public class Participant {
    private String name;
    private int age;
    private String card;

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public Participant(String name, int age, String card) {
        this.name = name;
        this.age = age;
        this.card = card;
    }
    public Participant(){}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Participant{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", card='" + card + '\'' +
                '}';
    }
}
