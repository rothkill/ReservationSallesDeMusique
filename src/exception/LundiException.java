package exception;

import utils.Constantes;

public class LundiException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7641304359495854094L;

	public LundiException() {
		super(Constantes.LUNDI_EXCEPTION);
	}
}