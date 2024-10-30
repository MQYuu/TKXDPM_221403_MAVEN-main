package student_app.ui;

import javax.swing.*;

import student_app.database.AddStudentDAOMemory;
import student_app.usecase.AddStudentUseCase;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentForm extends JFrame {
    private JTextField nameField, dobField, addressField;
    private JComboBox<String> majorComboBox;
    private JTextField javaScoreField, cssScoreField, htmlScoreField;
    private JTextField marketingScoreField, salesScoreField;
    private JButton addButton, resetButton;
    private JPanel softwarePanel, economicsPanel, dynamicPanel;
    private AddStudentController addStudentController = null;

    public void setAddStudentController(AddStudentController addStudentController) {
        this.addStudentController = addStudentController;
    }

    public StudentForm() {
        setTitle("Student Registration Form");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel for common fields
        JPanel commonPanel = new JPanel(new GridLayout(4, 2));
        commonPanel.setBorder(BorderFactory.createTitledBorder("Student Information"));

        // Student name
        commonPanel.add(new JLabel("Student Name:"));
        nameField = new JTextField();
        commonPanel.add(nameField);

        // Student date of birth
        commonPanel.add(new JLabel("Date of Birth (dd/MM/yyyy):"));
        dobField = new JTextField();
        commonPanel.add(dobField);

        // Student address
        commonPanel.add(new JLabel("Address:"));
        addressField = new JTextField();
        commonPanel.add(addressField);

        // Student major (ComboBox)
        commonPanel.add(new JLabel("Major:"));
        majorComboBox = new JComboBox<>(new String[]{"Software", "Economics"});
        commonPanel.add(majorComboBox);

        // Dynamic panel to swap between software and economics panels
        dynamicPanel = new JPanel(new CardLayout());

        // Panel for Software Major
        softwarePanel = new JPanel(new GridLayout(3, 2));
        softwarePanel.setBorder(BorderFactory.createTitledBorder("Software Scores"));

        softwarePanel.add(new JLabel("Java Score:"));
        javaScoreField = new JTextField();
        softwarePanel.add(javaScoreField);

        softwarePanel.add(new JLabel("CSS Score:"));
        cssScoreField = new JTextField();
        softwarePanel.add(cssScoreField);

        softwarePanel.add(new JLabel("HTML Score:"));
        htmlScoreField = new JTextField();
        softwarePanel.add(htmlScoreField);

        // Panel for Economics Major
        economicsPanel = new JPanel(new GridLayout(2, 2));
        economicsPanel.setBorder(BorderFactory.createTitledBorder("Economics Scores"));

        economicsPanel.add(new JLabel("Marketing Score:"));
        marketingScoreField = new JTextField();
        economicsPanel.add(marketingScoreField);

        economicsPanel.add(new JLabel("Sales Score:"));
        salesScoreField = new JTextField();
        economicsPanel.add(salesScoreField);

        // Add both panels to the dynamic panel
        dynamicPanel.add(softwarePanel, "Software");
        dynamicPanel.add(economicsPanel, "Economics");

        // Initially show the panel for Software Major
        CardLayout cl = (CardLayout)(dynamicPanel.getLayout());
        cl.show(dynamicPanel, "Software");

        // Major selection handler
        majorComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedMajor = (String) majorComboBox.getSelectedItem();
                if (selectedMajor.equals("Software")) {
                    cl.show(dynamicPanel, "Software");
                } else {
                    cl.show(dynamicPanel, "Economics");
                }
            }
        });

        // Panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        addButton = new JButton("Add Student");
        resetButton = new JButton("Reset");

        buttonPanel.add(addButton);
        buttonPanel.add(resetButton);

        // Add panels to frame
        add(commonPanel, BorderLayout.NORTH);
        add(dynamicPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.PAGE_END);

        // Button listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAddStudent();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetForm();
            }
        });
    }

    // Handling Add Student logic
    private void handleAddStudent() {
        String name = nameField.getText();
        String dob = dobField.getText();
        String address = addressField.getText();
        String major = (String) majorComboBox.getSelectedItem();

        // Validate and fetch scores based on selected major
        if (major.equals("Software")) {
            String javaScore = javaScoreField.getText();
            String cssScore = cssScoreField.getText();
            String htmlScore = htmlScoreField.getText();

            addStudentController.addStudentPhanMem(name, dob, address, major, 
            javaScore, cssScore, htmlScore);

            // Process and validate Software scores
            System.out.println("Adding Software Student: " + name + ", " + dob + ", Java: " + javaScore);
        } else if (major.equals("Economics")) {
            String marketingScore = marketingScoreField.getText();
            String salesScore = salesScoreField.getText();
            // Process and validate Economics scores
            System.out.println("Adding Economics Student: " + name + ", " + dob + ", Marketing: " + marketingScore);
        }

        // Further validation and logic can be added here
        JOptionPane.showMessageDialog(this, "Student added successfully!");
    }

    // Reset form fields
    private void resetForm() {
        nameField.setText("");
        dobField.setText("");
        addressField.setText("");
        javaScoreField.setText("");
        cssScoreField.setText("");
        htmlScoreField.setText("");
        marketingScoreField.setText("");
        salesScoreField.setText("");
    }

    public static void main(String[] args) {

        // StudentForm form = null;
        // SwingUtilities.invokeLater(() -> {
        //     form = new StudentForm();
        //     form.setVisible(true);
        // });

        StudentForm form = new StudentForm();
        form.setVisible(true);

        AddStudentDAOMemory addStudentDAOMemory = new AddStudentDAOMemory();

        AddStudentPresenter addStudentPresenter = new AddStudentPresenter();

        AddStudentUseCase addStudentUseCase = new AddStudentUseCase(
            addStudentDAOMemory, addStudentPresenter);

        AddStudentController addStudentController = 
        new AddStudentController(addStudentUseCase);

        form.setAddStudentController(addStudentController);
    }
}