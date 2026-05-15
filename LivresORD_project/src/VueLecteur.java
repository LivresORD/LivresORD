import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class VueLecteur extends JFrame implements ActionListener {
    private final JTextField searchbar = new JTextField(20);
    private final JButton searchButton = new JButton("🔎");
    private final JButton empruntsButton = new JButton("Mes emprunts");
    
    private final JPanel headerPanel = new JPanel(new BorderLayout());
    private final JLabel lblTitrePrincipal = new JLabel("Bibliothèque LivresORD");
    
    private final JPanel topExtrasPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    private final JButton btnDeconnexion = new JButton("Déconnexion");
    
    // Added a placeholder panel to balance the left side for perfect centering
    private final JPanel leftPlaceholder = new JPanel();
    
    private final JPanel searchPanel = new JPanel();
    private final JPanel panneauLivres = new JPanel();
    private final GridLayout leGrid = new GridLayout(0, 4, 15, 15);
    private final JPanel containerPanel = new JPanel(new BorderLayout());

    private final JScrollPane scrollPane = new JScrollPane(containerPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
    JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    public VueLecteur() {
        setTitle("LivresORD - Lecteur");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        
        lblTitrePrincipal.setFont(new Font("Serif", Font.BOLD, 28));
        lblTitrePrincipal.setForeground(new Color(44, 62, 80));
        lblTitrePrincipal.setHorizontalAlignment(JLabel.CENTER);
        lblTitrePrincipal.setBorder(new EmptyBorder(20, 0, 10, 0));

        topExtrasPanel.setOpaque(false);
        topExtrasPanel.setBorder(new EmptyBorder(10, 0, 0, 15)); 
        btnDeconnexion.setPreferredSize(new Dimension(120, 30));
        topExtrasPanel.add(btnDeconnexion);

        // Styling and adding the placeholder to balance the layout
        leftPlaceholder.setOpaque(false);
        leftPlaceholder.setPreferredSize(new Dimension(135, 30)); 

        headerPanel.setBackground(new Color(245, 245, 245));
        
        // Use the center for the title and side panels to maintain symmetry
        headerPanel.add(lblTitrePrincipal, BorderLayout.CENTER);
        headerPanel.add(leftPlaceholder, BorderLayout.WEST);
        headerPanel.add(topExtrasPanel, BorderLayout.EAST);
        headerPanel.add(searchPanel, BorderLayout.SOUTH);
        
        headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        scrollPane.setBorder(null);
        containerPanel.setBackground(Color.WHITE);
        containerPanel.add(panneauLivres, BorderLayout.NORTH);

        scrollPane.setViewportView(containerPanel);
        panneauLivres.setLayout(leGrid);
        panneauLivres.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        panneauLivres.setBackground(Color.WHITE);
        searchPanel.setBackground(new Color(245, 245, 245));
        searchPanel.setBorder(new EmptyBorder(0, 10, 20, 10));

        searchButton.addActionListener(this);
        empruntsButton.addActionListener(this);
        btnDeconnexion.addActionListener(this);
        
        searchbar.setPreferredSize(new Dimension(300, 30));
        searchButton.setPreferredSize(new Dimension(50, 30));
        empruntsButton.setPreferredSize(new Dimension(140, 30));

        searchPanel.add(searchbar);
        searchPanel.add(searchButton);
        searchPanel.add(empruntsButton);

        add(headerPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        loadBooks("");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            String recherche = searchbar.getText();
            loadBooks(recherche);
        } else if (e.getSource() == empruntsButton) {
            new EmpruntsFrame().setVisible(true);
            this.dispose();
        } else if (e.getSource() == btnDeconnexion) {
            new LivresORD().setVisible(true);
            this.dispose();
        } else {
            JButton boutonSource = (JButton) e.getSource();
            String titreLivre = boutonSource.getName();
            new DetailsLivreFrame(titreLivre).setVisible(true);
            this.dispose();
        }
    }

    private void loadBooks(String recherche) {
        panneauLivres.removeAll();
        String sql = "SELECT titre, imageExtension FROM books WHERE titre LIKE ?";
        try (Connection conn = DatabaseHandler.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + recherche + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String fullTitle = rs.getString("titre");
                String imageExtension = rs.getString("imageExtension");
                JButton boutonLivres = new JButton(fullTitle);
                boutonLivres.setName(fullTitle);
                
                // Sanitize title for filename
                String imageTitle = fullTitle.replaceAll("[\\\\/:*?\"<>|\\s]", "_").toLowerCase();
                String imagePath = "images/" + imageTitle + "." + imageExtension;
                
                if (imagePath != null) {
                    ImageIcon icon = new ImageIcon(imagePath);
                    // SCALE SMALLER: 160x200 leaves room for the text at the bottom of a 180x250 button
                    Image img = icon.getImage().getScaledInstance(160, 230, Image.SCALE_SMOOTH);
                    boutonLivres.setIcon(new ImageIcon(img));
                    
                    boutonLivres.setHorizontalTextPosition(SwingConstants.CENTER);
                    boutonLivres.setVerticalTextPosition(SwingConstants.BOTTOM);
                    // Adds space between the image and the text

                    boutonLivres.setIconTextGap(10); 
                }
                
                boutonLivres.setPreferredSize(new Dimension(180, 280));
                boutonLivres.setBackground(Color.WHITE);
                boutonLivres.setFont(new Font("Serif", Font.PLAIN, 16));
                boutonLivres.setFocusPainted(false);
                boutonLivres.setBorder(new LineBorder(new Color(220, 220, 220), 1));
                
                boutonLivres.addActionListener(this);
                panneauLivres.add(boutonLivres);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        panneauLivres.revalidate();
        panneauLivres.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VueLecteur vue = new VueLecteur();
            vue.setVisible(true);
        });
    }
}