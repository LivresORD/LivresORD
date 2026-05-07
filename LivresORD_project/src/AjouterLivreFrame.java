// *******************************************************



// Programme: Ajouter Livre Frame



// *******************************************************







// On importe du code



import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;















// On démarre une classe AjouterLivreFrame qui hérite de JFrame



public class AjouterLivreFrame extends JFrame implements ActionListener {



	// On définit le flow



	FlowLayout leFlow  = new FlowLayout();



	  



	// On crée des boutons



	private JButton btnValider    = new JButton ("Valider");



	private JButton btnEffacer    = new JButton ("Effacer");



	private JButton btnRetourner    = new JButton ("Retourner");







	// On crée des panneaux



	JPanel panelDescription = new JPanel();



	JPanel panelInsertionTitre = new JPanel();



    JPanel panelInsertionAuteur = new JPanel();



	JPanel panelInsertionAnnee = new JPanel();



	JPanel panelResultatFormulaire = new JPanel();



	JPanel panelBoutons = new JPanel();



	



	// On construit les labels



	private JLabel lblDescription = new JLabel("Ajouter un livre: ", JLabel.CENTER);



	private JLabel lblInsertionTitre = new JLabel("Titre:", JLabel.CENTER);



	private JLabel lblInsertionAuteur = new JLabel("Auteur:", JLabel.CENTER);



    private JLabel lblInsertionAnnee = new JLabel("Année de Publication:", JLabel.CENTER);



    private JLabel lblResultatFormulaire = new JLabel("", JLabel.CENTER);



	  



	// On crée les zones de texte



	private JTextField txtInsertionTitre = new JTextField(20);



	private JTextField txtInsertionAuteur = new JTextField(20);



	private JTextField txtInsertionAnnee = new JTextField(20);



	  



	// On définit le grid-Layout



	private GridLayout leGrid = new GridLayout(3, 2, 20, 50);



	



	boolean titreValide;



	boolean anneeValide;



	boolean nomValide;



		



	// On crée le constructeur



    public AjouterLivreFrame() {



    	



    	// On crée le close operation



    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        



        // attribue-lui le gestionnaire de GridLayout   



        setLayout(new GridLayout(6,2));



        setResizable(false);



        setLocationRelativeTo(null);  //affiche la fenêtre au centre  



       



		



        



        //Change l'apparence - couleur et font



        panelDescription.setBackground(Color.GREEN);



        panelInsertionTitre.setBackground(Color.BLUE);



        panelInsertionAuteur.setBackground(Color.ORANGE);



        panelInsertionAnnee.setBackground(Color.GRAY);



        panelResultatFormulaire.setBackground(Color.YELLOW);



        panelBoutons.setBackground(Color.PINK);



        



        // On définit le style d'écriture pour le titre du formulaire 



        lblDescription.setForeground(Color.RED);



        lblDescription.setFont(new Font("Serif", Font.BOLD, 20));



        



        // On définit le style d'écriture pour l'affichage du résultat du formulaire 



        lblResultatFormulaire.setForeground(Color.RED);



		lblResultatFormulaire.setFont(new Font("Serif", Font.BOLD, 12));



		



        //Layout des panneaux



        panelDescription.setLayout(new FlowLayout(FlowLayout.CENTER));



        panelInsertionTitre.setLayout(new FlowLayout(FlowLayout.CENTER));



        panelInsertionAuteur.setLayout(new FlowLayout(FlowLayout.CENTER));



        panelInsertionAnnee.setLayout(new FlowLayout(FlowLayout.CENTER));



        panelResultatFormulaire.setLayout(new FlowLayout(FlowLayout.CENTER));



        panelBoutons.setLayout(new FlowLayout(FlowLayout.CENTER));



        



        // On ajoute de l'espace autour de la fenêtre



        panelDescription.setBorder(new EmptyBorder(10, 10, 10, 10));



        panelInsertionTitre.setBorder(new EmptyBorder(10, 10, 10, 10));



        panelInsertionAuteur.setBorder(new EmptyBorder(10, 10, 10, 10));



        panelInsertionAnnee.setBorder(new EmptyBorder(10, 10, 10, 10));



        panelResultatFormulaire.setBorder(new EmptyBorder(10, 10, 10, 10));



        panelBoutons.setBorder(new EmptyBorder(10, 10, 10, 10));



		



    	// On ajoute les action listener



        btnValider.addActionListener( this );



        btnEffacer.addActionListener( this );



        btnRetourner.addActionListener( this );







        // On définit la taille, la position général et le type de layout



        setSize(600, 300);



        setLocationRelativeTo(null);



        



        // On ajoute des éléments aux panneaux



        setTitle("LivresORD - Ajouter un Livre");



     	panelDescription.add(lblDescription);



     	panelInsertionTitre.add(lblInsertionTitre);



     	panelInsertionTitre.add(txtInsertionTitre);



     	panelInsertionAuteur.add(lblInsertionAuteur);



     	panelInsertionAuteur.add(txtInsertionAuteur);



     	panelInsertionAnnee.add(lblInsertionAnnee);



     	panelInsertionAnnee.add(txtInsertionAnnee);



     	panelResultatFormulaire.add(lblResultatFormulaire);



     	panelBoutons.add(btnValider);



     	panelBoutons.add(btnEffacer);



     	panelBoutons.add(btnRetourner);



     	



     	// On ajoute les panneaux



     	add(panelDescription);



     	add(panelInsertionTitre);



     	add(panelInsertionAuteur);



     	add(panelInsertionAnnee);



     	add(panelResultatFormulaire);



     	add(panelBoutons);



    }



    



    



