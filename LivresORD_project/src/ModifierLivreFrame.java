//*******************************************************
//Programme: Modifier Livre Frame
//*******************************************************
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ModifierLivreFrame extends JFrame implements ActionListener {
    FlowLayout leFlow = new FlowLayout();
    private JButton btnValider = new JButton("Valider");
    private JButton btnEffacer = new JButton("Effacer");
    private JButton btnRetourner = new JButton("Retourner");
    private JPanel panelDescriptionPrincipale = new JPanel();
    private JPanel panelDescriptionEtape1 = new JPanel();
    private JPanel panelInsertionTitre = new JPanel();
    private JPanel panelInsertionAuteur = new JPanel();
    private JPanel panelInsertionAnnee = new JPanel();
    private JPanel panelInsertionNombreDePages = new JPanel();
    private JPanel panelDescriptionEtape2 = new JPanel();
    private JPanel panelInsertionTitreNouveau = new JPanel();
    private JPanel panelInsertionAuteurNouveau = new JPanel();
    private JPanel panelInsertionAnneeNouveau = new JPanel();
    private JPanel panelInsertionNombreDePagesNouveau = new JPanel();
    private JPanel panelResultatFormulaire = new JPanel();
    private JPanel panelBoutons = new JPanel();
    private JLabel lblDescriptionPrincipale = new JLabel("Modifier un livre: ", JLabel.CENTER);
    private JLabel lblDescriptionEtape1 = new JLabel("Insérez dans les cases ci-dessous, les informations initiales à propos du livre que vous désirez modifier: ", JLabel.CENTER);
    private JLabel lblInsertionTitre = new JLabel("Titre Initiale:", JLabel.CENTER);
    private JLabel lblInsertionAuteur = new JLabel("Auteur Initiale:", JLabel.CENTER);
    private JLabel lblInsertionAnnee = new JLabel("Année de Publication Initiale:", JLabel.CENTER);
    private JLabel lblNombreDePages = new JLabel("Nombre de pages Initiale:", JLabel.CENTER);
    private JLabel lblDescriptionEtape2 = new JLabel("Insérez dans les cases ci-dessous, les nouvelles informations à propos du livre que vous désirez modifier: ", JLabel.CENTER);
    private JLabel lblInsertionTitreNouveau = new JLabel("Nouveau Titre:", JLabel.CENTER);
    private JLabel lblInsertionAuteurNouveau = new JLabel("Nouveau Auteur:", JLabel.CENTER);
    private JLabel lblInsertionAnneeNouveau = new JLabel("Nouveau Année de Publication:", JLabel.CENTER);
    private JLabel lblInsertionNombreDePagesNouveau = new JLabel("Nouveau Nombre de pages:", JLabel.CENTER);
    private JLabel lblTitreResultatFormulaire = new JLabel("Résultat du formulaire: ", JLabel.CENTER);
    private JLabel lblResultatFormulaire = new JLabel("", JLabel.CENTER);
    private JTextField txtInsertionTitre = new JTextField(20);
    private JTextField txtInsertionAuteur = new JTextField(20);
    private JTextField txtInsertionAnnee = new JTextField(20);
    private JTextField txtNombreDePages = new JTextField(20);
    private JTextField txtInsertionTitreNouveau = new JTextField(20);
    private JTextField txtInsertionAuteurNouveau = new JTextField(20);
    private JTextField txtInsertionAnneeNouveau = new JTextField(20);
    private JTextField txtInsertionNombreDePagesNouveau = new JTextField(20);
    private GridLayout leGrid = new GridLayout(3, 2, 20, 50);
    boolean titreValide;
    boolean anneeValide;
    boolean nomValide;
    boolean nombreDePagesValide;
    boolean titreNouveauValide;
    boolean anneeNouveauValide;
    boolean nomNouveauValide;
    boolean nombreDePagesNouveauValide;

    public ModifierLivreFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(13,2));
        setResizable(false);
        setLocationRelativeTo(null);

        panelDescriptionPrincipale.setBackground(Color.GREEN);
        panelDescriptionEtape1.setBackground(Color.MAGENTA);
        panelInsertionTitre.setBackground(Color.CYAN);
        panelInsertionAuteur.setBackground(Color.ORANGE);
        panelInsertionAnnee.setBackground(Color.GRAY);
        panelInsertionNombreDePages.setBackground(Color.WHITE);
        panelDescriptionEtape2.setBackground(Color.MAGENTA);
        panelInsertionTitreNouveau.setBackground(Color.CYAN);
        panelInsertionAuteurNouveau.setBackground(Color.ORANGE);
        panelInsertionAnneeNouveau.setBackground(Color.GRAY);
        panelInsertionNombreDePagesNouveau.setBackground(Color.WHITE);
        panelResultatFormulaire.setBackground(Color.YELLOW);
        panelBoutons.setBackground(Color.PINK);
        lblDescriptionPrincipale.setForeground(Color.RED);

        lblDescriptionPrincipale.setFont(new Font("Serif", Font.BOLD, 20));
        lblDescriptionEtape1.setForeground(Color.BLUE);
        lblDescriptionEtape1.setFont(new Font("Serif", Font.BOLD, 20));
        lblDescriptionEtape2.setForeground(Color.BLUE);
        lblDescriptionEtape2.setFont(new Font("Serif", Font.BOLD, 20));
        lblResultatFormulaire.setForeground(Color.RED);
        lblResultatFormulaire.setFont(new Font("Serif", Font.BOLD, 12));
        
        panelDescriptionPrincipale.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelDescriptionEtape1.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelInsertionTitre.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelInsertionAuteur.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelInsertionAnnee.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelInsertionNombreDePages.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelDescriptionEtape2.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelInsertionTitreNouveau.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelInsertionAuteurNouveau.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelInsertionAnneeNouveau.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelInsertionNombreDePagesNouveau.setLayout(new FlowLayout(FlowLayout.CENTER));
        panelResultatFormulaire.setLayout(new GridLayout(2, 1));
        panelBoutons.setLayout(new FlowLayout(FlowLayout.CENTER));

        panelDescriptionPrincipale.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelDescriptionEtape1.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelInsertionTitre.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelInsertionAuteur.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelInsertionAnnee.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelInsertionNombreDePages.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelDescriptionEtape2.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelInsertionTitreNouveau.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelInsertionAuteurNouveau.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelInsertionAnneeNouveau.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelInsertionNombreDePagesNouveau.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelResultatFormulaire.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelBoutons.setBorder(new EmptyBorder(10, 10, 10, 10));

        btnValider.addActionListener( this );
        btnEffacer.addActionListener( this );
        btnRetourner.addActionListener( this );

        setSize(1300, 800);
        setLocationRelativeTo(null);
        setTitle("LivresORD - Modifier un Livre");

        panelDescriptionPrincipale.add(lblDescriptionPrincipale);
        panelDescriptionEtape1.add(lblDescriptionEtape1);

        panelInsertionTitre.add(lblInsertionTitre);
        panelInsertionTitre.add(txtInsertionTitre);

        panelInsertionAuteur.add(lblInsertionAuteur);
        panelInsertionAuteur.add(txtInsertionAuteur);

        panelInsertionAnnee.add(lblInsertionAnnee);
        panelInsertionAnnee.add(txtInsertionAnnee);

        panelInsertionNombreDePages.add(lblNombreDePages);
        panelInsertionNombreDePages.add(txtNombreDePages);

        panelDescriptionEtape2.add(lblDescriptionEtape2);

        panelInsertionTitreNouveau.add(lblInsertionTitreNouveau);
        panelInsertionTitreNouveau.add(txtInsertionTitreNouveau);

        panelInsertionAuteurNouveau.add(lblInsertionAuteurNouveau);
        panelInsertionAuteurNouveau.add(txtInsertionAuteurNouveau);

        panelInsertionAnneeNouveau.add(lblInsertionAnneeNouveau);
        panelInsertionAnneeNouveau.add(txtInsertionAnneeNouveau);

        panelInsertionNombreDePagesNouveau.add(lblInsertionNombreDePagesNouveau);
        panelInsertionNombreDePagesNouveau.add(txtInsertionNombreDePagesNouveau);

        panelResultatFormulaire.add(lblTitreResultatFormulaire);
        panelResultatFormulaire.add(lblResultatFormulaire);

        panelBoutons.add(btnValider);
        panelBoutons.add(btnEffacer);
        panelBoutons.add(btnRetourner);
        
        add(panelDescriptionPrincipale);
        add(panelDescriptionEtape1);
        add(panelInsertionTitre);
        add(panelInsertionAuteur);
        add(panelInsertionAnnee);
        add(panelInsertionNombreDePages);
        add(panelDescriptionEtape2);
        add(panelInsertionTitreNouveau);
        add(panelInsertionAuteurNouveau);
        add(panelInsertionAnneeNouveau);
        add(panelInsertionNombreDePagesNouveau);
        add(panelResultatFormulaire);
        add(panelBoutons);
    }

    public void actionPerformed( ActionEvent actionEvent ) {
        String command = actionEvent.getActionCommand();
        try {
            if (command.equals("Effacer")) {
                txtInsertionTitre.setText("");
                txtInsertionAuteur.setText("");
                txtInsertionAnnee.setText("");
                txtNombreDePages.setText("");
                txtInsertionTitreNouveau.setText("");
                txtInsertionAuteurNouveau.setText("");
                txtInsertionAnneeNouveau.setText("");
                txtInsertionNombreDePagesNouveau.setText("");
            } else if (command.equals("Valider")) {
                titreValide = true;
                anneeValide = true;
                nomValide = true;
                nombreDePagesValide = true;
                titreNouveauValide = true;
                anneeNouveauValide = true;
                nomNouveauValide = true;
                nombreDePagesNouveauValide = true;
                if(txtInsertionTitre.getText().equals("")) {
                    titreValide = false;
                    lblResultatFormulaire.setText("ERREUR! Vous n'avez pas inséré un titre pour le livre. Svp, révisez vôtre réponse pour le titre initiale du livre.");
                    return;
                }
                if(txtInsertionAuteur.getText().equals("")) {
                    nomValide = false;
                    lblResultatFormulaire.setText("ERREUR! Vous n'avez pas inséré un nom d'auteur pour le livre. Svp, révisez vôtre réponse pour le nom d'auteur initiale du livre.");
                    return;
                }
                if(txtInsertionAnnee.getText().equals("")) {
                    anneeValide = false;
                    lblResultatFormulaire.setText("ERREUR! Vous n'avez pas inséré une année de publication pour le livre. Svp, révisez vôtre réponse pour l'année de publication initiale du livre.");
                    return;
                }
                if(txtNombreDePages.getText().equals("")) {
                    nombreDePagesValide = false;
                    lblResultatFormulaire.setText("ERREUR! Vous n'avez pas inséré le nombre de pages pour le livre. Svp, révisez vôtre réponse pour le nombre de pages initiale du livre.");
                    return;
                }
                if(txtInsertionTitreNouveau.getText().equals("")) {
                    titreNouveauValide = false;
                    lblResultatFormulaire.setText("ERREUR! Vous n'avez pas inséré un titre pour le livre. Svp, révisez vôtre réponse pour le tire finale du livre.");
                    return;
                }
                if(txtInsertionAuteurNouveau.getText().equals("")) {
                    nomNouveauValide = false;
                    lblResultatFormulaire.setText("ERREUR! Vous n'avez pas inséré un nom d'auteur pour le livre. Svp, révisez vôtre réponse pour le nom d'auteur finale du livre.");
                    return;
                }
                if(txtInsertionAnneeNouveau.getText().equals("")) {
                    anneeNouveauValide = false;
                    lblResultatFormulaire.setText("ERREUR! Vous n'avez pas inséré une année de publication pour le livre. Svp, révisez vôtre réponse pour l'année de publication finale du livre.");
                    return;
                }
                if(txtInsertionNombreDePagesNouveau.getText().equals("")) {
                    nombreDePagesNouveauValide = false;
                    lblResultatFormulaire.setText("ERREUR! Vous n'avez pas inséré le nombre de pages pour le livre. Svp, révisez vôtre réponse pour le nombre de pages finale du livre.");
                    return;
                }
                if (txtInsertionAuteur.getText().matches("\\d+")) {
                    nomValide = false;
                    lblResultatFormulaire.setText("ERREUR! Le nom de l'auteur doit contenir des lettres. Svp, révisez vôtre réponse pour le nom d'auteur initiale.");
                    return;
                } else if (!txtInsertionAuteur.getText().matches("[a-zA-ZÀ-ÿ\\s'-]+")) {
                    nomValide = false;
                    lblResultatFormulaire.setText("ERREUR! Le nom de l'auteur doit seulement contenir des lettres et non des nombres. Svp, révisez vôtre réponse pour le nom d'auteur initiale.");
                    return;
                } else {
                    nomValide = true;
                }
                try {
                    Integer.parseInt(txtInsertionAnnee.getText());
                    anneeValide = true;
                } catch(NumberFormatException e) {
                    anneeValide = false;
                    lblResultatFormulaire.setText("ERREUR! L'année de l'écriture du livre doit être un entier et non une chaîne de caractères. Svp, révisez vôtre réponse pour l'année d'écriture du livre initiale.");
                    return;
                }
                if(Integer.parseInt(txtInsertionAnnee.getText()) < -7000000) {
                    anneeValide = false;
                    lblResultatFormulaire.setText("ERREUR! L'année de publication du livre ne peut pas être plus ancien que 7 000 000 avant JC. Svp, révisez vôtre réponse pour l'année de publication initiale.");
                    return;
                } else if(Integer.parseInt(txtInsertionAnnee.getText()) > 2026) {
                    anneeValide = false;
                    lblResultatFormulaire.setText("ERREUR! L'année de publication du livre ne peut pas être plus tard que 2026. Svp, révisez vôtre réponse pour l'année de publication initiale.");
                    return;
                } else {
                    anneeValide = true;
                }
                try {
                    Integer.parseInt(txtNombreDePages.getText());
                    nombreDePagesValide = true;
                } catch(NumberFormatException e) {
                    nombreDePagesValide = false;
                    lblResultatFormulaire.setText("ERREUR! Le nombre de pages du livre doit être un entier et non une chaîne de caractères. Svp, révisez vôtre réponse pour le nombre de pages initiales.");
                    return;
                }
                if(Integer.parseInt(txtNombreDePages.getText()) < 0) {
                    lblResultatFormulaire.setText("ERREUR! Le nombre de pages du livre ne peut pas prendre une valeur négative. Svp, révisez vôtre réponse pour le nombre de pages initiales.");
                    nombreDePagesValide = false;
                    return;
                } else if(Integer.parseInt(txtNombreDePages.getText()) > 15000) {
                    lblResultatFormulaire.setText("ERREUR! Un livre de plus de 15000 pages bât de manière significative le record. Svp, révisez vôtre réponse pour le nombre de pages initiales.");
                    nombreDePagesValide = false;
                    return;
                } else {
                    nombreDePagesValide = true;
                }
                if (txtInsertionAuteurNouveau.getText().matches("\\d+")) {
                    nomNouveauValide = false;
                    lblResultatFormulaire.setText("ERREUR! Le nom de l'auteur doit contenir des lettres. Svp, révisez vôtre réponse pour le nom d'auteur finale.");
                    return;
                } else if (!txtInsertionAuteurNouveau.getText().matches("[a-zA-ZÀ-ÿ\\s'-]+")) {
                    nomNouveauValide = false;
                    lblResultatFormulaire.setText("ERREUR! Le nom de l'auteur doit seulement contenir des lettres et non des nombres. Svp, révisez vôtre réponse pour le nom d'auteur finale.");
                    return;
                } else {
                    nomNouveauValide = true;
                }
                try {
                    Integer.parseInt(txtInsertionAnneeNouveau.getText());
                    anneeNouveauValide = true;
                } catch(NumberFormatException e) {
                    anneeNouveauValide = false;
                    lblResultatFormulaire.setText("ERREUR! L'année de l'écriture du livre doit être un entier et non une chaîne de caractères. Svp, révisez vôtre réponse pour l'année d'écriture du livre finale.");
                    return;
                }
                if(Integer.parseInt(txtInsertionAnneeNouveau.getText()) < -7000000) {
                    anneeNouveauValide = false;
                    lblResultatFormulaire.setText("ERREUR! L'année de publication du livre ne peut pas être plus ancien que 7 000 000 avant JC. Svp, révisez vôtre réponse pour l'année de publication finale.");
                    return;
                } else if(Integer.parseInt(txtInsertionAnneeNouveau.getText()) > 2026) {
                    anneeNouveauValide = false;
                    lblResultatFormulaire.setText("ERREUR! L'année de publication du livre ne peut pas être plus tard que 2026. Svp, révisez vôtre réponse pour l'année de publication finale.");
                    return;
                } else {
                    anneeNouveauValide = true;
                }
                try {
                    Integer.parseInt(txtInsertionNombreDePagesNouveau.getText());
                    nombreDePagesNouveauValide = true;
                } catch(NumberFormatException e) {
                    nombreDePagesNouveauValide = false;
                    lblResultatFormulaire.setText("ERREUR! Le nombre de pages du livre doit être un entier et non une chaîne de caractères. Svp, révisez vôtre réponse pour le nombre de pages finales.");
                    return;
                }
                if(Integer.parseInt(txtInsertionNombreDePagesNouveau.getText()) < 0) {
                    lblResultatFormulaire.setText("ERREUR! Le nombre de pages du livre ne peut pas prendre une valeur négative. Svp, révisez vôtre réponse pour le nombre de pages finales.");
                    nombreDePagesNouveauValide = false;
                    return;
                } else if(Integer.parseInt(txtInsertionNombreDePagesNouveau.getText()) > 15000) {
                    lblResultatFormulaire.setText("ERREUR! Un livre de plus de 15000 pages bât de manière significative le record. Svp, révisez vôtre réponse pour le nombre de pages finales.");
                    nombreDePagesNouveauValide = false;
                    return;
                } else {
                    nombreDePagesNouveauValide = true;
                }
                if(titreValide && anneeValide && nomValide && nombreDePagesValide && titreNouveauValide && anneeNouveauValide && nomNouveauValide && nombreDePagesNouveauValide) {
                    lblResultatFormulaire.setText("Vôtre réponse a été enregistré avec succès!");
                } else {
                    lblResultatFormulaire.setText("Malheureusement, il y a eu des erreurs lors de l'inscription!");
                }
            } else if (command.equals("Retourner")) {
                new VueBibliothecaire().setVisible(true);
                this.dispose();
            }
            repaint();
        } catch (Exception e) {
            System.out.println("Il y a eu une erreur avec l'inscription du fichier.");
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ModifierLivreFrame().setVisible(true));
    }
}