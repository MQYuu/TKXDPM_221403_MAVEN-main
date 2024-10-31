package book.usecase;

import java.util.ArrayList;
import java.util.Date;
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

    public void addBook(Book book) {
        bookAdd.addBook(books, book);
    }

    public void editBook(Book book, String publisher, double unitPrice, int quantity, String type) {
        bookEdit.editBook(book, publisher, unitPrice, quantity, type);
    }

    public void deleteBook(int bookId) {
        bookDelete.deleteBook(books, bookId);
    }

    public Book findBookById(int id) {
        return bookSearch.findBookById(id);
    }

    // public void printBook(Book book) {
    //     bookPrint.printBook(book);
    // }

    public int getTotalBooks() {
        return totalBook.getTotal(books);
    }

    // public double getAveragePrice() {
    //     return averageBook.calculateAveragePrice(books);
    // }

    public void exportBooks() {
        exportBook.export(books);
    }
}
