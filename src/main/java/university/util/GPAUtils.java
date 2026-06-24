package university.util;
import university.entities.Enrollment;
import university.entities.Student;
import university.enums.Grade;

public class GPAUtils {
    public static double calculateGPA(Enrollment[] studentEnrollments, int count) {
        if (count == 0) return 0.0;

        double totalPoints = 0;
        int gradedCoursesCount = 0;

        for (int i = 0; i < count; i++) {
            if (studentEnrollments[i].getGrade() != Grade.NA) {
                totalPoints += studentEnrollments[i].getGrade().getGpaValue();
                gradedCoursesCount++;
            }
        }
        return gradedCoursesCount == 0 ? 0.0 : totalPoints / gradedCoursesCount;
    }

    // bubble sort by name
    public static void sortStudentsByName(Student[] students, int size) {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (students[j].getName().compareToIgnoreCase(students[j + 1].getName()) > 0) {
                    Student temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                }
            }
        }
    }
}
