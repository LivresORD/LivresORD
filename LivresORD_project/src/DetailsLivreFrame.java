import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

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

    // New UI Panels for better structure
    private JPanel headerPanel = new JPanel(new BorderLayout());
    private JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 10));
    private JPanel textPanel = new JPanel(new GridLayout(5, 1, 10, 10));
    private JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

    public DetailsLivreFrame(String titre) {
        this.titreLivre = titre;
        setTitle("Détails du Livre - " + titre);
        setSize(600, 450); // Increased size for better readability
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

        textPanel.setBackground(Color.WHITE);
        textPanel.add(titreLabel);
        textPanel.add(auteurLabel);
        textPanel.add(anneeLabel);
        textPanel.add(pagesLabel);
        textPanel.add(quantiteLabel);

        // Button Styling
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));

        emprunterButton = new JButton("Emprunter ce livre");
        emprunterButton.setPreferredSize(new Dimension(180, 35));
        emprunterButton.addActionListener(this);

        boutonRetour = new JButton("Retour");
        boutonRetour.setPreferredSize(new Dimension(120, 35));
        boutonRetour.addActionListener(this);

        
        buttonPanel.add(boutonRetour);
        buttonPanel.add(emprunterButton);

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
                String imageExtension = rs.getString("imageExtension");
                
                int qte = rs.getInt("quantiteDisponible");
                quantiteLabel.setText("<html><b>Disponibilité:</b> " + qte + " exemplaires</html>");
                quantiteDisponible = qte;

                String imageTitle = rs.getString("titre").replaceAll("[\\\\/:*?\"<>|\\s]", "_").toLowerCase();
                String imagePath = "images/" + imageTitle + "." + imageExtension;
                
                if (imagePath != null) {
                    ImageIcon icon = new ImageIcon(imagePath);
                    // SCALE SMALLER: 160x200 leaves room for the text at the bottom of a 180x250 button
                    Image img = icon.getImage().getScaledInstance(160, 230, Image.SCALE_SMOOTH);
                    JLabel imageLabel = new JLabel(new ImageIcon(img));
                    imageLabel.setHorizontalAlignment(JLabel.CENTER);
                    centerPanel.add(imageLabel);
                    centerPanel.add(textPanel);
                }

                // Color code the availability
                if (qte <= 0) quantiteLabel.setForeground(Color.RED);
                else quantiteLabel.setForeground(new Color(0, 150, 0));
            }
        } catch (SQLException e) {
            System.out.println("Erreur SQL: " + e.getMessage());
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boutonRetour) {
            new VueLecteur().setVisible(true);
            this.dispose();
        } else if (e.getSource() == emprunterButton) {
            processEmpreunt();
            loadBookDetails();
        }
    }

    public void processEmpreunt() {
        String updateStock = "UPDATE books SET quantiteDisponible = quantiteDisponible - 1 WHERE titre = ?";
        String recordEmpreunt = "INSERT INTO emprunts(username, idLivre) VALUES(?, (SELECT id FROM books WHERE titre = ?))";
        
        if (quantiteDisponible <= 0) {
            JOptionPane.showMessageDialog(this, "Ce livre n'est pas disponible pour le moment.", "Indisponible", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try (Connection conn = DatabaseHandler.connect()) {
            if (alreadyBorrowed()) {
                JOptionPane.showMessageDialog(this, "Vous détenez déjà un exemplaire de cet ouvrage.", "Doublon", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            try (PreparedStatement pstmt1 = conn.prepareStatement(updateStock);
                 PreparedStatement pstmt2 = conn.prepareStatement(recordEmpreunt)) {
                
                pstmt1.setString(1, titreLivre);
                pstmt1.executeUpdate();

                pstmt2.setString(1, CurrentUser.getUsername());
                pstmt2.setString(2, titreLivre);
                pstmt2.executeUpdate();

                JOptionPane.showMessageDialog(this, "L'emprunt a été enregistré avec succès.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            System.out.println("Erreur transactionnelle: " + e.getMessage());
        }
    }

    private boolean alreadyBorrowed() {
        String sql = "SELECT * FROM emprunts WHERE username = ? AND idLivre = (SELECT id FROM books WHERE titre = ?)";
        try (Connection conn = DatabaseHandler.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, CurrentUser.getUsername());
            pstmt.setString(2, titreLivre);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return true; 
        }
    }
}