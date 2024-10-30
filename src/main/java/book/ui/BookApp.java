package book.ui;

import javax.swing.SwingUtilities;

import book.database.DatabaseConnection;

public class BookApp {
        public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BookManagement().setVisible(true));

        // DatabaseConnection dbConnection = new DatabaseConnection();
        // dbConnection.testConnection(); // Gọi phương thức kiểm tra kết nối
    }
}
