package ihm.annulation;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import metier.AnnulerReservationMetier;
import utils.Constantes;
import data.Reservation;
import data.Utilisateur;
import exception.ReservationNonSelectionneeException;

/**
 * Panel qui correspond a une ligne du tableau d'annulation des reservations non
 * confirmees.
 * 
 * @author grimonprez
 * 
 */
public class UtilisateurReservationNonConfirmeePanel extends JPanel implements
		ActionListener {

	private JLabel nomUtilisateur = new JLabel();
	private JComboBox jComboBoxReservations = new JComboBox();
	private JButton annulerReservation = new JButton(
			Constantes.ANNULER_RESERVATION);

	public UtilisateurReservationNonConfirmeePanel(Utilisateur utilisateur) {
		// TODO
		nomUtilisateur.setText(utilisateur.toString());
		creerComboBoxReservation(utilisateur);
		annulerReservation.addActionListener(this);

		this.setLayout(new GridLayout(1, 3));
		this.add(nomUtilisateur);
		this.add(jComboBoxReservations);
		this.add(annulerReservation);

		this.setVisible(true);

	}

	private void creerComboBoxReservation(Utilisateur utilisateur) {
		for (Reservation reservation : utilisateur
				.getListReservationsUtilisateur()) {
			jComboBoxReservations.addItem(reservation);
		}
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource() == annulerReservation) {
			try {
				AnnulerReservationMetier.getInstance()
						.annulerReservationNonConfirmee(
								(Reservation) jComboBoxReservations
										.getSelectedItem());
			} catch (ReservationNonSelectionneeException exception) {
				// TODO gerer le message d'exception
			}
		}
	}

}
