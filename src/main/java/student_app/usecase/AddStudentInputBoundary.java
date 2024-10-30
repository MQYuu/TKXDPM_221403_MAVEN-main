package student_app.usecase;

import student_app.usecase.dto.AddStudentInputDTO;

public interface AddStudentInputBoundary {

    void execute(AddStudentInputDTO studentInputDTO);

}
