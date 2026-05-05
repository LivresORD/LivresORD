import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginFrame extends JFrame {
    private JLabel usernameLabel = new JLabel("Nom d'utilisateur:");
    private JLabel passwordLabel = new JLabel("Mot de passe:");
    private JPanel userPanel = new JPanel();
    private JPanel passwordPanel = new JPanel();
    private JTextField usernameField = new JTextField(20);
    private JPasswordField passwordField = new JPasswordField(20);
    private JButton loginButton = new JButton("Se connecter");
    private JButton retourButton = new JButton("Retour");
    private JPanel buttonPanel = new JPanel();

    public LoginFrame() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        retourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LivresORD().setVisible(true);
                dispose();
            }
        });
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        userPanel.add(usernameLabel);
        userPanel.add(usernameField);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        panel.add(userPanel);
        panel.add(passwordPanel);
        buttonPanel.add(loginButton);
        buttonPanel.add(retourButton);
        panel.add(buttonPanel);
        

        add(panel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
            }
        });
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}