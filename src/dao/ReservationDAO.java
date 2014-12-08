package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import utils.ConnectionDB;
import data.Reservation;
import data.Salle;

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

	/**
	 * /** Création d'une réservation de salle
	 * 
	 * @param idReservation
	 * @param dateReservation
	 * @param dateDebutSceance
	 * @param duree
	 * @param confirmation
	 * @param idSalle
	 * @param idUtilisateur
	 * @param tarif
	 * @return
	 */
	public Reservation creer(Date dateReservation, Date dateFinReservation,
			Boolean confirmation, int idUtilisateur, float tarif, int idSalle,
			Date dateDebutReservation) {
		try {
			PreparedStatement st = con
					.prepareStatement("insert into reservation( values(?,?,?,?,?,?,?)");
			st.setDate(1, (java.sql.Date) dateReservation);
			st.setDate(2, (java.sql.Date) dateFinReservation);
			st.setBoolean(3, confirmation);
			st.setInt(4, idUtilisateur);
			st.setFloat(5, tarif);
			st.setInt(6, idSalle);
			st.setDate(7, (java.sql.Date) dateDebutReservation);
			st.executeUpdate();
			ResultSet rs = st.getGeneratedKeys();
			if (rs.next()){
				return new Reservation(rs.getInt(1), dateReservation,
						dateDebutReservation,dateFinReservation, confirmation, SalleDAO.getInstance()
								.rechercher(idSalle), UtilisateurDAO
								.getInstance().rechercher(idUtilisateur), tarif);}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
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

	//
	public Reservation rechercherFromIdUtilisateurDate(Integer idUtilisateur,
			Date date) {
		try {
			PreparedStatement st = con
					.prepareStatement("select idreservation,datedebutreservation,datefinreservation,confirmation,idSalle,tarif from reservation where idutilisateur = ? and datereservation = ?");
			st.setInt(1, idUtilisateur);
			st.setDate(2, (java.sql.Date) date);
			ResultSet rs = st.executeQuery();
			if (rs.next())
				return new Reservation(rs.getInt(1), date, rs.getDate(2),rs.getDate(3),
						rs.getBoolean(4), SalleDAO.getInstance().rechercher(
								rs.getInt(5)), UtilisateurDAO.getInstance()
								.rechercher(idUtilisateur), rs.getFloat(6));
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
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
		List<Reservation> lesReservations = new ArrayList<Reservation>();
		try {
			PreparedStatement st = con
					.prepareStatement("select idreservation,datereservation,datedebutreservation,datefinreservation,confirmation,idSalle,tarif from reservation where idutilisateur = ?");
			st.setInt(1, idUtilisateur);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				lesReservations.add(new Reservation(rs.getInt(1),
						rs.getDate(2), rs.getDate(3),rs.getDate(4), rs.getBoolean(5),
						SalleDAO.getInstance().rechercher(rs.getInt(6)),
						UtilisateurDAO.getInstance().rechercher(idUtilisateur),
						rs.getFloat(7)));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return lesReservations;
	}

	/**
	 * Passe la {@link Reservation} a l'etat confirme.
	 * 
	 * 
	 * @param idReservation
	 * @return
	 */
	public boolean confirmerReservation(Integer idReservation) {
		try {
			PreparedStatement st = con
					.prepareStatement("update reservation set confirmation = ? where idreservation = ?");
			st.setBoolean(1, true);
			st.setInt(2, idReservation);
			st.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	/**
	 * Verifie si la salle est reservee.
	 * 
	 * @param idSalle
	 * @param date
	 * @return
	 */
	public boolean isSalleReservee(Integer idSalle, Date date) {
		try {
			PreparedStatement st = con
					.prepareStatement("select confirmation from reservation where idsalle = ? and date = ?");
			st.setInt(1, idSalle);
			st.setDate(2, (java.sql.Date) date);
			ResultSet rs = st.executeQuery();
			if (rs.next())
				return rs.getBoolean(1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	/**
	 * Reservation d'une salle
	 * 
	 * @param idUtilisateur
	 * @param idSalle
	 * @param date
	 * @return
	 */
	public boolean reserver(Integer idUtilisateur, Integer idSalle, Date dateDebutReservation,
			Date dateFinReservation) {
		try {
			PreparedStatement st = con
					.prepareStatement("insert into reservation(datereservation,datedebutreservation,datefinreservation,confirmation,idutilisateur,idsalle) values(?,?,?,?,?,?)");
			st.setDate(1, (java.sql.Date) new Date());
			st.setDate(2, (java.sql.Date) dateDebutReservation);
			st.setDate(3, (java.sql.Date) dateFinReservation);
			st.setBoolean(4, false);
			st.setInt(5, idUtilisateur);
			st.setInt(6, idSalle);
			st.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public List<Salle> rechercherReservationParCategorieEtDate(int idCategory,
			int jour, int mois, int annee) {
		// TODO Auto-generated method stub
		return null;
	}



}
