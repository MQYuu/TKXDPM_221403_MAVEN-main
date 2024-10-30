package student_app.ui;

import javax.swing.*;
import java.awt.*;

import student_app.usecase.dto.AddStudentOutputDTO;

public class StudentDetailsForm extends JFrame {
    
public StudentDetailsForm(AddStudentOutputDTO outputData) {
    
    setTitle("Thông tin sinh viên");
    setLayout(new GridLayout(6, 2));
    setSize(400, 300);

    SuccessDialog successDialog = new SuccessDialog(this);
    successDialog.setVisible(true);

    add(new JLabel("Mã sinh viên:"));
    add(new JLabel(""+outputData.getMaSV()));

    add(new JLabel("Họ tên:"));
    add(new JLabel(outputData.getHoTen()));

    add(new JLabel("Ngày sinh:"));
    add(new JLabel("" +outputData.getNgaySinh()));

    add(new JLabel("Địa chỉ:"));
    add(new JLabel(outputData.getDiaChi()));

    add(new JLabel("Điểm trung bình:"));
    add(new JLabel(String.valueOf(outputData.getDiemTB())));

    add(new JLabel("Học lực:"));
    add(new JLabel(outputData.getHocLuc()));

    // Button to close the form
    JButton closeButton = new JButton("Close");
    closeButton.addActionListener(e -> dispose());

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(closeButton);

    add(buttonPanel);

    setVisible(true);

    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
}