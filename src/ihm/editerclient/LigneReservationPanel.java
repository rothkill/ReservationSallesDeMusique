package ihm.editerclient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utils.Constantes;
import data.Reservation;
import data.Utilisateur;

public class LigneReservationPanel extends JPanel implements ActionListener {

	private Utilisateur utilisateur;
	private Reservation reservation;

	private JLabel resevation;
	private JLabel tarif;

	private JButton confirmer = new JButton(
			Constantes.CONFIRMER_RESERVATION_BUTTON);
	private JButton forfait = new JButton(
			Constantes.CONFIRMER_RESERVATION_FORFAIT_BUTTON);
	private JButton fidelite = new JButton(
			Constantes.CONFIRMER_RESERVATION_FIDELITE_BUTTON);

	public LigneReservationPanel(Utilisateur utilisateur,
			Reservation reservation) {
		this.reservation = reservation;
		this.utilisateur = utilisateur;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
