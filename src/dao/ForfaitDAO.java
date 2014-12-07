package dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

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
		List<Forfait> listeForfaits = new ArrayList<Forfait>();
		
		return listeForfaits;
	}

	public boolean lier(Integer idUtilisateur, Integer idForfait) {
		// TODO
		return false;
	}
}
