package book.usecase;

public class BookDelete {
    private BookUsecase bookUsecase;

    public BookDelete(BookUsecase bookUsecase) {
        this.bookUsecase = bookUsecase;
    }

    public void execute(String bookId) {
        bookUsecase.deleteBook(bookId);
    }
}
