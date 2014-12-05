package metier;

import java.util.List;

import dao.UtilisateurDAO;
import data.Utilisateur;
import exception.AucunUtilisateurException;

public class UtilisateurMetier {

	private static final UtilisateurMetier UTILISATEUR_METIER = new UtilisateurMetier();

	private UtilisateurMetier() {
		super();
	}

	public static UtilisateurMetier getInstance() {
		return UTILISATEUR_METIER;
	}

	/**
	 * Retourne la liste de tous les utilisateurs.
	 * 
	 * @return
	 * @throws AucunUtilisateurException
	 */
	public List<Utilisateur> listerUtilisateurs()
			throws AucunUtilisateurException {
		List<Utilisateur> listUtilisateurs = UtilisateurDAO.getInstance()
				.listerUtilisateurs();
		if (listUtilisateurs == null || listUtilisateurs.size() <= 0) {
			throw new AucunUtilisateurException();
		}
		return listUtilisateurs;
	}
}
