//*******************************************************
//Programme: Ajouter Livre Frame
//*******************************************************
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

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
	private JPanel panelInsertionQuantite = new JPanel();
    private JPanel panelImage = new JPanel();
    private JPanel panelResultatFormulaire = new JPanel();
    private JPanel panelBoutons = new JPanel();

    private JLabel lblDescription = new JLabel("Ajouter un livre: ", JLabel.CENTER);
    private JLabel lblInsertionTitre = new JLabel("Titre:", JLabel.CENTER);
    private JLabel lblInsertionAuteur = new JLabel("Auteur:", JLabel.CENTER);
    private JLabel lblInsertionAnnee = new JLabel("Année de Publication:", JLabel.CENTER);
    private JLabel lblNombreDePages = new JLabel("Nombre de pages:", JLabel.CENTER);
    private JLabel lblQuantite = new JLabel("Quantité disponible:", JLabel.CENTER);
    private JLabel lblTitreResultatFormulaire = new JLabel("Résultat du formulaire: ", JLabel.CENTER);
    private JLabel lblResultatFormulaire = new JLabel("", JLabel.CENTER);

    private JTextField txtInsertionTitre = new JTextField(20);
    private JTextField txtInsertionAuteur = new JTextField(20);
    private JTextField txtInsertionAnnee = new JTextField(20);
    private JTextField txtNombreDePages = new JTextField(20);
    private JTextField txtQuantite = new JTextField(20);

    private JButton btnImporterImage = new JButton("Importer une image de couverture");

    private GridLayout leGrid = new GridLayout(3, 2, 20, 50);
    boolean titreValide, anneeValide, nomValide, nombreDePagesValide;

    public AjouterLivreFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(9, 2));
        setResizable(false);
        setLocationRelativeTo(null);

        panelDescription.setBackground(Color.GREEN);
        panelInsertionTitre.setBackground(Color.CYAN);
        panelInsertionAuteur.setBackground(Color.ORANGE);
        panelInsertionAnnee.setBackground(Color.GRAY);
        panelInsertionNombreDePages.setBackground(Color.WHITE);
        panelInsertionQuantite.setBackground(Color.LIGHT_GRAY);
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
        btnImporterImage.addActionListener(e -> importImage()); // Source: https://stackoverflow.com/questions/284899/how-do-you-add-an-actionlistener-onto-a-jbutton-in-java

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
		panelInsertionQuantite.add(lblQuantite);
		panelInsertionQuantite.add(txtQuantite);
        panelResultatFormulaire.add(lblTitreResultatFormulaire);
        panelResultatFormulaire.add(lblResultatFormulaire);
        panelBoutons.add(btnValider);
        panelBoutons.add(btnEffacer);
        panelBoutons.add(btnRetourner);
        panelImage.add(btnImporterImage);

        add(panelDescription);
        add(panelInsertionTitre);
        add(panelInsertionAuteur);
        add(panelInsertionAnnee);
        add(panelInsertionNombreDePages);
        add(panelInsertionQuantite);
        add(panelImage);
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
                txtQuantite.setText("");
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

                try {
                    int quantite = Integer.parseInt(txtQuantite.getText());
                    if (quantite < 0 || quantite > 1000) throw new NumberFormatException();
                } catch (NumberFormatException e) {
                    lblResultatFormulaire.setText("ERREUR! Quantité invalide.");
                    return;
                }

				if (saveBookToDatabase(txtInsertionTitre.getText(), txtInsertionAuteur.getText(), Integer.parseInt(txtInsertionAnnee.getText()), Integer.parseInt(txtNombreDePages.getText()), Integer.parseInt(txtQuantite.getText()))) {
					lblResultatFormulaire.setText("Le livre a été ajouté avec succès!");
				} else {
					lblResultatFormulaire.setText("ERREUR! Echec de l'ajout du livre à la base de données.");
				}

			} else if (command.equals("Retourner")) {
				new VueBibliothecaire().setVisible(true);
                this.dispose();
            }
            repaint();
        } catch (Exception e) {
            System.out.println("Il y a eu une erreur avec l'inscription.");
        }
    }

	private boolean saveBookToDatabase(String title, String author, int year, int pages, int quantite) {
        String sql = "INSERT INTO books(titre, auteur, annee, nombreDePages, quantiteDisponible) VALUES(?,?,?,?,?)";

        try (Connection conn = DatabaseHandler.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setInt(3, year);
            pstmt.setInt(4, pages);
            pstmt.setInt(5, quantite);
            
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            return false;
        }
    }

    // Méthode pour importer une image de couverture
    // Source: https://docs.oracle.com/javase/8/docs/api/javax/swing/JFileChooser.html
    public void importImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Image Files", "jpg", "jpeg", "png", "bmp"));
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                BufferedImage image = ImageIO.read(selectedFile);
                // Vous pouvez maintenant utiliser l'image, par exemple l'afficher dans un JLabel
                JLabel imageLabel = new JLabel(new ImageIcon(image));
                JOptionPane.showMessageDialog(this, imageLabel, "Image Importée", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erreur lors de l'importation de l'image: " + e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AjouterLivreFrame().setVisible(true));
    }
}
