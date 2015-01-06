package ihm.editerclient;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import utils.Constantes;
import metier.EditerInfosClientMetier;
import metier.UtilisateurMetier;
import data.Forfait;
import data.Utilisateur;
import exception.AucunForfaitExistantException;
import exception.ForfaitNonSelectionneException;
import exception.UtilisateurNonSelectionneException;

public class AchatForfaitPanel extends JPanel implements ActionListener {

	EditerInfosClientPanel editerInfosClientPanel;

	private JComboBox jComboBoxForfait;

	private JLabel typeForfait = new JLabel(Constantes.TYPE_FORFAIT);

	private JButton valider = new JButton(Constantes.VALIDER);

	public AchatForfaitPanel(EditerInfosClientPanel editerInfosClientPanel) {
		// TODO Auto-generated constructor stub

		this.editerInfosClientPanel = editerInfosClientPanel;

		creerComboForfait();

		this.setLayout(new GridLayout(1, 3));
		this.add(typeForfait);
		this.add(jComboBoxForfait);
		this.add(valider);

		valider.addActionListener(this);

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource() == valider) {
			System.out.println("gné");
			try {
				if (UtilisateurMetier.getInstance().attacherForfaitUtilisateur(
						editerInfosClientPanel.getCurrentUtilisateur(),
						(Forfait) jComboBoxForfait.getSelectedItem())) {

				}
			} catch (UtilisateurNonSelectionneException e) {
				// TODO gerer exception
				e.printStackTrace();
			} catch (ForfaitNonSelectionneException e) {
				// TODO gerer exception
				e.printStackTrace();
			}
		}
	}

	private void creerComboForfait() {
		jComboBoxForfait = new JComboBox();
		List<Forfait> listForfaits;
		try {
			listForfaits = EditerInfosClientMetier.getInstance()
					.listerForfait();
			for (Forfait forfait : listForfaits) {
				jComboBoxForfait.addItem(forfait);
			}
		} catch (AucunForfaitExistantException exception) {
			// TODO gererErreur
		}
	}

}
