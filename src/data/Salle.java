package data;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe correspondant a une Salle.
 * 
 * @author grimonprez
 * 
 */
public class Salle {
	private Integer idSalle;
	private int categorie;
	private String nom;
	private String etat;
	private List<Reservation> listReservationSalle;

	public Salle(Integer idSalle, int categorie, String nom, String etat) {
		super();
		this.idSalle = idSalle;
		this.categorie = categorie;
		this.nom = nom;
		this.etat = etat;
		this.listReservationSalle = new ArrayList<Reservation>();
	}

	public Integer getIdSalle() {
		return idSalle;
	}

	public void setIdSalle(Integer idSalle) {
		this.idSalle = idSalle;
	}

	public int getCategorie() {
		return categorie;
	}

	public void setCategorie(int categorie) {
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

	public List<Reservation> getListReservationSalle() {
		return listReservationSalle;
	}

	public void setListReservationSalle(List<Reservation> listReservationSalle) {
		this.listReservationSalle = listReservationSalle;
	}

	public String toString() {
		return nom;
	}

}
