package dao;

public class CategorieDAO {

	private static final CategorieDAO CATEGORIE_DAO = new CategorieDAO();

	private CategorieDAO() {
		super();
	}

	public static CategorieDAO getInstance() {
		return CATEGORIE_DAO;
	}

}
