import javax.swing.*;

public class VueBibliothecaire extends JFrame {
    private JLabel TitreLabel = new JLabel("Gestion des Livres", JLabel.CENTER);

    public VueBibliothecaire() {
        setTitle("LivresORD - Bibliothécaire");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        add(TitreLabel);

    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VueBibliothecaire().setVisible(true));
    }
}