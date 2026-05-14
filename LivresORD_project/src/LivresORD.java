import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class LivresORD extends JFrame implements ActionListener {

    // composantes du GUI
    private JLabel utilisateurLabel = new JLabel("Choisissez votre rôle", JLabel.CENTER);
    private JLabel imageLabel1 = new JLabel();
    private JLabel imageLabel2 = new JLabel();
    private JButton adminButton = new JButton();
    private JButton userButton = new JButton();
    private JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
    private JPanel loginPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
    private JButton signUpButton = new JButton("S'inscrire");
    private JButton loginButton = new JButton("Se connecter");
    private ImageIcon imageIcon = new ImageIcon("src/images/user.jpg");

    // New Header Panel for styling consistency
    private JPanel headerPanel = new JPanel(new BorderLayout());

    // variable pour stocker le rôle sélectionné
    public Boolean isAdmin = null;

    // constructeur
    public LivresORD() {
        super("LivresORD");

        // General Frame Setup
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        // Header Styling
        headerPanel.setBackground(new Color(245, 245, 245));
        headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
        
        utilisateurLabel.setFont(new Font("Serif", Font.BOLD, 28));
        utilisateurLabel.setForeground(new Color(44, 62, 80));
        utilisateurLabel.setBorder(new EmptyBorder(30, 0, 30, 0));
        headerPanel.add(utilisateurLabel, BorderLayout.CENTER);

        // Button Panel (Role Selection) Styling
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(new EmptyBorder(20, 10, 10, 10));

        // Sign Up / Login Panel Styling
        loginPanel.setBackground(new Color(245, 245, 245));
        loginPanel.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));

        // action listeners
        userButton.addActionListener(this);
        adminButton.addActionListener(this);
        signUpButton.addActionListener(this);
        loginButton.addActionListener(this);
        
        loginButton.setEnabled(false);
        signUpButton.setEnabled(false);
        
        // Button dimensioning for consistency
        loginButton.setPreferredSize(new Dimension(120, 35));
        signUpButton.setPreferredSize(new Dimension(120, 35));

        // redimensionner l'image et l'ajouter au label
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH));
        imageLabel1.setIcon(imageIcon);
        imageLabel2.setIcon(imageIcon);

        // Styling the Role Buttons as "Cards"
        styleRoleButton(userButton, imageLabel1, "Lecteur");
        styleRoleButton(adminButton, imageLabel2, "Bibliothecaire");

        buttonPanel.add(adminButton);
        buttonPanel.add(userButton);
        
        loginPanel.add(signUpButton);
        loginPanel.add(loginButton);

        // Final assembly
        add(headerPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(loginPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // New helper method to style the role buttons without changing original structure
    private void styleRoleButton(JButton btn, JLabel img, String text) {
        btn.setLayout(new BorderLayout());
        btn.setPreferredSize(new Dimension(180, 180));
        btn.setBackground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(new LineBorder(new Color(220, 220, 220), 1));
        
        img.setHorizontalAlignment(JLabel.CENTER);
        img.setBorder(new EmptyBorder(10, 0, 0, 0));
        
        JLabel txtLabel = new JLabel(text, JLabel.CENTER);
        txtLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        txtLabel.setBorder(new EmptyBorder(0, 0, 15, 0));

        btn.add(img, BorderLayout.CENTER);
        btn.add(txtLabel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == adminButton) {
            adminButton.setBorder(new LineBorder(new Color(44, 62, 80), 3));
            userButton.setBorder(new LineBorder(new Color(220, 220, 220), 1));
            adminButton.setBackground(new Color(236, 240, 241));
            userButton.setBackground(Color.WHITE);
            isAdmin = true;
            loginButton.setEnabled(true);
            signUpButton.setEnabled(true);
        } else if (e.getSource() == userButton) {
            userButton.setBorder(new LineBorder(new Color(44, 62, 80), 3));
            adminButton.setBorder(new LineBorder(new Color(220, 220, 220), 1));
            userButton.setBackground(new Color(236, 240, 241));
            adminButton.setBackground(Color.WHITE);
            isAdmin = false;
            loginButton.setEnabled(true);
            signUpButton.setEnabled(true);
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