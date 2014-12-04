package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import main.ConnectionDB;
import data.Categorie;
import data.Salle;

public class SalleDAO {

	private Connection con = ConnectionDB.getInstance().getConnection();

	private static SalleDAO SINGLETON;

	public SalleDAO() {
	}

	public static SalleDAO getInstance() {
		if (SINGLETON == null) {
			SINGLETON = new SalleDAO();
		}
		return SINGLETON;
	}

	// public Salle creer(int idCategory, String nom, int tarifUneHeure,
	// int tarifDeuxHeures, int nbPersonne){
	// try {
	// PreparedStatement st =
	// con.prepareStatement("insert into categorie values(?,?,?,?,?)");
	// st.setInt(1, idCategory);
	// st.setString(2, nom);
	// st.setInt(3, tarifUneHeure);
	// st.setInt(4, tarifDeuxHeures);
	// st.setInt(5,nbPersonne);
	// st.executeUpdate();
	// // return new
	// Salle(idCategory,nom,tarifUneHeure,tarifDeuxHeures,nbPersonne);
	// } catch (SQLException e) {
	// System.out.println(e.getMessage());
	// return null;
	// }
	//
	// }

	/**
	 * Recherche les reservation d'une certaine categorie de salle a une
	 * certaine date.
	 * 
	 * @param idCategorie
	 * @param jour
	 * @param mois
	 * @param annee
	 * @return
	 */
	public List<Salle> rechercherParCategorieEtDate(Integer idCategorie,
			int jour, int mois, int annee) {
		// TODO
		return null;
	}

	public Salle rechercher(int idSalle) {
		Salle salle = null;
		try {
			PreparedStatement st = con
					.prepareStatement("select idcategorie,nom,etat from salle where idsalle = ?");
			st.setInt(1, idSalle);
			ResultSet rs = st.executeQuery();
			if (rs.next() != false) {
				salle = new Salle(idSalle, rs.getInt(1), rs.getString(2),
						rs.getString(3));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return salle;

	}

	public boolean supprimer(int idCategory) {
		try {
			PreparedStatement st = con
					.prepareStatement("delete from categorie where idcategorie = ?");
			st.setInt(1, idCategory);
			st.execute();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
}
