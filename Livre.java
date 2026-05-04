public class Livre {
    private String titre;
    private String auteur;
    private String isbn;
    private int anneePublication;

    public Livre(String titre, String auteur, String isbn, int anneePublication) {
        this.titre = titre;
        this.auteur = auteur;
        this.isbn = isbn;
        this.anneePublication = anneePublication;
    }

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getAnneePublication() {
        return anneePublication;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public void setAnneePublication(int anneePublication) {
        this.anneePublication = anneePublication;
    }
    public String getImage() {
        return "images/" + isbn + ".jpg";
    }
}
