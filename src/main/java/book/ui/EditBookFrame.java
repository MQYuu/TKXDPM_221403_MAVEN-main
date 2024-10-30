package book.ui;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditBookFrame extends JFrame {
    private BookManagement bookManagement;

    public EditBookFrame(BookManagement bookManagement) {
        this.bookManagement = bookManagement;
        setTitle("Sửa Sách");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(9, 2, 10, 10));

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
                    bookToEdit.dateImported = parseDate(txtDate.getText());
                    bookToEdit.unitPrice = Double.parseDouble(txtUnitPrice.getText());
                    bookToEdit.quantity = Integer.parseInt(txtQuantity.getText());
                    bookToEdit.publisher = txtPublisher.getText();
                    
                    // Cập nhật thông tin cho sách giáo khoa hoặc sách tham khảo
                    if (bookToEdit.type.equals("Giáo Khoa")) {
                        bookToEdit.status = txtStatus.getText();
                    } else if (bookToEdit.type.equals("Tham Khảo")) {
                        bookToEdit.tax = Double.parseDouble(txtTax.getText());
                    }

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
            int id = Integer.parseInt(txtId.getText());
            Book book = findBookById(id);
            if (book != null) {
                txtDate.setText(new SimpleDateFormat("dd/MM/yyyy").format(book.dateImported));
                txtUnitPrice.setText(String.valueOf(book.unitPrice));
                txtQuantity.setText(String.valueOf(book.quantity));
                txtPublisher.setText(book.publisher);
                cbType.setSelectedItem(book.type);
                if (book.type.equals("Giáo Khoa")) {
                    txtStatus.setText(book.status);
                    txtTax.setVisible(false); // Ẩn thuế
                    add(new JLabel("Tình trạng:"));
                    add(txtStatus);
                } else {
                    txtTax.setText(String.valueOf(book.tax));
                    txtStatus.setVisible(false); // Ẩn tình trạng
                    add(new JLabel("Thuế:"));
                    add(txtTax);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy sách với ID: " + id);
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
        add(btnEdit);
    }

    private Book findBookById(int id) {
        for (Book book : bookManagement.getBooks()) {
            if (book.id == id) {
                return book;
            }
        }
        return null;
    }

    private Date parseDate(String dateString) throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
    }
}
