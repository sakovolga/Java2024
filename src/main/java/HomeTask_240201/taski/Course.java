package HomeTask_240201.taski;

import java.util.List;

public class Course {
    private String id;
    private String title;
    private int credits;
    private String professor;
    private List<String> topics;

    public Course() {
    }

    public Course(String id, String title, int credits, String professor, List<String> topics) {
        this.id = id;
        this.title = title;
        this.credits = credits;
        this.professor = professor;
        this.topics = topics;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", credits=" + credits +
                ", professor='" + professor + '\'' +
                ", topics=" + topics +
                '}';
    }
}
