package book.entity;

import book.ui.Book;
import java.util.List;

public class BookCalculator {

    // Phương thức tính tổng thành tiền cho sách
    public static double calculateTotalPrice(Book book) {
        if (book == null) return 0;  // Kiểm tra null cho book
        
        double totalPrice = 0;
        
        // Kiểm tra và tính toán dựa trên loại sách và tình trạng
        if ("Giáo Khoa".equals(book.getType())) {
            if ("mới".equalsIgnoreCase(book.getStatus())) {
                totalPrice = book.getQuantity() * book.getUnitPrice();
            } else if ("cũ".equalsIgnoreCase(book.getStatus())) {
                totalPrice = book.getQuantity() * book.getUnitPrice() * 0.5;
            }
        } else if ("Tham Khảo".equals(book.getType())) {
            totalPrice = book.getQuantity() * book.getUnitPrice() + book.getTax();
        }
        
        return totalPrice;
    }

    // Phương thức tính tổng thành tiền cho tất cả sách
    public static double calculateTotalPriceForAllBooks(List<Book> books) {
        if (books == null) return 0;  // Kiểm tra null cho danh sách sách
        
        double total = 0;
        for (Book book : books) {
            total += calculateTotalPrice(book);
        }
        
        return total;
    }

    // Phương thức tính trung bình cộng đơn giá cho sách tham khảo
    public static double calculateAveragePriceForReferenceBooks(List<Book> books) {
        if (books == null) return 0;  // Kiểm tra null cho danh sách sách
        
        double totalPrice = 0;
        int totalQuantity = 0;
        
        for (Book book : books) {
            if ("Tham Khảo".equals(book.getType())) {
                totalPrice += book.getUnitPrice() * book.getQuantity();
                totalQuantity += book.getQuantity();
            }
        }
        
        return totalQuantity > 0 ? totalPrice / totalQuantity : 0;
    }
}
