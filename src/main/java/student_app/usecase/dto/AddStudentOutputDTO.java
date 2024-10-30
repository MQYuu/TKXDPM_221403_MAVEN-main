package student_app.usecase.dto;

import java.util.Date;

public class AddStudentOutputDTO {

    protected int maSV;
    protected String hoTen;
    protected Date ngaySinh;
    protected String diaChi;
    protected String nganh;
    
    protected double diemTB;

    protected String hocLuc;

    public AddStudentOutputDTO(int maSV, String hoTen, String nganh, Date ngaySinh, String diaChi, double diemTB,
            String hocLuc) {
        this.maSV = maSV;
        this.hoTen = hoTen;
        this.nganh = nganh;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.diemTB = diemTB;
        this.hocLuc = hocLuc;
    }

    public int getMaSV() {
        return maSV;
    }

    public void setMaSV(int maSV) {
        this.maSV = maSV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

   

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public double getDiemTB() {
        return diemTB;
    }

    public void setDiemTB(double diemTB) {
        this.diemTB = diemTB;
    }

    public String getHocLuc() {
        return hocLuc;
    }

    public void setHocLuc(String hocLuc) {
        this.hocLuc = hocLuc;
    }


    public String getNganh() {
        return nganh;
    }
    

    
}
