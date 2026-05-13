import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class EmpruntsFrame extends JFrame implements ActionListener {

    private JPanel mainPanel = new JPanel();
    private JScrollPane scrollPane = new JScrollPane(mainPanel);
    private JButton btnRetour = new JButton("Retour au menu");
    private JLabel lblHeader = new JLabel("Mes Livres Empruntés", JLabel.CENTER);

    public EmpruntsFrame() {

        setTitle("LivresORD - Mes Emprunts");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        lblHeader.setFont(new Font("Serif", Font.BOLD, 22));
        lblHeader.setBorder(new EmptyBorder(10, 10, 10, 10));

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        btnRetour.addActionListener(this);

        add(lblHeader, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(btnRetour, BorderLayout.SOUTH);

        afficherEmprunts();

    }

    private void afficherEmprunts() {

        mainPanel.removeAll();

        // Fix: Use 'titre' instead of 'title'
        String sql = "SELECT books.id, books.titre FROM books " +
                     "JOIN emprunts ON books.id = emprunts.idLivre " +
                     "WHERE emprunts.username = ?";

        try (Connection conn = DatabaseHandler.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, CurrentUser.getUsername());
            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {

                    int bookId = rs.getInt("id");
                    String bookTitle = rs.getString("titre");

                    JPanel bookRow = new JPanel(new BorderLayout());
                    bookRow.setMaximumSize(new Dimension(500, 50));
                    bookRow.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

                    JLabel nameLabel = new JLabel(bookTitle);
                    JButton returnBtn = new JButton("Rendre");

                    returnBtn.addActionListener(e -> rendreLivre(bookId));

                    bookRow.add(nameLabel, BorderLayout.CENTER);
                    bookRow.add(returnBtn, BorderLayout.EAST);

                    mainPanel.add(bookRow);

                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        mainPanel.revalidate();
        mainPanel.repaint();

    }

    private void rendreLivre(int bookId) {

        String deleteEmprunt = "DELETE FROM emprunts WHERE username = ? AND idLivre = ?";
        String updateStock = "UPDATE books SET quantiteDisponible = quantiteDisponible + 1 WHERE id = ?";

        try (Connection conn = DatabaseHandler.connect()) {
            
            conn.setAutoCommit(false);

            try (PreparedStatement pstmt1 = conn.prepareStatement(deleteEmprunt);
                 PreparedStatement pstmt2 = conn.prepareStatement(updateStock)) {

                pstmt1.setString(1, CurrentUser.getUsername());
                pstmt1.setInt(2, bookId);
                pstmt1.executeUpdate();

                pstmt2.setInt(1, bookId);
                pstmt2.executeUpdate();

                conn.commit();
                JOptionPane.showMessageDialog(this, "Livre rendu avec succès!");
                afficherEmprunts();

            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRetour) {
            new VueLecteur().setVisible(true);
            this.dispose();
        }
    }
}