package university.services;
import university.entities.Course;
import university.entities.Teacher;

public class CourseService {
    private Course[] courses = new Course[100];
    private int size = 0;
    private int idCounter = 1;
    private TeacherService teacherService;

    public CourseService(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    // create
    public void addCourse(String title, int credits, int teacherId) {
        if (size >= courses.length) {
            System.out.println("Помилка: база даних курсів заповнена!");
            return;
        }
        Teacher teacher = teacherService.findById(teacherId);
        if (teacher == null) {
            System.out.println("Помилка: Викладача з ID " + teacherId + " не знайдено! Курс не створено.");
            return;
        }

        Course course = new Course(idCounter++, title, credits, teacher);
        courses[size++] = course;
        System.out.println("Курс успішно створено! (ID: " + course.getId() + ")");
    }

    // read
    public void showAllCourses() {
        if (size == 0) {
            System.out.println("Курсів у базі немає.");
            return;
        }
        for(int i = 0; i < size; i++) {
            System.out.println(courses[i]);
        }
    }

    // find by id
    public Course findById(int id) {
        for (int i = 0; i < size; i++) {
            if (courses[i].getId() == id) {
                return courses[i];
            }
        }
        return null;
    }

    // update
    public void updateCourse(int id, String newTitle, int newCredits, int newTeacherId) {
        Course course = findById(id);
        if (course == null) {
            System.out.println("Помилка: Курс з ID " + id + " не знайдено.");
            return;
        }
        Teacher newTeacher = teacherService.findById(newTeacherId);
        if (newTeacher == null) {
            System.out.println("Помилка: Викладача з ID " + newTeacherId + " не знайдено. Зміни не збережено.");
            return;
        }

        course.setTitle(newTitle);
        course.setCredits(newCredits);
        course.setTeacher(newTeacher);
        System.out.println("Дані курсу оновлено.");
    }

    //delete
    public void deleteCourse(int id) {
        int index = 1;
        for (int i = 0; i < size; i++) {
            if (courses[i].getId() == id) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("Помилка: Курс з ID " + id + " не знайдено.");
            return;
        }
        for (int i = index; i < size - 1; i++) {
            courses[i] = courses[i + 1];
        }
        courses[--size] = null;
        System.out.println("Курс видалено.");
    }

    // filter by teacher
    public void filterCoursesByTeacher(int teacherId) {
        boolean found = false;
        for (int i = 0; i < size; i++) {
            if (courses[i].getTeacher() != null && courses[i].getTeacher().getId() == teacherId) {
                System.out.println(courses[i]);
                found = true;
            }
        }
        if (!found) System.out.println("Курсів для цього викладача не знайдено.");
    }

    // filter by credits
    public void filterCoursesByCredits(int credits) {
        boolean found = false;
        for (int i = 0; i < size; i++) {
            if (courses[i].getCredits() == credits) {
                System.out.println(courses[i]);
                found = true;
            }
        }
        if (!found) System.out.println("Курсів із такою кількістю кредитів не знайдено.");
    }

    public Course[] getCourses() { return courses; }
    public int getSize() { return size; }
}
