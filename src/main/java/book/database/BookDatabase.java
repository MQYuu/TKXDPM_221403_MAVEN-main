package book.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import book.ui.Book;

public class BookDatabase {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/books";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "12345678";

    public static ArrayList<Book> getAllBooks() {
        ArrayList<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String publisher = rs.getString("publisher");
                String type = rs.getString("type");
                double unitPrice = rs.getDouble("unit_price");
                int quantity = rs.getInt("quantity");
                Date dateImported = rs.getDate("date_imported");
                String status = rs.getString("status");
                double tax = rs.getDouble("tax");

                Book book = new Book(id, publisher, type, unitPrice, quantity, dateImported, status, tax);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    public static boolean insertBook(Book book) {
        String query = "INSERT INTO books (id, publisher, type, unit_price, quantity, date_imported, status, tax) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, book.getId());
            pstmt.setString(2, book.getPublisher());
            pstmt.setString(3, book.getType());
            pstmt.setDouble(4, book.getUnitPrice());
            pstmt.setInt(5, book.getQuantity());
            pstmt.setDate(6, new java.sql.Date(book.getDateImported().getTime()));
            pstmt.setString(7, book.getStatus());
            pstmt.setDouble(8, book.getTax());

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void updateBookInDatabase(Book book) {
        String query = "UPDATE books SET publisher = ?, type = ?, unit_price = ?, quantity = ?, date_imported = ?, status = ?, tax = ? WHERE id = ?";
        
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            // Cập nhật các trường thông tin
            pstmt.setString(1, book.getPublisher());
            pstmt.setString(2, book.getType());
            pstmt.setDouble(3, book.getUnitPrice());
            pstmt.setInt(4, book.getQuantity());
            pstmt.setDate(5, new java.sql.Date(book.getDateImported().getTime())); // Chuyển đổi sang java.sql.Date
    
            // Cập nhật trạng thái và thuế tùy theo loại sách
            if (book.getType().equals("Giáo Khoa")) {
                pstmt.setString(6, book.getStatus()); // Nếu là sách giáo khoa, cập nhật trạng thái
                pstmt.setDouble(7, 0); // Đặt thuế về 0 vì không áp dụng cho sách giáo khoa
            } else if (book.getType().equals("Tham Khảo")) {
                pstmt.setString(6, null); // Đặt trạng thái về null vì không áp dụng cho sách tham khảo
                pstmt.setDouble(7, book.getTax()); // Cập nhật thuế cho sách tham khảo
            }
    
            pstmt.setInt(8, book.getId()); // ID của sách cần cập nhật
    
            // Thực thi câu lệnh update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Book getBookById(int id) {
        String query = "SELECT * FROM books WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String publisher = rs.getString("publisher");
                String type = rs.getString("type");
                double unitPrice = rs.getDouble("unit_price");
                int quantity = rs.getInt("quantity");
                Date dateImported = rs.getDate("date_imported");
                String status = rs.getString("status");
                double tax = rs.getDouble("tax");
    
                return new Book(id, publisher, type, unitPrice, quantity, dateImported, status, tax);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Không tìm thấy sách
    }
    
    
    
}
