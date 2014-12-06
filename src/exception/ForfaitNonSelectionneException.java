package exception;

import utils.Constantes;

public class ForfaitNonSelectionneException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7049465127066658199L;

	public ForfaitNonSelectionneException() {
		super(Constantes.FORFAIT_NON_SELECTIONNEE_EXCEPTION);
	}
}
