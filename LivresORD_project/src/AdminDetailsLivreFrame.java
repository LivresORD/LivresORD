import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class AdminDetailsLivreFrame extends JFrame implements ActionListener {
    private JLabel titreLabel = new JLabel();
    private JLabel auteurLabel = new JLabel();
    private JLabel anneeLabel = new JLabel();
    private JLabel pagesLabel = new JLabel();
    private JLabel quantiteLabel = new JLabel();
    private JLabel lblResultatFormulaire = new JLabel("", JLabel.CENTER);

    private JTextField titreField = new JTextField();
    private JTextField auteurField = new JTextField();
    private JTextField anneeField = new JTextField();
    private JTextField pagesField = new JTextField();
    private JTextField quantiteField = new JTextField();

    private JButton boutonRetour;
    private JButton boutonModifier;
    private JButton boutonSupprimer;
    private String titreLivre;

    private boolean isEditMode = false;

    // New UI Panels for better structure
    private JPanel headerPanel = new JPanel(new BorderLayout());
    private JPanel textPanel = new JPanel(new GridLayout(5, 1, 10, 10));
    private JPanel centerPanel = new JPanel(new GridLayout(1, 2, 10, 10));
    private JPanel centerBorderPanel = new JPanel(new BorderLayout());
    private JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

    public AdminDetailsLivreFrame(String titre) {
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

        lblResultatFormulaire.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblResultatFormulaire.setForeground(Color.RED);
        lblResultatFormulaire.setBorder(new EmptyBorder(0, 0, 10, 0));
        centerBorderPanel.add(lblResultatFormulaire, BorderLayout.SOUTH);
        centerBorderPanel.setBackground(Color.WHITE);

        

        // Button Styling
        centerBorderPanel.add(centerPanel, BorderLayout.CENTER);
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
        add(centerBorderPanel, BorderLayout.CENTER);
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
                titreLabel.setText("Titre: " + rs.getString("titre"));
                auteurLabel.setText("Auteur: " + rs.getString("auteur"));
                anneeLabel.setText("Année: " + rs.getInt("annee"));
                pagesLabel.setText("Pages: " + rs.getInt("nombreDePages"));
                String imageExtension = rs.getString("imageExtension");
                
                int qte = rs.getInt("quantiteDisponible");
                quantiteLabel.setText("Disponibilité: " + qte + " exemplaires");
                

                String imageTitle = rs.getString("titre").replaceAll("[\\\\/:*?\"<>|\\s]", "_").toLowerCase();
                String imagePath = "images/" + imageTitle + "." + imageExtension;
                
                if (imagePath != null) {
                    ImageIcon icon = new ImageIcon(imagePath);
                    // SCALE SMALLER: 160x200 leaves room for the text at the bottom of a 180x250 button
                    Image img = icon.getImage().getScaledInstance(160, 230, Image.SCALE_SMOOTH);
                    JLabel imageLabel = new JLabel(new ImageIcon(img));
                    imageLabel.setHorizontalAlignment(JLabel.CENTER);
                    centerPanel.add(imageLabel);
                    textPanel.add(titreLabel);
                    textPanel.add(auteurLabel);
                    textPanel.add(anneeLabel);
                    textPanel.add(pagesLabel);
                    textPanel.add(quantiteLabel);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == boutonRetour) {
            new VueBibliothecaire().setVisible(true);
            this.dispose();
        }
        if (e.getSource() == boutonModifier) {
            if (!isEditMode) {
                // Switch to edit mode
                titreField.setText(titreLabel.getText().replace("Titre: ", ""));
                auteurField.setText(auteurLabel.getText().replace("Auteur: ", ""));
                anneeField.setText(anneeLabel.getText().replace("Année: ", ""));
                pagesField.setText(pagesLabel.getText().replace("Pages: ", ""));
                quantiteField.setText(quantiteLabel.getText().replace("Disponibilité: ", "").replace(" exemplaires", ""));

                textPanel.removeAll();
                textPanel.add(titreField);
                textPanel.add(auteurField);
                textPanel.add(anneeField);
                textPanel.add(pagesField);
                textPanel.add(quantiteField);
                textPanel.revalidate();
                textPanel.repaint();

                boutonModifier.setText("Enregistrer");
                isEditMode = true;
            } else {

                if (titreField.getText().equals("")) {
                    lblResultatFormulaire.setText("ERREUR! Titre manquant.");
                    return;
                }
                if (auteurField.getText().equals("")) {
                    lblResultatFormulaire.setText("ERREUR! Auteur manquant.");
                    return;
                }
                if (anneeField.getText().equals("")) {
                    lblResultatFormulaire.setText("ERREUR! Année manquante.");
                    return;
                }
                if (pagesField.getText().equals("")) {
                    lblResultatFormulaire.setText("ERREUR! Nombre de pages manquant.");
                    return;
                }

                if (!auteurField.getText().matches("^[a-zA-ZÀ-ÿ\\s\\.-]+$")) {
                    lblResultatFormulaire.setText("ERREUR! Nom de l'auteur invalide.");
                    return;
                }

                try {
                    int annee = Integer.parseInt(anneeField.getText());
                    if (annee < -7000000 || annee > 2026) throw new NumberFormatException();
                } catch (NumberFormatException ex) {
                    lblResultatFormulaire.setText("ERREUR! Année invalide.");
                    return;
                }

                try {
                    int pages = Integer.parseInt(pagesField.getText());
                    if (pages < 0 || pages > 15000) throw new NumberFormatException();
                } catch (NumberFormatException ex) {
                    lblResultatFormulaire.setText("ERREUR! Nombre de pages invalide.");
                    return;
                }

                try {
                    int quantite = Integer.parseInt(quantiteField.getText());
                    if (quantite < 0 || quantite > 10000) throw new NumberFormatException();
                } catch (NumberFormatException ex) {
                    lblResultatFormulaire.setText("ERREUR! Quantité invalide.");
                    return;
                }
                // 1. Prepare the SQL update
                String sql = "UPDATE books SET titre = ?, auteur = ?, annee = ?, nombreDePages = ?, quantiteDisponible = ? WHERE titre = ?";
                try (Connection conn = DatabaseHandler.connect();
                    PreparedStatement pstmt = conn.prepareStatement(sql)) {

                    // 2. Set the parameters from your text fields
                    pstmt.setString(1, titreField.getText());
                    pstmt.setString(2, auteurField.getText());
                    pstmt.setInt(3, Integer.parseInt(anneeField.getText()));
                    pstmt.setInt(4, Integer.parseInt(pagesField.getText()));
                    pstmt.setInt(5, Integer.parseInt(quantiteField.getText()));
                    pstmt.setString(6, titreLivre); // This is the old title used as the key

                    int rows = pstmt.executeUpdate();

                    if (rows > 0) {
                        JOptionPane.showMessageDialog(this, "Modifications enregistrées !");
                        
                        // 3. Update the local key in case the title was changed
                        titreLivre = titreField.getText();
                        lblResultatFormulaire.setText("");
                        
                        // 4. Toggle the mode back
                        isEditMode = false;
                        boutonModifier.setText("Modifier");

                        // 5. Reset the UI by clearing and reloading labels
                        centerPanel.removeAll(); 
                        textPanel.removeAll();
                        loadBookDetails(); // This refills textPanel and adds it back to centerPanel
                        
                        this.revalidate();
                        this.repaint();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Erreur lors de la mise à jour.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Veuillez entrer des nombres valides pour l'année, les pages et la quantité.");
                }
            }
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
        } catch (SQLException ex) {
            System.out.println("Erreur SQL: " + ex.getMessage());
        }
    }
}