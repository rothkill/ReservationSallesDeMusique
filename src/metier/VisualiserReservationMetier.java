package metier;

/**
 * Metier du cas d'utilisation 1 : visualisation des reservations.
 * 
 * @author grimonprez
 * 
 */
public class VisualiserReservationMetier {

	private static final VisualiserReservationMetier VISUALISER_RESERVATION_METIER = new VisualiserReservationMetier();

	private VisualiserReservationMetier() {
		super();
	}

	public static VisualiserReservationMetier getInstance() {
		return VISUALISER_RESERVATION_METIER;
	}
}
