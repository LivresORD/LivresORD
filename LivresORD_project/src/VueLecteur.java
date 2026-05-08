import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VueLecteur extends JFrame implements ActionListener {
    private JTextField searchbar = new JTextField(20);
    private JButton searchButton = new JButton("🔎");
    private JPanel searchPanel = new JPanel();

    public VueLecteur() {
        setTitle("LivresORD - Lecteur");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1));

        searchButton.addActionListener(this);

        searchPanel.add(searchbar);
        searchPanel.add(searchButton);
        add(searchPanel);
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