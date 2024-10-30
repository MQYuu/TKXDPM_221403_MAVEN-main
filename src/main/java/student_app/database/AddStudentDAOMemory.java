package student_app.database;

import java.util.HashMap;
import java.util.Map;

import student_app.entity.Student;
import student_app.usecase.AddStudentDatabaseBoundary;

public class AddStudentDAOMemory implements AddStudentDatabaseBoundary{
    private Map<Integer, Student> mockDatabase = new HashMap<>();

    private int currentStudentId = 0;
    @Override
    public int addStudent(Student student) {
        mockDatabase.put(++currentStudentId, student);
        return currentStudentId;
    }

   public Student findStudentById (int newStudentId){
        Student student = null;
        student = this.mockDatabase.get(newStudentId);
        return student;
   }

}
