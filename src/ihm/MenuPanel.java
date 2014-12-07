package ihm;

import ihm.annulation.AnnulerReservationPanel;
import ihm.editerclient.EditerInfosClientPanel;
import ihm.visualisation.VisualiserReservationPanel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
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

	private JFrame frame;
	private JDialog dialog;

	private JButton visualiserReservationsEtReserver = new JButton(
			Constantes.VISUALISER_RESERVATION_ET_RESERVER);
	private JButton editerClient = new JButton(Constantes.EDITER_INFO_CLIENT);
	private JButton annulationReservation = new JButton(
			Constantes.ANNULATION_RESERVATION);

	public MenuPanel(final JFrame frame) {
		super();
		this.frame = frame;
		this.dialog = new JDialog(this.frame);

		this.setLayout(new GridLayout(3, 1));

		visualiserReservationsEtReserver.addActionListener(this);
		editerClient.addActionListener(this);
		annulationReservation.addActionListener(this);

		this.add(visualiserReservationsEtReserver);
		this.add(editerClient);
		this.add(annulationReservation);

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource() == visualiserReservationsEtReserver) {
			dialog.getContentPane().removeAll();
			dialog.getContentPane().add(new VisualiserReservationPanel(dialog));
			dialog.setLocationRelativeTo(null);
			dialog.setTitle(Constantes.VISUALISER_RESERVATION);
			dialog.pack();
			dialog.setVisible(true);
		} else if (actionEvent.getSource() == editerClient) {
			dialog.getContentPane().removeAll();
			dialog.getContentPane().add(new EditerInfosClientPanel(dialog));
			dialog.setLocationRelativeTo(null);
			dialog.setTitle(Constantes.EDITER_INFO_CLIENT);
			dialog.pack();
			dialog.setVisible(true);
		} else if (actionEvent.getSource() == annulationReservation) {
			dialog.getContentPane().removeAll();
			dialog.getContentPane().add(new AnnulerReservationPanel(dialog));
			dialog.setLocationRelativeTo(null);
			dialog.setTitle(Constantes.ANNULATION_RESERVATION);
			dialog.pack();
			dialog.setVisible(true);
		}

	}

}
