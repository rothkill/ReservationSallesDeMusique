package ihm;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;

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

	public VisualiserReservationPanel() {

		creerComboCategorie();

		center.add(jComboBoxCategorie);

		this.setLayout(new BorderLayout());
		this.add(north, BorderLayout.NORTH);
		this.add(center, BorderLayout.CENTER);
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
	public void actionPerformed(ActionEvent e) {
		// TODO JBG

	}

}
