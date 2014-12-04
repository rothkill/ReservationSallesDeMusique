package ihm.annulation;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import metier.AnnulerReservationMetier;

import data.Utilisateur;
import exception.AucuneReservationNonConfirmeeException;

import utils.Constantes;

/**
 * APnel du cas d'utilisation 6.
 * 
 * @author grimonprez
 * 
 */
public class AnnulerReservationPanel extends JPanel implements ActionListener {

	private JPanel center = new JPanel();

	private JButton annulerReservation = new JButton(
			Constantes.ANNULER_RESERVATION);
	private JButton annulerToutesReservation = new JButton(
			Constantes.ANNULER_LES_RESERVATIONS);

	private JLabel annulerToutesReservationLabel = new JLabel(
			Constantes.ANNULER_TOUTES_RESERVATION_LABEL);
	private JLabel informationLabel = new JLabel(Constantes.INFO_LABEL);

	public AnnulerReservationPanel() {
		// TODO JBG

		annulerReservation.addActionListener(this);
		annulerToutesReservation.addActionListener(this);

		creationPanelCenter();

		this.setLayout(new BorderLayout());
		this.add(informationLabel, BorderLayout.SOUTH);
		this.add(center, BorderLayout.CENTER);
		this.setVisible(true);
	}

	private void creationPanelCenter() {
		try {
			List<Utilisateur> listUtilisateurs = AnnulerReservationMetier
					.getInstance().listerUtilisateursEtResNonConfirmees();

			center.setLayout(new GridLayout(1, 1));
			for (Utilisateur utilisateur : listUtilisateurs) {
				center.add(new UtilisateurReservationNonConfirmeePanel(
						utilisateur));
			}

		} catch (AucuneReservationNonConfirmeeException exception) {
			String erreur = Constantes.INFO_ERREUR;
			erreur = String.format(erreur.replace("?", "%s"),
					exception.getMessage());
			informationLabel.setText(erreur);
		}
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource() == annulerReservation) {
			// TODO JBG
		} else if (actionEvent.getSource() == annulerToutesReservation) {
			// TODO JBG
		}

		// TODO JBG
	}

}
