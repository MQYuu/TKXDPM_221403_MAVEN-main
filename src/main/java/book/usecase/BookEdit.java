package book.usecase;

import book.ui.Book;

public class BookEdit {
    private BookUsecase bookUsecase;

    public BookEdit(BookUsecase bookUsecase) {
        this.bookUsecase = bookUsecase;
    }

    public void execute(String bookId, Book updatedBook) {
        bookUsecase.editBook(bookId, updatedBook);
    }
}