  //On gère la réaction du clic du bouton



  		public void actionPerformed( ActionEvent actionEvent ) {



  			



  				String command = actionEvent.getActionCommand(); // Le programme prend la commande



  				



  								  



  				try {



  					if (command.equals("Effacer")) {



  						// On éfface le formulaire au complet et on met les boîtes textes à vide



  						txtInsertionTitre.setText("");



  						txtInsertionAuteur.setText("");



  						txtInsertionAnnee.setText("");  						



  					} else if (command.equals("Valider")) {



  						



  						// On crée des booleans pour la validité des réponses de l'utilisateur



  						titreValide = true;



  						anneeValide = false;



  						nomValide = false;



  						



  						// On vérifie qu'il y a bel et bien un titre d'inséré



  						if(txtInsertionTitre.getText().equals("")) {



  							titreValide = false;



  	  						lblResultatFormulaire.setText("ERREUR! Vous n'avez pas inséré un titre pour le livre. Svp, révisez vôtre réponse.");



  	  						return;







  						}



  						



  						// On vérifie qu'il y a bel et bien un nom d'auteur d'inséré



  						if(txtInsertionAuteur.getText().equals("")) {



  							titreValide = false;



  	  						lblResultatFormulaire.setText("ERREUR! Vous n'avez pas inséré un nom d'auteur pour le livre. Svp, révisez vôtre réponse.");



  	  						return;



  						}



  						



  						// On vérifie qu'il y a bel et bien un nom d'auteur d'inséré



  						if(txtInsertionAnnee.getText().equals("")) {



  							titreValide = false;



  	  						lblResultatFormulaire.setText("ERREUR! Vous n'avez pas inséré une année de publication pour le livre. Svp, révisez vôtre réponse.");



  	  						return;



  						}



  						



  						



  						// On vérifie la validité du nom de l'utilisateur



  				        	if (txtInsertionAuteur.getText().matches("\\d+")) { // source: https://cs.bradpenney.io/essentials/regular_expressions/



  				        	nomValide = false;



  				        	txtInsertionTitre.setText("");



  	  						txtInsertionAuteur.setText("");



  	  						txtInsertionAnnee.setText("");



  				        	throw new IllegalArgumentException("ERREUR! Le nom de l'auteur ne peut pas être un nombre. Svp, révisez vôtre réponse.");



  				        	} else if (!txtInsertionAuteur.getText().matches(".*[a-zA-ZÀ-ÿ].*")) { // source: https://cs.bradpenney.io/essentials/regular_expressions/



  				        		nomValide = false;



  				        		lblResultatFormulaire.setText("ERREUR! Le nom de l'auteur doit contenir des lettres. Svp, révisez vôtre réponse.");



  				        	} else {



  				        	nomValide = true;



  				        }



  				        



  				        



  				        // Le nombre d'années ne peut pas être une chaîne de caractères



  				        try {



  				        	// On vérifie l'entrée du nombre d'années d'expérience de l'utilisateur



  	  				        int annees = Integer.parseInt(txtInsertionAnnee.getText());



  				        	anneeValide = true;



  				        } catch(NumberFormatException e) {



  				        	anneeValide = false;



  							lblResultatFormulaire.setText("ERREUR! L'année de l'écriture du livre doit être un entier et non une chaîne de caractères. Svp, révisez vôtre réponse.");



  							return;



  						}



  				        



  				        // On affiche le résultat du formulaire



  				        if(titreValide == true && anneeValide == true && nomValide == true) {



  					        // Ici, le formulaire a été rempli avec succès



  				        	lblResultatFormulaire.setText("Vôtre réponse a été enregistré avec succès!");



  				        } else {



  				        	// Ici, le formulaire n'a pas été rempli avec succès



  				        	lblResultatFormulaire.setText("Malheureusement, il y a eu des erreurs lors de l'inscription!");



  				        }



  					} else if (command.equals("Retourner"))  {



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



        SwingUtilities.invokeLater(() -> new AjouterLivreFrame().setVisible(true));



    }



} // FIN GUI Package