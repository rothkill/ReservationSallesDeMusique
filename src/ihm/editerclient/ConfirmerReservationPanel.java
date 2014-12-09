package ihm.editerclient;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import utils.Constantes;
import data.Reservation;
import data.Utilisateur;

public class ConfirmerReservationPanel extends JPanel {

	private JScrollPane jScroll;
	private EditerInfosClientPanel editerInfosClientPanel;

	public ConfirmerReservationPanel(
			EditerInfosClientPanel editerInfosClientPanel,
			Utilisateur utilisateur) {
		this.editerInfosClientPanel = editerInfosClientPanel;
		if (utilisateur.getListReservationsUtilisateur() == null
				|| utilisateur.getListReservationsUtilisateur().size() <= 0) {
			editerInfosClientPanel
					.changerLabelInfo(Constantes.AUCUNE_RESERVATION_INFO);
		}

		this.setLayout(new GridLayout(1, 1));

		for (Reservation reservation : utilisateur
				.getListReservationsUtilisateur()) {
			// TODO gerer la duree forfait
			this.add(new LigneReservationPanel(utilisateur, reservation, 0));
		}
		this.setVisible(true);
	}
}
