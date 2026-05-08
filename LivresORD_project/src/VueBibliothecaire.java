import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class VueBibliothecaire extends JFrame implements ActionListener {
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
        ajouterLivreButton.addActionListener(this);
        supprimerLivreButton.addActionListener(this);
        modifierLivreButton.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ajouterLivreButton) {
            new AjouterLivreFrame().setVisible(true);
            this.dispose();
        //} else if (e.getSource() == supprimerLivreButton) {
        //    new SupprimerLivreFrame().setVisible(true);
        //    this.dispose();
        //} else if (e.getSource() == modifierLivreButton) {
        //    new ModifierLivreFrame().setVisible(true);
        //    this.dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VueBibliothecaire().setVisible(true));
    }
}