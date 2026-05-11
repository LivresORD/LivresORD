//*******************************************************
//Programme: Modifier Livre Frame
//*******************************************************



//On importe du code

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;



//On démarre une classe ModifierLivreFrame qui hérite de JFrame
public class ModifierLivreFrame extends JFrame implements ActionListener {

// On définit le flow
FlowLayout leFlow = new FlowLayout();



// On crée des boutons
private JButton btnValider = new JButton ("Valider");
private JButton btnEffacer = new JButton ("Effacer");
private JButton btnRetourner = new JButton ("Retourner");


// On crée des panneaux
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
private JPanel panelInsertionQuantiteLivreNouveau = new JPanel();
private JPanel panelResultatFormulaire = new JPanel();
private JPanel panelBoutons = new JPanel();


// On construit les labels
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
private JLabel lblInsertionQuantiteLivreNouveau = new JLabel("Nouvelle quantité de livres disponibles: ", JLabel.CENTER);
private JLabel lblTitreResultatFormulaire = new JLabel("Résultat du formulaire: ", JLabel.CENTER);
private JLabel lblResultatFormulaire = new JLabel("", JLabel.CENTER);



// On crée les zones de texte
private JTextField txtInsertionTitre = new JTextField(20);
private JTextField txtInsertionAuteur = new JTextField(20);
private JTextField txtInsertionAnnee = new JTextField(20);
private JTextField txtNombreDePages = new JTextField(20);
private JTextField txtInsertionTitreNouveau = new JTextField(20);
private JTextField txtInsertionAuteurNouveau = new JTextField(20);
private JTextField txtInsertionAnneeNouveau = new JTextField(20);
private JTextField txtInsertionNombreDePagesNouveau = new JTextField(20);
private JTextField txtInsertionQuantiteLivreNouveau = new JTextField(20);

// On définit le grid-Layout
private GridLayout leGrid = new GridLayout(3, 2, 20, 50);



// On crée des booleans pour la validité de l'insertion de données de l'utilisateur
boolean titreValide;
boolean anneeValide;
boolean nomValide;
boolean nombreDePagesValide;
boolean titreNouveauValide;
boolean anneeNouveauValide;
boolean nomNouveauValide;
boolean nombreDePagesNouveauValide;
boolean quantiteLivreNouveauValide;



// On crée le constructeur
public ModifierLivreFrame() {


 // On crée le close operation
 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



 // attribue-lui le gestionnaire de GridLayout 
 setLayout(new GridLayout(14,2));
 setResizable(false);
 setLocationRelativeTo(null); //affiche la fenêtre au centre 
 

 //Change l'apparence - couleur et font
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
 panelInsertionQuantiteLivreNouveau.setBackground(Color.RED);
 panelResultatFormulaire.setBackground(Color.YELLOW);
 panelBoutons.setBackground(Color.PINK);


 // On définit le style d'écriture pour le titre du formulaire 
 lblDescriptionPrincipale.setForeground(Color.RED);
 lblDescriptionPrincipale.setFont(new Font("Serif", Font.BOLD, 20));


 // On définit le style d'écriture pour le titre du formulaire 
 lblDescriptionEtape1.setForeground(Color.BLUE);
 lblDescriptionEtape1.setFont(new Font("Serif", Font.BOLD, 20));

 
 // On définit le style d'écriture pour le titre du formulaire 
 lblDescriptionEtape2.setForeground(Color.BLUE);
 lblDescriptionEtape2.setFont(new Font("Serif", Font.BOLD, 20));
 

 // On définit le style d'écriture pour l'affichage du résultat du formulaire 
 lblResultatFormulaire.setForeground(Color.RED);
 lblResultatFormulaire.setFont(new Font("Serif", Font.BOLD, 12));

 
 //Layout des panneaux
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
 panelInsertionQuantiteLivreNouveau.setLayout(new FlowLayout(FlowLayout.CENTER));
 panelResultatFormulaire.setLayout(new GridLayout(2, 1));
 panelBoutons.setLayout(new FlowLayout(FlowLayout.CENTER));


 // On ajoute de l'espace autour de la fenêtre
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
 panelInsertionQuantiteLivreNouveau.setBorder(new EmptyBorder(10, 10, 10, 10));
 panelResultatFormulaire.setBorder(new EmptyBorder(10, 10, 10, 10));
 panelBoutons.setBorder(new EmptyBorder(10, 10, 10, 10));
 

 // On ajoute les action listener
 btnValider.addActionListener( this );
 btnEffacer.addActionListener( this );
 btnRetourner.addActionListener( this );


 // On définit la taille, la position général et le type de layout
 setSize(1400, 800);
 setLocationRelativeTo(null);


 // On ajoute des éléments aux panneaux
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
 panelInsertionQuantiteLivreNouveau.add(lblInsertionQuantiteLivreNouveau);
 panelInsertionQuantiteLivreNouveau.add(txtInsertionQuantiteLivreNouveau);
 panelResultatFormulaire.add(lblTitreResultatFormulaire);
 panelResultatFormulaire.add(lblResultatFormulaire);
 panelBoutons.add(btnValider);
 panelBoutons.add(btnEffacer);
 panelBoutons.add(btnRetourner);


 // On ajoute les panneaux
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
 add(panelInsertionQuantiteLivreNouveau);
 add(panelResultatFormulaire);
 add(panelBoutons);
}


//On gère la réaction du clic du bouton
 public void actionPerformed( ActionEvent actionEvent ) {
 
 // Le programme prend la commande
 String command = actionEvent.getActionCommand(); 
 
 try {
 if (command.equals("Effacer")) {
 // On éfface le formulaire au complet et on met les boîtes textes à vide
 txtInsertionTitre.setText("");
 txtInsertionAuteur.setText("");
 txtInsertionAnnee.setText(""); 
 txtNombreDePages.setText("");
 txtInsertionTitreNouveau.setText("");
 txtInsertionAuteurNouveau.setText("");
 txtInsertionAnneeNouveau.setText(""); 
 txtInsertionNombreDePagesNouveau.setText("");
 txtInsertionQuantiteLivreNouveau.setText("");
 } else if (command.equals("Valider")) {


 // On crée des booleans pour la validité des réponses de l'utilisateur
 titreValide = true;
 anneeValide = true;
 nomValide = true;
 nombreDePagesValide = true;
 titreNouveauValide = true;
 anneeNouveauValide = true;
 nomNouveauValide = true;
 nombreDePagesNouveauValide = true;
 quantiteLivreNouveauValide = true;
 

 // On vérifie qu'il y a bel et bien un titre initiale d'inséré
 if(txtInsertionTitre.getText().equals("")) {
 titreValide = false;
 lblResultatFormulaire.setText("ERREUR! Vous n'avez pas inséré un titre pour le livre. Svp, révisez vôtre réponse pour le titre initiale du livre.");
 return;
 }

 // On vérifie qu'il y a bel et bien un nom d'auteur initial d'inséré
 if(txtInsertionAuteur.getText().equals("")) {
 nomValide = false;
 lblResultatFormulaire.setText("ERREUR! Vous n'avez pas inséré un nom d'auteur pour le livre. Svp, révisez vôtre réponse pour le nom d'auteur initiale du livre.");
 return;
 }

 // On vérifie qu'il y a bel et bien une année de publication initiale insérée
 if(txtInsertionAnnee.getText().equals("")) {
 anneeValide = false;
 lblResultatFormulaire.setText("ERREUR! Vous n'avez pas inséré une année de publication pour le livre. Svp, révisez vôtre réponse pour l'année de publication initiale du livre.");
 return;
 }


 // On vérifie qu'il y a bel et bien un nombre de pages initiale d'inséré
 if(txtNombreDePages.getText().equals("")) {
 nombreDePagesValide = false;
 lblResultatFormulaire.setText("ERREUR! Vous n'avez pas inséré le nombre de pages pour le livre. Svp, révisez vôtre réponse pour le nombre de pages initiale du livre.");
 return;
 }

 // On vérifie qu'il y a bel et bien un titre finale d'inséré
 if(txtInsertionTitreNouveau.getText().equals("")) {
 titreNouveauValide = false;
 lblResultatFormulaire.setText("ERREUR! Vous n'avez pas inséré un titre pour le livre. Svp, révisez vôtre réponse pour le tire finale du livre.");
 return;
 }
 
 // On vérifie qu'il y a bel et bien un nom d'auteur finale d'inséré
 if(txtInsertionAuteurNouveau.getText().equals("")) {
 nomNouveauValide = false;
 lblResultatFormulaire.setText("ERREUR! Vous n'avez pas inséré un nom d'auteur pour le livre. Svp, révisez vôtre réponse pour le nom d'auteur finale du livre.");
 return;
 }
 

 // On vérifie qu'il y a bel et bien une année de publication finale d'insérée
 if(txtInsertionAnneeNouveau.getText().equals("")) {
 anneeNouveauValide = false;
 lblResultatFormulaire.setText("ERREUR! Vous n'avez pas inséré une année de publication pour le livre. Svp, révisez vôtre réponse pour l'année de publication finale du livre.");
 return;
 }

 
 // On vérifie qu'il y a bel et bien un nombre de pages finale d'inséré
 if(txtInsertionNombreDePagesNouveau.getText().equals("")) {
 nombreDePagesNouveauValide = false;
 lblResultatFormulaire.setText("ERREUR! Vous n'avez pas inséré le nombre de pages pour le livre. Svp, révisez vôtre réponse pour le nombre de pages finale du livre.");
 return;
 }
 
 //On vérifie qu'il y a bel et bien une nouvelle quantité de copies du livre de disponible
 if(txtInsertionQuantiteLivreNouveau.getText().equals("")) {
 quantiteLivreNouveauValide = false;
 lblResultatFormulaire.setText("ERREUR! Vous n'avez pas inséré le nouveau quantité du livre disponible. Svp, révisez vôtre réponse pour la quantité de livres disponibles.");
 return;
 }

 // On vérifie la validité du nom de l'auteur à noter qu'il n'y a pas de restrictions pour le titre du livre
 if (txtInsertionAuteur.getText().matches("\\d+")) { // source: https://cs.bradpenney.io/essentials/regular_expressions/
 nomValide = false;
 lblResultatFormulaire.setText("ERREUR! Le nom de l'auteur doit contenir des lettres. Svp, révisez vôtre réponse pour le nom d'auteur initiale.");
 return;
 } else if (!txtInsertionAuteur.getText().matches("[a-zA-ZÀ-ÿ\\s'-]+")) { // source: https://cs.bradpenney.io/essentials/regular_expressions/
 nomValide = false;
 lblResultatFormulaire.setText("ERREUR! Le nom de l'auteur doit seulement contenir des lettres et non des nombres. Svp, révisez vôtre réponse pour le nom d'auteur initiale.");
 return;
 } else {
 nomValide = true;
 }

 // Le nombre d'années ne peut pas être une chaîne de caractères
 try {
 Integer.parseInt(txtInsertionAnnee.getText());
 anneeValide = true;

 } catch(NumberFormatException e) {
 anneeValide = false;
 lblResultatFormulaire.setText("ERREUR! L'année de l'écriture du livre doit être un entier et non une chaîne de caractères. Svp, révisez vôtre réponse pour l'année d'écriture du livre initiale.");
 return;
 }

 // On vérifie que l'année de publication prenne un temps raisonable
 if(Integer.parseInt(txtInsertionAnnee.getText()) < -7000000) {
 anneeValide = false;
 lblResultatFormulaire.setText("ERREUR! L'année de publication du livre ne peut pas être plus ancien que 7 000 000 avant JC. C'est plus ancien que la première trace connue des humains. Svp, révisez vôtre réponse pour l'année de publication initiale."); //Source: https://www.mnhn.fr/fr/frise-des-lignees-humaines
 return;
 } else if(Integer.parseInt(txtInsertionAnnee.getText()) > 2026) {
 anneeValide = false;
 lblResultatFormulaire.setText("ERREUR! L'année de publication du livre ne peut pas être plus tard que 2026. Cela serait dans le futur. Svp, révisez vôtre réponse pour l'année de publication initiale."); //Source: https://www.mnhn.fr/fr/frise-des-lignees-humaines
 return;
 } else {
 anneeValide = true;
 }

 // Le nombre de pages ne peut pas être une chaîne de caractères
 try {
 Integer.parseInt(txtNombreDePages.getText());
 nombreDePagesValide = true;
 
 } catch(NumberFormatException e) {
 nombreDePagesValide = false;
 lblResultatFormulaire.setText("ERREUR! Le nombre de pages du livre doit être un entier et non une chaîne de caractères. Svp, révisez vôtre réponse pour le nombre de pages initiales.");
 return;
 }

 // On vérifie que le nombre de pages n'est pas inférieur à 1 et pas suppérieur à 15 000
 if(Integer.parseInt(txtNombreDePages.getText()) < 0) {
 lblResultatFormulaire.setText("ERREUR! Le nombre de pages du livre ne peut pas prendre une valeur négative. Svp, révisez vôtre réponse pour le nombre de pages initiales.");
 nombreDePagesValide = false;
 return;
 } else if(Integer.parseInt(txtNombreDePages.getText()) > 15000) {
 lblResultatFormulaire.setText("ERREUR! Un livre de plus de 15000 pages bât de manière significative le record de nombre de pages pour un roman. Svp, révisez vôtre réponse pour le nombre de pages initiales."); // Source: https://www.cnews.fr/culture/2026-01-09/voici-les-5-romans-les-plus-longs-au-monde-1798146
 nombreDePagesValide = false;
 return;
 } else {
 nombreDePagesValide = true;
 }

 // On vérifie la validité du nom de l'auteur à noter qu'il n'y a pas de restrictions pour le nouveau titre du livre
 if (txtInsertionAuteurNouveau.getText().matches("\\d+")) { // source: https://cs.bradpenney.io/essentials/regular_expressions/
 nomNouveauValide = false;
 lblResultatFormulaire.setText("ERREUR! Le nom de l'auteur doit contenir des lettres. Svp, révisez vôtre réponse pour le nom d'auteur finale.");
 return;
 } else if (!txtInsertionAuteurNouveau.getText().matches("[a-zA-ZÀ-ÿ\\s'-]+")) { // source: https://cs.bradpenney.io/essentials/regular_expressions/
 nomNouveauValide = false;
 lblResultatFormulaire.setText("ERREUR! Le nom de l'auteur doit seulement contenir des lettres et non des nombres. Svp, révisez vôtre réponse pour le nom d'auteur finale.");
 return;
 } else {
 nomNouveauValide = true;
 }

 // Le nombre d'années ne peut pas être une chaîne de caractères
 try {
 // On vérifie l'entrée du nouveau nombre d'années d'expérience de l'utilisateur
 Integer.parseInt(txtInsertionAnneeNouveau.getText());
 anneeNouveauValide = true;
 
 } catch(NumberFormatException e) {
 anneeNouveauValide = false;
 lblResultatFormulaire.setText("ERREUR! L'année de l'écriture du livre doit être un entier et non une chaîne de caractères. Svp, révisez vôtre réponse pour l'année d'écriture du livre finale.");
 return;
 }

 // On vérifie que la nouvelle année de publication prenne un temps raisonable
 if(Integer.parseInt(txtInsertionAnneeNouveau.getText()) < -7000000) {
 anneeNouveauValide = false;
 lblResultatFormulaire.setText("ERREUR! L'année de publication du livre ne peut pas être plus ancien que 7 000 000 avant JC. C'est plus ancien que la première trace connue des humains. Svp, révisez vôtre réponse pour l'année de publication finale."); //Source: https://www.mnhn.fr/fr/frise-des-lignees-humaines
 return;
 } else if(Integer.parseInt(txtInsertionAnneeNouveau.getText()) > 2026) {
 anneeNouveauValide = false;
 lblResultatFormulaire.setText("ERREUR! L'année de publication du livre ne peut pas être plus tard que 2026. Cela serait dans le futur. Svp, révisez vôtre réponse pour l'année de publication finale."); //Source: https://www.mnhn.fr/fr/frise-des-lignees-humaines
 return;
 } else {
 anneeNouveauValide = true;
 }

 // Le nouveau nombre de pages ne peut pas être une chaîne de caractères
 try {
 Integer.parseInt(txtInsertionNombreDePagesNouveau.getText());
 nombreDePagesNouveauValide = true;

 } catch(NumberFormatException e) {
 nombreDePagesNouveauValide = false;
 lblResultatFormulaire.setText("ERREUR! Le nombre de pages du livre doit être un entier et non une chaîne de caractères. Svp, révisez vôtre réponse pour le nombre de pages finales.");
 return;
 }

 // On vérifie que le nouveau nombre de pages n'est pas inférieur à 1 et pas suppérieur à 15 000
 if(Integer.parseInt(txtInsertionNombreDePagesNouveau.getText()) < 0) {
 lblResultatFormulaire.setText("ERREUR! Le nombre de pages du livre ne peut pas prendre une valeur négative. Svp, révisez vôtre réponse pour le nombre de pages finales.");
 nombreDePagesNouveauValide = false;
 return;
 } else if(Integer.parseInt(txtInsertionNombreDePagesNouveau.getText()) > 15000) {
 lblResultatFormulaire.setText("ERREUR! Un livre de plus de 15000 pages bât de manière significative le record de nombre de pages pour un roman. Svp, révisez vôtre réponse pour le nombre de pages finales."); // Source: https://www.cnews.fr/culture/2026-01-09/voici-les-5-romans-les-plus-longs-au-monde-1798146
 nombreDePagesNouveauValide = false;
 return;
 } else {
 nombreDePagesNouveauValide = true;
 }

//La nouvelle quantité de livres disponibles ne peut pas être une chaîne de caractères
try {
Integer.parseInt(txtInsertionQuantiteLivreNouveau.getText());
quantiteLivreNouveauValide = true;

} catch(NumberFormatException e) {
	quantiteLivreNouveauValide = false;
lblResultatFormulaire.setText("ERREUR! La quantité du nombre de livres disponible doit être un entier et non une chaîne de caractères. Svp, révisez vôtre réponse pour la quantité de livres disponibles finales.");
return;
}

// On vérifie que la nouvelle quantité de livres n'est pas inférieur à 1 et pas suppérieur à 7 milliards de copies
if(Integer.parseInt(txtInsertionQuantiteLivreNouveau.getText()) < 0) {
lblResultatFormulaire.setText("ERREUR! Le nombre livres disponible ne peut pas prendre une valeur négative. Svp, révisez vôtre réponse pour le nombre de livres disponibles finales.");
quantiteLivreNouveauValide = false;
return;
} else if(Integer.parseInt(txtInsertionQuantiteLivreNouveau.getText()) > (7* Math.pow(10, 9))) { // Source: https://magazine.1000libraries.com/the-10-best-selling-books-in-history/
lblResultatFormulaire.setText("ERREUR! Un livre ayant plus de 7 000 000 000 copies vendues bât de manière significative le record mondial. Svp, révisez vôtre réponse pour la quantité de livres disponibles."); 
quantiteLivreNouveauValide = false;
return;
} else {
	quantiteLivreNouveauValide = true;
}

 // On affiche le résultat du formulaire
 if(titreValide == true && anneeValide == true && nomValide == true && nombreDePagesValide == true && titreNouveauValide == true && anneeNouveauValide == true && nomNouveauValide == true && nombreDePagesNouveauValide == true && quantiteLivreNouveauValide == true) {
 // **************************************************************************
 // Continuer cette partie incomplète... Il faut ne pas oublier que le livre à Modifier doit înitialement exister dans le système. 
 // Il faut ajouter une dernière condition de validité.
 // **************************************************************************
 
 // Ici, le formulaire a été rempli avec succès
 lblResultatFormulaire.setText("Vôtre réponse a été enregistré avec succès!");

 } else {
 // Ici, le formulaire n'a pas été rempli avec succès
 lblResultatFormulaire.setText("Malheureusement, il y a eu des erreurs lors de l'inscription!");
 }


 } else if (command.equals("Retourner")) {
 // **************************************************************************
 // Continuer cette partie incomplète.............................................................................................
 // **************************************************************************
 }


 // On affiche le résultat de l'inscription du formulaire
 repaint();

 } catch (Exception e) {
 // Ici, l'entrée de l'utilisateur n'est pas valide
 System.out.println("Il y a eu une erreur avec l'inscription du fichier.");
 }
} //fin actionPerformed 


public static void main(String[] args) {

 SwingUtilities.invokeLater(() -> new ModifierLivreFrame().setVisible(true));

}

} // FIN GUI Package
