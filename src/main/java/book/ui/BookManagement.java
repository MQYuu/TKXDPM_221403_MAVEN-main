package book.ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import book.entity.AverageBook;
import book.entity.TotalBook;
import book.ui.SearchBookFrame;

public class BookManagement extends JFrame {
    private ArrayList<Book> books = new ArrayList<>();

    public BookManagement() {
        setTitle("Quản lý sách");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        JButton btnAdd = new JButton("Thêm");
        JButton btnEdit = new JButton("Sửa");
        JButton btnDelete = new JButton("Xóa");
        JButton btnPrint = new JButton("In");
        JButton btnSearch = new JButton("Tìm kiếm");
        JButton btnTotalPrice = new JButton("Tổng thành tiền");
        JButton btnAvgPrice = new JButton("TB đơn giá (Tham Khảo)");
        JButton btnPublisherX = new JButton("Sách NXB X");

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(btnAdd, gbc);
        gbc.gridx = 1;
        panel.add(btnEdit, gbc);
        gbc.gridx = 2;
        panel.add(btnDelete, gbc);
        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(btnPrint, gbc);
        gbc.gridx = 1;
        panel.add(btnSearch, gbc);
        gbc.gridx = 2;
        panel.add(btnTotalPrice, gbc);
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(btnAvgPrice, gbc);
        gbc.gridy = 3;
        panel.add(btnPublisherX, gbc);

        btnAdd.addActionListener(e -> new AddBookFrame(this).setVisible(true));
        btnEdit.addActionListener(e -> new EditBookFrame(this).setVisible(true));
        btnDelete.addActionListener(e -> new DeleteBookFrame(this).setVisible(true));
        btnSearch.addActionListener(e -> SearchBookFrame(this).setVisible(true));
        btnPrint.addActionListener(e -> printBooks());
        btnTotalPrice.addActionListener(e -> calculateTotalPrice());
        btnAvgPrice.addActionListener(e -> calculateAveragePrice());
        btnPublisherX.addActionListener(e -> displayPublisherXBooks());

        add(panel);
    }

    public Window SearchBookFrame(BookManagement bookManagement) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'SearchBookFrame'");
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
    }
    
    public boolean deleteBook(int id) {
        for (Book book : books) {
            if (book.getId() == id) { // Sử dụng thuộc tính id để tìm sách
                books.remove(book);
                return true; // Đã xóa thành công
            }
        }
        return false; // Không tìm thấy sách
    }

    private void printBooks() {
        StringBuilder result = new StringBuilder();
        for (Book book : books) {
            result.append("ID: ").append(book.getId())
                .append(", Ngày nhập: ").append(book.getDateImported())
                .append(", NXB: ").append(book.getPublisher())
                .append(", Loại: ").append(book.getType())
                .append(", Đơn giá: ").append(book.getUnitPrice())
                .append(", Số lượng: ").append(book.getQuantity())
                .append("\n");
        }
        JOptionPane.showMessageDialog(this, result.toString());
    }

    private void calculateTotalPrice() {
        double total = TotalBook.calculateTotalPriceForAllBooks(books);
        JOptionPane.showMessageDialog(this, "Tổng thành tiền tất cả sách: " + total);
    }

    private void calculateAveragePrice() {
        double averagePrice = AverageBook.calculateAveragePriceForReferenceBooks(books);
        JOptionPane.showMessageDialog(this, "Trung bình đơn giá sách Tham Khảo: " + averagePrice);
    }

    private void displayPublisherXBooks() {
        StringBuilder result = new StringBuilder();
        for (Book book : books) {
            if (book.getType().equalsIgnoreCase("Giáo Khoa") && book.getPublisher().equalsIgnoreCase("X")) {
                result.append("ID: ").append(book.getId())
                    .append(", Ngày nhập: ").append(book.getDateImported())
                    .append(", Đơn giá: ").append(book.getUnitPrice())
                    .append(", Số lượng: ").append(book.getQuantity())
                    .append("\n");
            }
        }
        JOptionPane.showMessageDialog(this, result.length() > 0 ? result.toString() : "Không có sách giáo khoa của NXB X.");
    }
}
