package dao;

import java.util.Date;

public class ReservationDAO {

	private static final ReservationDAO RESERVATION_DAO = new ReservationDAO();

	private ReservationDAO() {
		super();
	}

	public static ReservationDAO getInstance() {
		return RESERVATION_DAO;
	}

	public boolean deleteFromIdUtilisateurDate(Integer idUtilisateur, Date date) {
		// TODO JBG RDK
		return false;
	}

	/**
	 * Supprime toutes les reservations non confirmees dont la date de debut <
	 * date actuelle
	 * 
	 * @return
	 */
	public boolean deleteAllReservationNonConfirmees() {
		// TODO JBG RDK
		/*
		 * requete : delete from reservation where confirme="dateDepassee" and
		 * date debut < curr
		 */
		return false;
	}
}
