package book.usecase;

import java.util.List;

import book.ui.Book;

class AverageBook {
    public double calculateAverage(List<Book> books) {
        double sum = 0;
        for (Book book : books) {
            sum += book.thanhTien(); // Assuming thanhTien() calculates the price
        }
        return books.isEmpty() ? 0 : sum / books.size();
    }
}