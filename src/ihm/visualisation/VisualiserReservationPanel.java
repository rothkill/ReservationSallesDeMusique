package ihm.visualisation;

import ihm.MenuPanel;
import ihm.alert.AlertPopup;
import ihm.visualisation.formulaire.FormulaireReservationAutoPanel;
import ihm.visualisation.formulaire.ReservationManuellePanel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import metier.ReservationMetier;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import utils.Constantes;
import utils.DateLabelFormatter;
import data.Categorie;
import data.Reservation;
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

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8085934538899530099L;

	private JFrame frame;

	private JPanel northNorth = new JPanel();
	private JPanel northSouth = new JPanel();
	private JPanel north = new JPanel();
	private JDialog dialog;
	private JDialog dialogReservation = new JDialog();

	private JComboBox jComboBoxCategorie;
	private JComboBox jComboBoxSalle = new JComboBox();

	private UtilDateModel model;
	private Properties p;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl datePicker;

	private JLabel slash = new JLabel(Constantes.SLASH_LABEL);
	private JLabel slash2 = new JLabel(Constantes.SLASH_LABEL);

	private JButton valider = new JButton(Constantes.VALIDER);
	private JButton retourMenu = new JButton(Constantes.RETOUR_MENU);
	private JButton reserverManuellement = new JButton(
			Constantes.RESERVER_MANUELLEMENT);
	private JButton reserverAutomatiquement = new JButton(
			Constantes.RESERVER_AUTOMATIQUEMENT);

	public VisualiserReservationPanel(JDialog dialog, JFrame frame) {
		this.dialog = dialog;
		this.frame = frame;

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

		if (actionEvent.getSource() == retourMenu) {
			frame.getContentPane().removeAll();
			frame.getContentPane().add(new MenuPanel(frame));
			frame.setTitle(Constantes.ANNULATION_RESERVATION);
			frame.pack();
			frame.setVisible(true);
		} else if (actionEvent.getSource() == valider) {
			rechargerChronologie();
		} else if (actionEvent.getSource() == reserverManuellement) {
			this.removeAll();
			this.add(north, BorderLayout.NORTH);
			this.add(new ReservationManuellePanel());
			frame.pack();
		} else if (actionEvent.getSource() == reserverAutomatiquement) {

			dialogReservation.getContentPane().add(
					new FormulaireReservationAutoPanel(
							(Categorie) jComboBoxCategorie.getSelectedItem()));
			dialogReservation.pack();
			dialogReservation.setLocationRelativeTo(null);
			dialogReservation.setVisible(true);

			this.removeAll();
			this.add(north, BorderLayout.NORTH);
			this.add(new FormulaireReservationAutoPanel(
					(Categorie) jComboBoxCategorie.getSelectedItem()));
			frame.pack();

		}
	}

	private void rechargerChronologie() {
		Categorie categorie = (Categorie) jComboBoxCategorie.getSelectedItem();
		try {
			List<Salle> listSalles = ReservationMetier.getInstance()
					.getListeSalleByCategory(categorie);
			List<Reservation> lesReservations = new ArrayList<Reservation>();
			JFrame frame = new JFrame("Planning");
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(1, 0));
			for (Salle salle : listSalles) {
				lesReservations = ReservationMetier.getInstance()
						.getListReservation(salle.getIdSalle(),
								(Date) datePicker.getModel().getValue());
				panel.add(new ChronologieSallePanel(salle, lesReservations));
			}
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setVisible(true);
			frame.getContentPane().add(panel);
			frame.setLocationRelativeTo(null);
			frame.pack();

		} catch (DateIncorrecteException dateIncorrecteException) {
			new AlertPopup(dateIncorrecteException.getMessage(),
					JOptionPane.ERROR_MESSAGE);
		} catch (CategorieNonSelectionneeException categorieNonSelectionneeException) {
			new AlertPopup(categorieNonSelectionneeException.getMessage(),
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
