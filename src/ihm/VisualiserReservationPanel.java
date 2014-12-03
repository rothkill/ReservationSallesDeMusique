package ihm;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import utils.Constantes;
import metier.VisualiserReservationMetier;
import data.Categorie;

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

	private JComboBox<Categorie> jComboBoxCategorie;

	private JTextField jour = new JTextField("JJ");
	private JTextField mois = new JTextField("MM");
	private JTextField annee = new JTextField("AAAA");

	private JLabel informationLabel = new JLabel(Constantes.INFO_LABEL);
	private JLabel slash = new JLabel(Constantes.SLASH_LABEL);

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
		north.add(slash);
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
		jComboBoxCategorie = new JComboBox<Categorie>();
		List<Categorie> listCategories = VisualiserReservationMetier
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
		} else if (actionEvent.getSource() == jComboBoxCategorie) {
			// TODO JBG
			System.out.println(jComboBoxCategorie.getSelectedItem());
		}
	}

}
