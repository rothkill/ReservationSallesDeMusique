package data;

public class Categorie {

	private int idCategory;

	private String nom;

	private int tarifUneHeure;

	private int tarifDeuxHeures;

	private int nbPersonne;

	public Categorie(int idCategory, String nom, int tarifUneHeure,
			int tarifDeuxHeures, int nbPersonne) {
		super();
		this.idCategory = idCategory;
		this.nom = nom;
		this.tarifUneHeure = tarifUneHeure;
		this.tarifDeuxHeures = tarifDeuxHeures;
		this.nbPersonne = nbPersonne;
	}

	public int getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getTarifUneHeure() {
		return tarifUneHeure;
	}

	public void setTarifUneHeure(int tarifUneHeure) {
		this.tarifUneHeure = tarifUneHeure;
	}

	public int getTarifDeuxHeures() {
		return tarifDeuxHeures;
	}

	public void setTarifDeuxHeures(int tarifDeuxHeures) {
		this.tarifDeuxHeures = tarifDeuxHeures;
	}

	public int getNbPersonne() {
		return nbPersonne;
	}

	public void setNbPersonne(int nbPersonne) {
		this.nbPersonne = nbPersonne;
	}

	@Override
	public String toString() {
		return nom;
	}

}
