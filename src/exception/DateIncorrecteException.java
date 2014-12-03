package exception;

import utils.Constantes;

public class DateIncorrecteException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 533604472266190132L;

	public DateIncorrecteException() {
		super(Constantes.DATE_INCORRECTE_EXCEPTION);
	}
}
