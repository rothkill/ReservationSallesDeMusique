package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
					.prepareStatement("select idforfait,forfait,validite,categorie,prix from forfait");
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				forfait = new Forfait(rs.getInt(1), rs.getInt(2),
						rs.getInt(3), rs.getInt(4), rs.getInt(5));
				listForfait.add(forfait);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return listForfait;
	}

	public boolean lier(Integer idUtilisateur, Integer idForfait) {
		// TODO
		return false;
	}
}
