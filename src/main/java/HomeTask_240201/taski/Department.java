package HomeTask_240201.taski;

import java.util.List;

public class Department {
    private String name;
    private String head;
    private List<Course> courses;

    public Department() {
    }

    public Department(String name, String head, List<Course> courses) {
        this.name = name;
        this.head = head;
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", head='" + head + '\'' +
                ", courses=" + courses +
                '}';
    }
}
