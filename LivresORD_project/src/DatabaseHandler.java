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
        String sqlComptesLecteur = "CREATE TABLE IF NOT EXISTS comptes_lecteur ("
                   + "username TEXT PRIMARY KEY,"
                   + "email TEXT,"
                   + "password TEXT);";
        String sqlComptesBibliothecaire = "CREATE TABLE IF NOT EXISTS comptes_bibliothecaire ("
                   + "username TEXT PRIMARY KEY,"
                   + "email TEXT,"
                   + "password TEXT);";

        String sqlLivres = "CREATE TABLE IF NOT EXISTS books ("
                   + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                   + "titre TEXT,"
                   + "auteur TEXT,"
                   + "annee INTEGER,"
                   + "nombreDePages INTEGER,"
                   + "quantiteDisponible INTEGER,"
                   + "imageExtension TEXT);";
        
        String sqlEmprunts = "CREATE TABLE IF NOT EXISTS emprunts ("
                   + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                   + "username TEXT,"
                   + "idLivre TEXT);";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sqlComptesLecteur);
            stmt.execute(sqlComptesBibliothecaire);
            stmt.execute(sqlLivres);
            stmt.execute(sqlEmprunts);
        } catch (SQLException e) {
            System.out.println("Table creation error: " + e.getMessage());
        }
    }
}