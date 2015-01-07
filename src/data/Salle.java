package data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.ReservationDAO;

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
	private List<Reservation> listeReservation;

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

	@Override
	public String toString() {
		return nom;
	}

	public List<Reservation> getListeReservation() {
		if (listeReservation == null) {
			listeReservation = ReservationDAO.getInstance()
					.listerReservationParSalle(idSalle);
		}
		return listeReservation;
	}
	
	public void setListeReservation(List<Reservation> listeReservation) {
		this.listeReservation = listeReservation;
	}

}
