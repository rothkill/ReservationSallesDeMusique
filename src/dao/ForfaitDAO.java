package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import utils.ConnectionDB;
import data.Forfait;

public class ForfaitDAO {

	private Connection con = ConnectionDB.getInstance().getConnection();

	private static ForfaitDAO SINGLETON;

	public ForfaitDAO() {
		super();
	}

	public static ForfaitDAO getInstance() {
		if (SINGLETON == null) {
			SINGLETON = new ForfaitDAO();
		}
		return SINGLETON;
	}

	/**
	 * Liste les forfaits existants.
	 * 
	 * @return
	 */
	public List<Forfait> listerForfait() {
		Forfait forfait = null;
		List<Forfait> listForfait = new ArrayList<Forfait>();
		try {
			PreparedStatement st = con
					.prepareStatement("select idforfait,forfait,validite,idcategorie,prix from forfait");
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				forfait = new Forfait(rs.getInt(1), rs.getInt(2), rs.getInt(3),
						CategorieDAO.getInstance().rechercher(rs.getInt(4)),
						rs.getInt(5));
				listForfait.add(forfait);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return listForfait;
	}

	public boolean lier(Integer idUtilisateur, Integer idForfait) {
		Forfait forfait = ForfaitDAO.getInstance().rechercher(idForfait);
		try {
			PreparedStatement st = con
					.prepareStatement("insert into carteforfait values(?,?,CURRENT_TIMESTAMP,ADD_MONTHS ( CURRENT_TIMESTAMP,"
							+ forfait.getValidite() + "),?)");
			st.setInt(1, idUtilisateur);
			st.setInt(2, idForfait);
			st.setInt(3, forfait.getForfait());
			st.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public static Date ajouterMois(Date date, int nbMois) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		;
		cal.add(Calendar.MONTH, nbMois);
		return cal.getTime();
	}

	public Forfait rechercher(Integer idForfait) {
		try {
			PreparedStatement st = con
					.prepareStatement("select forfait,validite,idcategorie,prix from forfait where idforfait = ?");
			st.setInt(1, idForfait);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				return new Forfait(idForfait, rs.getInt(1), rs.getInt(2),
						CategorieDAO.getInstance().rechercher(rs.getInt(3)),
						rs.getInt(4));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	/**
	 * Supprime tous les forfaits d'un utilisateur.
	 * 
	 * @param idUtilisateur
	 * @param idCategorie
	 */
	public boolean supprimerTousForfaits(Integer idUtilisateur, int idCategorie) {
		try {
			PreparedStatement st = con
					.prepareStatement("delete from carteforfait join forfait on carteforfait.idforfait = forfait.idforfait where idutilisateur = ? and forfait.idCategorie = ?");
			st.setInt(1, idUtilisateur);
			st.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;

	}

	/**
	 * Liste les forfaits non perimes par utilisateur, ordonne par date d'achat
	 * du forfait.
	 * 
	 * @param idUtilisateur
	 * @param idCategorie
	 * @return
	 */
	public List<Forfait> listerForfaitUtilisateur(Integer idUtilisateur,
			int idCategorie) {
		List<Forfait> lesForfaits = new ArrayList<Forfait>();
		try {
			PreparedStatement st = con
					.prepareStatement("select idforfait,forfait,validite,idcategorie,prix from forfait join forfait on carteutilisateur.idforfait = forfait.idforfait where carteforfait.idutilisateur = ? and carteforfait.idcategorie = ? join carteforfait on utilisateur.idutilisateur = carteforfait.idutilisateur order by carteforfait.dateachat desc");
			st.setInt(1, idUtilisateur);
			st.setInt(2, idCategorie);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				lesForfaits.add(new Forfait(rs.getInt(1), rs.getInt(2), rs
						.getInt(3), CategorieDAO.getInstance().rechercher(
						rs.getInt(3)), rs.getInt(4)));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	/**
	 * Supprime un lien forfait-utilisateur
	 * 
	 * @param idForfait
	 * @return
	 */
	public boolean supprimer(Integer idForfait) {
		try {
			PreparedStatement st = con
					.prepareStatement("delete from carteforfait join forfait on carteforfait.idforfait = forfait.idforfait where forfait.idforfait = ?");
			st.setInt(1, idForfait);
			st.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
}
