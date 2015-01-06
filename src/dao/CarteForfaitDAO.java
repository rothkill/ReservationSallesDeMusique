package dao;

import java.sql.Connection;
import java.util.List;

import data.CarteForfait;
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

	public void supprimer(int idCarteForfait) {

	}

	public List<CarteForfait> listerParUtilisateurCategorie(int utilisateur,
			int categorie) {
		// TODO
		return null;
	}

	public void modifierCarteForfait(int idCarteForfait, int nouvelleDuree) {
		// TODO
	}
}
