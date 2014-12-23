package metier;

import java.util.List;

import dao.ForfaitDAO;
import dao.UtilisateurDAO;
import data.Forfait;
import data.Utilisateur;
import exception.AucunUtilisateurException;
import exception.ForfaitNonSelectionneException;
import exception.UtilisateurNonSelectionneException;

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

	/**
	 * Creation d'un utilisateur.
	 * 
	 * @param nom
	 * @param telephone
	 * @param forfait
	 * @return
	 * @throws ForfaitNonSelectionneException
	 * @throws UtilisateurNonSelectionneException
	 */
	public boolean creerUtilisateur(String nom, String telephone,
			Forfait forfait) throws UtilisateurNonSelectionneException,
			ForfaitNonSelectionneException {
		Utilisateur utilisateur = UtilisateurDAO.getInstance().creer(nom,
				telephone, 0);

		if (utilisateur == null) {
			return false;
		}

		if (forfait != null) {
			attacherForfaitUtilisateur(utilisateur, forfait);
		}
		return true;
	}

	/**
	 * Lie un forfait a un utilisateur.
	 * 
	 * @param utilisateur
	 * @param forfait
	 * @return
	 * @throws UtilisateurNonSelectionneException
	 * @throws ForfaitNonSelectionneException
	 */
	public boolean attacherForfaitUtilisateur(Utilisateur utilisateur,
			Forfait forfait) throws UtilisateurNonSelectionneException,
			ForfaitNonSelectionneException {
		if (utilisateur == null) {
			throw new UtilisateurNonSelectionneException();
		}
		if (forfait == null) {
			throw new ForfaitNonSelectionneException();
		}
		return ForfaitDAO.getInstance().lier(utilisateur.getIdUtilisateur(),
				forfait.getIdForfait());
	}
}
