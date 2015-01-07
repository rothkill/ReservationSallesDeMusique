package ihm.editerclient;

import ihm.alert.AlertPopup;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import metier.EditerInfosClientMetier;
import metier.UtilisateurMetier;
import utils.Constantes;
import data.Forfait;
import exception.AucunForfaitExistantException;
import exception.ForfaitNonSelectionneException;
import exception.UtilisateurNonSelectionneException;

public class NouvelUtilisateurPanel extends JPanel implements ActionListener {

	private JLabel typeForfait = new JLabel(Constantes.TYPE_FORFAIT);
	private JLabel nom = new JLabel(Constantes.NOM_UTILISATEUR_LABEL);
	private JLabel telephone = new JLabel(Constantes.TELEPHONE_LABEL);

	private JTextField nomTextField = new JTextField();
	private JTextField telTextField = new JTextField();

	private JButton valider = new JButton(Constantes.VALIDER);

	private JComboBox jComboBoxForfait;

	public NouvelUtilisateurPanel() {
		creerComboForfait();

		valider.addActionListener(this);

		this.setLayout(new GridLayout(4, 4));
		this.add(nom);
		this.add(nomTextField);
		this.add(telephone);
		this.add(telTextField);
		this.add(typeForfait);
		this.add(jComboBoxForfait);
		this.add(new JLabel());
		this.add(valider);

		this.setVisible(true);
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
		} catch (AucunForfaitExistantException e) {
			new AlertPopup(e.getMessage(), JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource() == valider) {
			try {
				if (UtilisateurMetier.getInstance().creerUtilisateur(
						nomTextField.getText(), telTextField.getText(),
						(Forfait) jComboBoxForfait.getSelectedItem())) {
					new AlertPopup(Constantes.UTILISATEUR_CREE,
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					new AlertPopup(Constantes.UTILISATEUR_NON_CREE,
							JOptionPane.ERROR_MESSAGE);
				}
			} catch (UtilisateurNonSelectionneException e) {
				new AlertPopup(e.getMessage(), JOptionPane.ERROR_MESSAGE);
			} catch (ForfaitNonSelectionneException e) {
				new AlertPopup(e.getMessage(), JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}
