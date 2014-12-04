package metier;

import java.util.Date;
import java.util.List;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import dao.CategorieDAO;
import dao.SalleDAO;
import data.Categorie;
import data.Salle;
import exception.CategorieNonSelectionneeException;
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

	/**
	 * Retourne la liste des salles correspondant a la categorie recherchee,
	 * ainsi que les reservation a la date demandee.
	 * 
	 * @param categorie
	 * @param jourString
	 * @param moisString
	 * @param anneeString
	 * @return
	 * @throws DateIncorrecteException
	 * @throws CategorieNonSelectionneeException
	 */
	public List<Salle> getListeSalleByCategory(Categorie categorie,
			String jourString, String moisString, String anneeString)
			throws DateIncorrecteException, CategorieNonSelectionneeException {

		if (categorie == null) {
			throw new CategorieNonSelectionneeException();
		}

		// TODO JBG rajouter les intervalles de date

		try {
			int jour = Integer.parseInt(jourString);
			int mois = Integer.parseInt(moisString);
			int annee = Integer.parseInt(anneeString);

			if ((jour <= 1 || jour >= 31) || (mois <= 1 || mois >= 12)) {
				throw new DateIncorrecteException();
			}

			Date date = new Date();
			// TODO JBG

			return SalleDAO.getInstance().rechercherParCategorieEtDate(
					categorie.getIdCategory(), jour, mois, annee);

		} catch (NumberFormatException exception) {
			throw new DateIncorrecteException();
		}
	}

	/**
	 * @return la liste de toutes les categories de salle existantes.
	 */
	public List<Categorie> getListeCategorie() {
		return CategorieDAO.getInstance().listerCategories();
	}
}
