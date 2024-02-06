package HomeTask_240206;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentRegistryTest {
    StudentRegistry SR = new StudentRegistry();
    final static Student STUDENT = new Student(1, "Piter", "Jackson",
            "jackson@gmail.com", 4.6, Major.BIOLOGY, 3, true);
    final static Student STUDENT1 = new Student(2, "John", "Bunyakovsky",
            "bunyakovsky@gmail.com", 3.7, Major.BIOLOGY, 4, true);
    final static Student STUDENT2 = new Student(3, "Mike", "Patterson",
            "patterson@gmail.com", 5.0, Major.LAW, 4, false);
    final static Student STUDENT3 = new Student(4, "Anton", "Krasnoshchokov",
            "krasnoshchokov@gmail.com", 3.9, Major.ECONOMICS, 2, true);
    @BeforeEach
    void setUp(){
        StudentRegistry.getStudentMap().clear();
        SR.addStudent(STUDENT);
        SR.addStudent(STUDENT1);
        SR.addStudent(STUDENT2);
        SR.addStudent(STUDENT3);
    }
    @Test
    void addStudentTest() {
        assertEquals(4, StudentRegistry.getStudentMap().size());
    }
    @Test
    void removeStudentTest() {
        SR.removeStudent(1);
        assertEquals(3, StudentRegistry.getStudentMap().size());
    }
    @Test
    void findStudentsByMajorTest() {
        List<Student> list = SR.findStudentsByMajor(String.valueOf(Major.BIOLOGY));
        assertEquals(2, list.size());
    }
    @Test
    void calculateAverageGradeTest() {
        double actual = SR.calculateAverageGrade();
        assertEquals(4.3, actual);
    }
    @Test
    void listStudentsByYearTest() {
        List<Student> actual = SR.listStudentsByYear(4);
        assertEquals(2, actual.size());
    }
    @Test
    void getStudentTest() {
        assertEquals(STUDENT, SR.getStudent(1));
    }
    @Test
    void getTotalNumberOfStudentsTest() {
        assertEquals(4, SR.getTotalNumberOfStudents());
    }
    @Test
    void getStudentsWithGradeAboveTest() {
        assertEquals(2, SR.getStudentsWithGradeAbove(4.0).size());
    }
    @Test
    void getAverageGradeByMajorTest() {
        assertEquals(4.15, SR.getAverageGradeByMajor(String.valueOf(Major.BIOLOGY)));
    }
    @Test
    void isStudentPresentPositiveTest() {
        assertTrue(SR.isStudentPresent("bunyakovsky@gmail.com"));
    }
    @Test
    void isStudentPresentNegativeTest() {
        assertFalse(SR.isStudentPresent("qwert@gmail.com"));
    }
}