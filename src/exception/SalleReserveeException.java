package exception;

import utils.Constantes;

public class SalleReserveeException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5873688132295750844L;

	public SalleReserveeException() {
		super(Constantes.SALLE_RESEVEE_EXCEPTION);
	}
}
