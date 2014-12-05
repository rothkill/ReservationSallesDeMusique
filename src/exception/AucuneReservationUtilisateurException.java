package exception;

import utils.Constantes;

public class AucuneReservationUtilisateurException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1595994499028292055L;

	public AucuneReservationUtilisateurException() {
		super(Constantes.AUCUNE_RESERVATION_UTILISATEUR_EXCEPTION);
	}
}
