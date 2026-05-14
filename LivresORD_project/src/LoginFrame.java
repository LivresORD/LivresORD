import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LoginFrame extends JFrame implements ActionListener {
    private JLabel usernameLabel = new JLabel("Nom d'utilisateur:");
    private JLabel passwordLabel = new JLabel("Mot de passe:");
    private JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    private JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    private JTextField usernameField = new JTextField(20);
    private JPasswordField passwordField = new JPasswordField(20);
    private JButton loginButton = new JButton("Se connecter");
    private JButton retourButton = new JButton("Retour");
    
    // New UI Panels for styling consistency
    private JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
    private JPanel headerPanel = new JPanel(new BorderLayout());
    private String sql;

    public LoginFrame() {
        super("Se connecter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 350);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        // Header Styling
        headerPanel.setBackground(new Color(245, 245, 245));
        headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        JLabel headerTitle = new JLabel("Connexion");
        headerTitle.setFont(new Font("Serif", Font.BOLD, 24));
        headerTitle.setHorizontalAlignment(JLabel.CENTER);
        headerTitle.setBorder(new EmptyBorder(20, 0, 20, 0));
        headerPanel.add(headerTitle, BorderLayout.CENTER);

        // Main Form Container
        JPanel mainContainer = new JPanel();
        mainContainer.setLayout(new GridLayout(2, 1, 0, 10));
        mainContainer.setBackground(Color.WHITE);
        mainContainer.setBorder(new EmptyBorder(30, 40, 30, 40));

        userPanel.setBackground(Color.WHITE);
        passwordPanel.setBackground(Color.WHITE);

        userPanel.add(usernameLabel);
        userPanel.add(usernameField);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        mainContainer.add(userPanel);
        mainContainer.add(passwordPanel);

        // Button Panel Styling
        buttonPanel.setBackground(new Color(245, 245, 245));
        buttonPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));
        
        loginButton.setPreferredSize(new Dimension(130, 35));
        retourButton.setPreferredSize(new Dimension(100, 35));

        buttonPanel.add(retourButton);
        buttonPanel.add(loginButton);

        // Adding Panels to Frame
        add(headerPanel, BorderLayout.NORTH);
        add(mainContainer, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        retourButton.addActionListener(this);
        loginButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == retourButton) {
            new LivresORD().setVisible(true);
            this.dispose();
        } else if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (validateLogin(username, password)) {
                CurrentUser.setUsername(username);
                JOptionPane.showMessageDialog(null, "Connexion réussie !");
                if (CurrentUser.getRole().equals("Bibliothecaire")) {
                    new VueBibliothecaire().setVisible(true);
                    this.dispose();
                } else if (CurrentUser.getRole().equals("Lecteur")) {
                    new VueLecteur().setVisible(true);
                    this.dispose();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Nom d'utilisateur ou mot de passe incorrect.");
                passwordField.setText("");
                usernameField.setText("");
            }
        }
    }

    private boolean validateLogin(String username, String password) {
        if (CurrentUser.getRole() == null) {
            JOptionPane.showMessageDialog(this, "Erreur: Aucun rôle sélectionné.");
            new LivresORD().setVisible(true);
            this.dispose();
            return false;
        } else if (CurrentUser.getRole().equals("Bibliothecaire")) {
            sql = "SELECT * FROM comptes_bibliothecaire WHERE username = ? AND password = ?";
        } else if (CurrentUser.getRole().equals("Lecteur")) {
            sql = "SELECT * FROM comptes_lecteur WHERE username = ? AND password = ?";
        }
        try (Connection conn = DatabaseHandler.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
            
        } catch (SQLException e) {
            System.out.println("Login error: " + e.getMessage());
            return false;
        }
    }
}