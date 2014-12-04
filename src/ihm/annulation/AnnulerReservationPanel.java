package ihm.annulation;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utils.Constantes;

/**
 * APnel du cas d'utilisation 6.
 * 
 * @author grimonprez
 * 
 */
public class AnnulerReservationPanel extends JPanel implements ActionListener {

	private JPanel center = new JPanel();

	private JButton annulerReservation = new JButton(
			Constantes.ANNULER_RESERVATION);
	private JButton annulerToutesReservation = new JButton(
			Constantes.ANNULER_LES_RESERVATIONS);

	private JLabel annulerToutesReservationLabel = new JLabel(
			Constantes.ANNULER_TOUTES_RESERVATION_LABEL);
	private JLabel informationLabel = new JLabel(Constantes.INFO_LABEL);

	public AnnulerReservationPanel() {
		// TODO JBG

		annulerReservation.addActionListener(this);
		annulerToutesReservation.addActionListener(this);

		
		
		this.setLayout(new BorderLayout());
		this.add(informationLabel, BorderLayout.SOUTH);
		this.add(center, BorderLayout.CENTER);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource() == annulerReservation) {
			// TODO JBG
		} else if (actionEvent.getSource() == annulerToutesReservation) {
			// TODO JBG
		}

		// TODO JBG
	}

}
