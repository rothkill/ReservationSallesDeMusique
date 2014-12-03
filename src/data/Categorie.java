package data;

/**
 * Represente une categorie de salle.
 * 
 * @author grimonprez
 *
 */
public class Categorie {
	private Integer idCategorie;
	private String nom;
	private int tarifUneHeure;
	private int tarifDeuxHeures;
	private int nbPersonne;

	public Categorie(Integer idCategorie, String nom, int tarifUneHeure,
			int tarifDeuxHeures, int nbPersonne) {
		super();
		this.idCategorie = idCategorie;
		this.nom = nom;
		this.tarifUneHeure = tarifUneHeure;
		this.tarifDeuxHeures = tarifDeuxHeures;
		this.nbPersonne = nbPersonne;
	}

	public Integer getIdCategorie() {
		return idCategorie;
	}

	public void setIdCategorie(Integer idCategorie) {
		this.idCategorie = idCategorie;
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

	public String toString() {
		return nom;
	}
}
