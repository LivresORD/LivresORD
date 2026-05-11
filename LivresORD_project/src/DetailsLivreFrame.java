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
            // Implémentez la logique d'emprunt ici
            JOptionPane.showMessageDialog(this, "Vous avez emprunté: " + titreLivre);
        }
    }
}