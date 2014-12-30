package data;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Classe correspondant a la reservation d'une salle.
 * 
 * @author grimonprez
 * 
 */
public class Reservation {

	private Integer idReservation;

	private Date dateReservation;

	private Date dateDebutReservation;

	private Date dateFinReservation;

	private boolean confirmation;

	private Salle salle;

	private Utilisateur utilisateur;

	private float tarif;

	public Reservation(Integer idReservation, Date dateReservation,
			Date dateDebutReservation, Date dateFinReservation,
			boolean confirmation, Salle salle, Utilisateur utilisateur,
			float tarif) {
		super();
		this.idReservation = idReservation;
		this.dateReservation = dateReservation;
		this.dateDebutReservation = dateDebutReservation;
		this.dateFinReservation = dateFinReservation;
		this.confirmation = confirmation;
		this.salle = salle;
		this.utilisateur = utilisateur;
		this.tarif = tarif;
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

	public Date getDateDebutReservation() {
		return dateDebutReservation;
	}

	public void setDateDebutReservation(Date dateDebutReservation) {
		this.dateDebutReservation = dateDebutReservation;
	}

	public Date getDateFinReservation() {
		return dateFinReservation;
	}

	public void setDateFinReservation(Date dateFinReservation) {
		this.dateFinReservation = dateFinReservation;
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

	public float getTarif() {
		return tarif;
	}

	public void setTarif(float tarif) {
		this.tarif = tarif;
	}

	public int getDuree() {
		TimeUnit timeUnit = TimeUnit.HOURS;
		long diffInMillies = dateFinReservation.getTime()
				- dateDebutReservation.getTime();
		return (int) timeUnit.convert(diffInMillies, TimeUnit.HOURS);
	}

	public String toString() {
		return dateDebutReservation.toString() + " "
				+ dateReservation.toString() + " " + salle.getNom();
	}

}
