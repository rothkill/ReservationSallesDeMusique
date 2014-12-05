package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import data.Reservation;
import main.ConnectionDB;

public class ReservationDAO {

	private Connection con = ConnectionDB.getInstance().getConnection();

	private static ReservationDAO SINGLETON;

	public ReservationDAO() {
	}

	public static ReservationDAO getInstance() {
		if (SINGLETON == null) {
			SINGLETON = new ReservationDAO();
		}
		return SINGLETON;
	}

	public Reservation creer(int idReservation, Date dateReservation,
			Date dateDebutSceance, int duree, Boolean confirmation,
			int idSalle, int idUtilisateur) {
		try {
			PreparedStatement st = con
					.prepareStatement("insert into reservation values(?,?,?,?,?,?,?)");
			st.setInt(1, idReservation);
			st.setDate(2, (java.sql.Date) dateReservation);
			st.setDate(3, (java.sql.Date) dateDebutSceance);
			st.setInt(4, duree);
			st.setBoolean(5, confirmation);
			st.setInt(6, idSalle);
			st.setInt(7, idUtilisateur);
			st.executeUpdate();
			return new Reservation(idReservation, dateReservation,
					dateDebutSceance, duree, confirmation, SalleDAO
							.getInstance().rechercher(idSalle), UtilisateurDAO
							.getInstance().rechercher(idUtilisateur));
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	public boolean deleteReservation(Integer idReservation) {
		try {
			PreparedStatement st = con
					.prepareStatement("delete from reservation where idreservation = ?");
			st.setInt(1, idReservation);
			st.execute();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	// TODO modifier en methode de recherche
	public boolean deleteFromIdUtilisateurDate(Integer idUtilisateur, Date date) {
		try {
			PreparedStatement st = con
					.prepareStatement("delete from reservation where idutilisateur = ? and datereservation = ?");
			st.setInt(1, idUtilisateur);
			st.setDate(2, (java.sql.Date) date);
			st.execute();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	/**
	 * Supprime toutes les reservations non confirmees dont la date de debut <
	 * date actuelle
	 * 
	 * @return
	 */
	public boolean deleteAllReservationNonConfirmees() {
		try {
			PreparedStatement st = con
					.prepareStatement("delete from reservation where confirmation = ?");
			st.setBoolean(1, false);
			st.execute();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	/**
	 * Retourne la liste de toutes les reservations d'un utilisateur.
	 * 
	 * @param idUtilisateur
	 * @return
	 */
	public List<Reservation> listerReservationParUtilisateur(
			Integer idUtilisateur) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Passe la {@link Reservation} a l'etat confirme.
	 * 
	 * 
	 * @param idReservation
	 * @return
	 */
	public boolean confirmerReservation(Integer idReservation) {
		// TODO Auto-generated method stub
		return false;
	}

}
