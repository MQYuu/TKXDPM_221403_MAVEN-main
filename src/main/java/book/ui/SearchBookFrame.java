package book.ui;

import javax.swing.*;
import java.util.ArrayList;

public class SearchBookFrame extends JFrame {
    private ArrayList<Book> books;

    public SearchBookFrame(ArrayList<Book> books) {
        this.books = books;

        setTitle("Tìm kiếm sách");
        setSize(300, 150);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        JLabel label = new JLabel("Nhập ID sách:");
        JTextField textField = new JTextField(10);
        JButton searchButton = new JButton("Tìm kiếm");

        // ActionListener for the search button
        searchButton.addActionListener(e -> {
            String input = textField.getText().trim();
            if (!input.isEmpty()) {
                try {
                    int id = Integer.parseInt(input);
                    Book book = findBookById(id);
                    if (book != null) {
                        String message = "ID: " + book.getId() +
                                         "\nNgày nhập: " + book.getDateImported() +
                                         "\nNhà xuất bản: " + book.getPublisher() +
                                         "\nLoại: " + book.getType() +
                                         "\nĐơn giá: " + book.getUnitPrice() +
                                         "\nSố lượng: " + book.getQuantity();
                        JOptionPane.showMessageDialog(this, message);
                    } else {
                        JOptionPane.showMessageDialog(this, "Không tìm thấy sách với ID: " + id);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "ID không hợp lệ. Vui lòng nhập số.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập ID sách.");
            }
        });

        panel.add(label);
        panel.add(textField);
        panel.add(searchButton);
        add(panel);
    }

    // Method to find a book by its ID
    private Book findBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) {
                return book;  // Return the book if found
            }
        }
        return null;  // Return null if no book found with the given ID
    }
}
