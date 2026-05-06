import java.sql.*;

public class DatabaseHandler {
    private static final String URL = "jdbc:sqlite:users.db";

    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
            return null;
        }
    }

    public static void setupDatabase() {
        String sql = "CREATE TABLE IF NOT EXISTS accounts ("
                   + "username TEXT PRIMARY KEY,"
                   + "email TEXT,"
                   + "password TEXT);";
        
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Table creation error: " + e.getMessage());
        }
    }
}