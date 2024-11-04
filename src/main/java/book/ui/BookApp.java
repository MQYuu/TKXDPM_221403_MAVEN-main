package book.ui;

import javax.swing.SwingUtilities;

public class BookApp {
        public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BookManagement().setVisible(true));

    }
}