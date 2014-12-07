package ihm.visualisation;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import metier.ReservationMetier;
import utils.Constantes;
import data.Categorie;
import data.Salle;
import exception.CategorieNonSelectionneeException;
import exception.DateIncorrecteException;

/**
 * Panel du cas d'utilisation 1 : visualisation des reservations.
 * 
 * @author grimonprez
 * 
 */
public class VisualiserReservationPanel extends JPanel implements
		ActionListener {

	private JPanel north = new JPanel();
	private ChronologiePanel center = new ChronologiePanel();

	private JComboBox jComboBoxCategorie;

	private JTextField jour = new JTextField("JJ");
	private JTextField mois = new JTextField("MM");
	private JTextField annee = new JTextField("AAAA");

	private JLabel informationLabel = new JLabel(Constantes.INFO_LABEL);
	private JLabel slash = new JLabel(Constantes.SLASH_LABEL);
	private JLabel slash2 = new JLabel(Constantes.SLASH_LABEL);

	private JButton valider = new JButton(Constantes.VALIDER);

	public VisualiserReservationPanel() {

		String message = Constantes.INFO_ERREUR;
		System.out.println(String.format(message.replace("?", "%s"),
				Constantes.DATE_INCORRECTE_EXCEPTION));

		creerComboCategorie();
		north.setLayout(new FlowLayout());
		north.add(jComboBoxCategorie);
		north.add(jour);
		north.add(slash);
		north.add(mois);
		north.add(slash2);
		north.add(annee);
		north.add(valider);

		this.setLayout(new BorderLayout());
		this.add(north, BorderLayout.NORTH);
		this.add(center, BorderLayout.CENTER);
		this.add(informationLabel, BorderLayout.SOUTH);

		valider.addActionListener(this);
		jComboBoxCategorie.addActionListener(this);

		this.setVisible(true);
	}

	private void creerComboCategorie() {
		jComboBoxCategorie = new JComboBox();
		List<Categorie> listCategories = ReservationMetier
				.getInstance().getListeCategorie();
		for (Categorie categorie : listCategories) {
			jComboBoxCategorie.addItem(categorie);
		}
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		// TODO JBG

		if (actionEvent.getSource() == valider) {
			// TODO JBG
			rechargerChronologie();
		} else if (actionEvent.getSource() == jComboBoxCategorie) {
			// TODO JBG
			System.out.println(jComboBoxCategorie.getSelectedItem());

		}
	}

	private void rechargerChronologie() {
		// TODO JBG
		try {
			List<Salle> listSalles = ReservationMetier.getInstance()
					.getListeSalleByCategory(
							(Categorie) jComboBoxCategorie.getSelectedItem(),
							jour.getText(), mois.getText(), annee.getText());
			center.recharger(listSalles);
		} catch (DateIncorrecteException dateIncorrecteException) {
			String erreur = Constantes.INFO_ERREUR;
			erreur = String.format(erreur.replace("?", "%s"),
					dateIncorrecteException.getMessage());
			informationLabel.setText(erreur);
		} catch (CategorieNonSelectionneeException categorieNonSelectionneeException) {
			String erreur = Constantes.INFO_ERREUR;
			erreur = String.format(erreur.replace("?", "%s"),
					categorieNonSelectionneeException.getMessage());
			informationLabel.setText(erreur);
		}

		this.add(center, BorderLayout.CENTER);
	}
}
