import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class DetailsLivreFrame extends JFrame implements ActionListener {
    private JLabel titreLabel;
    private JLabel auteurLabel;
    private JLabel anneeLabel;
    private JLabel pagesLabel;
    private JLabel quantiteLabel;
    private JButton boutonRetour;
    private JButton emprunterButton;
    private String titreLivre;
    private int quantiteDisponible;

    public DetailsLivreFrame(String titre) {
        this.titreLivre = titre;
        setTitle("Détails du Livre");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 1));
        setLocationRelativeTo(null);

        titreLabel = new JLabel();
        auteurLabel = new JLabel();
        anneeLabel = new JLabel();
        pagesLabel = new JLabel();
        quantiteLabel = new JLabel();
        emprunterButton = new JButton("Emprunter");
        boutonRetour = new JButton("Retour");
        boutonRetour.addActionListener(this);

        add(titreLabel);
        add(auteurLabel);
        add(anneeLabel);
        add(pagesLabel);
        add(quantiteLabel);
        add(emprunterButton);
        add(boutonRetour);

        emprunterButton.addActionListener(this);

        loadBookDetails();
    }

    private void loadBookDetails() {
        String sql = "SELECT * FROM books WHERE titre = ?";
        try (Connection conn = DatabaseHandler.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, titreLivre);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                titreLabel.setText("Titre: " + rs.getString("titre"));
                auteurLabel.setText("Auteur: " + rs.getString("auteur"));
                anneeLabel.setText("Année: " + rs.getInt("annee"));
                pagesLabel.setText("Nombre de Pages: " + rs.getInt("nombreDePages"));
                quantiteLabel.setText("Quantité Disponible: " + rs.getInt("quantiteDisponible"));
                quantiteDisponible = rs.getInt("quantiteDisponible");
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors du chargement des détails du livre: " + e.getMessage());
        }
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boutonRetour) {
            new VueLecteur().setVisible(true);
            this.dispose();
        } else if (e.getSource() == emprunterButton) {
            processEmpreunt();
            loadBookDetails();
            revalidate();
            repaint();
        }
    }
    public void processEmpreunt() {
        String updateStock = "UPDATE books SET quantiteDisponible = quantiteDisponible - 1 WHERE titre = ?";
        String recordEmpreunt = "INSERT INTO emprunts(username, idLivre) VALUES(?, (SELECT id FROM books WHERE titre = ?))";
        if (quantiteDisponible <= 0) {
            JOptionPane.showMessageDialog(this, "Désolé, ce livre n'est pas disponible pour le moment.");
            return;
        }
        try (Connection conn = DatabaseHandler.connect();
             PreparedStatement pstmt1 = conn.prepareStatement(updateStock);
             PreparedStatement pstmt2 = conn.prepareStatement(recordEmpreunt)) {
            try { 
                pstmt1.setString(1, titreLivre);
                pstmt1.executeUpdate();
                pstmt2.setString(1, CurrentUser.getUsername());
                pstmt2.setString(2, titreLivre);
                pstmt2.executeUpdate();
                JOptionPane.showMessageDialog(this, "Livre emprunté avec succès !");
            } catch (SQLException ex) {
                conn.rollback();
                System.out.println("Erreur lors de l'emprunt du livre: " + ex.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'emprunt du livre: " + e.getMessage());
        }
    }
}