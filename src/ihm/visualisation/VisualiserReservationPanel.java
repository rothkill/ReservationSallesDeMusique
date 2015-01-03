package ihm.visualisation;

import ihm.visualisation.formulaire.ReservationManuellePanel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import metier.ReservationMetier;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import utils.Constantes;
import utils.DateLabelFormatter;
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

	private UtilDateModel model;
	private Properties p;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl datePicker;

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

		north.setLayout(new BorderLayout());

		// DatePicker
		model = new UtilDateModel();
		p = new Properties();
		p.put("text.today", Constantes.JOUR_PICKER);
		p.put("text.month", Constantes.MOIS_PICKER);
		p.put("text.year", Constantes.ANNEE_PICKER);
		datePanel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

		creerComboCategorie();
		northNorth.setLayout(new FlowLayout());
		northNorth.add(jComboBoxCategorie);
		northNorth.add(datePicker);
		northNorth.add(valider);
		northNorth.add(retourMenu);

		northSouth.setLayout(new FlowLayout());
		northSouth.add(reserverManuellement);
		northSouth.add(reserverAutomatiquement);

		north.add(northNorth, BorderLayout.NORTH);
		north.add(northSouth, BorderLayout.SOUTH);

		this.setLayout(new BorderLayout());
		this.add(north, BorderLayout.NORTH);
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

			System.out.println(datePicker.getModel().getValue());
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

			// TODO faire une classe pour la frame ?
			center.recharger(listSalles);
			JFrame frame = new JFrame("Planning");
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setVisible(true);
			frame.getContentPane().add(center);
			frame.setLocationRelativeTo(null);
			frame.pack();

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
