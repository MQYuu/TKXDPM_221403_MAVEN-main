package student_app.usecase.dto;

import java.util.Date;

public class AddStudentInputDTO {

    protected String hoTen;
    protected String nganh;
    protected Date ngaySinh;
    protected String diaChi;
    private double diemJava;
    private double diemCss;
    private double diemHtml;

    private double diemMarketing;
    private double diemSales;

    private AddStudentInputDTO(String hoTen, String nganh, Date ngaySinh, String diaChi) {
        this.hoTen = hoTen;
        this.nganh = nganh;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
    }

    public AddStudentInputDTO(String hoTen, String nganh, Date ngaySinh, String diaChi, double diemJava, double diemCss,
            double diemHtml) {
        this(hoTen, nganh, ngaySinh, diaChi);
        this.diemJava = diemJava;
        this.diemCss = diemCss;
        this.diemHtml = diemHtml;

    }

    public AddStudentInputDTO(String hoTen, String nganh, Date ngaySinh, String diaChi, double diemMarketing,
            double diemSales) {
        this(hoTen, nganh, ngaySinh, diaChi);
        this.diemMarketing = diemMarketing;
        this.diemSales = diemSales;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getNganh() {
        return nganh;
    }

    public void setNganh(String nganh) {
        this.nganh = nganh;
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

    public double getDiemJava() {
        return diemJava;
    }

    public void setDiemJava(double diemJava) {
        this.diemJava = diemJava;
    }

    public double getDiemCss() {
        return diemCss;
    }

    public void setDiemCss(double diemCss) {
        this.diemCss = diemCss;
    }

    public double getDiemHtml() {
        return diemHtml;
    }

    public void setDiemHtml(double diemHtml) {
        this.diemHtml = diemHtml;
    }

    public double getDiemMarketing() {
        return diemMarketing;
    }

    public void setDiemMarketing(double diemMarketing) {
        this.diemMarketing = diemMarketing;
    }

    public double getDiemSales() {
        return diemSales;
    }

    public void setDiemSales(double diemSales) {
        this.diemSales = diemSales;
    }

    // diem
    // java, css, html
    // marketing, sales

    

}
