package book.usecase;

import book.ui.Book;

class BookEdit {
    public void editBook(Book book, String publisher, double unitPrice, int quantity, String type) {
        book.setPublisher(publisher);
        book.setUnitPrice(unitPrice);
        book.setQuantity(quantity);
        book.setType(type);
    }
}
