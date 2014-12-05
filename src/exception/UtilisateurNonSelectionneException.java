package exception;

import utils.Constantes;

public class UtilisateurNonSelectionneException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3553483201843309839L;

	public UtilisateurNonSelectionneException() {
		super(Constantes.UTILISATEUR_NON_SELECTIONNEE_EXCEPTION);
	}

}
