package student_app.entity;

import java.util.Date;

public abstract class Student {
    protected int maSV;
    protected String hoTen;
    protected String nganh;
    protected Date ngaySinh;
    protected String diaChi;

    public Student(String hoTen, String nganh, Date ngaySinh, String diaChi) {
        this.hoTen = hoTen;
        this.nganh = nganh;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
    }

    // Abstract methods
    public abstract double tinhDiemTB();
    
    public String getHocLuc() {
        double diemTB = tinhDiemTB();
        if (diemTB < 5) {
            return "Yếu";
        } else if (diemTB < 6.5) {
            return "Trung bình";
        } else if (diemTB < 7.5) {
            return "Khá";
        } else if (diemTB < 9) {
            return "Giỏi";
        } else {
            return "Xuất sắc";
        }
    }

    public String getHoTen() {
        return hoTen;
    }

    public String getNganh() {
        return nganh;
    }

    public int getMaSV() {
        return maSV;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }
}