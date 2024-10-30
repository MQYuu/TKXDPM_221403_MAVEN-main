package book.ui;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddBookFrame extends JFrame {
    private BookManagement bookManagement;

    public AddBookFrame(BookManagement bookManagement) {
        
        this.bookManagement = bookManagement;
        setTitle("Thêm Sách");
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
        JButton btnAdd = new JButton("Thêm");

        // Thiết lập sự kiện cho nút Thêm
        btnAdd.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText());
                String dateString = txtDate.getText();
                Date dateImported = parseDate(dateString);
                double unitPrice = Double.parseDouble(txtUnitPrice.getText());
                int quantity = Integer.parseInt(txtQuantity.getText());
                String publisher = txtPublisher.getText();
                String type = cbType.getSelectedItem().toString();
                String status = "";
                double tax = 0.0;

                // Xử lý thêm sách giáo khoa hoặc sách tham khảo
                if (type.equals("Giáo Khoa")) {
                    status = txtStatus.getText();
                    bookManagement.addBook(new Book(id, "", publisher, type, unitPrice, quantity, dateImported, status, tax));
                } else if (type.equals("Tham Khảo")) {
                    tax = Double.parseDouble(txtTax.getText());
                    bookManagement.addBook(new Book(id, "", publisher, type, unitPrice, quantity, dateImported, status, tax));
                }

                JOptionPane.showMessageDialog(this, "Đã thêm sách thành công.");
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin hợp lệ.");
            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập định dạng ngày hợp lệ (dd/MM/yyyy).");
            }
        });

        // Tạo các thành phần giao diện
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

        // Thêm các trường tình trạng và thuế, hiển thị theo loại sách
        add(new JLabel("Tình trạng (nếu là sách giáo khoa):"));
        add(txtStatus);
        add(new JLabel("Thuế (nếu là sách tham khảo):"));
        add(txtTax);
        
        // Thay đổi khả năng hiển thị các trường này theo loại sách
        cbType.addActionListener(e -> {
            String selectedType = cbType.getSelectedItem().toString();
            if (selectedType.equals("Giáo Khoa")) {
                txtStatus.setVisible(true);
                txtTax.setVisible(false);
            } else {
                txtStatus.setVisible(false);
                txtTax.setVisible(true);
            }
        });
        
        // Nút Thêm
        add(btnAdd);
    }

    private Date parseDate(String dateString) throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
    }
    
}


