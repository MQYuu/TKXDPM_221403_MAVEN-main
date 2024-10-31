package book.entity;

import book.ui.Book;
import java.util.List;

public class AverageBook {

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
