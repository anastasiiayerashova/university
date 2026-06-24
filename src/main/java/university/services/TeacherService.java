package university.services;
import university.entities.Teacher;
import university.enums.TeacherPosition;

public class TeacherService {
    private Teacher[] teachers = new Teacher[100];
    private int size = 0;
    private int idCounter = 1;

    // create
    public void addTeacher(String name, String email, TeacherPosition position) {
        if (size >= teachers.length) {
            System.out.println("Помилка: база даних викладачів заповнена!");
            return;
        }
        Teacher teacher = new Teacher(idCounter++, name, email, position);
        teachers[size++] = teacher;
        System.out.println("Викладача успішно додано! (ID: " + teacher.getId() + ")");
    }

    // read
    public void showAllTeachers() {
        if (size == 0) {
            System.out.println("Викладачів у базі немає.");
            return;
        }
        for (int i = 0; i < size; i++) {
            System.out.println(teachers[i]);
        }
    }

    // find by id
    public Teacher findById(int id) {
        for (int i = 0; i < size; i++) {
            if (teachers[i].getId() == id) {
                return teachers[i];
            }
        }
        return null;
    }

    // update
    public void updateTeacher(int id, String newName, String newEmail, TeacherPosition newPosition) {
        Teacher teacher = findById(id);
        if (teacher == null) {
            System.out.println("Помилка: Викладача з ID " + id + " не знайдено.");
            return;
        }
        teacher.setName(newName);
        teacher.setEmail(newEmail);
        teacher.setPosition(newPosition);
        System.out.println("Дані викладача оновлено.");
    }

    // delete
    public void deleteTeacher(int id) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (teachers[i].getId() == id) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("Помилка: Викладача з ID " + id + " не знайдено.");
            return;
        }
        for (int i = index; i < size - 1; i++) {
            teachers[i] = teachers[i + 1];
        }
        teachers[--size] = null;
        System.out.println("Викладача видалено.");
    }

    public Teacher[] getTeachers() { return teachers; }
    public int getSize() { return size; }
}
