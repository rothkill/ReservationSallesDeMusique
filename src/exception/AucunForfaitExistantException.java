package exception;

import utils.Constantes;

public class AucunForfaitExistantException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5161664363742179705L;

	public AucunForfaitExistantException() {
		super(Constantes.AUCUN_FORFAIT_EXISTANT);
	}
}
