package data;

import java.util.Date;

/**
 * Classe correspondant a la reservation d'une salle.
 * 
 * @author grimonprez
 * 
 */
public class Reservation {

	private Integer idReservation;

	private Date DateReservation;

	private Date DateFinReservation;

	private boolean confirmation;

	private Salle salle;

	private Utilisateur utilisateur;

	public Reservation(Integer idReservation, Date dateReservation,
			Date dateFinReservation, boolean confirmation, Salle salle,
			Utilisateur utilisateur) {
		super();
		this.idReservation = idReservation;
		DateReservation = dateReservation;
		DateFinReservation = dateFinReservation;
		this.confirmation = confirmation;
		this.salle = salle;
		this.utilisateur = utilisateur;
	}

	public Integer getIdReservation() {
		return idReservation;
	}

	public void setIdReservation(Integer idReservation) {
		this.idReservation = idReservation;
	}

	public Date getDateReservation() {
		return DateReservation;
	}

	public void setDateReservation(Date dateReservation) {
		DateReservation = dateReservation;
	}

	public Date getDateFinReservation() {
		return DateFinReservation;
	}

	public void setDateFinReservation(Date dateFinReservation) {
		DateFinReservation = dateFinReservation;
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
		return DateReservation.toString() + " " + salle.toString();
	}

}
