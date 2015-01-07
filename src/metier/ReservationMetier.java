package metier;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import utils.Constantes;
import dao.CategorieDAO;
import dao.ReservationDAO;
import dao.SalleDAO;
import dao.UtilisateurDAO;
import data.Categorie;
import data.Reservation;
import data.Salle;
import data.Utilisateur;
import exception.AucuneSalleSelectionneeException;
import exception.CategorieNonSelectionneeException;
import exception.DateIncorrecteException;
import exception.LundiException;
import exception.SalleReserveeException;
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
	public List<Salle> getListeSalleByCategory(Categorie categorie)
			throws DateIncorrecteException, CategorieNonSelectionneeException {

		if (categorie == null) {
			throw new CategorieNonSelectionneeException();
		}

		return SalleDAO.getInstance().lister(categorie.getIdCategory());
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

	/**
	 * Supprime une reservation en fonction de la dateHeure et de la salle.
	 * 
	 * @param salle
	 * @param jour
	 * @param mois
	 * @param annee
	 * @param heure
	 * @return
	 * @throws AucuneSalleSelectionneeException
	 * @throws UtilisateurNonSelectionneException
	 * @throws DateIncorrecteException
	 */
	public boolean supprimerReservationParDateEtSalle(Salle salle, Date date, int heure)
			throws AucuneSalleSelectionneeException,
			UtilisateurNonSelectionneException, DateIncorrecteException {
		if (salle == null) {
			throw new AucuneSalleSelectionneeException();
		}
		try {
			String dateDebut = jour + mois + annee + heure;
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHH");
			Date dateDebutReservation = sdf.parse(dateDebut);

			return ReservationDAO.getInstance().supprimerReservation(
					salle.getIdSalle(), dateDebutReservation);
		} catch (ParseException e) {
			throw new DateIncorrecteException();
		}
	}

	// TODO : Ajout des gestions de dateReservation et dateFinReservation
	// gerer jours feries et week-ends
	public boolean reserverSalle(Utilisateur utilisateur, Salle salle,
			Date date, int heure, int duree)
			throws AucuneSalleSelectionneeException,
			UtilisateurNonSelectionneException, DateIncorrecteException,
			SalleReserveeException, LundiException {
		if (salle == null) {
			throw new AucuneSalleSelectionneeException();
		}
		if (utilisateur == null) {
			throw new UtilisateurNonSelectionneException();
		}

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR, heure);
		calendar.set(Calendar.MINUTE, 0);

		try {
			Date dateDebutReservation = new Date(calendar.getTimeInMillis());
			calendar.set(Calendar.HOUR, heure + duree);
			Date dateFinReservation = new Date(calendar.getTimeInMillis());
			if (estUnLundi(dateDebutReservation)) {
				throw new LundiException();
			}

			System.out.println("Date Debut Reservation : "
					+ dateDebutReservation.toString());
			System.out.println("Date Fin Reservation : "
					+ dateFinReservation.toString());

			if (salleReservee(salle, dateDebutReservation)) {
				throw new SalleReserveeException();
			}

			float tarif = calculerTarif(salle, heure, duree);

			return ReservationDAO.getInstance().reserver(
					utilisateur.getIdUtilisateur(), salle.getIdSalle(),
					dateDebutReservation, dateFinReservation, tarif);
		} catch (NumberFormatException exception) {
			throw new DateIncorrecteException();
		}
	}

	/**
	 * Calcule le tarif de la reservation. Les heures apres 20H sont majorees de
	 * 2%.
	 * 
	 * @param salle
	 * @param heureInt
	 * @param dureeInt
	 * @return
	 */
	private float calculerTarif(Salle salle, int heureInt, int dureeInt) {
		float result = 0;
		int heuresApres20H = heureInt + dureeInt - 20;
		int dureeAvant20H = dureeInt;

		if (heuresApres20H > 0) {
			dureeAvant20H = dureeInt - heuresApres20H;
			for (int i = 0; i < dureeAvant20H; i += 2) {
				result += salle.getCategorie().getTarifDeuxHeures();
			}
			if (dureeAvant20H % 2 >= 1) {
				result += salle.getCategorie().getTarifUneHeure();
			}
			result += ((result * 2) / 100);
		}

		for (int i = 0; i < dureeAvant20H; i += 2) {
			result += salle.getCategorie().getTarifDeuxHeures();
		}
		if (dureeAvant20H % 2 >= 1) {
			result += salle.getCategorie().getTarifUneHeure();
		}
		return result;
	}

	// TODO : Ajout des gestions de dateReservation et dateFinReservation
	public boolean reserverSurDuree(Utilisateur utilisateur, Salle salle,
			Date date, int heure, int duree, String nbSemaines)
			throws AucuneSalleSelectionneeException,
			UtilisateurNonSelectionneException, LundiException {
		if (salle == null) {
			throw new AucuneSalleSelectionneeException();
		}
		if (utilisateur == null) {
			throw new UtilisateurNonSelectionneException();
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		for (int i = 0; i < Integer.parseInt(nbSemaines); i++) {
			try {
				calendar.add(Calendar.WEEK_OF_YEAR, 1);
				reserverSalle(utilisateur, salle,
						new Date(calendar.getTimeInMillis()), heure, duree);

			} catch (DateIncorrecteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SalleReserveeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (Integer.parseInt(nbSemaines) >= 4) {
			UtilisateurDAO.getInstance().ajouterFidelite(
					utilisateur.getIdUtilisateur(), 30);
		}
		return true;
	}

	/**
	 * Liste les salles existantes.
	 * 
	 * @return
	 * @throws AucuneSalleSelectionneeException
	 */
	public List<Salle> getListeSalle() throws AucuneSalleSelectionneeException {
		List<Salle> listSalles = SalleDAO.getInstance().lister();
		if (listSalles == null || listSalles.size() <= 0) {
			throw new AucuneSalleSelectionneeException();
		}
		return listSalles;
	}

	/**
	 * Liste les reservations pour un utilisateur donne.
	 * 
	 * @param utilisateur
	 * @return
	 * @throws UtilisateurNonSelectionneException
	 */
	public List<Reservation> getlisteReservationUtilisateur(
			Utilisateur utilisateur) throws UtilisateurNonSelectionneException {
		if (utilisateur == null) {
			throw new UtilisateurNonSelectionneException();
		}
		return ReservationDAO.getInstance().listerReservationParUtilisateur(
				utilisateur.getIdUtilisateur());
	}

	/**
	 * Reserve automatiquement une salle en fonction de la categorie et de la
	 * date recherchee.
	 * 
	 * @param utilisateur
	 * @param categorie
	 * @param jour
	 * @param mois
	 * @param annee
	 * @param heure
	 * @param duree
	 * @return
	 * @throws CategorieNonSelectionneeException
	 * @throws UtilisateurNonSelectionneException
	 * @throws DateIncorrecteException
	 * @throws SalleReserveeException
	 * @throws LundiException
	 */
	public Reservation reservationAutomatique(Utilisateur utilisateur,
			Categorie categorie, Date date, String heure, String duree)
			throws CategorieNonSelectionneeException,
			UtilisateurNonSelectionneException, DateIncorrecteException,
			SalleReserveeException, LundiException {
		if (categorie == null) {
			throw new CategorieNonSelectionneeException();
		}
		if (utilisateur == null) {
			throw new UtilisateurNonSelectionneException();
		}

		int heureInt = Integer.parseInt(heure);
		int dureeInt = Integer.parseInt(duree);

		Reservation reservation = null;
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHH");
		if (estUnLundi(date)) {
			throw new LundiException();
		}

		List<Salle> listeSalles = SalleDAO.getInstance().lister(
				categorie.getIdCategory());

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR, heureInt);
		calendar.set(Calendar.MINUTE, 0);

		Date dateDebutReservation = new Date(calendar.getTimeInMillis());
		calendar.set(Calendar.HOUR, heureInt + dureeInt);
		Date dateFinReservation = new Date(calendar.getTimeInMillis());

		for (Salle salle : listeSalles) {
			if (ReservationDAO.getInstance().isSalleReservee(
					salle.getIdSalle(), dateDebutReservation)) {
				float tarif = calculerTarif(salle, heureInt, dureeInt);
				ReservationDAO.getInstance().reserver(
						utilisateur.getIdUtilisateur(), salle.getIdSalle(),
						dateDebutReservation, dateFinReservation, tarif);
				// TODO creation de la reservation
				// reservation =
				// ReservationDAO.getInstance().creer(dateReservation,
				// dateFinReservation, confirmation, idUtilisateur, tarif,
				// idSalle, dateDebutReservation)
				break;
			}
		}

		// TODO

		return reservation;
	}

	/**
	 * Methode verifiant si une date correspond a un lundi.
	 * 
	 * @param date
	 * @return
	 */
	public boolean estUnLundi(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY;
	}

	public List<Reservation> getListReservation(int idSalle, Date date) {
		return ReservationDAO.getInstance().listerReservationParDateEtSalle(
				idSalle, date);
	}
}
