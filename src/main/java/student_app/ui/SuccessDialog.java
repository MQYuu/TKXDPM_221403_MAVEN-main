package student_app.ui;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SuccessDialog extends JDialog {

    public SuccessDialog(JFrame parent) {
        super(parent, "Success", true);

        JLabel successLabel = new JLabel("Đã thêm mới sinh viên thành công!!!", JLabel.CENTER);
        successLabel.setFont(successLabel.getFont().deriveFont(14f));

        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> dispose());

        JPanel panel = new JPanel();
        panel.add(okButton);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(successLabel);
        add(panel);

        setSize(300, 150);
        setLocationRelativeTo(parent);
    }
}
