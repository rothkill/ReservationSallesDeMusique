package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

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

	public boolean creer(int idBureau, String description) {
		try {
			PreparedStatement st = con
					.prepareStatement("insert into bureau values(?,?)");
			st.setInt(1, idBureau);
			st.setString(2, description);
			st.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}

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
