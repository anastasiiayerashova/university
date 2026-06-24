package university.entities;

public class Course {
    private int id;
    private String title;
    private int credits;
    private Teacher teacher;     // Агрегація, зв'язок з об'єктом Teacher

    public Course(int id, String title, int credits, Teacher teacher) {
        if (credits <= 0) {
            throw new IllegalArgumentException("Помилка: кількість кредитів має бути більшою за 0.");
        }
        this.id = id;
        this.title = title;
        this.credits = credits;
        this.teacher = teacher;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public String toString() {
        return "ID курсу: " + id + ", Назва: " + title + ", Кредити: " + credits +
                ", Викладач: " + (teacher != null ? teacher.getName() : "Не призначено");
    }
}
