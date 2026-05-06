import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class SignUpFrame extends JFrame implements ActionListener {
    private JLabel emailLabel = new JLabel("Courriel:", JLabel.LEFT);
    private JLabel usernameLabel = new JLabel("Nom d'utilisateur:", JLabel.LEFT);
    private JLabel passwordLabel = new JLabel("Mot de passe:", JLabel.LEFT);
    private JLabel confirmPasswordLabel = new JLabel("Confirmer le mot de passe:", JLabel.LEFT);
    private JPanel userPanel = new JPanel();
    private JPanel passwordPanel = new JPanel();
    private JPanel confirmPasswordPanel = new JPanel();
    private JPanel emailPanel = new JPanel();
    private JTextField emailField = new JTextField(20);
    private JTextField usernameField = new JTextField(20);
    private JPasswordField passwordField = new JPasswordField(20);
    private JPasswordField confirmPasswordField = new JPasswordField(20);
    private JButton signUpBouton = new JButton("S'inscrire");
    private JButton retourBouton = new JButton("Retour");
    private JPanel panneauBoutons = new JPanel();

    public SignUpFrame() {
        setTitle("S'inscrire");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));
        emailPanel.add(emailLabel);
        emailPanel.add(emailField);
        userPanel.add(usernameLabel);
        userPanel.add(usernameField);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        confirmPasswordPanel.add(confirmPasswordLabel);
        confirmPasswordPanel.add(confirmPasswordField);
        panel.add(userPanel);
        panel.add(emailPanel);
        panel.add(passwordPanel);
        panel.add(confirmPasswordPanel);
        panneauBoutons.add(retourBouton);
        panneauBoutons.add(signUpBouton);
        panel.add(panneauBoutons);

        add(panel);

        signUpBouton.addActionListener(this);
        retourBouton.addActionListener(this);
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
        }

        if (pass.equals(confirm)) {
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
    String sql = "INSERT INTO accounts(username, email, password) VALUES(?,?,?)";

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
        // If the username already exists, SQLite will throw an error
        JOptionPane.showMessageDialog(this, "Erreur: Ce nom d'utilisateur existe déjà.");
    }
}
}