import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;

public class LivresORD extends JFrame implements ActionListener {

    // composantes du GUI
    private JLabel utilisateurLabel = new JLabel("Choisissez votre rôle:");
    private JLabel imageLabel = new JLabel();
    private JButton adminButton = new JButton();
    private JButton userButton = new JButton();
    private JPanel buttonPanel = new JPanel();
    private JPanel loginPanel = new JPanel();
    private JButton signUpButton = new JButton("Sign up");
    private JButton loginButton = new JButton("Login");
    private ImageIcon imageIcon = new ImageIcon("src/images/user.jpg");

    // variable pour stocker le rôle sélectionné
    public Boolean isAdmin = null;

    // constructeur
    public LivresORD() {
        super("LivresORD");

        // ajouter les boutons au frame
        userButton.addActionListener(this);
        adminButton.addActionListener(this);
        signUpButton.addActionListener(this);
        loginButton.addActionListener(this);
        buttonPanel.add(adminButton);
        buttonPanel.add(userButton);
        loginPanel.add(signUpButton);
        loginPanel.add(loginButton);

        // redimensionner l'image et l'ajouter au label
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH));
        imageLabel.setIcon(imageIcon);
        userButton.setLayout(new GridLayout(2, 1));
        userButton.add(imageLabel);
        userButton.add(new JLabel("Lecteur"));
        adminButton.setLayout(new GridLayout(2, 1));
        adminButton.add(imageLabel);
        adminButton.add(new JLabel("Bibliothecaire"));
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
            if (isAdmin) {
                CurrentUser.setRole("Bibliothecaire");
            } else {
                CurrentUser.setRole("Lecteur");
            }
        } else if (e.getSource() == signUpButton) {
            new SignUpFrame().setVisible(true);
            this.dispose();
                if (isAdmin) {
                    CurrentUser.setRole("Bibliothecaire");
                } else if (isAdmin != null) {
                    CurrentUser.setRole("Lecteur");
                }
        }
    }

    public static void main(String[] args) {
        DatabaseHandler.setupDatabase();
        SwingUtilities.invokeLater(() -> new LivresORD());
    }
}