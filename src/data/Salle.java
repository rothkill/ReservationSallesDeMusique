package data;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe correspondant a une Salle.
 * 
 * @author grimonprez
 * 
 */
public class Salle {
	private Integer idSalle;
	private Categorie categorie;
	private String nom;
	private String etat;
	
	public Salle(Integer idSalle, Categorie categorie, String nom, String etat) {
		super();
		this.idSalle = idSalle;
		this.categorie = categorie;
		this.nom = nom;
		this.etat = etat;
	}

	public Integer getIdSalle() {
		return idSalle;
	}

	public void setIdSalle(Integer idSalle) {
		this.idSalle = idSalle;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	
}
