package exception;

import utils.Constantes;

public class AucuneReservationNonConfirmeeException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4588956801882374731L;

	public AucuneReservationNonConfirmeeException() {
		super(Constantes.AUCUNE_RESERVATION_NON_CONFIRMEE_EXCEPTION);
	}
}
