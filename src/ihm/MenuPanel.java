package ihm;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import utils.Constantes;

/**
 * Panel du menu principal.
 * 
 * @author grimonprez
 * 
 */
public class MenuPanel extends JPanel implements ActionListener {

	/**
	 * Generated serialVersionUID
	 */
	private static final long serialVersionUID = -1608075661453345640L;

	private JButton visualiserReservations = new JButton(
			Constantes.VISUALISER_RESERVATION);
	private JButton reservationAuto = new JButton(
			Constantes.EFFECTUER_RESERVATION_AUTOMATIQUE);
	private JButton reservationManuelle = new JButton(
			Constantes.EFFECTUER_RESERVATION_MANUELLE);
	private JButton editerCLient = new JButton(Constantes.EDITER_INFO_CLIENT);
	private JButton reservationPeriode = new JButton(
			Constantes.EFECTUER_RESERVATION_MEME_JOUR_SEMAINE);
	private JButton annulationReservation = new JButton(
			Constantes.ANNULATION_RESERVATION);

	public MenuPanel() {
		this.setLayout(new GridLayout(3, 3));

		this.add(visualiserReservations);
		this.add(reservationAuto);
		this.add(reservationManuelle);
		this.add(editerCLient);
		this.add(reservationPeriode);
		this.add(annulationReservation);

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource() == visualiserReservations) {
			// TODO JBG
		} else if (actionEvent.getSource() == reservationAuto) {
			// TODO JBG
		} else if (actionEvent.getSource() == reservationManuelle) {
			// TODO JBG
		} else if (actionEvent.getSource() == editerCLient) {
			// TODO JBG
		} else if (actionEvent.getSource() == reservationPeriode) {
			// TODO JBG
		} else if (actionEvent.getSource() == annulationReservation) {
			// TODO JBG
		}

	}

}
