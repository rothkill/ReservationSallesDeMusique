package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utils.ConnectionDB;

import data.Utilisateur;

public class UtilisateurDAO {
	private Connection con = ConnectionDB.getInstance().getConnection();

	private static UtilisateurDAO SINGLETON;

	public UtilisateurDAO() {
	}

	public static UtilisateurDAO getInstance() {
		if (SINGLETON == null) {
			SINGLETON = new UtilisateurDAO();
		}
		return SINGLETON;
	}

	public Utilisateur creer(String nom, String telephone, int pointsFidelite) {
		try {
			PreparedStatement st = con
					.prepareStatement("insert into utilisateur(nom,telephone,pointsfidelite) values(?,?,?)");
			st.setString(1, nom);
			st.setString(2, telephone);
			st.setInt(3, pointsFidelite);
			st.executeUpdate();
			ResultSet rs = st.getGeneratedKeys();
			return new Utilisateur(rs.getInt(1), nom, telephone, pointsFidelite);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	public Utilisateur rechercher(int idUtilisateur) {
		Utilisateur utilisateur = null;
		try {
			PreparedStatement st = con
					.prepareStatement("select nom,telephone,pointsfidelite from utilisateur where idutilisateur = ?");
			st.setInt(1, idUtilisateur);
			ResultSet rs = st.executeQuery();
			if (rs.next() != false) {
				utilisateur = new Utilisateur(idUtilisateur, rs.getString(1),
						rs.getString(2), rs.getInt(3));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return utilisateur;

	}

	public boolean supprimer(int idUtilisateur) {
		try {
			PreparedStatement st = con
					.prepareStatement("delete from utilisateur where idutilisateur = ?");
			st.setInt(1, idUtilisateur);
			st.execute();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	/**
	 * Retourne la liste de tous les utilisateurs ayant des reservations non
	 * confirmees.
	 * 
	 * @return
	 */
	public List<Utilisateur> listerUtilisateursEtResNonConfirmees() {

		List<Utilisateur> listUtilisateurs = new ArrayList<Utilisateur>();

		try {
			PreparedStatement st = con
					.prepareStatement("select idutilisateur from reservation where confirmation = ?");
			st.setBoolean(1, false);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Utilisateur utilisateur = UtilisateurDAO.getInstance()
						.rechercher(rs.getInt(1));
				utilisateur.setListReservationsUtilisateur(ReservationDAO
						.getInstance()
						.listerReservationNonConfirmeesParUtilisateur(
								utilisateur.getIdUtilisateur()));
				listUtilisateurs.add(utilisateur);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return listUtilisateurs;
	}

	/**
	 * Retourne la liste de tous les utilisateurs.
	 * 
	 * @return
	 */
	public List<Utilisateur> listerUtilisateurs() {
		List<Utilisateur> listUtilisateurs = new ArrayList<Utilisateur>();
		Utilisateur utilisateur = null;
		try {
			PreparedStatement st = con
					.prepareStatement("select idutilisateur,nom,telephone,pointsfidelite from utilisateur");
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				utilisateur = new Utilisateur(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getInt(4));
				listUtilisateurs.add(utilisateur);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return listUtilisateurs;
	}

	/**
	 * retire les points fidelites d'un utilisateur.
	 * 
	 * @param idUtilisateur
	 * @param points
	 * @return
	 */
	public boolean retirerFidelite(Integer idUtilisateur, int points) {
		try {
			PreparedStatement st = con
					.prepareStatement("update from utilisateur set pointsfidelite =  pointsfidelite - ? where idutilisateur = ?");
			st.setInt(1, points);
			st.setInt(2, idUtilisateur);
			st.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	/**
	 * Ajoute des points fidelites à un utlisateur
	 * @param idUtilisateur
	 * @param points
	 * @return
	 */
	public boolean ajouterFidelite(Integer idUtilisateur, int points) {
		try {
			PreparedStatement st = con
					.prepareStatement("update from utilisateur set pointsfidelite = pointsFidelite + ? where idutilisateur = ?");
			st.setInt(1, points);
			st.setInt(2, idUtilisateur);
			st.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

}
