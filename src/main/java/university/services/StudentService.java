package university.services;
import university.entities.Student;
import university.enums.StudentStatus;

public class StudentService {
    private Student[] students = new Student[100];
    private int size = 0;
    private int idCounter = 1;

    // create
    public void addStudent(String name, String email, int studyYear) {
        if (size >= students.length) {
            System.out.println("Помилка: база даних студентів заповнена!");
            return;
        }
        Student student = new Student(idCounter++, name, email, studyYear);
        students[size++] = student;
        System.out.println("Студента успішно додано! (ID: " + student.getId() + ")");
    }

    // read
    public void showAllStudents() {
        if (size == 0) {
            System.out.println("Студентів у базі немає.");
            return;
        }
        for (int i = 0; i < size; i++) {
            System.out.println(students[i]);
        }
    }

    // find by id
    public Student findById(int id) {
        for (int i = 0; i < size; i++) {
            if (students[i].getId() == id) {
                return students[i];
            }
        }
        return null;
    }

    // update
    public void updateStudent(int id, String newName, String newEmail, int newYear) {
        Student student = findById(id);
        if (student == null) {
            System.out.println("Помилка: Студента з ID " + id + " не знайдено.");
            return;
        }
        student.setName(newName);
        student.setEmail(newEmail);
        student.setStudyYear(newYear);
        System.out.println("Дані студента оновлено.");
    }

    // delete
    public void deleteStudent(int id) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (students[i].getId() == id) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("Помилка: Студента з ID " + id + " не знайдено.");
            return;
        }
        for (int i = index; i < size - 1; i++) {
            students[i] = students[i + 1];
        }
        students[--size] = null;
        System.out.println("Студента видалено.");
    }

    // status change
    public void changeStatus(int id, StudentStatus newStatus) {
        Student student = findById(id);
        if (student == null) {
            System.out.println("Студента не знайдено.");
            return;
        }
        student.setStatus(newStatus);
        System.out.println("Статус успішно змінено на " + newStatus);
    }

    public Student[] getStudents() { return students; }
    public int getSize() { return size; }
}
