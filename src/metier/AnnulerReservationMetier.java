package metier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.ReservationDAO;
import dao.UtilisateurDAO;
import data.Reservation;
import data.Salle;
import data.Utilisateur;
import exception.AucuneReservationNonConfirmeeException;
import exception.ReservationNonSelectionneeException;

/**
 * Metier cas d'utilisation 6 : annulation d'une reservation.
 * 
 * @author grimonprez
 * 
 */
public class AnnulerReservationMetier {

	private static final AnnulerReservationMetier ANNULER_RESERVATION_METIER = new AnnulerReservationMetier();

	private AnnulerReservationMetier() {
		super();
	}

	public static AnnulerReservationMetier getInstance() {
		return ANNULER_RESERVATION_METIER;
	}

	/**
	 * Retourne la liste de tous les utilisateurs ayant des reservations non
	 * confirmees.
	 * 
	 * @return
	 * @throws AucuneReservationNonConfirmeeException
	 */
	public List<Utilisateur> listerUtilisateursEtResNonConfirmees()
			throws AucuneReservationNonConfirmeeException {
		// TODO JBG retirer les tests
		List<Utilisateur> listUtilisateurs = UtilisateurDAO.getInstance()
				.listerUtilisateursEtResNonConfirmees();
		if (listUtilisateurs == null || listUtilisateurs.size() <= 0) {
			throw new AucuneReservationNonConfirmeeException();
		}

		// Test
//		List<Utilisateur> listUtilisateurs = new ArrayList<Utilisateur>();
//		Utilisateur utilisateur = new Utilisateur(1, "util", "tel", 3);
//		Reservation reservation = new Reservation(1, new Date(), new Date(), 3,
//				false, new Salle(1, 1, "salle", "etat"), utilisateur);
//		utilisateur.getListReservationsUtilisateur().add(reservation);
//		listUtilisateurs.add(utilisateur);
		//
		return listUtilisateurs;
	}

	/**
	 * Supprime une reservation effectue par un utilisateur a une date donnee.
	 * 
	 * @param reservation
	 * @return <code>true</code> si la suppression a ete effectuee,
	 *         <code>false</code> sinon.
	 * @throws ReservationNonSelectionneeException
	 */
	public boolean annulerReservationNonConfirmee(Reservation reservation)
			throws ReservationNonSelectionneeException {

		if (reservation == null) {
			throw new ReservationNonSelectionneeException();
		}

		return ReservationDAO.getInstance().deleteReservation(
				reservation.getIdReservation());
	}

	/**
	 * Supprime toutes les reservations non confirmees dont la date de debut <
	 * date actuelle
	 * 
	 * @return <code>true</code> si les suppressions ont ete effectuees,
	 *         <code>false</code> sinon.
	 */
	public boolean annulerToutesReservationsNonConfirmeesDebutees() {
		return ReservationDAO.getInstance().deleteAllReservationNonConfirmees();
	}

}
