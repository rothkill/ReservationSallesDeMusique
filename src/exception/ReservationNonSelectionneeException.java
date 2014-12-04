package exception;

import utils.Constantes;

public class ReservationNonSelectionneeException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5631419049790786126L;

	public ReservationNonSelectionneeException() {
		super(Constantes.RESERVATION_NON_SELECTIONNEE_EXCEPTION);
	}

}
