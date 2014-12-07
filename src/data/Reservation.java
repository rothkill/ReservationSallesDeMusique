package data;

import java.util.Date;

import javax.swing.JLabel;

/**
 * Classe correspondant a la reservation d'une salle.
 * 
 * @author grimonprez
 * 
 */
public class Reservation {

	private Integer idReservation;

	private Date dateReservation;

	private Date dateDebutSceance;

	private int duree;

	private boolean confirmation;

	private Salle salle;

	private Utilisateur utilisateur;

	private float tarif;

	public Reservation(Integer idReservation, Date dateReservation,
			Date dateDebutSceance, int duree, boolean confirmation,
			Salle salle, Utilisateur utilisateur, float tarif) {
		super();
		this.idReservation = idReservation;
		this.dateReservation = dateReservation;
		this.setDateDebutSceance(dateDebutSceance);
		this.setDuree(duree);
		this.confirmation = confirmation;
		this.salle = salle;
		this.utilisateur = utilisateur;
		this.setTarif(tarif);
	}

	public Integer getIdReservation() {
		return idReservation;
	}

	public void setIdReservation(Integer idReservation) {
		this.idReservation = idReservation;
	}

	public Date getDateReservation() {
		return dateReservation;
	}

	public void setDateReservation(Date dateReservation) {
		this.dateReservation = dateReservation;
	}

	public boolean isConfirmation() {
		return confirmation;
	}

	public void setConfirmation(boolean confirmation) {
		this.confirmation = confirmation;
	}

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	@Override
	public String toString() {
		return dateReservation.toString() + " " + salle.toString();
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public Date getDateDebutSceance() {
		return dateDebutSceance;
	}

	public void setDateDebutSceance(Date dateDebutSceance) {
		this.dateDebutSceance = dateDebutSceance;
	}

	public float getTarif() {
		return this.tarif;
	}

	public void setTarif(float tarif) {
		this.tarif = tarif;
	}

}
