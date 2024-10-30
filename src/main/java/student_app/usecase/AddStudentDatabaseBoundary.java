package student_app.usecase;

import student_app.entity.Student;

public interface AddStudentDatabaseBoundary {

    int addStudent(Student student);

    Student findStudentById(int newStudentId);

}
