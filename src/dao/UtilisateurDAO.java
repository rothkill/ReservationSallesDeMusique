package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import data.Utilisateur;
import main.ConnectionDB;

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

	public Utilisateur creer(int idUtilisateur, String nom, String telephone,
			int pointsFidelite) {
		try {
			PreparedStatement st = con
					.prepareStatement("insert into utilisateur values(?,?,?,?)");
			st.setInt(1, idUtilisateur);
			st.setString(2, nom);
			st.setString(3, telephone);
			st.setInt(4, pointsFidelite);
			st.executeUpdate();
			return new Utilisateur(idUtilisateur, nom, telephone,
					pointsFidelite);
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

		// TODO
		return null;
	}
}
