package ihm.annulation;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6499200575552265318L;

	private JPanel center = new JPanel();

	private JScrollPane jScrollPane = new JScrollPane();

	private JButton annulerToutesReservation = new JButton(
			Constantes.ANNULER_LES_RESERVATIONS);

	private JLabel informationLabel = new JLabel(Constantes.INFO_LABEL);

	public AnnulerReservationPanel() {
		// TODO JBG

		annulerToutesReservation.addActionListener(this);

		creationPanelCenter();

		this.setLayout(new BorderLayout());
		this.add(annulerToutesReservation, BorderLayout.NORTH);
		this.add(informationLabel, BorderLayout.SOUTH);
		jScrollPane.getViewport().add(center);
		this.add(jScrollPane, BorderLayout.CENTER);
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
		if (actionEvent.getSource() == annulerToutesReservation) {
			if (AnnulerReservationMetier.getInstance()
					.annulerToutesReservationsNonConfirmeesDebutees()) {
				this.remove(jScrollPane);
				this.repaint();
			}
		}

		// TODO JBG messages erreur/reussite
	}

}
