package book.usecase;

import java.awt.print.Book;
import java.util.List;

public class BookPrint {
    private BookUsecase bookUsecase;
    private BookOutputBoundary outputBoundary;

    public BookPrint(BookUsecase bookUsecase, BookOutputBoundary outputBoundary) {
        this.bookUsecase = bookUsecase;
        this.outputBoundary = outputBoundary;
    }

    public void execute() {
        List<Book> books = bookUsecase.getBooks();
        outputBoundary.displayBooks(books);
    }
}

