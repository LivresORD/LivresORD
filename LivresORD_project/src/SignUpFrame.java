import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SignUpFrame extends JFrame implements ActionListener {
    private JLabel emailLabel = new JLabel("Courriel:", JLabel.LEFT);
    private JLabel usernameLabel = new JLabel("Nom d'utilisateur:", JLabel.LEFT);
    private JLabel passwordLabel = new JLabel("Mot de passe:", JLabel.LEFT);
    private JLabel confirmPasswordLabel = new JLabel("Confirmer le mot de passe:", JLabel.LEFT);
    
    private JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    private JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    private JPanel confirmPasswordPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    private JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    
    private JTextField emailField = new JTextField(20);
    private JTextField usernameField = new JTextField(20);
    private JPasswordField passwordField = new JPasswordField(20);
    private JPasswordField confirmPasswordField = new JPasswordField(20);
    
    private JButton signUpBouton = new JButton("S'inscrire");
    private JButton retourBouton = new JButton("Retour");
    
    private JPanel panneauBoutons = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
    private JPanel headerPanel = new JPanel(new BorderLayout());
    private String sql;

    public SignUpFrame() {
        setTitle("S'inscrire");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 450); // Slightly larger for better spacing
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        // Header Styling
        headerPanel.setBackground(new Color(245, 245, 245));
        headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        JLabel headerTitle = new JLabel("Créer un compte");
        headerTitle.setFont(new Font("Serif", Font.BOLD, 24));
        headerTitle.setHorizontalAlignment(JLabel.CENTER);
        headerTitle.setBorder(new EmptyBorder(20, 0, 20, 0));
        headerPanel.add(headerTitle, BorderLayout.CENTER);

        // Main Form Container
        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new GridLayout(4, 1, 0, 5));
        mainContainer.setBackground(Color.WHITE);
        mainContainer.setBorder(new EmptyBorder(30, 40, 30, 40));

        // Styling individual rows
        styleRowPanel(userPanel);
        styleRowPanel(emailPanel);
        styleRowPanel(passwordPanel);
        styleRowPanel(confirmPasswordPanel);

        userPanel.add(usernameLabel);
        userPanel.add(usernameField);
        emailPanel.add(emailLabel);
        emailPanel.add(emailField);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        confirmPasswordPanel.add(confirmPasswordLabel);
        confirmPasswordPanel.add(confirmPasswordField);

        mainContainer.add(userPanel);
        mainContainer.add(emailPanel);
        mainContainer.add(passwordPanel);
        mainContainer.add(confirmPasswordPanel);

        // Button Panel Styling
        panneauBoutons.setBackground(new Color(245, 245, 245));
        panneauBoutons.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));
        
        signUpBouton.setPreferredSize(new Dimension(120, 35));
        retourBouton.setPreferredSize(new Dimension(100, 35));

        panneauBoutons.add(retourBouton);
        panneauBoutons.add(signUpBouton);

        // Final Assembly
        add(headerPanel, BorderLayout.NORTH);
        add(mainContainer, BorderLayout.CENTER);
        add(panneauBoutons, BorderLayout.SOUTH);

        signUpBouton.addActionListener(this);
        retourBouton.addActionListener(this);
    }

    // Helper to keep row panel styling consistent
    private void styleRowPanel(JPanel p) {
        p.setBackground(Color.WHITE);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signUpBouton) {
            String user = usernameField.getText();
            String mail = emailField.getText();
            String pass = new String(passwordField.getPassword());
            String confirm = new String(confirmPasswordField.getPassword());

            if (user.isEmpty() || mail.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs.");
                return;
            } else if (!mail.contains("@") || !mail.contains(".")) {
                JOptionPane.showMessageDialog(this, "Veuillez entrer une adresse e-mail valide.");
                return;
            } else if (pass.length() < 6 || pass.length() > 20) {
                JOptionPane.showMessageDialog(this, "Le mot de passe doit contenir entre 6 et 20 caractères.");
                return;
            } else if (user.length() < 3 || user.length() > 15) {
                JOptionPane.showMessageDialog(this, "Le nom d'utilisateur doit contenir entre 3 et 15 caractères.");
                return;
            } else if (pass.contains(" ") || user.contains(" ")) {
                JOptionPane.showMessageDialog(this, "Le nom d'utilisateur et le mot de passe ne doivent pas contenir d'espaces.");
                return;
            } else if (!pass.matches(".*[A-Z].*") || !pass.matches(".*[a-z].*") || !pass.matches(".*\\d.*")) {
                JOptionPane.showMessageDialog(this, "Le mot de passe doit contenir au moins une majuscule, une minuscule et un chiffre.");
                return;
            } else if (pass.equals(confirm)) {
                saveUserToDatabase(user, mail, pass);
            } else {
                JOptionPane.showMessageDialog(this, "Les mots de passe ne correspondent pas.");
            }
        } else if (e.getSource() == retourBouton) {
            new LivresORD().setVisible(true);
            dispose();
        }
    }

    private void saveUserToDatabase(String user, String mail, String pass) {
        if (CurrentUser.getRole() == null) {
            JOptionPane.showMessageDialog(this, "Erreur: Aucun rôle sélectionné.");
            new LivresORD().setVisible(true);
            this.dispose();
            return;
        } else if (CurrentUser.getRole().equals("Bibliothecaire")) {
            sql = "INSERT INTO comptes_bibliothecaire(username, email, password) VALUES(?,?,?)";
        } else if (CurrentUser.getRole().equals("Lecteur")) {
            sql = "INSERT INTO comptes_lecteur(username, email, password) VALUES(?,?,?)";
        } 

        try (Connection conn = DatabaseHandler.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, user);
            pstmt.setString(2, mail);
            pstmt.setString(3, pass);
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(this, "Inscription réussie!");
            new LoginFrame().setVisible(true);
            dispose();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erreur: Ce nom d'utilisateur existe déjà.");
        }
    }
}