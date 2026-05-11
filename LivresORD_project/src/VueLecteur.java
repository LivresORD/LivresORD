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
        String text = searchbar.getText();
        JOptionPane.showMessageDialog(this, "Vous avez entré: " + text);
    }

    private void loadBooks() {
    String sql = "SELECT titre FROM books";
    try (Connection conn = DatabaseHandler.connect();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        while (rs.next()) {
            panneauLivres.add(new JButton(rs.getString("titre")));
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
