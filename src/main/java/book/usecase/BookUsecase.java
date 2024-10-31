package book.usecase;

import java.util.ArrayList;
import java.util.List;

import book.ui.Book;

public class BookUsecase {
    private List<Book> books;
    private BookAdd bookAdd;
    private BookEdit bookEdit;
    private BookDelete bookDelete;
    private BookSearch bookSearch;
    private BookPrint bookPrint;
    private TotalBook totalBook;
    private AverageBook averageBook;
    private ExportBook exportBook;

    public BookUsecase() {
        this.books = new ArrayList<>();
        this.bookAdd = new BookAdd(this);
        this.bookEdit = new BookEdit(this);
        this.bookDelete = new BookDelete(this);
        this.bookSearch = new BookSearch(this);
        this.bookPrint = new BookPrint(this, null);
        this.totalBook = new TotalBook(this);
        this.averageBook = new AverageBook(this);
        this.exportBook = new ExportBook(this);
    }

    public Book findBookById(int bookId) {
        return bookSearch.findBookById(bookId);
    }
}

