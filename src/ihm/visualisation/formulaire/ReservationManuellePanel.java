package ihm.visualisation.formulaire;

import ihm.alert.AlertPopup;
import ihm.visualisation.formulaire.confirmation.ConfirmationReserverSalleDejaReserveePanel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import metier.ReservationMetier;
import metier.UtilisateurMetier;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import utils.Constantes;
import utils.DateLabelFormatter;
import data.Salle;
import data.Utilisateur;
import exception.AucunUtilisateurException;
import exception.AucuneSalleSelectionneeException;
import exception.DateIncorrecteException;
import exception.LundiException;
import exception.SalleReserveeException;
import exception.UtilisateurNonSelectionneException;

public class ReservationManuellePanel extends JPanel implements ActionListener {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7257789881155476241L;

	private JPanel north = new JPanel();
	private JPanel south = new JPanel();
	private JComboBox jComboBoxSalles;
	private JComboBox jComboBoxUtilisateur;

	private UtilDateModel model;
	private Properties p;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl datePicker;

	private JComboBox horaire = new JComboBox();
	private JComboBox duree = new JComboBox();

	private JTextField nombreSemaines = new JTextField(
			Constantes.SEMAINES_LABEL);

	private JCheckBox plusieursReservations = new JCheckBox();
	private JLabel reserverSurDuree = new JLabel(
			Constantes.MULTIPLE_RESERVATION_LABEL);
	private JLabel slash = new JLabel(Constantes.SLASH_LABEL);
	private JLabel slash2 = new JLabel(Constantes.SLASH_LABEL);

	private JButton valider = new JButton(Constantes.VALIDER);

	public ReservationManuellePanel() {
		creerComboSalle();
		creerComboUtilisateur();
		nombreSemaines.setEnabled(false);

		horaire.addItem(9);
		horaire.addItem(10);
		horaire.addItem(11);
		horaire.addItem(12);
		horaire.addItem(13);
		horaire.addItem(14);
		horaire.addItem(15);
		horaire.addItem(16);
		horaire.addItem(17);
		horaire.addItem(18);
		horaire.addItem(19);
		horaire.addItem(20);
		horaire.addItem(21);
		horaire.addItem(22);
		horaire.addItem(23);
		horaire.addActionListener(this);

		plusieursReservations.addActionListener(this);
		valider.addActionListener(this);

		// DatePicker
		model = new UtilDateModel();
		p = new Properties();
		p.put("text.today", Constantes.JOUR_PICKER);
		p.put("text.month", Constantes.MOIS_PICKER);
		p.put("text.year", Constantes.ANNEE_PICKER);
		datePanel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

		north.setLayout(new FlowLayout());
		south.setLayout(new FlowLayout());

		north.add(jComboBoxUtilisateur);
		north.add(jComboBoxSalles);
		north.add(datePicker);
		south.add(horaire);
		south.add(duree);
		south.add(reserverSurDuree);
		south.add(plusieursReservations);
		south.add(nombreSemaines);
		south.add(valider);

		this.setLayout(new BorderLayout());
		this.add(north, BorderLayout.NORTH);
		this.add(south, BorderLayout.SOUTH);
		this.setVisible(true);
	}

	private void creerComboSalle() {
		jComboBoxSalles = new JComboBox();
		List<Salle> listSalles;
		try {
			listSalles = ReservationMetier.getInstance().getListeSalle();
			for (Salle salle : listSalles) {
				jComboBoxSalles.addItem(salle);
			}
		} catch (AucuneSalleSelectionneeException e) {
			new AlertPopup(e.getMessage(), JOptionPane.ERROR_MESSAGE);
		}
	}

	private void creerComboUtilisateur() {
		jComboBoxUtilisateur = new JComboBox();
		List<Utilisateur> listUtilisateur;
		try {
			listUtilisateur = UtilisateurMetier.getInstance()
					.listerUtilisateurs();
			for (Utilisateur utilisateur : listUtilisateur) {
				jComboBoxUtilisateur.addItem(utilisateur);
			}
		} catch (AucunUtilisateurException e) {
			new AlertPopup(e.getMessage(), JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource() == horaire) {
			duree.removeAllItems();
			for (int i = 1; i <= (24 - (int) horaire.getSelectedItem()); i++) {
				duree.addItem(i);
			}
		} else if (actionEvent.getSource() == valider) {
			Date selectedValue = (Date) datePicker.getModel().getValue();
			try {
				if (plusieursReservations.isSelected()) {
					ReservationMetier
							.getInstance()
							.reserverSurDuree(
									(Utilisateur) jComboBoxUtilisateur
											.getSelectedItem(),
									(Salle) jComboBoxSalles.getSelectedItem(),
									selectedValue,
									(int) horaire.getSelectedItem(),
									(int) duree.getSelectedItem(),
									nombreSemaines.getText());
				} else {
					ReservationMetier
							.getInstance()
							.reserverSalle(
									(Utilisateur) jComboBoxUtilisateur
											.getSelectedItem(),
									(Salle) jComboBoxSalles.getSelectedItem(),
									selectedValue,
									(int) horaire.getSelectedItem(),
									(int) duree.getSelectedItem());
				}
			} catch (SalleReserveeException exception) {
				JDialog dialog = new JDialog();
				dialog.getContentPane().add(
						new ConfirmationReserverSalleDejaReserveePanel(
								(Salle) jComboBoxSalles.getSelectedItem(),
								this, dialog));
				dialog.setLocationRelativeTo(null);
				dialog.setVisible(true);
			} catch (DateIncorrecteException e) {
				new AlertPopup(e.getMessage(), JOptionPane.ERROR_MESSAGE);
			} catch (AucuneSalleSelectionneeException e) {
				new AlertPopup(e.getMessage(), JOptionPane.ERROR_MESSAGE);
			} catch (UtilisateurNonSelectionneException e) {
				new AlertPopup(e.getMessage(), JOptionPane.ERROR_MESSAGE);
			} catch (LundiException e) {
				new AlertPopup(e.getMessage(), JOptionPane.ERROR_MESSAGE);
			}
		} else if (actionEvent.getSource() == plusieursReservations) {
			if (plusieursReservations.isSelected()) {
				nombreSemaines.setEnabled(true);
			} else {
				nombreSemaines.setEnabled(false);
			}
		}
	}

	public Date getDate() {
		return (Date) datePicker.getModel().getValue();
	}

	public int getHeure() {
		return (int) horaire.getSelectedItem();
	}
}
