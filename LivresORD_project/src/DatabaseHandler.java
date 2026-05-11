import java.sql.*;

public class DatabaseHandler {
    private static final String URL = "jdbc:sqlite:database.db";

    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
            return null;
        }
    }

    public static void setupDatabase() {
        String sqlAccounts = "CREATE TABLE IF NOT EXISTS accounts ("
                   + "username TEXT PRIMARY KEY,"
                   + "email TEXT,"
                   + "password TEXT);";

        String sqlBooks = "CREATE TABLE IF NOT EXISTS books ("
                   + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                   + "titre TEXT,"
                   + "auteur TEXT,"
                   + "annee INTEGER,"
                   + "nombreDePages INTEGER,"
                   + "quantiteDisponible INTEGER);";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sqlAccounts);
            stmt.execute(sqlBooks);
        } catch (SQLException e) {
            System.out.println("Table creation error: " + e.getMessage());
        }
    }
}