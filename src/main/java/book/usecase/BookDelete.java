package book.usecase;

import java.util.List;

import book.ui.Book;

class BookDelete {
    public void deleteBook(List<Book> books, int bookId) {
        books.removeIf(book -> book.getId() == bookId);
    }
}