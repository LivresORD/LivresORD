import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

public class LivresORD extends JFrame implements ActionListener {
    private JLabel utilisateurLabel = new JLabel("Choisissez votre rôle:");
    private JLabel imageLabel = new JLabel();
    private JButton adminButton = new JButton("Bibliothecaire");
    private JButton userButton = new JButton("Lecteur");
    private JPanel buttonPanel = new JPanel();
    private JPanel loginPanel = new JPanel();
    private JButton signUpButton = new JButton("Sign up");
    private JButton loginButton = new JButton("Login");
    private ImageIcon imageIcon = new ImageIcon("images/user.jpg");
    public boolean isAdmin = false;
    public LivresORD() {
        super("LivresORD");
        userButton.addActionListener(this);
        adminButton.addActionListener(this);
        signUpButton.addActionListener(this);
        loginButton.addActionListener(this);
        buttonPanel.add(adminButton);
        buttonPanel.add(userButton);
        loginPanel.add(signUpButton);
        loginPanel.add(loginButton);
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
        imageLabel.setIcon(imageIcon);
        userButton.setLayout(new GridLayout(2, 1));
        userButton.add(imageLabel);
        userButton.add(new JLabel("Lecteur", SwingConstants.CENTER));
        adminButton.setLayout(new GridLayout(2, 1));
        adminButton.add(imageLabel);
        adminButton.add(new JLabel("Bibliothecaire", SwingConstants.CENTER));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setVisible(true);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1));
        
        add(utilisateurLabel);
        add(buttonPanel);
        add(loginPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == adminButton) {
            adminButton.setBackground(java.awt.Color.decode("#c9c9c9"));
            userButton.setBackground(null);
            isAdmin = true;
        } else if (e.getSource() == userButton) {
            userButton.setBackground(java.awt.Color.decode("#c9c9c9"));
            adminButton.setBackground(null);
            isAdmin = false;
        }
        if (e.getSource() == loginButton) {
            new LoginFrame().setVisible(true);
            this.dispose();
        } else if (e.getSource() == signUpButton) {
            new SignUpFrame().setVisible(true);
            this.dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LivresORD());
    }
}