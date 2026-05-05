import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SignUpFrame extends JFrame {
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
        Frame dialog = new Frame();
        panel.add(userPanel);
        panel.add(emailPanel);
        panel.add(passwordPanel);
        panel.add(confirmPasswordPanel);
        panneauBoutons.add(retourBouton);
        panneauBoutons.add(signUpBouton);
        panel.add(panneauBoutons);

        add(panel);

        signUpBouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());
                if (password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(null, "Inscription réussie pour " + username);
                } else {
                    JOptionPane.showMessageDialog(null, "Les mots de passe ne correspondent pas.");
                }
            }
        });
        retourBouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LivresORD().setVisible(true);
                dispose();
            }
        });
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SignUpFrame().setVisible(true));
    }
}