package student_app.usecase;

import student_app.usecase.dto.AddStudentOutputDTO;

public interface AddStudentOutputBoundary {

    void present(int newStudentId);

    void present(AddStudentOutputDTO addStudentOutputDTO);

}
