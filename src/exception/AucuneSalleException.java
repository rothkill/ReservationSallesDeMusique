package exception;

import utils.Constantes;

public class AucuneSalleException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3479594753822748056L;

	public AucuneSalleException() {
		super(Constantes.AUCUNE_SALLE_EXISTANTE);
	}
}
