package metier;

import java.util.Date;
import java.util.List;

import com.sun.org.apache.xerces.internal.impl.dv.xs.DateDV;

import dao.CategorieDAO;
import dao.ReservationDAO;
import dao.SalleDAO;
import data.Categorie;
import data.Salle;
import data.Utilisateur;
import exception.AucuneSalleSelectionneeException;
import exception.CategorieNonSelectionneeException;
import exception.DateIncorrecteException;
import exception.UtilisateurNonSelectionneException;

/**
 * Metier de visualisation du planning et reservations.
 * 
 * @author grimonprez
 * 
 */
public class ReservationMetier {

	private static final ReservationMetier VISUALISER_RESERVATION_METIER = new ReservationMetier();

	private ReservationMetier() {
		super();
	}

	public static ReservationMetier getInstance() {
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

			return ReservationDAO.getInstance().rechercherReservationParCategorieEtDate(
					categorie.getIdCategory(), jour,mois,annee);

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

	/**
	 * Verifie si la salle est reservee.
	 * 
	 * @param salle
	 * @param date
	 * @return
	 * @throws AucuneSalleSelectionneeException
	 */
	public boolean salleReservee(Salle salle, Date date)
			throws AucuneSalleSelectionneeException {
		if (salle == null) {
			throw new AucuneSalleSelectionneeException();
		}
		return ReservationDAO.getInstance().isSalleReservee(salle.getIdSalle(),
				date);
	}

	
	//TODO : Ajout des gestions de dateReservation et dateFinReservation
	public boolean reserverSalle(Utilisateur utilisateur, Salle salle, Date dateReservation,Date dateDebutReservation,Date dateFinReservation)
			throws AucuneSalleSelectionneeException,
			UtilisateurNonSelectionneException {
		if (salle == null) {
			throw new AucuneSalleSelectionneeException();
		}
		if (utilisateur == null) {
			throw new UtilisateurNonSelectionneException();
		}
		return ReservationDAO.getInstance().reserver(
				utilisateur.getIdUtilisateur(), salle.getIdSalle(), dateDebutReservation, dateFinReservation);
	}

	//TODO : Ajout des gestions de dateReservation et dateFinReservation
	public boolean reserverSurDuree(Utilisateur utilisateur, Salle salle,
			Date dateReservation,Date dateDebutReservation,Date dateFinReservation, int nbSemaines) throws AucuneSalleSelectionneeException,
			UtilisateurNonSelectionneException {
		if (salle == null) {
			throw new AucuneSalleSelectionneeException();
		}
		if (utilisateur == null) {
			throw new UtilisateurNonSelectionneException();
		}
		for (int i = 0; i < nbSemaines; i++) {
			reserverSalle(utilisateur, salle,dateReservation,dateDebutReservation,dateFinReservation);
		}
		return true;
	}
}
