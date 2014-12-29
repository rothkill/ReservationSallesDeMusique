package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import utils.ConnectionDB;
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

	public Salle creer(int idCategory, String nom, String etat) {
		try {
			PreparedStatement st = con
					.prepareStatement("insert into salledemusique values(?,?,?,?,?)");
			st.setInt(1, idCategory);
			st.setString(2, nom);
			st.setString(2, etat);
			st.executeUpdate();
			ResultSet rs = st.getGeneratedKeys();
			if (rs.next())
				return new Salle(rs.getInt(1), CategorieDAO.getInstance()
						.rechercher(idCategory), nom, etat);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
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
				salle = new Salle(idSalle, CategorieDAO.getInstance()
						.rechercher(rs.getInt(1)), rs.getString(2),
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

	/**
	 * Retourne la liste de toutes les salles.
	 * 
	 * @return
	 */
	public List<Salle> lister() {
		List<Salle> listSalles = new ArrayList<Salle>();
		try {
			PreparedStatement st = con
					.prepareStatement("select idsallemusique,idcategorie,nom,etat from salledemusique");
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				listSalles.add(new Salle(rs.getInt(1), CategorieDAO
						.getInstance().rechercher(rs.getInt(2)), rs
						.getString(3), rs.getString(4)));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return listSalles;
	}

	/**
	 * Retourne la liste de toutes les salles appartenant a une categorie.
	 * 
	 * @return
	 */
	public List<Salle> lister(Integer idCategorie) {
		List<Salle> listSalles = new ArrayList<Salle>();
		try {
			PreparedStatement st = con
					.prepareStatement("select idsallemusique,idcategorie,nom,etat from salledemusique where idcategorie = ?");
			st.setInt(1, idCategorie);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				listSalles.add(new Salle(rs.getInt(1), CategorieDAO
						.getInstance().rechercher(rs.getInt(2)), rs
						.getString(3), rs.getString(4)));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return listSalles;
	}
}
