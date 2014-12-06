package exception;

import utils.Constantes;

public class AucunUtilisateurException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3265251519978114273L;

	public AucunUtilisateurException() {
		super(Constantes.AUCUN_UTILISATEUR_EXCEPTION);
	}
}
