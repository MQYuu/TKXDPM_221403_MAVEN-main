package student_app.ui;

import student_app.usecase.AddStudentOutputBoundary;
import student_app.usecase.dto.AddStudentOutputDTO;

public class AddStudentPresenter implements AddStudentOutputBoundary{
    private int newStudentId;
    private AddStudentOutputDTO  addStudentOutputDTO= null;

    @Override
    public void present(int newStudentId) {

        this.newStudentId = newStudentId;
    }

    public int getNewStudentId() {
        return newStudentId;
    }

    @Override
    public void present(AddStudentOutputDTO addStudentOutputDTO) {
        this.addStudentOutputDTO = addStudentOutputDTO;
        StudentDetailsForm studentDetailsForm = new StudentDetailsForm(addStudentOutputDTO);
        
    }

    public AddStudentOutputDTO getAddStudentOutputDTO() {
        return addStudentOutputDTO;
    }

}
