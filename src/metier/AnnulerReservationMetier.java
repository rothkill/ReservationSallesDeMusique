package metier;

import java.util.Date;

import dao.ReservationDAO;
import data.Utilisateur;

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
	 * Supprime une reservation effectue par un utilisateur a une date donnee.
	 * 
	 * @param utilisateur
	 * @param date
	 * @return <code>true</code> si la suppression a ete effectuee,
	 *         <code>false</code> sinon.
	 */
	public boolean annulerReservationNonConfirmee(Utilisateur utilisateur,
			Date date) {
		return ReservationDAO.getInstance().deleteFromIdUtilisateurDate(
				utilisateur.getIdUtilisateur(), date);
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
