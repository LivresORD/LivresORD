import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class LoginFrame extends JFrame implements ActionListener {
    private JLabel usernameLabel = new JLabel("Nom d'utilisateur:");
    private JLabel passwordLabel = new JLabel("Mot de passe:");
    private JPanel userPanel = new JPanel();
    private JPanel passwordPanel = new JPanel();
    private JTextField usernameField = new JTextField(20);
    private JPasswordField passwordField = new JPasswordField(20);
    private JButton loginButton = new JButton("Se connecter");
    private JButton retourButton = new JButton("Retour");
    private JPanel buttonPanel = new JPanel();
    private String sql;

    public LoginFrame() {
        super("Se connecter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        retourButton.addActionListener(this);
        loginButton.addActionListener(this);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        userPanel.add(usernameLabel);
        userPanel.add(usernameField);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        panel.add(userPanel);
        panel.add(passwordPanel);
        buttonPanel.add(retourButton);
        buttonPanel.add(loginButton);
        panel.add(buttonPanel);
        

        add(panel);

            
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
            
            // If rs.next() is true, it means a record was found
            return rs.next();
            
        } catch (SQLException e) {
            System.out.println("Login error: " + e.getMessage());
            return false;
        }
    }
}