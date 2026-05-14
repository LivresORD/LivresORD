import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class AdminDetailsLivreFrame extends JFrame implements ActionListener {
    private JLabel titreLabel;
    private JLabel auteurLabel;
    private JLabel anneeLabel;
    private JLabel pagesLabel;
    private JLabel quantiteLabel;
    private JButton boutonRetour;
    private JButton boutonModifier;
    private JButton boutonSupprimer;
    private String titreLivre;
    private int quantiteDisponible;

    // New UI Panels for better structure
    private JPanel headerPanel = new JPanel(new BorderLayout());
    private JPanel centerPanel = new JPanel(new GridLayout(5, 1, 10, 10));
    private JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

    public AdminDetailsLivreFrame(String titre) {
        this.titreLivre = titre;
        setTitle("Détails du Livre - " + titre);
        setSize(500, 450); // Increased size for better readability
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // Header Styling
        headerPanel.setBackground(new Color(245, 245, 245));
        headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        JLabel headerTitle = new JLabel("Informations sur l'ouvrage");
        headerTitle.setFont(new Font("Serif", Font.BOLD, 22));
        headerTitle.setBorder(new EmptyBorder(15, 0, 15, 0));
        headerTitle.setHorizontalAlignment(JLabel.CENTER);
        headerPanel.add(headerTitle, BorderLayout.CENTER);

        // Center Data Styling
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(new EmptyBorder(30, 50, 30, 50));

        titreLabel = createStyledLabel();
        auteurLabel = createStyledLabel();
        anneeLabel = createStyledLabel();
        pagesLabel = createStyledLabel();
        quantiteLabel = createStyledLabel();

        centerPanel.add(titreLabel);
        centerPanel.add(auteurLabel);
        centerPanel.add(anneeLabel);
        centerPanel.add(pagesLabel);
        centerPanel.add(quantiteLabel);

        // Button Styling
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));


        boutonRetour = new JButton("Retour");
        boutonRetour.setPreferredSize(new Dimension(120, 35));
        boutonRetour.addActionListener(this);

        boutonModifier = new JButton("Modifier");
        boutonModifier.setPreferredSize(new Dimension(120, 35));
        boutonModifier.addActionListener(this);

        buttonPanel.add(boutonRetour);
        buttonPanel.add(boutonModifier);
        boutonSupprimer = new JButton("Supprimer");
        boutonSupprimer.setPreferredSize(new Dimension(120, 35));
        boutonSupprimer.addActionListener(this);
        buttonPanel.add(boutonSupprimer);

        // Adding Panels to Frame
        add(headerPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        loadBookDetails();
    }

    // Helper method to keep label styling consistent
    private JLabel createStyledLabel() {
        JLabel lbl = new JLabel();
        lbl.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lbl.setForeground(new Color(50, 50, 50));
        return lbl;
    }

    private void loadBookDetails() {
        String sql = "SELECT * FROM books WHERE titre = ?";
        try (Connection conn = DatabaseHandler.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, titreLivre);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                titreLabel.setText("<html><b>Titre:</b> " + rs.getString("titre") + "</html>");
                auteurLabel.setText("<html><b>Auteur:</b> " + rs.getString("auteur") + "</html>");
                anneeLabel.setText("<html><b>Année:</b> " + rs.getInt("annee") + "</html>");
                pagesLabel.setText("<html><b>Pages:</b> " + rs.getInt("nombreDePages") + "</html>");
                
                int qte = rs.getInt("quantiteDisponible");
                quantiteLabel.setText("<html><b>Disponibilité:</b> " + qte + " exemplaires</html>");
                quantiteDisponible = qte;

                // Color code the availability
                if (qte <= 0) quantiteLabel.setForeground(Color.RED);
                else quantiteLabel.setForeground(new Color(0, 150, 0));
            }
        } catch (SQLException e) {
            System.out.println("Erreur SQL: " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boutonRetour) {
            new VueBibliothecaire().setVisible(true);
            this.dispose();
        }
        if (e.getSource() == boutonModifier) {
            new ModifierLivreFrame().setVisible(true);
            this.dispose();
        }
        if (e.getSource() == boutonSupprimer) {
            String[] options = {"Oui", "Non"};
            int option = JOptionPane.showOptionDialog(
                null,
                "Voulez-vous continuer ?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options, // Texte personnalisé
                options[0] // Bouton par défaut
            );            
            if (option == JOptionPane.YES_OPTION) {
                deleteBook();
            }
        }
    }
    public void deleteBook() {
        String sql = "DELETE FROM books WHERE titre = ?";
        try (Connection conn = DatabaseHandler.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, titreLivre);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                JOptionPane.showMessageDialog(this, "Livre supprimé avec succès.");
                new VueBibliothecaire().setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Erreur: Livre non trouvé.");
            }
        } catch (SQLException e) {
            System.out.println("Erreur SQL: " + e.getMessage());
        }
    }
}