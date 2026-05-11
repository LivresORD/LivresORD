import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class VueLecteur extends JFrame implements ActionListener {
    private JTextField searchbar = new JTextField(20);
    private JButton searchButton = new JButton("🔎");
    private JButton empruntsButton = new JButton("Mes emprunts");
    private JPanel searchPanel = new JPanel();
    private JPanel panneauLivres = new JPanel();
    private GridLayout leGrid = new GridLayout(1, 4, 10, 10);

    public VueLecteur() {
        setTitle("LivresORD - Lecteur");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3,1));
        setLocationRelativeTo(null);
        panneauLivres.setBorder(new EmptyBorder(10, 10, 10, 10));


        searchButton.addActionListener(this);
        empruntsButton.addActionListener(this);
        searchPanel.add(searchbar);
        searchPanel.add(searchButton);
        searchPanel.add(empruntsButton);
        add(searchPanel);
        add(panneauLivres);
        
        panneauLivres.setLayout(leGrid);

        panneauLivres.add(new JButton("Livre 1"));
        panneauLivres.add(new JButton("Livre 2"));
        panneauLivres.add(new JButton("Livre 3"));
        panneauLivres.add(new JButton("Livre 4"));
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = searchbar.getText();
        JOptionPane.showMessageDialog(this, "Vous avez entré: " + text);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VueLecteur vue = new VueLecteur();
            vue.setVisible(true);
        });
    }
}
