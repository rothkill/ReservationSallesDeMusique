package ihm.visualisation;

import ihm.visualisation.formulaire.ReservationManuellePanel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
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

	private JPanel northNorth = new JPanel();
	private JPanel northSouth = new JPanel();
	private JPanel north = new JPanel();
	private ChronologiePanel center = new ChronologiePanel();
	private JDialog dialog;
	private JDialog dialogReservation = new JDialog();

	private JComboBox jComboBoxCategorie;
	private JComboBox jComboBoxSalle = new JComboBox();

	private JTextField jour = new JTextField(Constantes.JJ_LABEL);
	private JTextField mois = new JTextField(Constantes.MM_LABEL);
	private JTextField annee = new JTextField(Constantes.AAAA_LABEL);

	private JLabel informationLabel = new JLabel(Constantes.INFO_LABEL);
	private JLabel slash = new JLabel(Constantes.SLASH_LABEL);
	private JLabel slash2 = new JLabel(Constantes.SLASH_LABEL);

	private JButton valider = new JButton(Constantes.VALIDER);
	private JButton retourMenu = new JButton(Constantes.RETOUR_MENU);
	private JButton reserverManuellement = new JButton(
			Constantes.RESERVER_MANUELLEMENT);
	private JButton reserverAutomatiquement = new JButton(
			Constantes.RESERVER_AUTOMATIQUEMENT);

	public VisualiserReservationPanel(JDialog dialog) {
		this.dialog = dialog;

		String message = Constantes.INFO_ERREUR;
		System.out.println(String.format(message.replace("?", "%s"),
				Constantes.DATE_INCORRECTE_EXCEPTION));

		north.setLayout(new BorderLayout());

		creerComboCategorie();
		northNorth.setLayout(new FlowLayout());
		northNorth.add(jComboBoxCategorie);
		northNorth.add(jour);
		northNorth.add(slash);
		northNorth.add(mois);
		northNorth.add(slash2);
		northNorth.add(annee);
		northNorth.add(valider);
		northNorth.add(retourMenu);

		northSouth.setLayout(new FlowLayout());
		northSouth.add(reserverManuellement);
		northSouth.add(reserverAutomatiquement);

		north.add(northNorth, BorderLayout.NORTH);
		north.add(northSouth, BorderLayout.SOUTH);

		this.setLayout(new BorderLayout());
		this.add(north, BorderLayout.NORTH);
		this.add(center, BorderLayout.CENTER);
		this.add(informationLabel, BorderLayout.SOUTH);

		retourMenu.addActionListener(this);
		valider.addActionListener(this);
		reserverManuellement.addActionListener(this);
		reserverAutomatiquement.addActionListener(this);
		jComboBoxCategorie.addActionListener(this);

		this.setVisible(true);
	}

	private void creerComboCategorie() {
		jComboBoxCategorie = new JComboBox();
		List<Categorie> listCategories = ReservationMetier.getInstance()
				.getListeCategorie();
		for (Categorie categorie : listCategories) {
			jComboBoxCategorie.addItem(categorie);
		}
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		// TODO JBG

		if (actionEvent.getSource() == retourMenu) {
			dialog.dispose();
		} else if (actionEvent.getSource() == valider) {
			// TODO JBG
			rechargerChronologie();
		} else if (actionEvent.getSource() == jComboBoxCategorie) {
			// TODO JBG
			System.out.println(jComboBoxCategorie.getSelectedItem());

		} else if (actionEvent.getSource() == reserverManuellement) {
			dialogReservation.getContentPane().add(
					new ReservationManuellePanel());
			dialogReservation.pack();
			dialogReservation.setLocationRelativeTo(null);
			dialogReservation.setVisible(true);

		} else if (actionEvent.getSource() == reserverAutomatiquement) {
			// TODO JBG

		}
	}

	private void rechargerChronologie() {
		// TODO JBG
		try {
			List<Salle> listSalles = ReservationMetier.getInstance()
					.getListeSalleByCategory(
							(Categorie) jComboBoxCategorie.getSelectedItem());
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
