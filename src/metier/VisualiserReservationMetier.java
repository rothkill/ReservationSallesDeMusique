package metier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import dao.CategorieDAO;
import data.Categorie;
import data.Salle;
import exception.DateIncorrecteException;

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

	public List<Salle> getListeSalleByCategory(Categorie categorie,
			String jourString, String moisString, String anneeString)
			throws DateIncorrecteException {
		// TODO JBG rajouter les intervalles de date

		try {
			int jour = Integer.parseInt(jourString);
			int mois = Integer.parseInt(moisString);
			int annee = Integer.parseInt(anneeString);

			if ((jour <= 1 || jour >= 31) || (mois <= 1 || mois >= 12)) {
				throw new DateIncorrecteException();
			}

			Date date = new Date(annee, mois, jour);

		} catch (ParseException exception) {
			throw new DateIncorrecteException();
		}

		return null;
	}

	public List<Categorie> getListeCategorie() {
		// TODO JBG
		// return CategorieDAO.getInstance().listerCategories();
		List<Categorie> c = new ArrayList<Categorie>();
		c.add(new Categorie(1, "fr", 2, 3, 4));
		c.add(new Categorie(2, "fver", 2, 3, 4));
		return c;
	}
}
