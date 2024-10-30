package student_app.ui;

import java.util.Calendar;
import java.util.Date;

import student_app.usecase.dto.AddStudentOutputDTO;

public class AppStudentMain {
    public static void main(String[] args) {

        StudentDetailsForm studentDetailsForm = new StudentDetailsForm(getMockStudent());
        
    }

    private static AddStudentOutputDTO getMockStudent(){
        AddStudentOutputDTO student1 = null;
        Calendar calendar1 = Calendar.getInstance();
        
        // Đặt ngày, tháng, năm
        calendar1.set(Calendar.YEAR, 1984);
        calendar1.set(Calendar.MONTH, Calendar.NOVEMBER); // Tháng 11 (0 là tháng 1)
        calendar1.set(Calendar.DAY_OF_MONTH, 13);
        
        // Tạo đối tượng Date từ Calendar
        Date date1 = calendar1.getTime();

        //new PhanMemStudent("Nguyen Van A", date1, "HCM", 5, 5, 5)
         student1 = new AddStudentOutputDTO(11,"Nguyen Van A", 
        "Kinh Te", date1, "TPHCM",
        9, "Gioi");

        return student1;
    }



}
