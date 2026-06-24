package university;
import university.enums.TeacherPosition;
import university.services.CourseService;
import university.services.EnrollmentService;
import university.services.StudentService;
import university.services.TeacherService;
import university.enums.Grade;
import university.enums.StudentStatus;
import java.util.Scanner;

public class Main {
    private static final StudentService studentService = new StudentService();
    private static final TeacherService teacherService = new TeacherService();
    private static final CourseService courseService = new CourseService(teacherService);
    private static final EnrollmentService enrollmentService = new EnrollmentService(studentService, courseService);

    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initTestData();

        while (true) {
            try {
                System.out.println("\n=== ГОЛОВНЕ МЕНЮ ===");
                System.out.println("1. Студенти");
                System.out.println("2. Викладачі");
                System.out.println("3. Курси");
                System.out.println("4. Зарахування");
                System.out.println("5. Звіти / Пошук");
                System.out.println("0. Вихід");
                System.out.print("Оберіть пункт: ");

                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1: handleStudentMenu(); break;
                    case 2: handleTeacherMenu(); break;
                    case 3: handleCourseMenu(); break;
                    case 4: handleEnrollmentMenu(); break;
                    case 5: handleReportsMenu(); break;
                    case 0:
                        System.out.println("Завершення програми. Гарного дня!");
                        return;
                    default:
                        System.out.println("Помилка: Некоректний вибір. Спробуйте ще раз.");
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Помилка: Введіть числове значення!");
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void handleStudentMenu() {
        System.out.println("\n--- Меню Студентів ---");
        System.out.println("1. Додати студента");
        System.out.println("2. Показати всіх (оригінальний порядок)");
        System.out.println("3. Показати всіх (сортування за ПІБ)");
        System.out.println("4. Оновити дані");
        System.out.println("5. Змінити статус студента");
        System.out.println("6. Видалити студента");
        System.out.print("Вибір: ");

        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                System.out.print("ПІБ: "); String name = scanner.nextLine();
                System.out.print("Email: "); String email = scanner.nextLine();
                System.out.print("Рік навчання: "); int year = Integer.parseInt(scanner.nextLine());
                studentService.addStudent(name, email, year);
                break;
            case 2:
                studentService.showAllStudents();
                break;
            case 3:
                studentService.showStudentsSortedByName();
                break;
            case 4:
                System.out.print("ID студента для оновлення: "); int id = Integer.parseInt(scanner.nextLine());
                System.out.print("Нове ПІБ: "); String newName = scanner.nextLine();
                System.out.print("Новий Email: "); String newEmail = scanner.nextLine();
                System.out.print("Новий рік навчання: "); int newYear = Integer.parseInt(scanner.nextLine());
                studentService.updateStudent(id, newName, newEmail, newYear);
                break;
            case 5:
                System.out.print("ID студента: "); int stId = Integer.parseInt(scanner.nextLine());
                System.out.println("Оберіть статус (1 - ACTIVE, 2 - ON_LEAVE, 3 - EXPELLED, 4 - GRADUATED):");
                int stChoice = Integer.parseInt(scanner.nextLine());
                StudentStatus status = StudentStatus.ACTIVE;
                if (stChoice == 2) status = StudentStatus.ON_LEAVE;
                if (stChoice == 3) status = StudentStatus.EXPELLED;
                if (stChoice == 4) status = StudentStatus.GRADUATED;
                studentService.changeStatus(stId, status);
                break;
            case 6:
                System.out.print("ID студента для видалення: "); int delId = Integer.parseInt(scanner.nextLine());
                studentService.deleteStudent(delId);
                break;
            default:
                System.out.println("Невірний вибір.");
        }
    }

    private static void handleTeacherMenu() {
        System.out.println("\n--- Меню Викладачів ---");
        System.out.println("1. Додати викладача");
        System.out.println("2. Показати всіх");
        System.out.print("Вибір: ");

        int choice = Integer.parseInt(scanner.nextLine());

        if (choice == 1) {
            System.out.print("ПІБ: "); String name = scanner.nextLine();
            System.out.print("Email: "); String email = scanner.nextLine();
            System.out.println("Посада (1 - ASSISTANT, 2 - LECTURER, 3 - PROFESSOR): ");

            int posChoice = Integer.parseInt(scanner.nextLine());

            TeacherPosition pos = TeacherPosition.ASSISTANT;
            if (posChoice == 2) pos = TeacherPosition.LECTURER;
            if (posChoice == 3) pos = TeacherPosition.PROFESSOR;

            teacherService.addTeacher(name, email, pos);
        }
        else if (choice == 2) {
            teacherService.showAllTeachers();
        }
    }

    private static void handleCourseMenu() {
        System.out.println("\n--- Меню Курсів ---");
        System.out.println("1. Створити курс");
        System.out.println("2. Показати всі");
        System.out.print("Вибір: ");

        int choice = Integer.parseInt(scanner.nextLine());

        if (choice == 1) {
            System.out.print("Назва курсу: "); String title = scanner.nextLine();
            System.out.print("Кількість кредитів: "); int credits = Integer.parseInt(scanner.nextLine());
            System.out.print("ID викладача: "); int teacherId = Integer.parseInt(scanner.nextLine());
            courseService.addCourse(title, credits, teacherId);
        }
        else if (choice == 2) {
            courseService.showAllCourses();
        }
    }

    private static void handleEnrollmentMenu() {
        System.out.println("\n--- Меню Зарахування ---");
        System.out.println("1. Зарахувати студента на курс");
        System.out.println("2. Поставити оцінку за курс");
        System.out.println("3. Позначити оплату");
        System.out.println("4. Вивести транскрипт студента (та GPA)");
        System.out.print("Вибір: ");

        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                System.out.print("ID студента: "); int sId = Integer.parseInt(scanner.nextLine());
                System.out.print("ID курсу: "); int cId = Integer.parseInt(scanner.nextLine());
                System.out.print("Семестр (наприклад, 2026-Осінь): "); String sem = scanner.nextLine();
                enrollmentService.enrollStudent(sId, cId, sem);
                break;
            case 2:
                System.out.print("ID студента: "); int sId2 = Integer.parseInt(scanner.nextLine());
                System.out.print("ID курсу: "); int cId2 = Integer.parseInt(scanner.nextLine());
                System.out.print("Оцінка (A, B, C, D, F, NA): "); String gStr = scanner.nextLine().toUpperCase();
                try {
                    Grade grade = Grade.valueOf(gStr);
                    enrollmentService.setGrade(sId2, cId2, grade);
                }
                catch (IllegalArgumentException e) {
                    System.out.println("Помилка: такої оцінки не існує.");
                }
                break;
            case 3:
                System.out.print("ID студента: "); int sId3 = Integer.parseInt(scanner.nextLine());
                System.out.print("ID курсу: "); int cId3 = Integer.parseInt(scanner.nextLine());
                enrollmentService.markAsPaid(sId3, cId3);
                break;
            case 4:
                System.out.print("ID студента: "); int sId4 = Integer.parseInt(scanner.nextLine());
                enrollmentService.showStudentTranscript(sId4);
                break;
            default:
                System.out.println("Невірний вибір.");
        }
    }

