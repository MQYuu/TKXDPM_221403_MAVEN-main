package book.ui;

import javax.swing.*;
import java.awt.*;

public class DeleteBookFrame extends JFrame {
    private BookManagement bookManagement;

    public DeleteBookFrame(BookManagement bookManagement) {
        this.bookManagement = bookManagement;
        setTitle("Xóa Sách");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        JTextField txtId = new JTextField(15);
        JButton btnDelete = new JButton("Xóa");

        // Thêm thành phần vào giao diện
        add(new JLabel("Nhập ID sách cần xóa:"));
        add(txtId);
        add(btnDelete);

        // Thiết lập sự kiện cho nút Xóa
        btnDelete.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                boolean deleted = bookManagement.deleteBook(id);
                if (deleted) {
                    JOptionPane.showMessageDialog(this, "Đã xóa sách thành công.");
                } else {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy sách với ID đã nhập.");
                }
                dispose(); // Đóng cửa sổ
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập ID hợp lệ.");
            }
        });
    }
}

