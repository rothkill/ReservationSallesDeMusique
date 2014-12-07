package exception;

import utils.Constantes;

public class AucuneSalleSelectionneeException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5248061700065887286L;

	public AucuneSalleSelectionneeException() {
		super(Constantes.SALLE_NON_SELECTIONNEE_EXCEPTION);
	}
}
