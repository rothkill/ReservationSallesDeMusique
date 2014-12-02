package data;

import java.util.List;

/**
 * Classe correspondant a une Salle.
 * 
 * @author grimonprez
 *
 */
public class Salle {
	private Integer idSalle;
	private List<Reservation> listReservationSalle;

	public Salle(Integer idSalle, List<Reservation> listReservationSalle) {
		super();
		this.idSalle = idSalle;
		this.listReservationSalle = listReservationSalle;
	}

	public Integer getIdSalle() {
		return idSalle;
	}

	public void setIdSalle(Integer idSalle) {
		this.idSalle = idSalle;
	}

	public List<Reservation> getListReservationSalle() {
		return listReservationSalle;
	}

	public void setListReservationSalle(List<Reservation> listReservationSalle) {
		this.listReservationSalle = listReservationSalle;
	}

}
