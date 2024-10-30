package book.ui;

import java.util.Date;

public class Book {
    int id; // Mã sách
    String title;
    String publisher;
    String type;  // "Giáo Khoa" hoặc "Tham Khảo"
    double unitPrice;
    int quantity;
    Date dateImported; // Ngày nhập
    String status; // Tình trạng (chỉ cho sách giáo khoa)
    double tax; // Thuế (chỉ cho sách tham khảo)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDateImported() {
        return dateImported;
    }

    public void setDateImported(Date dateImported) {
        this.dateImported = dateImported;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public Book(int id, String title, String publisher, String type, double unitPrice, int quantity, Date dateImported, String status, double tax) {
        this.id = id;
        this.title = title;
        this.publisher = publisher;
        this.type = type;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.dateImported = dateImported;
        this.status = status;
        this.tax = tax;
    }

    public double getTotalPrice() {
        return unitPrice * quantity;
    }
}

