package metier;

import java.util.List;

import utils.Constantes;

import dao.ReservationDAO;
import dao.UtilisateurDAO;
import data.Reservation;
import data.Utilisateur;
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
	 * Confirme une reservatnio et ajoute les points fidelites au compte de
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
			if(utiliserPointsFidelite){
				return modifierPointsFidelite(utilisateur, reservation.getDuree());
			}
			return ajouterPointsFidelite(utilisateur, reservation.getDuree());
		}
		return false;
	}
	
	

	private boolean modifierPointsFidelite(Utilisateur utilisateur, int duree) {
		// TODO Auto-generated method stub
		return false;
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
}
