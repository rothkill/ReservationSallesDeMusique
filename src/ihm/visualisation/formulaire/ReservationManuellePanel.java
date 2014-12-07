package ihm.visualisation.formulaire;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utils.Constantes;

public class ReservationManuellePanel extends JPanel implements ActionListener {

	private JTextField jour = new JTextField(Constantes.JJ_LABEL);
	private JTextField mois = new JTextField(Constantes.MM_LABEL);
	private JTextField annee = new JTextField(Constantes.AAAA_LABEL);
	private JTextField duree = new JTextField();

	private JCheckBox plusieursReservations = new JCheckBox();
	private JLabel reserverSurDuree = new JLabel(
			Constantes.MULTIPLE_RESERVATION_LABEL);
	private JLabel slash = new JLabel(Constantes.SLASH_LABEL);
	private JLabel slash2 = new JLabel(Constantes.SLASH_LABEL);

	private JButton valider = new JButton(Constantes.VALIDER);

	public ReservationManuellePanel() {
		this.setLayout(new FlowLayout());
		this.add(jour);
		this.add(slash);
		this.add(mois);
		this.add(slash2);
		this.add(annee);
		this.add(reserverSurDuree);
		this.add(plusieursReservations);
		this.add(duree);
		this.add(valider);
		// TODO

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		// TODO Auto-generated method stub

	}

}
