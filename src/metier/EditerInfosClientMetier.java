package metier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import utils.Constantes;
import dao.CarteForfaitDAO;
import dao.CategorieDAO;
import dao.ForfaitDAO;
import dao.ReservationDAO;
import dao.UtilisateurDAO;
import data.CarteForfait;
import data.Categorie;
import data.Forfait;
import data.Reservation;
import data.Utilisateur;
import exception.AucunForfaitExistantException;
import exception.AucuneReservationUtilisateurException;
import exception.ReservationNonSelectionneeException;
import exception.UtilisateurNonSelectionneException;

public class EditerInfosClientMetier {

	private static final EditerInfosClientMetier EDITER_INFOS_CLIENT_METIER = new EditerInfosClientMetier();

	private EditerInfosClientMetier() {
		super();
	}

	public static EditerInfosClientMetier getInstance() {
		return EDITER_INFOS_CLIENT_METIER;
	}

	/**
	 * Retourne la liste des {@link Reservation} effectuees par un
	 * {@link Utilisateur}
	 * 
	 * @param utilisateur
	 * @return
	 * @throws UtilisateurNonSelectionneException
	 * @throws AucuneReservationUtilisateurException
	 */
	public List<Reservation> listerReservationParUtilisateur(
			Utilisateur utilisateur) throws UtilisateurNonSelectionneException,
			AucuneReservationUtilisateurException {
		if (utilisateur == null) {
			throw new UtilisateurNonSelectionneException();
		}

		List<Reservation> listReservations = ReservationDAO
				.getInstance()
				.listerReservationParUtilisateur(utilisateur.getIdUtilisateur());

		if (listReservations == null || listReservations.size() <= 0) {
			throw new AucuneReservationUtilisateurException();
		}
		return listReservations;
	}

	/**
	 * Passe la {@link Reservation} a l'etat confirme.
	 * 
	 * @param reservation
	 * @return
	 */
	public boolean confirmerReservation(Reservation reservation) {

		return ReservationDAO.getInstance().confirmerReservation(
				reservation.getIdReservation());
	}

	/**
	 * Confirme une reservation et ajoute les points fidelites au compte de
	 * l'utilisateur;
	 * 
	 * @param utilisateur
	 * @param reservation
	 * @param utiliserPointsFidelite
	 * @param utiliserForfait
	 * @return
	 * @throws ReservationNonSelectionneeException
	 * @throws UtilisateurNonSelectionneException
	 */

	// TODO renvoyer le prix restant a payer au lieu d'un boolean
	public boolean reservation(Utilisateur utilisateur,
			Reservation reservation, boolean utiliserPointsFidelite,
			boolean utiliserForfait)
			throws ReservationNonSelectionneeException,
			UtilisateurNonSelectionneException {
		if (utilisateur == null) {
			throw new UtilisateurNonSelectionneException();
		}
		if (reservation == null) {
			throw new ReservationNonSelectionneeException();
		}

		if (confirmerReservation(reservation)) {
			int duree = reservation.getDuree();

			// Si les forfaits sont utilises
			if (utiliserForfait) {
				Categorie categorie = CategorieDAO
						.getInstance()
						.rechercherParSalle(reservation.getSalle().getIdSalle());
				duree = utiliserForfait(utilisateur, categorie, duree);
			}
			// Si les points fidelite sont utilises
			if (utiliserPointsFidelite && duree > 0) {
				return modifierPointsFidelite(utilisateur, duree);
			}
			return ajouterPointsFidelite(utilisateur, duree);
		}
		return false;
	}

