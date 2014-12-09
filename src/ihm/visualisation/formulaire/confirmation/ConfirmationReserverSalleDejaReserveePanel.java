package ihm.visualisation.formulaire.confirmation;

import ihm.visualisation.formulaire.ReservationManuellePanel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import metier.ReservationMetier;
import utils.Constantes;
import data.Reservation;
import data.Salle;
import exception.AucuneSalleSelectionneeException;
import exception.DateIncorrecteException;
import exception.UtilisateurNonSelectionneException;

public class ConfirmationReserverSalleDejaReserveePanel extends JPanel
		implements ActionListener {
	private JDialog dialog;

	private Salle salle;

	private ReservationManuellePanel reservationManuellePanel;

	private JPanel south = new JPanel();

	private JLabel annulerResLabel = new JLabel(
			Constantes.CONFIRMER_ANNULATION_RESERVATION_LABEL);
	private JButton valider = new JButton(Constantes.VALIDER);
	private JButton annuler = new JButton(Constantes.ANNULER);

	public ConfirmationReserverSalleDejaReserveePanel(Salle salle,
			ReservationManuellePanel reservationManuellePanel, JDialog dialog) {
		this.dialog = dialog;
		this.salle = salle;
		this.reservationManuellePanel = reservationManuellePanel;
		this.setLayout(new BorderLayout());
		south.setLayout(new BorderLayout());

		south.add(valider, BorderLayout.WEST);
		south.add(annuler, BorderLayout.EAST);
		this.add(south, BorderLayout.SOUTH);
		this.add(annulerResLabel, BorderLayout.NORTH);

		valider.addActionListener(this);
		annuler.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource() == valider) {
			try {
				ReservationMetier.getInstance()
						.supprimerReservationParDateEtSalle(salle,
								reservationManuellePanel.getJour(),
								reservationManuellePanel.getMois(),
								reservationManuellePanel.getAnnee(),
								reservationManuellePanel.getHeure());
			} catch (AucuneSalleSelectionneeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UtilisateurNonSelectionneException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DateIncorrecteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (actionEvent.getSource() == annuler) {
			dialog.dispose();
		}
	}
}
