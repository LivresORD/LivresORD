import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class VueLecteur extends JFrame implements ActionListener {
    private final JTextField searchbar = new JTextField(20);
    private final JButton searchButton = new JButton("🔎");
    private final JButton empruntsButton = new JButton("Mes emprunts");
    private final JPanel searchPanel = new JPanel();
    private final JPanel panneauLivres = new JPanel();
    private final JScrollPane scrollPane = new JScrollPane(panneauLivres);
    private final GridLayout leGrid = new GridLayout(0, 4, 10, 10);

    public VueLecteur() {
        setTitle("LivresORD - Lecteur");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        scrollPane.setViewportView(panneauLivres);
        panneauLivres.setLayout(leGrid);
        panneauLivres.setBorder(new EmptyBorder(10, 10, 10, 10));


        searchButton.addActionListener(this);
        empruntsButton.addActionListener(this);
        searchPanel.add(searchbar);
        searchPanel.add(searchButton);
        searchPanel.add(empruntsButton);
        add(searchPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        panneauLivres.setLayout(leGrid);
        loadBooks("");
    
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            String recherche = searchbar.getText();
            loadBooks(recherche);
        } else if (e.getSource() == empruntsButton) {
            // Implémentez la logique pour afficher les emprunts de l'utilisateur ici
            JOptionPane.showMessageDialog(this, "Affichage des emprunts");
        } else {
            JButton boutonSource = (JButton) e.getSource();
            String titreLivre = boutonSource.getText();
            new DetailsLivreFrame(titreLivre).setVisible(true);
            this.dispose();
        }
    }

    private void loadBooks(String recherche) {
        panneauLivres.removeAll();
        String sql = "SELECT titre FROM books WHERE titre LIKE ?";
        try (Connection conn = DatabaseHandler.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + recherche + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                JButton boutonLivres = new JButton(rs.getString("titre"));
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
