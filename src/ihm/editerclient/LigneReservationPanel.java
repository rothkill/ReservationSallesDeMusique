package ihm.editerclient;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import metier.EditerInfosClientMetier;
import utils.Constantes;
import data.Reservation;
import data.Utilisateur;
import exception.ReservationNonSelectionneeException;
import exception.UtilisateurNonSelectionneException;

public class LigneReservationPanel extends JPanel implements ActionListener {

	private Utilisateur utilisateur;
	private Reservation reservation;

	private JLabel resevationLabel = new JLabel();
	private JLabel tarifLabel = new JLabel();

	private JButton confirmer = new JButton(
			Constantes.CONFIRMER_RESERVATION_BUTTON);
	private JCheckBox forfait = new JCheckBox(
			Constantes.CONFIRMER_RESERVATION_FORFAIT_BUTTON);
	private JCheckBox fidelite = new JCheckBox(
			Constantes.CONFIRMER_RESERVATION_FIDELITE_BUTTON);

	public LigneReservationPanel(Utilisateur utilisateur,
			Reservation reservation, int dureeForfait) {
		this.reservation = reservation;
		this.utilisateur = utilisateur;

		resevationLabel.setText(this.reservation.getTarif()
				+ Constantes.MONNAIE_UTILISEE);
		tarifLabel.setText(this.reservation.toString());

		this.setLayout(new FlowLayout());
		this.add(resevationLabel);
		this.add(tarifLabel);
		this.add(confirmer);
		this.add(fidelite);
		this.add(forfait);

		confirmer.addActionListener(this);
		fidelite.addActionListener(this);
		forfait.addActionListener(this);

		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		try {
			if (actionEvent.getSource() == confirmer) {
				try {
					EditerInfosClientMetier.getInstance().reservation(
							utilisateur, reservation, fidelite.isSelected(),
							forfait.isSelected());
				} catch (UtilisateurNonSelectionneException e) {
					// TODO gerer erreur
					e.printStackTrace();
				}
			}
			// else if (actionEvent.getSource() == fidelite) {
			// try {
			// EditerInfosClientMetier.getInstance().reservation(
			// utilisateur, reservation, true);
			// } catch (UtilisateurNonSelectionneException e) {
			// // TODO gerer erreur
			// e.printStackTrace();
			// }
			// } else if (actionEvent.getSource() == fidelite) {
			// // TODO
			// EditerInfosClientMetier.getInstance().utiliserForfait();
			// }
		} catch (ReservationNonSelectionneeException e) {
			// TODO gerer erreur
			e.printStackTrace();
		}
	}
}
