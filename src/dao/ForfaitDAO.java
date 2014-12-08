package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import data.Categorie;
import data.Forfait;
import utils.ConnectionDB;

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
				forfait = new Forfait(rs.getInt(1), rs.getInt(2),
						rs.getInt(3), CategorieDAO.getInstance().rechercher(rs.getInt(4)), rs.getInt(5));
				listForfait.add(forfait);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return listForfait;
	}

	public boolean lier(Integer idUtilisateur, Integer idForfait) {
		Forfait forfait = ForfaitDAO.getInstance().rechercher(idForfait);
		Date dateAchat = new Date();
		Date dateFinValidite = ajouterMois(dateAchat,forfait.getValidite());
		try {
			PreparedStatement st = con
					.prepareStatement("insert into carteforfait values(?,?,?,?,?)");
			st.setInt(1,idUtilisateur);
			st.setInt(2,idForfait);
			st.setDate(3, (java.sql.Date) dateAchat);
			st.setDate(4, (java.sql.Date) dateFinValidite);
			st.setInt(5, forfait.getForfait());
			st.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public static Date ajouterMois(Date date, int nbMois) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);;
		cal.add(Calendar.MONTH, nbMois);
		return cal.getTime();
		}

	private Forfait rechercher(Integer idForfait) {
		try {
			PreparedStatement st = con
					.prepareStatement("select forfait,validite,idcategorie,prix from forfait where idforfait = ?");
			st.setInt(1, idForfait);
			ResultSet rs = st.executeQuery();
			if(rs.next()){
				return new Forfait(idForfait,rs.getInt(1),rs.getInt(2),CategorieDAO.getInstance().rechercher(rs.getInt(3)),rs.getInt(4));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}
