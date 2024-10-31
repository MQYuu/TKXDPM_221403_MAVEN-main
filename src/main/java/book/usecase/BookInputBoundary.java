package book.usecase;

import book.ui.Book;

public interface BookInputBoundary {
    void addBook(Book book);
    void editBook(String bookId, Book book);
    void deleteBook(String bookId);
    Book searchBook(String bookId);
}

