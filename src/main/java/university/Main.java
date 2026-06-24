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
        university.util.DataSeeder.seedData(studentService, teacherService, courseService, enrollmentService);

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
        System.out.println("4. Оновити дані студента");
        System.out.println("5. Змінити статус студента");
        System.out.println("6. Фільтр за статусом");
        System.out.println("7. Фільтр за роком навчання");
        System.out.println("8. Видалити студента");
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
                System.out.println("Оберіть статус для фільтрації (1 - ACTIVE, 2 - ON_LEAVE, 3 - EXPELLED, 4 - GRADUATED):");
                int fChoice = Integer.parseInt(scanner.nextLine());
                StudentStatus fStatus = StudentStatus.ACTIVE;
                if (fChoice == 2) fStatus = StudentStatus.ON_LEAVE;
                if (fChoice == 3) fStatus = StudentStatus.EXPELLED;
                if (fChoice == 4) fStatus = StudentStatus.GRADUATED;
                studentService.filterStudentsByStatus(fStatus);
                break;
            case 7:
                System.out.print("Введіть рік навчання для фільтрації: ");
                int fYear = Integer.parseInt(scanner.nextLine());
                studentService.filterStudentsByYear(fYear);
                break;
            case 8:
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
        System.out.println("3. Оновити дані викладача");
        System.out.println("4. Видалити викладача");
        System.out.print("Вибір: ");

        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                System.out.print("ПІБ: "); String name = scanner.nextLine();
                System.out.print("Email: "); String email = scanner.nextLine();
                System.out.println("Посада (1 - ASSISTANT, 2 - LECTURER, 3 - PROFESSOR): ");
                int posChoice = Integer.parseInt(scanner.nextLine());
                TeacherPosition pos = TeacherPosition.ASSISTANT;
                if (posChoice == 2) pos = TeacherPosition.LECTURER;
                if (posChoice == 3) pos = TeacherPosition.PROFESSOR;
                teacherService.addTeacher(name, email, pos);
                break;
            case 2:
                teacherService.showAllTeachers();
                break;
            case 3:
                System.out.print("ID викладача для оновлення: "); int tId = Integer.parseInt(scanner.nextLine());
                System.out.print("Нове ПІБ: "); String tName = scanner.nextLine();
                System.out.print("Новий Email: "); String tEmail = scanner.nextLine();
                System.out.println("Нова посада (1 - ASSISTANT, 2 - LECTURER, 3 - PROFESSOR): ");
                int tPosChoice = Integer.parseInt(scanner.nextLine());
                TeacherPosition tPos = TeacherPosition.ASSISTANT;
                if (tPosChoice == 2) tPos = TeacherPosition.LECTURER;
                if (tPosChoice == 3) tPos = TeacherPosition.PROFESSOR;
                teacherService.updateTeacher(tId, tName, tEmail, tPos);
                break;
            case 4:
                System.out.print("ID викладача для видалення: "); int tDelId = Integer.parseInt(scanner.nextLine());
                teacherService.deleteTeacher(tDelId);
                break;
            default:
                System.out.println("Невірний вибір.");
        }
    }

    private static void handleCourseMenu() {
        System.out.println("\n--- Меню Курсів ---");
        System.out.println("1. Створити курс");
        System.out.println("2. Показати всі");
        System.out.println("3. Фільтр курсів за викладачем");
        System.out.println("4. Фільтр курсів за кредитами");
        System.out.println("5. Оновити дані курсу");
        System.out.println("6. Видалити курс");
        System.out.print("Вибір: ");

        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                System.out.print("Назва курсу: "); String title = scanner.nextLine();
                System.out.print("Кредити: "); int credits = Integer.parseInt(scanner.nextLine());
                System.out.print("ID викладача: "); int teacherId = Integer.parseInt(scanner.nextLine());
                courseService.addCourse(title, credits, teacherId);
                break;
            case 2:
                courseService.showAllCourses();
                break;
            case 3:
                System.out.print("Введіть ID викладача: ");
                int tId = Integer.parseInt(scanner.nextLine());
                courseService.filterCoursesByTeacher(tId);
                break;
            case 4:
                System.out.print("Введіть кількість кредитів: ");
                int cr = Integer.parseInt(scanner.nextLine());
                courseService.filterCoursesByCredits(cr);
                break;
            case 5:
                System.out.print("ID курсу для оновлення: "); int cId = Integer.parseInt(scanner.nextLine());
                System.out.print("Нова назва: "); String cTitle = scanner.nextLine();
                System.out.print("Нова кількість кредитів: "); int cCr = Integer.parseInt(scanner.nextLine());
                System.out.print("ID нового викладача: "); int cTId = Integer.parseInt(scanner.nextLine());
                courseService.updateCourse(cId, cTitle, cCr, cTId);
                break;
            case 6:
                System.out.print("ID курсу для видалення: "); int cDelId = Integer.parseInt(scanner.nextLine());
                courseService.deleteCourse(cDelId);
                break;
            default:
                System.out.println("Невірний вибір.");
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
}
