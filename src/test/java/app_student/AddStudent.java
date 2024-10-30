package app_student;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import student_app.database.AddStudentDAOMemory;
import student_app.ui.AddStudentPresenter;
import student_app.usecase.AddStudentInputBoundary;
import student_app.usecase.AddStudentUseCase;
import student_app.usecase.dto.AddStudentInputDTO;
import student_app.usecase.dto.AddStudentOutputDTO;

public class AddStudent {


    @Test
    public void testAddStudent() {

        AddStudentDAOMemory addStudentDAOMemory = new AddStudentDAOMemory();
        AddStudentPresenter addStudentPresenter = new AddStudentPresenter();
        //viet test case
        AddStudentInputBoundary addStudentInputBoundary = null;
        addStudentInputBoundary = new AddStudentUseCase(addStudentDAOMemory, 
        addStudentPresenter);

        addStudentInputBoundary.execute(getMockStudent());

        AddStudentOutputDTO addStudentOutputDTO = addStudentPresenter.getAddStudentOutputDTO();

        //int newStudentId = addStudentPresenterId.getNewStudentId();


        //System.out.println("Ma SV: " + newStudentId);
        assertEquals(getMockStudent().getHoTen(), addStudentOutputDTO.getHoTen());
        //.....
    }

    private AddStudentInputDTO getMockStudent(){
        AddStudentInputDTO student1 = null;
        Calendar calendar1 = Calendar.getInstance();
        
        // Đặt ngày, tháng, năm
        calendar1.set(Calendar.YEAR, 1984);
        calendar1.set(Calendar.MONTH, Calendar.NOVEMBER); // Tháng 11 (0 là tháng 1)
        calendar1.set(Calendar.DAY_OF_MONTH, 13);
        
        // Tạo đối tượng Date từ Calendar
        Date date1 = calendar1.getTime();

        //new PhanMemStudent("Nguyen Van A", date1, "HCM", 5, 5, 5)
         student1 = new AddStudentInputDTO("Nguyen Van A", 
        "Kinh Te", date1, "TPHCM",
        9, 9);

        return student1;
    }

}
