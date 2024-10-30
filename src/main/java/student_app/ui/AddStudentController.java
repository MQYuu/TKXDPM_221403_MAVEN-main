package student_app.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import student_app.usecase.AddStudentInputBoundary;
import student_app.usecase.dto.AddStudentInputDTO;

public class AddStudentController {
    private AddStudentInputBoundary 
    addStudentInputBoundary = null;


    public AddStudentController(AddStudentInputBoundary addStudentInputBoundary) {
        this.addStudentInputBoundary = addStudentInputBoundary;
    }


    public void addStudentPhanMem(String name, String  dob, String address, 
    String major, 
           String  javaScore, String cssScore, String htmlScore){

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

            AddStudentInputDTO addStudentInputDTO = null;
            try {
                addStudentInputDTO = new AddStudentInputDTO(name, address, simpleDateFormat.parse(dob), major, 
                
                Double.parseDouble(javaScore), 
                Double.parseDouble(cssScore), Double.parseDouble(htmlScore));
            } catch (NumberFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            addStudentInputBoundary.execute(addStudentInputDTO);
           }

}
