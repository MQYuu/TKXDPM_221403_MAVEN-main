package book.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import book.ui.Book;

public class BookDatabase {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/books";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "12345678";

    // Reusable method for obtaining a database connection
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
    }

    // Fetch all books from the database
    public static ArrayList<Book> getAllBooks() {
        ArrayList<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                books.add(extractBookFromResultSet(rs));
            }
        } catch (SQLException e) {
            logSQLException(e);
        }
        return books;
    }

    // Insert a new book into the database
    public static boolean insertBook(Book book) {
        String query = "INSERT INTO books (id, publisher, type, unit_price, quantity, date_imported, status, tax) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection(); 
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            setBookPreparedStatement(pstmt, book);
            pstmt.setInt(1, book.getId()); // Set ID separately, as it's a primary key

            return pstmt.executeUpdate() > 0; // Returns true if the insert was successful
        } catch (SQLException e) {
            logSQLException(e);
            return false;
        }
    }

    // Update an existing book in the database
    public static boolean updateBookInDatabase(Book book) {
        String query = "UPDATE books SET publisher = ?, type = ?, unit_price = ?, quantity = ?, date_imported = ?, status = ?, tax = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            setBookPreparedStatement(pstmt, book);
            pstmt.setInt(8, book.getId()); // Set ID for the WHERE clause

            return pstmt.executeUpdate() > 0; // Return true if update was successful
        } catch (SQLException e) {
            logSQLException(e);
            return false;
        }
    }

    // Fetch a book by its ID
    public static Book getBookById(int id) {
        String query = "SELECT * FROM books WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractBookFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            logSQLException(e);
        }
        return null;
    }

    // Delete a book by its ID
    public static boolean deleteBook(int id) {
        String query = "DELETE FROM books WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0; // Return true if the deletion was successful
        } catch (SQLException e) {
            logSQLException(e);
            return false;
        }
    }

    // Helper method to extract a book object from a ResultSet
    private static Book extractBookFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String publisher = rs.getString("publisher");
        String type = rs.getString("type");
        double unitPrice = rs.getDouble("unit_price");
        int quantity = rs.getInt("quantity");
        Date dateImported = rs.getDate("date_imported");
        String status = rs.getString("status");
        double tax = rs.getDouble("tax");

        return new Book(id, publisher, type, unitPrice, quantity, dateImported, status, tax);
    }

    // Helper method to set PreparedStatement parameters from a Book object
    private static void setBookPreparedStatement(PreparedStatement pstmt, Book book) throws SQLException {
        pstmt.setString(2, book.getPublisher());
        pstmt.setString(3, book.getType());
        pstmt.setDouble(4, book.getUnitPrice());
        pstmt.setInt(5, book.getQuantity());
        pstmt.setDate(6, new java.sql.Date(book.getDateImported().getTime()));

        if ("Giáo Khoa".equals(book.getType())) {
            pstmt.setString(7, book.getStatus());
            pstmt.setDouble(8, 0); // Tax is not applicable for educational books
        } else if ("Tham Khảo".equals(book.getType())) {
            pstmt.setString(7, null); // No status for reference books
            pstmt.setDouble(8, book.getTax()); // Tax for reference books
        }
    }

    // Centralized error logging for SQLException
    private static void logSQLException(SQLException e) {
        System.err.println("SQL error occurred: " + e.getMessage());
        e.printStackTrace();
    }
}
