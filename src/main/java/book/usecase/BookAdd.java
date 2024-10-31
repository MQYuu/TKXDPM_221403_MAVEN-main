package book.usecase;

import book.ui.Book;

public class BookAdd {
    private BookUsecase bookUsecase;

    public BookAdd(BookUsecase bookUsecase) {
        this.bookUsecase = bookUsecase;
    }

    public void execute(Book book) {
        bookUsecase.addBook(book);
    }
}


