package book.usecase;

import java.util.ArrayList;

import book.ui.Book;

public class BookSearch {
    private ArrayList<Book> books;

    public BookSearch(ArrayList<Book> books) {
        this.books = books;
    }

    public BookSearch(BookUsecase bookUsecase) {
        //TODO Auto-generated constructor stub
    }

    public Book findBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book; // Trả về sách nếu tìm thấy
            }
        }
        return null; // Trả về null nếu không tìm thấy sách
    }
}
    
    
