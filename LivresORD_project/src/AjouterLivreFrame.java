//*******************************************************
//Programme: Ajouter Livre Frame
//*******************************************************
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class AjouterLivreFrame extends JFrame implements ActionListener {
    private FlowLayout leFlow = new FlowLayout();
    private JButton btnValider = new JButton("Valider");
    private JButton btnEffacer = new JButton("Effacer");
    private JButton btnRetourner = new JButton("Retourner");

    private JPanel panelDescription = new JPanel();
    private JPanel panelInsertionTitre = new JPanel();
    private JPanel panelInsertionAuteur = new JPanel();
    private JPanel panelInsertionAnnee = new JPanel();
    private JPanel panelInsertionNombreDePages = new JPanel();
    private JPanel panelResultatFormulaire = new JPanel();
    private JPanel panelBoutons = new JPanel();

    private JLabel lblDescription = new JLabel("Ajouter un livre: ", JLabel.CENTER);
    private JLabel lblInsertionTitre = new JLabel("Titre:", JLabel.CENTER);
    private JLabel lblInsertionAuteur = new JLabel("Auteur:", JLabel.CENTER);
    private JLabel lblInsertionAnnee = new JLabel("Année de Publication:", JLabel.CENTER);
    private JLabel lblNombreDePages = new JLabel("Nombre de pages:", JLabel.CENTER);
    private JLabel lblTitreResultatFormulaire = new JLabel("Résultat du formulaire: ", JLabel.CENTER);
    private JLabel lblResultatFormulaire = new JLabel("", JLabel.CENTER);

    private JTextField txtInsertionTitre = new JTextField(20);
    private JTextField txtInsertionAuteur = new JTextField(20);
    private JTextField txtInsertionAnnee = new JTextField(20);
    private JTextField txtNombreDePages = new JTextField(20);

    private GridLayout leGrid = new GridLayout(3, 2, 20, 50);
    boolean titreValide, anneeValide, nomValide, nombreDePagesValide;

    public AjouterLivreFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 2));
        setResizable(false);
        setLocationRelativeTo(null);

        panelDescription.setBackground(Color.GREEN);
        panelInsertionTitre.setBackground(Color.CYAN);
        panelInsertionAuteur.setBackground(Color.ORANGE);
        panelInsertionAnnee.setBackground(Color.GRAY);
        panelInsertionNombreDePages.setBackground(Color.WHITE);
        panelResultatFormulaire.setBackground(Color.YELLOW);
        panelBoutons.setBackground(Color.PINK);

        lblDescription.setForeground(Color.RED);
        lblDescription.setFont(new Font("Serif", Font.BOLD, 20));
        lblResultatFormulaire.setForeground(Color.RED);
        lblResultatFormulaire.setFont(new Font("Serif", Font.BOLD, 12));

        panelDescription.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelInsertionTitre.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelInsertionAuteur.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelInsertionAnnee.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelInsertionNombreDePages.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelResultatFormulaire.setLayout(new GridLayout(2, 1));
        panelBoutons.setLayout(new FlowLayout(FlowLayout.CENTER));

        panelDescription.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelInsertionTitre.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelInsertionAuteur.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelInsertionAnnee.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelInsertionNombreDePages.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelResultatFormulaire.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelBoutons.setBorder(new EmptyBorder(10, 10, 10, 10));

        btnValider.addActionListener(this);
        btnEffacer.addActionListener(this);
        btnRetourner.addActionListener(this);

        setSize(1000, 600);
        setLocationRelativeTo(null);
        setTitle("LivresORD - Ajouter un Livre");

        panelDescription.add(lblDescription);
        panelInsertionTitre.add(lblInsertionTitre);
        panelInsertionTitre.add(txtInsertionTitre);
        panelInsertionAuteur.add(lblInsertionAuteur);
        panelInsertionAuteur.add(txtInsertionAuteur);
        panelInsertionAnnee.add(lblInsertionAnnee);
        panelInsertionAnnee.add(txtInsertionAnnee);
        panelInsertionNombreDePages.add(lblNombreDePages);
        panelInsertionNombreDePages.add(txtNombreDePages);
        panelResultatFormulaire.add(lblTitreResultatFormulaire);
        panelResultatFormulaire.add(lblResultatFormulaire);
        panelBoutons.add(btnValider);
        panelBoutons.add(btnEffacer);
        panelBoutons.add(btnRetourner);

        add(panelDescription);
        add(panelInsertionTitre);
        add(panelInsertionAuteur);
        add(panelInsertionAnnee);
        add(panelInsertionNombreDePages);
        add(panelResultatFormulaire);
        add(panelBoutons);
    }

    public void actionPerformed(ActionEvent actionEvent) {
        String command = actionEvent.getActionCommand();
        try {
            if (command.equals("Effacer")) {
                txtInsertionTitre.setText("");
                txtInsertionAuteur.setText("");
                txtInsertionAnnee.setText("");
                txtNombreDePages.setText("");
            } else if (command.equals("Valider")) {
                titreValide = anneeValide = nomValide = nombreDePagesValide = true;

                if (txtInsertionTitre.getText().equals("")) {
                    lblResultatFormulaire.setText("ERREUR! Titre manquant.");
                    return;
                }
                if (txtInsertionAuteur.getText().equals("")) {
                    lblResultatFormulaire.setText("ERREUR! Auteur manquant.");
                    return;
                }
                if (txtInsertionAnnee.getText().equals("")) {
                    lblResultatFormulaire.setText("ERREUR! Année manquante.");
                    return;
                }
                if (txtNombreDePages.getText().equals("")) {
                    lblResultatFormulaire.setText("ERREUR! Nombre de pages manquant.");
                    return;
                }

                if (!txtInsertionAuteur.getText().matches("[a-zA-ZÀ-ÿ\\s'-]+")) {
                    lblResultatFormulaire.setText("ERREUR! Nom de l'auteur invalide.");
                    return;
                }

                try {
                    int annee = Integer.parseInt(txtInsertionAnnee.getText());
                    if (annee < -7000000 || annee > 2026) throw new NumberFormatException();
                } catch (NumberFormatException e) {
                    lblResultatFormulaire.setText("ERREUR! Année invalide.");
                    return;
                }

                try {
                    int pages = Integer.parseInt(txtNombreDePages.getText());
                    if (pages < 0 || pages > 15000) throw new NumberFormatException();
                } catch (NumberFormatException e) {
                    lblResultatFormulaire.setText("ERREUR! Nombre de pages invalide.");
                    return;
                }

                lblResultatFormulaire.setText("Votre réponse a été enregistrée avec succès!");
            } else if (command.equals("Retourner")) {
                this.dispose();
            }
            repaint();
        } catch (Exception e) {
            System.out.println("Il y a eu une erreur avec l'inscription.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AjouterLivreFrame().setVisible(true));
    }
}