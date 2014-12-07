package ihm.editerclient;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
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

	private JLabel resevationLabel;
	private JLabel tarifLabel;

	private JButton confirmer = new JButton(
			Constantes.CONFIRMER_RESERVATION_BUTTON);
	private JButton forfait = new JButton(
			Constantes.CONFIRMER_RESERVATION_FORFAIT_BUTTON);
	private JButton fidelite = new JButton(
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

		verifieButtons(dureeForfait);

		this.setVisible(true);

	}

	/**
	 * Verifie si la reservation peut etre payee avec un forfait ou des points
	 * fidelite.
	 * 
	 * @param dureeForfait
	 */
	private void verifieButtons(int dureeForfait) {
		if (dureeForfait <= this.reservation.getDuree()) {
			forfait.setEnabled(false);
		}
		if (utilisateur.getPointFidelite() >= Constantes.CORRESPONDANCE_HEURE_GRATUITE_POINTS_FIDELITE) {
			fidelite.setEnabled(false);
		}
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		try {
			if (actionEvent.getSource() == confirmer) {
				try {
					EditerInfosClientMetier.getInstance().reservation(
							utilisateur, reservation, false);
				} catch (UtilisateurNonSelectionneException e) {
					// TODO gerer erreur
					e.printStackTrace();
				}
			} else if (actionEvent.getSource() == fidelite) {
				try {
					EditerInfosClientMetier.getInstance().reservation(
							utilisateur, reservation, true);
				} catch (UtilisateurNonSelectionneException e) {
					// TODO gerer erreur
					e.printStackTrace();
				}
			} else if (actionEvent.getSource() == fidelite) {
				// TODO
				EditerInfosClientMetier.getInstance().utiliserForfait();
			}
		} catch (ReservationNonSelectionneeException e) {
			// TODO gerer erreur
			e.printStackTrace();
		}
	}
}
