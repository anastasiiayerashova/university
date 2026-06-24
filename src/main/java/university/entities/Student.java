package university.entities;
import university.enums.StudentStatus;

public class Student extends Person {
    private int studyYear;
    private StudentStatus status;

    public Student(int id, String name, String email, int studyYear) {
        super(id, name, email);
        if (studyYear < 1) {
            throw new IllegalArgumentException("Помилка: рік навчання не може бути менше 1.");
        }
        this.studyYear = studyYear;
        this.status = StudentStatus.ACTIVE;
    }

    public int getStudyYear() {
        return studyYear;
    }

    public void setStudyYear(int studyYear) {
        this.studyYear = studyYear;
    }

    public StudentStatus getStatus() {
        return status;
    }

    public void setStatus(StudentStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return super.toString() + ", Рік навчання: " + studyYear + ", Статус: " + status;
    }
}
