package book.entity;

import java.util.List;
import book.ui.Book;

public class TotalBook {
    // Phương thức tính tổng thành tiền cho một sách
    public static double calculateTotalPrice(Book book) {
        if (book == null) return 0;  // Kiểm tra null cho book

        double totalPrice = 0;
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
        if (books == null) return 0;

        double total = 0;
        for (Book book : books) {
            total += calculateTotalPrice(book);
        }

        return total;
    }
}