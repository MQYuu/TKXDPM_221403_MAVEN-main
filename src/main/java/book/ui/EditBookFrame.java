package book.ui;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import book.database.BookDatabase;

public class EditBookFrame extends JFrame {
    private BookManagement bookManagement;

    public EditBookFrame(BookManagement bookManagement) {
        this.bookManagement = bookManagement;
        setTitle("Sửa Sách");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(10, 2, 10, 10)); // Tăng số hàng lên 10 để đủ chỗ cho mọi trường

        JTextField txtId = new JTextField();
        JTextField txtDate = new JTextField(); // Ngày nhập
        JTextField txtUnitPrice = new JTextField();
        JTextField txtQuantity = new JTextField();
        JTextField txtPublisher = new JTextField();
        JTextField txtStatus = new JTextField(); // Tình trạng (cho sách giáo khoa)
        JTextField txtTax = new JTextField(); // Thuế (cho sách tham khảo)
        JComboBox<String> cbType = new JComboBox<>(new String[]{"Giáo Khoa", "Tham Khảo"});
        JButton btnEdit = new JButton("Sửa");

        // Thiết lập sự kiện cho nút Sửa
        btnEdit.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                Book bookToEdit = findBookById(id);

                if (bookToEdit != null) {
                    bookToEdit.setDateImported(parseDate(txtDate.getText())); // Sử dụng java.sql.Date
                    bookToEdit.setUnitPrice(Double.parseDouble(txtUnitPrice.getText()));
                    bookToEdit.setQuantity(Integer.parseInt(txtQuantity.getText()));
                    bookToEdit.setPublisher(txtPublisher.getText());
                    
                    // Cập nhật thông tin cho sách giáo khoa hoặc sách tham khảo
                    if (bookToEdit.getType().equals("Giáo Khoa")) {
                        bookToEdit.setStatus(txtStatus.getText());
                    } else if (bookToEdit.getType().equals("Tham Khảo")) {
                        bookToEdit.setTax(Double.parseDouble(txtTax.getText()));
                    }

                    // Cập nhật sách trong cơ sở dữ liệu
                    bookManagement.updateBook(bookToEdit); // phương thức này trong BookManagement

                    JOptionPane.showMessageDialog(this, "Đã sửa sách thành công.");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy sách với ID: " + id);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin hợp lệ.");
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập định dạng ngày hợp lệ (dd/MM/yyyy).");
            }
        });

        // Tìm kiếm thông tin sách theo ID
        txtId.addActionListener(e -> {
            int id;
            try {
                id = Integer.parseInt(txtId.getText());
                Book book = BookDatabase.getBookById(id); // Gọi phương thức để lấy sách theo ID từ database
                if (book != null) {
                    txtDate.setText(new SimpleDateFormat("dd/MM/yyyy").format(book.getDateImported()));
                    txtUnitPrice.setText(String.valueOf(book.getUnitPrice()));
                    txtQuantity.setText(String.valueOf(book.getQuantity()));
                    txtPublisher.setText(book.getPublisher());
                    cbType.setSelectedItem(book.getType());

                    // Hiển thị thông tin tùy thuộc vào loại sách
                    if (book.getType().equals("Giáo Khoa")) {
                        txtStatus.setText(book.getStatus());
                        txtStatus.setVisible(true);
                        txtTax.setVisible(false); // Ẩn thuế
                    } else {
                        txtTax.setText(String.valueOf(book.getTax()));
                        txtTax.setVisible(true);
                        txtStatus.setVisible(false); // Ẩn tình trạng
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Không tìm thấy sách với ID: " + id);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập ID hợp lệ.");
            }
        });

        // Thêm các thành phần vào giao diện
        add(new JLabel("ID sách:"));
        add(txtId);
        add(new JLabel("Ngày nhập (dd/MM/yyyy):"));
        add(txtDate);
        add(new JLabel("Đơn giá:"));
        add(txtUnitPrice);
        add(new JLabel("Số lượng:"));
        add(txtQuantity);
        add(new JLabel("Nhà xuất bản:"));
        add(txtPublisher);
        add(new JLabel("Loại sách:"));
        add(cbType);
        add(new JLabel("Tình trạng (nếu là sách giáo khoa):"));
        add(txtStatus);
        add(new JLabel("Thuế (nếu là sách tham khảo):"));
        add(txtTax);
        add(btnEdit);

        // Ẩn các trường không cần thiết ban đầu
        txtStatus.setVisible(false);
        txtTax.setVisible(false);
    }

    private Book findBookById(int id) {
        for (Book book : bookManagement.getBooks()) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    private Date parseDate(String dateString) throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
    }
}