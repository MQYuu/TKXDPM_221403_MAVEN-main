package student_app.usecase;

import java.util.Date;

import student_app.entity.KinhTeStudent;
import student_app.entity.PhanMemStudent;
import student_app.entity.Student;
import student_app.usecase.dto.AddStudentInputDTO;
import student_app.usecase.dto.AddStudentOutputDTO;

public class AddStudentUseCase implements AddStudentInputBoundary{
    private AddStudentDatabaseBoundary addStudentDBB = null;
    private AddStudentOutputBoundary addStudentOB = null;


    public AddStudentUseCase(AddStudentDatabaseBoundary addStudentDBB, AddStudentOutputBoundary addStudentOB) {
        this.addStudentDBB = addStudentDBB;
        this.addStudentOB = addStudentOB;
    }

    @Override
    public void execute(AddStudentInputDTO studentInputDTO) {
        Student student = null;
        //nhan thong tin sv

        //kiem tra -  validate
        //HOP LE
        String hotTen = studentInputDTO.getHoTen();
        Date ngaySinh = studentInputDTO.getNgaySinh();
        String diaChi = studentInputDTO.getDiaChi();

        if(studentInputDTO.getNganh().equals("PM")){
            student = new PhanMemStudent(hotTen, ngaySinh, diaChi, 
            studentInputDTO.getDiemJava(), studentInputDTO.getDiemCss(), 
            studentInputDTO.getDiemHtml());
        }else{
            student = new KinhTeStudent(hotTen, ngaySinh, diaChi, 
            studentInputDTO.getDiemMarketing(), studentInputDTO.getDiemSales());
        }

        
        int newStudentId = addStudentDBB.addStudent(student);

        //addStudentOB.present(newStudentId);

        //find student by newStudentId
        Student foundStudent = addStudentDBB.findStudentById(newStudentId);

        //chuyen  foundStudent thanh AddStudentOutputDTO
        AddStudentOutputDTO addStudentOutputDTO = new 
        AddStudentOutputDTO(foundStudent.getMaSV(), foundStudent.getHoTen(), 
        foundStudent.getNganh(), foundStudent.getNgaySinh(), foundStudent.getDiaChi(), 
        foundStudent.tinhDiemTB(), foundStudent.getHocLuc());

        addStudentOB.present(addStudentOutputDTO);
        


    }

    private boolean validateNgaySinh(Date ngaySinh){

        //TO-DO
        return false;

    }

    private boolean validateScore(double diem){
        //TO-DO
        return false;
    }

}
