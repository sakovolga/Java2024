package HomeTask_240206;

import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class StudentRegistry {
    @Getter
    private static Map<Integer, Student> studentMap = new HashMap<>();

    //    Реализуйте в этом классе следующие методы для обработки данных с использованием Stream API:
//    1. addStudent(Student student) - добавляет студента в реестр.
    public void addStudent(Student student) {
        studentMap.put(student.getId(), student);
    }
//    2. removeStudent(int id) - удаляет студента по идентификатору.
    public void removeStudent(int id){
        studentMap.remove(id);
    }
//    3. findStudentsByMajor(String major) - возвращает список студентов, обучающихся на указанной специальности.
    public List<Student> findStudentsByMajor(String major){
        return studentMap.values().stream()
                .filter(student -> student.getMajor() == Major.valueOf(major))
                .toList();
    }
//    4. calculateAverageGrade() - вычисляет средний балл среди всех студентов.
    public double calculateAverageGrade(){
        return studentMap.values().stream()
                .map(Student::getGrade)
                .collect(Collectors.averagingDouble(Double::doubleValue));
    }
//    5. listStudentsByYear(int year) - возвращает список студентов, обучающихся на указанном курсе.
    public List<Student> listStudentsByYear(int year){
        return studentMap.values().stream()
                .filter(student -> student.getYear() == year)
                .toList();
    }
//    6. getStudent(int id) - возвращает студента по его идентификатору.
    public Student getStudent(int id){
        return studentMap.get(id);
    }
//    7. getTotalNumberOfStudents() - возвращает общее количество студентов.
    public int getTotalNumberOfStudents(){
        return studentMap.size();
    }
//    8. getStudentsWithGradeAbove(double grade) - возвращает список студентов, чей средний балл выше указанного.
    public List<Student> getStudentsWithGradeAbove(double grade){
        return studentMap.values().stream()
                .filter(student -> student.getGrade() > grade)
                .toList();
    }
//    9. getAverageGradeByMajor(String major) - вычисляет средний балл среди студентов определенной специальности.
    public double getAverageGradeByMajor(String major){
        return studentMap.values().stream()
                .filter(student -> student.getMajor() == Major.valueOf(major))
                .collect(Collectors.averagingDouble(Student::getGrade));
    }
//    10. isStudentPresent(String email) - проверяет, существует ли студент с заданным email в реестре.
    public boolean isStudentPresent(String email){
        List<Student> list = studentMap.values().stream()
                .filter(student -> student.getEmail().equals(email))
                .toList();
        return !list.isEmpty();
    }
}
