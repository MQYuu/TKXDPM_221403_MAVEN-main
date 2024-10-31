package book.usecase;

import java.util.List;
import book.ui.Book;

class BookAdd {
    public void addBook(List<Book> books, Book book) {
        books.add(book);
    }
}

