package ihm.visualisation.formulaire;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import metier.ReservationMetier;
import metier.UtilisateurMetier;
import data.Categorie;
import data.Salle;
import data.Utilisateur;
import exception.AucunUtilisateurException;
import exception.AucuneSalleSelectionneeException;
import utils.Constantes;

public class ReservationManuellePanel extends JPanel implements ActionListener {

	private JComboBox jComboBoxSalles;
	private JComboBox jComboBoxUtilisateur;

	private JTextField jour = new JTextField(Constantes.JJ_LABEL);
	private JTextField mois = new JTextField(Constantes.MM_LABEL);
	private JTextField annee = new JTextField(Constantes.AAAA_LABEL);
	private JTextField duree = new JTextField(Constantes.DUREE_LABEL);
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

		plusieursReservations.addActionListener(this);
		valider.addActionListener(this);

		this.setLayout(new FlowLayout());
		this.add(jComboBoxUtilisateur);
		this.add(jComboBoxSalles);
		this.add(jour);
		this.add(slash);
		this.add(mois);
		this.add(slash2);
		this.add(annee);
		this.add(duree);
		this.add(reserverSurDuree);
		this.add(plusieursReservations);
		this.add(nombreSemaines);
		this.add(valider);
		// TODO

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
			// TODO gerer exception
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
			// TODO gerer exception
		}
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getSource() == valider) {
			// TODO gerer les dates
			// ReservationMetier.getInstance().reserverSalleManuellement(utilisateur,
			// jComboBoxSalles.getSelectedItem(), dateReservation,
			// dateDebutReservation, dateFinReservation)
		} else if (actionEvent.getSource() == plusieursReservations) {
			if (plusieursReservations.isSelected()) {
				nombreSemaines.setEnabled(true);
			} else {
				nombreSemaines.setEnabled(false);
			}
		}
	}
}
