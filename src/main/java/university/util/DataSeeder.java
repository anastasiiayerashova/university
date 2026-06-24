package university.util;
import university.enums.Grade;
import university.enums.TeacherPosition;
import university.services.CourseService;
import university.services.EnrollmentService;
import university.services.StudentService;
import university.services.TeacherService;

public class DataSeeder {
    public static void seedData(StudentService studentService, TeacherService teacherService,
                                CourseService courseService, EnrollmentService enrollmentService) {

        String[] firstNames = {"Олександр", "Дмитро", "Ярослав", "Сергій", "Андрій", "Марія", "Анна", "Олена", "Тетяна", "Наталія"};
        String[] lastNames = {"Коваленко", "Мельник", "Шевченко", "Бондаренко", "Ткаченко", "Кравченко", "Олійник", "Поліщук", "Козак", "Гончар"};

        // генеруємо 15 студентів
        for (int i = 0; i < 15; i++) {
            String firstName = firstNames[(int) (Math.random() * firstNames.length)];
            String lastName = lastNames[(int) (Math.random() * lastNames.length)];
            String fullName = lastName + " " + firstName;

            String email = lastName.toLowerCase() + (i + 1) + "@univ.edu.ua";
            int studyYear = (int) (Math.random() * 4) + 1;

            studentService.addStudent(fullName, email, studyYear);
        }

        teacherService.addTeacher("Петров Петро Петрович", "petrov@univ.edu.ua", TeacherPosition.PROFESSOR);
        teacherService.addTeacher("Сидоров Сидір Сидорович", "sidorov@univ.edu.ua", TeacherPosition.LECTURER);
        teacherService.addTeacher("Іванова Ірина Іванівна", "ivanova@univ.edu.ua", TeacherPosition.ASSISTANT);

        courseService.addCourse("Вступ до Java", 5, 1);
        courseService.addCourse("Алгоритми та структури даних", 6, 1);
        courseService.addCourse("Бази даних", 4, 2);
        courseService.addCourse("Веб-програмування", 4, 3);

        Grade[] grades = {Grade.A, Grade.B, Grade.C, Grade.D, Grade.F, Grade.NA};

        for (int studentId = 1; studentId <= 10; studentId++) {
            enrollmentService.enrollStudent(studentId, 1, "2026-Осінь");
            Grade randomGrade1 = grades[(int) (Math.random() * grades.length)];
            enrollmentService.setGrade(studentId, 1, randomGrade1);

            if (Math.random() > 0.5) {
                enrollmentService.markAsPaid(studentId, 1);
            }

            enrollmentService.enrollStudent(studentId, 3, "2026-Осінь");
            Grade randomGrade3 = grades[(int) (Math.random() * grades.length)];
            enrollmentService.setGrade(studentId, 3, randomGrade3);
        }

        System.out.println("\n[SYSTEM]: Базу даних успішно наповнено тестовими даними!");
    }
}