	/**
	 * Utilise les forfaits puis supprime les forfaits perimes ou vides.
	 * 
	 * @param utilisateur
	 * @param categorie
	 * @return
	 */
	private int utiliserForfait(Utilisateur utilisateur, Categorie categorie,
			int duree) {
		List<CarteForfait> listeForfait = CarteForfaitDAO.getInstance()
				.listerParUtilisateurCategorie(utilisateur.getIdUtilisateur(),
						categorie.getIdCategory());
		List<CarteForfait> listeForfaitASupprimer = new ArrayList<CarteForfait>();

		for (CarteForfait forfait : listeForfait) {
			// if FORFAIT PERIME
			if (new Date().compareTo(forfait.getDateFinValidite()) > 0) {
				listeForfaitASupprimer.add(forfait);
				continue;
			}
			if (forfait.getDureeRestante() <= duree) {
				duree -= forfait.getDureeRestante();
				listeForfaitASupprimer.add(forfait);
				continue;
			} else {
				CarteForfaitDAO.getInstance().modifierCarteForfait(
						forfait.getForfait().getForfait(),
						forfait.getUtilisateur().getIdUtilisateur(),
						forfait.getDureeRestante() - duree);
				duree = 0;
				break;
			}
		}

		if (listeForfaitASupprimer.size() > 0) {
			supprimerForfaits(listeForfaitASupprimer);
		}

		return duree;
	}

	private void supprimerForfaits(List<CarteForfait> listeForfaits) {
		for (CarteForfait carteForfait : listeForfaits) {
			CarteForfaitDAO.getInstance().supprimer(
					carteForfait.getForfait().getIdForfait(),
					carteForfait.getUtilisateur().getIdUtilisateur());
		}
	}

	private void supprimerTousForfaitCategorie(Utilisateur utilisateur,
			Categorie categorie) {
		ForfaitDAO.getInstance().supprimerTousForfaits(
				utilisateur.getIdUtilisateur(), categorie.getIdCategory());
	}

	/**
	 * Totalise les forfaits
	 * 
	 * @param utilisateur
	 * @param categorie
	 * @return
	 */
	private int totalForfait(Utilisateur utilisateur, Categorie categorie) {
		utilisateur.setListForfaitsUtilisateur(ForfaitDAO.getInstance()
				.listerForfaitUtilisateur(utilisateur.getIdUtilisateur(),
						categorie.getIdCategory()));
		return 0;
	}

	/**
	 * Modifie les points fidelites d'un client selon la duree de la
	 * reservation.
	 * 
	 * @param utilisateur
	 * @param duree
	 * @return
	 */
	private boolean modifierPointsFidelite(Utilisateur utilisateur, int duree) {
		int modificateurPointFidelite;
		if (utilisateur.getPointFidelite()
				/ Constantes.CORRESPONDANCE_HEURE_GRATUITE_POINTS_FIDELITE >= duree) {
			// on retire les points de fidelite necessaires
			modificateurPointFidelite = duree
					* Constantes.CORRESPONDANCE_HEURE_POINTS_FIDELITE - duree
					* Constantes.CORRESPONDANCE_HEURE_GRATUITE_POINTS_FIDELITE;
		} else {
			// on retire tous les points disponibles
			modificateurPointFidelite = duree
					* Constantes.CORRESPONDANCE_HEURE_POINTS_FIDELITE
					- utilisateur.getPointFidelite();
		}
		return UtilisateurDAO.getInstance().retirerFidelite(
				utilisateur.getIdUtilisateur(), modificateurPointFidelite);
	}

	/**
	 * Ajoute des points fidelites a un utilisateur.
	 * 
	 * @param utilisateur
	 * @param duree
	 * @return
	 */

	private boolean ajouterPointsFidelite(Utilisateur utilisateur, int duree) {
		return UtilisateurDAO.getInstance().ajouterFidelite(
				utilisateur.getIdUtilisateur(),
				duree * Constantes.CORRESPONDANCE_HEURE_POINTS_FIDELITE);
	}

	/**
	 * Retourne la liste de tous les forfaits existants.
	 * 
	 * @return
	 * @throws AucunForfaitExistantException
	 */
	public List<Forfait> listerForfait() throws AucunForfaitExistantException {
		List<Forfait> listForfaits = ForfaitDAO.getInstance().listerForfait();
		if (listForfaits.size() <= 0) {
			throw new AucunForfaitExistantException();
		}
		return listForfaits;
	}
}
