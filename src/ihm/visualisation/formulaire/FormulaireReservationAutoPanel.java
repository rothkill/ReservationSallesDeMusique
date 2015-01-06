package ihm.visualisation.formulaire;

import ihm.alert.AlertPopup;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import metier.ReservationMetier;
import metier.UtilisateurMetier;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import data.Categorie;
import data.Utilisateur;
import exception.AucunUtilisateurException;
import exception.CategorieNonSelectionneeException;
import exception.DateIncorrecteException;
import exception.LundiException;
import exception.SalleReserveeException;
import exception.UtilisateurNonSelectionneException;
import utils.Constantes;
import utils.DateLabelFormatter;

public class FormulaireReservationAutoPanel extends JPanel implements
		ActionListener {

	private UtilDateModel model;
	private Properties p;
	private JDatePanelImpl datePanel;
	private JDatePickerImpl datePicker;

	private JComboBox jComboBoxUtilisateur = new JComboBox();
	private JComboBox trancheHoraire = new JComboBox();

	private JComboBox duree = new JComboBox();

	private JLabel dureeLabel = new JLabel(Constantes.DUREE_LABEL);
	private JLabel heureLabel = new JLabel(Constantes.TRANCHE_HORAIRE_LABEL);

	private JButton valider = new JButton(Constantes.VALIDER);

	private Categorie categorie;

	public FormulaireReservationAutoPanel(Categorie categorie) {
		this.categorie = categorie;

		creerComboUtilisateur();

		trancheHoraire.addItem("9");
		trancheHoraire.addItem("13");
		trancheHoraire.addItem("20");

		duree.addItem("1");
		duree.addItem("2");
		duree.addItem("3");

		// DatePicker
		model = new UtilDateModel();
		p = new Properties();
		p.put("text.today", Constantes.JOUR_PICKER);
		p.put("text.month", Constantes.MOIS_PICKER);
		p.put("text.year", Constantes.ANNEE_PICKER);
		datePanel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

		this.add(jComboBoxUtilisateur);
		this.add(datePicker);

		this.add(dureeLabel);
		this.add(duree);
		this.add(heureLabel);
		this.add(trancheHoraire);

		this.add(valider);
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource() == valider) {
			try {
				ReservationMetier.getInstance().reservationAutomatique(
						(Utilisateur) jComboBoxUtilisateur.getSelectedItem(),
						categorie, (Date) datePicker.getModel().getValue(),
						(String) trancheHoraire.getSelectedItem(),
						(String) duree.getSelectedItem());
			} catch (CategorieNonSelectionneeException e) {
				new AlertPopup(e.getMessage(), JOptionPane.ERROR_MESSAGE);
			} catch (UtilisateurNonSelectionneException e) {
				new AlertPopup(e.getMessage(), JOptionPane.ERROR_MESSAGE);
			} catch (DateIncorrecteException e) {
				new AlertPopup(e.getMessage(), JOptionPane.ERROR_MESSAGE);
			} catch (SalleReserveeException e) {
				new AlertPopup(e.getMessage(), JOptionPane.ERROR_MESSAGE);
			} catch (LundiException e) {
				new AlertPopup(e.getMessage(), JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void creerComboUtilisateur() {
		jComboBoxUtilisateur = new JComboBox();
		List<Utilisateur> listUtilisateurs;
		try {
			listUtilisateurs = UtilisateurMetier.getInstance()
					.listerUtilisateurs();
			for (Utilisateur utilisateur : listUtilisateurs) {
				jComboBoxUtilisateur.addItem(utilisateur);
			}
		} catch (AucunUtilisateurException exception) {
			new AlertPopup(exception.getLocalizedMessage(),
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
