package metier;

import java.util.List;

import utils.Constantes;
import dao.ForfaitDAO;
import dao.ReservationDAO;
import dao.UtilisateurDAO;
import data.Forfait;
import data.Reservation;
import data.Utilisateur;
import exception.AucunForfaitExistantException;
import exception.AucuneReservationUtilisateurException;
import exception.ForfaitNonSelectionneException;
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
	 * @return
	 * @throws ReservationNonSelectionneeException
	 * @throws UtilisateurNonSelectionneException
	 */
	public boolean reservation(Utilisateur utilisateur,
			Reservation reservation, boolean utiliserPointsFidelite)
			throws ReservationNonSelectionneeException,
			UtilisateurNonSelectionneException {
		if (utilisateur == null) {
			throw new UtilisateurNonSelectionneException();
		}
		if (reservation == null) {
			throw new ReservationNonSelectionneeException();
		}

		if (confirmerReservation(reservation)) {
			if (utiliserPointsFidelite) {
				return modifierPointsFidelite(utilisateur,
						reservation.getDuree());
			}
			return ajouterPointsFidelite(utilisateur, reservation.getDuree());
		}
		return false;
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

		// TODO modifier pour utiliser un modulo sur les pf en fonction de la
		// duree et des pf
		// return le prix restant ?
		if (utilisateur.getPointFidelite()
				/ Constantes.CORRESPONDANCE_HEURE_GRATUITE_POINTS_FIDELITE >= duree) {
			// on retire les points de fidelité necessaires
			modificateurPointFidelite = duree
					* Constantes.CORRESPONDANCE_HEURE_POINTS_FIDELITE - duree
					* Constantes.CORRESPONDANCE_HEURE_GRATUITE_POINTS_FIDELITE;
		} else {
			// on retire tous les points disponibles
			modificateurPointFidelite = duree
					* Constantes.CORRESPONDANCE_HEURE_POINTS_FIDELITE
					+ (utilisateur.getPointFidelite() % Constantes.CORRESPONDANCE_HEURE_GRATUITE_POINTS_FIDELITE);
		}
		return UtilisateurDAO.getInstance().modifierFidelite(
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
		return UtilisateurDAO.getInstance().modifierFidelite(
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

	/**
	 * Lie un forfait a un utilisateur.
	 * 
	 * @param utilisateur
	 * @param forfait
	 * @return
	 * @throws UtilisateurNonSelectionneException
	 * @throws ForfaitNonSelectionneException
	 */
	public boolean attacherForfaitUtilisateur(Utilisateur utilisateur,
			Forfait forfait) throws UtilisateurNonSelectionneException,
			ForfaitNonSelectionneException {
		if (utilisateur == null) {
			throw new UtilisateurNonSelectionneException();
		}
		if (forfait == null) {
			throw new ForfaitNonSelectionneException();
		}
		return ForfaitDAO.getInstance().lier(utilisateur.getIdUtilisateur(),
				forfait.getIdForfait());
	}

	/**
	 * Creation d'un utilisateur.
	 * 
	 * @param nom
	 * @param telephone
	 * @param forfait
	 * @return
	 */
	public boolean creerUtilisateur(String nom, String telephone,
			Forfait forfait) {
		// TODO

		// UtilisateurDAO.getInstance().creer(idUtilisateur, nom, telephone,
		// pointsFidelite)
		if (forfait == null) {

		} else {

		}

		return false;
	}

	/**
	 * Confirme une reservation en utilisant le forfait de l'utilisateur.
	 */
	public void utiliserForfait() {
		// TODO Auto-generated method stub

	}
}
