package ihm.alert;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import utils.Constantes;

/**
 * Popup d'alerte.
 * 
 * @author JeanBaptiste
 *
 */
public class AlertPopup extends JDialog {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8308044810793930143L;

	/**
	 * @param message
	 * @param jOptionPaneTypeMsg
	 *            JOptionPane.ERROR_MESSAGE ou JOptionPane.INFORMATION_MESSAGE
	 */
	public AlertPopup(String message, int jOptionPaneTypeMsg) {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		JOptionPane.showMessageDialog(this, message, Constantes.TITRE_POPUP,
				jOptionPaneTypeMsg);
	}
}
