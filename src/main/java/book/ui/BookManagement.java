package book.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

import book.database.BookDatabase;
import book.entity.AverageBook;
import book.entity.TotalBook;

public class BookManagement extends JFrame {
    private ArrayList<Book> books = new ArrayList<>();
    private JTable bookTable;
    private DefaultTableModel tableModel;

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

        btnAdd.addActionListener(e -> {
            new AddBookFrame(this).setVisible(true);
            loadBooksFromDatabase(); // Tải lại dữ liệu sau khi thêm
        });
        btnEdit.addActionListener(e -> {
            new EditBookFrame(this).setVisible(true);
            loadBooksFromDatabase(); // Tải lại dữ liệu sau khi sửa
        });
        btnDelete.addActionListener(e -> {
            new DeleteBookFrame(this).setVisible(true);
            loadBooksFromDatabase(); // Tải lại dữ liệu sau khi xóa
        
        });
        btnSearch.addActionListener(e -> {
            new SearchBookFrame(books).setVisible(true); // Pass books to the SearchBookFrame for searching
        });
        
        btnPrint.addActionListener(e -> printBooks());
        btnTotalPrice.addActionListener(e -> calculateTotalPrice());
        btnAvgPrice.addActionListener(e -> calculateAveragePrice());
        btnPublisherX.addActionListener(e -> displayPublisherXBooks());

        add(panel);

        String[] columnNames = {"ID", "Ngày nhập", "NXB", "Loại", "Đơn giá", "Số lượng", "Tình trạng", "Thuế"};
        tableModel = new DefaultTableModel(columnNames, 0);
        bookTable = new JTable(tableModel);
        
        // Đặt bảng vào bên trong JScrollPane
        JScrollPane scrollPane = new JScrollPane(bookTable);
        scrollPane.setPreferredSize(new Dimension(750, 300));

        // Thêm bảng và panel nút vào form
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(panel, BorderLayout.SOUTH);

        loadBooksFromDatabase();
    }

    private void loadBooksFromDatabase() {
        books = BookDatabase.getAllBooks(); // Lấy dữ liệu từ cơ sở dữ liệu
        updateTable();
    }

    private void updateTable() {
        tableModel.setRowCount(0); // Xóa các hàng cũ trong bảng
        for (Book book : books) {
            Object[] rowData = {
                book.getId(),
                book.getDateImported(),
                book.getPublisher(),
                book.getType(),
                book.getUnitPrice(),
                book.getQuantity(),
                book.getStatus(),
                book.getTax()
            };
            tableModel.addRow(rowData);
        }
    }

    public void updateBook(Book book) {
        BookDatabase.updateBookInDatabase(book); // Phương thức cập nhật trong BookDatabase
        loadBooksFromDatabase(); // Tải lại dữ liệu sau khi cập nhật
    }
    
    public ArrayList<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        if (BookDatabase.insertBook(book)) {
            books.add(book);
            loadBooksFromDatabase(); // Tải lại dữ liệu sau khi thêm
            JOptionPane.showMessageDialog(this, "Đã thêm sách vào cơ sở dữ liệu thành công.");
        } else {
            JOptionPane.showMessageDialog(this, "Không thể thêm sách vào cơ sở dữ liệu.");
        }
    }
    
    public Window SearchBookFrame(BookManagement bookManagement) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'SearchBookFrame'");
    }
    
    public boolean deleteBook(int id) {
        if (BookDatabase.deleteBook(id)) {
            loadBooksFromDatabase(); // Tải lại dữ liệu sau khi xóa
            return true;
        }
        return false;
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
