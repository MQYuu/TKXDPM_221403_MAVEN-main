package book.ui;

import java.util.Date;

class Book {
    int id; // Mã sách
    String title;
    String publisher;
    String type;  // "Giáo Khoa" hoặc "Tham Khảo"
    double unitPrice;
    int quantity;
    Date dateImported; // Ngày nhập
    String status; // Tình trạng (chỉ cho sách giáo khoa)
    double tax; // Thuế (chỉ cho sách tham khảo)

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

