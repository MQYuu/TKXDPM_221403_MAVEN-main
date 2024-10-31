package book.usecase;

import java.util.ArrayList;
import book.ui.Book;
import java.util.List;

class BookSearch {
    private List<Book> books;

    public BookSearch(List<Book> books) {
        this.books = books;
    }

    public List<Book> bookSearch(String query) {
        List<Book> results = new ArrayList<>();
        // Logic to search books by query
        return results;
    }

    public Book findBookById(int id) {
            for (Book book : books) {
        if (book.getId() == id) {
            return book; // Trả về sách nếu tìm thấy
            }
        }
        return null;
    }
}


    
    
