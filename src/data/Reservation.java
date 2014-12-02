package data;

/**
 * Classe correspondant a la reservation d'une salle.
 * 
 * @author grimonprez
 *
 */
public class Reservation {
	private Integer idReservation;
	private Salle salle;

	public Reservation(Integer idReservation, Salle salle) {
		super();
		this.idReservation = idReservation;
		this.salle = salle;
	}

	public Integer getIdReservation() {
		return idReservation;
	}

	public void setIdReservation(Integer idReservation) {
		this.idReservation = idReservation;
	}

	public Salle getSalle() {
		return salle;
	}

	public void setSalle(Salle salle) {
		this.salle = salle;
	}

}
