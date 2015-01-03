package ihm.editerclient;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
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
		// TODO Auto-generated constructor stub

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
		} catch (AucunForfaitExistantException exception) {
			// TODO gererErreur
		}
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource() == valider) {
			try {
				if (UtilisateurMetier.getInstance().creerUtilisateur(
						nomTextField.getText(), telTextField.getText(),
						(Forfait) jComboBoxForfait.getSelectedItem())) {
					// TODO gerer
				} else {
					// TODO gerer
				}
			} catch (UtilisateurNonSelectionneException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ForfaitNonSelectionneException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
