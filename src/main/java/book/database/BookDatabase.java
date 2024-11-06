package book.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import book.ui.Book;

public class BookDatabase {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/books";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "12345678";

    // Fetch all books from the database
    public static ArrayList<Book> getAllBooks() {
        ArrayList<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
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

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            setBookPreparedStatement(pstmt, book);
            pstmt.setInt(1, book.getId()); // Set ID separately as it doesn't change

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            logSQLException(e);
            return false;
        }
    }

    // Update an existing book in the database
    public static void updateBookInDatabase(Book book) {
        String query = "UPDATE books SET publisher = ?, type = ?, unit_price = ?, quantity = ?, date_imported = ?, status = ?, tax = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            setBookPreparedStatement(pstmt, book);
            pstmt.setInt(8, book.getId()); // Set ID for the WHERE clause

            pstmt.executeUpdate();
        } catch (SQLException e) {
            logSQLException(e);
        }
    }

    // Fetch a book by its ID
    public static Book getBookById(int id) {
        String query = "SELECT * FROM books WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
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

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
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
        pstmt.setString(1, book.getPublisher());
        pstmt.setString(2, book.getType());
        pstmt.setDouble(3, book.getUnitPrice());
        pstmt.setInt(4, book.getQuantity());
        pstmt.setDate(5, new java.sql.Date(book.getDateImported().getTime()));

        if ("Giáo Khoa".equals(book.getType())) {
            pstmt.setString(6, book.getStatus());
            pstmt.setDouble(7, 0);
        } else if ("Tham Khảo".equals(book.getType())) {
            pstmt.setString(6, null);
            pstmt.setDouble(7, book.getTax());
        }
    }

    // Centralized error logging for SQLException
    private static void logSQLException(SQLException e) {
        System.err.println("SQL error occurred: " + e.getMessage());
        e.printStackTrace();
    }
}
