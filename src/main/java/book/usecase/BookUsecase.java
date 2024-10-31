package book.usecase;

import java.util.ArrayList;
import java.util.List;

import book.ui.Book;

class BookUsecase {
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
        this.bookAdd = new BookAdd();
        this.bookEdit = new BookEdit();
        this.bookDelete = new BookDelete();
        this.bookSearch = new BookSearch(books);
        this.bookPrint = new BookPrint();
        this.totalBook = new TotalBook();
        this.averageBook = new AverageBook();
        this.exportBook = new ExportBook();
    }

    // Implement other methods to use the various services, like addBook, deleteBook, etc.
}
