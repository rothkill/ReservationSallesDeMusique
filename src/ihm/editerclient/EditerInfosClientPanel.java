package ihm.editerclient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import utils.Constantes;

public class EditerInfosClientPanel extends JPanel implements ActionListener {

	private JComboBox jComboBoxUtilisateur = new JComboBox();

	private JButton confirmerReservation = new JButton(
			Constantes.CONFIRMER_RESERVATION);
	private JButton acheterForfait = new JButton(Constantes.ACHETER_FORFAIT);
	private JButton nouveau = new JButton(Constantes.NOUVEL_UTILISATEUR);
	private JButton retourMenu = new JButton(Constantes.RETOUR_MENU);

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		// TODO JBG

	}

}
