import javax.swing.*;

public class VueBibliothecaire extends JFrame {
    private JLabel TitreLabel = new JLabel("Gestion des Livres", JLabel.CENTER);
    private JButton ajouterLivreButton = new JButton("Ajouter un Livre");
    private JButton supprimerLivreButton = new JButton("Supprimer un Livre");
    private JButton modifierLivreButton = new JButton("Modifier un Livre");
    private JPanel buttonPanel = new JPanel();

    public VueBibliothecaire() {
        setTitle("LivresORD - Bibliothécaire");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        add(TitreLabel);
        buttonPanel.add(ajouterLivreButton);
        buttonPanel.add(supprimerLivreButton);
        buttonPanel.add(modifierLivreButton);
        add(buttonPanel, "South");


    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VueBibliothecaire().setVisible(true));
    }
}