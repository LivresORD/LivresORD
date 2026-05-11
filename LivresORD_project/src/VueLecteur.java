import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class VueLecteur extends JFrame implements ActionListener {
    private JTextField searchbar = new JTextField(20);
    private JButton searchButton = new JButton("🔎");
    private JButton empruntsButton = new JButton("Mes emprunts");
    private JPanel searchPanel = new JPanel();
    private JPanel panneauLivres = new JPanel();
    private JScrollPane scrollPane = new JScrollPane(panneauLivres);
    private GridLayout leGrid = new GridLayout(0, 4, 10, 10);

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
        loadBooks();
    
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            String query = searchbar.getText();
            // Implémentez la logique de recherche ici
            JOptionPane.showMessageDialog(this, "Recherche pour: " + query);
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

    private void loadBooks() {
        String sql = "SELECT titre FROM books";
        try (Connection conn = DatabaseHandler.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                JButton boutonLivres = new JButton(rs.getString("titre"));
                boutonLivres.addActionListener(this);
                panneauLivres.add(boutonLivres);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VueLecteur vue = new VueLecteur();
            vue.setVisible(true);
        });
    }
}
