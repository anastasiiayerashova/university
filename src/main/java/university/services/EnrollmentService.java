package university.services;
import university.entities.Course;
import university.entities.Enrollment;
import university.entities.Student;
import university.enums.Grade;
import university.util.GPAUtils;

public class EnrollmentService {
    private Enrollment[] enrollments = new Enrollment[200];
    private int size = 0;

    private StudentService studentService;
    private CourseService courseService;

    public EnrollmentService(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    public void enrollStudent(int studentId, int courseId, String semester) {
        if (size >= enrollments.length) {
            System.out.println("Помилка: База даних зарахувань заповнена!");
            return;
        }

        Student student = studentService.findById(studentId);
        Course course = courseService.findById(courseId);

        if (student == null || course == null) {
            System.out.println("Помилка: Студента або курс з такими ID не знайдено.");
            return;
        }

        for (int i = 0; i < size; i++) {
            if (enrollments[i].getStudent().getId() == studentId &&
                    enrollments[i].getCourse().getId() == courseId &&
                    enrollments[i].getSemester().equalsIgnoreCase(semester)) {
                System.out.println("Помилка: Студент вже зарахований на цей курс у цьому семестрі.");
                return;
            }
        }
        enrollments[size++] = new Enrollment(student, course, semester);
        System.out.println("Студента успішно зараховано на курс!");
    }

    public void setGrade(int studentId, int courseId, Grade grade) {
        Enrollment enrollment = findEnrollment(studentId, courseId);
        if (enrollment == null) {
            System.out.println("Помилка: Зарахування не знайдено.");
            return;
        }
        enrollment.setGrade(grade);
        System.out.println("Оцінку " + grade + " успішно виставлено.");
    }

    public void markAsPaid(int studentId, int courseId) {
        Enrollment enrollment = findEnrollment(studentId, courseId);
        if (enrollment == null) {
            System.out.println("Помилка: Зарахування не знайдено.");
            return;
        }
        enrollment.setPaid(true);
        System.out.println("Оплату успішно підтверджено.");
    }

    public void showStudentTranscript(int studentId) {
        Student student = studentService.findById(studentId);
        if (student == null) {
            System.out.println("Студента не знайдено.");
            return;
        }

        System.out.println("\n=== ТРАНСКРИПТ СТУДЕНТА: " + student.getName() + " ===");

        Enrollment[] studentEnrollments = new Enrollment[50];
        int count = 0;

        for (int i = 0; i < size; i++) {
            if (enrollments[i].getStudent().getId() == studentId) {
                studentEnrollments[count++] = enrollments[i];
                System.out.println("- Курс: " + enrollments[i].getCourse().getTitle() +
                        " | Семестр: " + enrollments[i].getSemester() +
                        " | Оцінка: " + enrollments[i].getGrade() +
                        " | Оплачено: " + (enrollments[i].isPaid() ? "Так" : "Ні"));
            }
        }

        double gpa = GPAUtils.calculateGPA(studentEnrollments, count);
        System.out.printf("Поточний середній бал (GPA): %.2f\n", gpa);
        System.out.println("=========================================");
    }

    private Enrollment findEnrollment(int studentId, int courseId) {
        for (int i = 0; i < size; i++) {
            if (enrollments[i].getStudent().getId() == studentId && enrollments[i].getCourse().getId() == courseId) {
                return enrollments[i];
            }
        }
        return null;
    }

    public void showUnpaidEnrollments() {
        boolean found = false;
        for (int i = 0; i < size; i++) {
            if (!enrollments[i].isPaid()) {
                System.out.println("Студент: " + enrollments[i].getStudent().getName() +
                        " (ID: " + enrollments[i].getStudent().getId() + ") " +
                        "| Неоплачений курс: " + enrollments[i].getCourse().getTitle());
                found = true;
            }
        }
        if (!found) System.out.println("Усі курси оплачені! Заборгованостей немає.");
    }

    public Enrollment[] getEnrollments() { return enrollments; }
    public int getSize() { return size; }
}
