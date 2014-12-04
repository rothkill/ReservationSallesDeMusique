package exception;

import utils.Constantes;

public class CategorieNonSelectionneeException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7615288312153122089L;

	public CategorieNonSelectionneeException() {
		super(Constantes.CATEGORIE_NON_SELECTIONNEE_EXCEPTION);
	}

}
