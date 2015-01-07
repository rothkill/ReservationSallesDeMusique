package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
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
	 * /** Cr�ation d'une r�servation de salle
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
			if (rs.next()) {
				return new Reservation(rs.getInt(1), dateReservation,
						dateDebutReservation, dateFinReservation, confirmation,
						SalleDAO.getInstance().rechercher(idSalle),
						UtilisateurDAO.getInstance().rechercher(idUtilisateur),
						tarif);
			}
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
				return new Reservation(rs.getInt(1), date, rs.getDate(2),
						rs.getDate(3), rs.getBoolean(4), SalleDAO.getInstance()
								.rechercher(rs.getInt(5)), UtilisateurDAO
								.getInstance().rechercher(idUtilisateur),
						rs.getFloat(6));
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	/**
	 * Liste les reservations a partir d'une salle;
	 * 
	 * @param idSalle
	 * @return
	 */
	public List<Reservation> listerReservationParSalle(Integer idSalle) {
		List<Reservation> liste = new ArrayList<Reservation>();
		try {
			PreparedStatement st = con
					.prepareStatement("select idreservation,datereservation,datedebutreservation,datefinreservation,confirmation,idutilisateur,tarif from reservation where idSalle = ?");
			st.setInt(1, idSalle);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Reservation r = new Reservation(rs.getInt(1), rs.getDate(2),
						rs.getDate(3), rs.getDate(4), rs.getBoolean(5), null,
						UtilisateurDAO.getInstance().rechercher(rs.getInt(6)),
						rs.getFloat(7));
				liste.add(r);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return liste;
	}
	
	/**
	 * Liste les reservations a partir d'une salle;
	 * 
	 * @param idSalle
	 * @return
	 */
	public List<Reservation> listerReservationParCategorie(Integer idCategorie) {
		List<Reservation> liste = new ArrayList<Reservation>();
		try {
			PreparedStatement st = con
					.prepareStatement("select idreservation,datereservation,datedebutreservation,datefinreservation,confirmation,idutilisateur,tarif from reservation join salledemusique on salledemusique.idsallemusique = reservation.idsalle where idCategorie = ?");
			st.setInt(1, idCategorie);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Reservation r = new Reservation(rs.getInt(1), rs.getDate(2),
						rs.getDate(3), rs.getDate(4), rs.getBoolean(5), null,
						UtilisateurDAO.getInstance().rechercher(rs.getInt(6)),
						rs.getFloat(7));
				liste.add(r);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return liste;
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
						rs.getDate(2), rs.getDate(3), rs.getDate(4), rs
								.getBoolean(5), SalleDAO.getInstance()
								.rechercher(rs.getInt(6)), UtilisateurDAO
								.getInstance().rechercher(idUtilisateur), rs
								.getFloat(7)));
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
	public boolean isSalleReservee(Integer idSalle, Date dateDebutReservation) {
		try {
			java.sql.Date dateDebutReservationSQL = new java.sql.Date(
					dateDebutReservation.getTime());
			PreparedStatement st = con
					.prepareStatement("select idreservation from reservation where idsalle = ? and datedebutreservation = ?");
			st.setInt(1, idSalle);
			st.setDate(2, (java.sql.Date) dateDebutReservationSQL);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				return true;
			}
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
	public boolean reserver(Integer idUtilisateur, Integer idSalle,
			Date dateDebutReservation, Date dateFinReservation, float tarif) {
		try {
			java.sql.Date dateDebutReservationSQL = new java.sql.Date(
					dateDebutReservation.getTime());
			java.sql.Date dateFinReservationSQL = new java.sql.Date(
					dateFinReservation.getTime());
			PreparedStatement st = con
					.prepareStatement("insert into reservation(datereservation,datedebutreservation,datefinreservation,confirmation,idutilisateur,idsalle,tarif,datelimitereservation) values(CURRENT_TIMESTAMP,?,?,?,?,?,?, DATEADD ( 'day', 7, CURRENT_TIMESTAMP))");
			st.setDate(1, dateDebutReservationSQL);
			st.setDate(2, dateFinReservationSQL);
			st.setBoolean(3, false);
			st.setInt(4, idUtilisateur);
			st.setInt(5, idSalle);
			st.setFloat(6, tarif);
			st.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	/**
	 * Retourne la liste des reservations non confirmees pour un utilisateur.
	 * 
	 * @param idUtilisateur
	 * @return
	 */
	public List<Reservation> listerReservationNonConfirmeesParUtilisateur(
			Integer idUtilisateur) {

		List<Reservation> listReservations = new ArrayList<Reservation>();

		try {
			PreparedStatement st = con
					.prepareStatement("select idreservation,datedebutreservation,datefinreservation,confirmation,idSalle,tarif,datereservation from reservation where confirmation = ? and idutilisateur = ?");
			st.setBoolean(1, false);
			st.setInt(2, idUtilisateur);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				listReservations.add(new Reservation(rs.getInt(1), rs
						.getDate(7), rs.getDate(2), rs.getDate(3), rs
						.getBoolean(4), SalleDAO.getInstance().rechercher(
						rs.getInt(5)), UtilisateurDAO.getInstance().rechercher(
						idUtilisateur), rs.getFloat(6)));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return listReservations;
	}

	/**
	 * Supprime une reservation selon la salle et la date.
	 * 
	 * @param idSalle
	 * @param dateDebutReservation
	 * @return
	 */
	public boolean supprimerReservation(Integer idSalle,
			Date dateDebutReservation) {
		try {
			java.sql.Date dateDebutReservationSQL = new java.sql.Date(
					dateDebutReservation.getTime());
			PreparedStatement st = con
					.prepareStatement("delete from reservation where idsalle = ? and datedebutreservation = ?");
			st.setInt(1, idSalle);
			st.setDate(2, (java.sql.Date) dateDebutReservationSQL);
			st.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;

	}

	public List<Reservation> listerReservationParDateEtSalle(int idSalle, Date date) {
		List<Reservation> listReservations = new ArrayList<Reservation>();
		java.sql.Date dateRes = new java.sql.Date(
				date.getTime());
		System.out.println(dateRes);
		try {
			PreparedStatement st = con
					.prepareStatement("select idreservation,datereservation,datedebutreservation,datefinreservation,confirmation,idUtilisateur,tarif from reservation where idsalle = ? and to_char(datedebutreservation,'DD/MM/YY') <= to_char(?,'DD/MM/YY') and to_char(datefinreservation,'DD/MM/YY') >= to_char(?,'DD/MM/YY')");
			st.setInt(1, idSalle);
			st.setDate(2, dateRes);
			st.setDate(3, dateRes);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				listReservations.add(new Reservation(rs.getInt(1),rs.getTimestamp(2),rs.getTimestamp(3),rs.getTimestamp(4),rs.getBoolean(5),SalleDAO.getInstance().rechercher(idSalle),UtilisateurDAO.getInstance().rechercher(rs.getInt(6)),rs.getFloat(7)));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("liste : " + listReservations);
		return listReservations;
	}
}