    private static void handleReportsMenu() {
        System.out.println("\n--- Меню Звітів та Пошуку ---");
        System.out.println("1. Пошук студента за частиною ПІБ або Email");
        System.out.println("2. Список студентів з неоплаченими курсами");
        System.out.print("Вибір: ");

        int choice = Integer.parseInt(scanner.nextLine());

        if (choice == 1) {
            System.out.print("Введіть запит для пошуку: ");
            String query = scanner.nextLine();
            studentService.searchStudents(query);
        } else if (choice == 2) {
            enrollmentService.showUnpaidEnrollments();
        } else {
            System.out.println("Невірний вибір.");
        }
    }

    private static void initTestData() {
        studentService.addStudent("Антонов Антон", "anton@test.com", 1);
        studentService.addStudent("Борисов Борис", "boris@test.com", 2);
        studentService.addStudent("Сергій Сергій", "ser@test.com", 3);
        teacherService.addTeacher("Дмитро Олександрович", "dmitro@univ.com", TeacherPosition.PROFESSOR);
        courseService.addCourse("Вступ до Java", 5, 1);
        courseService.addCourse("Math", 4, 1);

        enrollmentService.enrollStudent(1, 1, "2026-1");
        enrollmentService.enrollStudent(1, 2, "2026-1");
        enrollmentService.setGrade(1, 1, Grade.A);
        enrollmentService.setGrade(1, 2, Grade.B);
    }
}
