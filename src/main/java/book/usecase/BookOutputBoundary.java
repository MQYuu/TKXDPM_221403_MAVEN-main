package book.usecase;

import java.util.List;

import book.ui.Book;

public interface BookOutputBoundary {
    void displayBooks(List<java.awt.print.Book> books);
    void displayMessage(String message);
}
