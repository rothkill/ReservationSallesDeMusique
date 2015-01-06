package ihm.editerclient;

import ihm.alert.AlertPopup;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import metier.ReservationMetier;
import utils.Constantes;
import data.Reservation;
import data.Utilisateur;
import exception.UtilisateurNonSelectionneException;

public class ConfirmerReservationPanel extends JPanel {

	private JScrollPane jScroll;
	private EditerInfosClientPanel editerInfosClientPanel;

	public ConfirmerReservationPanel(
			EditerInfosClientPanel editerInfosClientPanel,
			Utilisateur utilisateur) {
		this.editerInfosClientPanel = editerInfosClientPanel;
		List<Reservation> listeReservationUtilisateur;
		try {
			listeReservationUtilisateur = ReservationMetier.getInstance()
					.getlisteReservationUtilisateur(utilisateur);
			if (listeReservationUtilisateur == null
					|| listeReservationUtilisateur.size() <= 0) {
				editerInfosClientPanel
						.changerLabelInfo(Constantes.AUCUNE_RESERVATION_INFO);
			}
			this.setLayout(new GridLayout(0, 1));

			for (Reservation reservation : listeReservationUtilisateur) {
				if (reservation != null) {
					this.add(new LigneReservationPanel(utilisateur,
							reservation, 0));
				}
			}
		} catch (UtilisateurNonSelectionneException e) {
			new AlertPopup(e.getMessage(), JOptionPane.ERROR_MESSAGE);
		}

		this.setVisible(true);
	}
}
