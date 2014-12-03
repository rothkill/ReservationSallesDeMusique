package metier;

import java.util.List;

import data.Categorie;
import data.Salle;

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

	public List<Salle> getListeSalleByCategory(Categorie categorie) {
		// TODO JBG
		return null;
	}
}
