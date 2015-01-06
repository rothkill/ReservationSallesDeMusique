package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import data.CarteForfait;
import data.Categorie;
import data.Forfait;
import data.Utilisateur;
import utils.ConnectionDB;

public class CarteForfaitDAO {
	private Connection con = ConnectionDB.getInstance().getConnection();

	private static CarteForfaitDAO SINGLETON;

	public CarteForfaitDAO() {
	}

	public static CarteForfaitDAO getInstance() {
		if (SINGLETON == null) {
			SINGLETON = new CarteForfaitDAO();
		}
		return SINGLETON;
	}

	public List<CarteForfait> listerParUtilisateurCategorie(int idUtilisateur,
			int idCategorie) {
		List<CarteForfait> listCarteForfait = new ArrayList<CarteForfait>();
		try {
			PreparedStatement st = con
					.prepareStatement("select dateachat,datefin,tempsrestant,idforfait from carteforfait join forfait on forfait.idforfait = carteforfait.idforfait where carteforfait.idforfait = ? and forfait.idcategorie = ?");
			st.setInt(1, idUtilisateur);
			st.setInt(2, idCategorie);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				listCarteForfait.add(new CarteForfait(rs.getInt(1),rs.getDate(2),rs.getDate(3),rs.getInt(4),ForfaitDAO.getInstance().rechercher(rs.getInt(5)),UtilisateurDAO.getInstance().rechercher(idUtilisateur)));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return listCarteForfait;
	}

	public boolean modifierCarteForfait(int idForfait,int idUtilisateur, int nouvelleDuree) {
		try {
			PreparedStatement st = con
					.prepareStatement("update carteforfait set tempsrestant = ? where idutilisateur = ? and idforfait = ? ");
			st.setInt(1, nouvelleDuree);
			st.setInt(2, idUtilisateur);
			st.setInt(3, idForfait);
			st.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
		
	public boolean supprimer(Integer idUtilisateur, Integer idForfait) {
		try {
			PreparedStatement st = con
					.prepareStatement("delete from carteforfait where idforfait = ? and idutilisateur = ?");
			st.setInt(1, idForfait);
			st.setInt(2, idUtilisateur);
			st.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
}
