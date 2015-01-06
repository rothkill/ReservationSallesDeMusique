package ihm.editerclient;

import ihm.MenuPanel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import metier.EditerInfosClientMetier;
import metier.UtilisateurMetier;
import metier.ReservationMetier;
import data.Categorie;
import data.Utilisateur;
import exception.AucunUtilisateurException;
import utils.Constantes;

public class EditerInfosClientPanel extends JPanel implements ActionListener {

	private JDialog dialog;

	private JFrame frame;

	private JPanel north = new JPanel();

	private JComboBox jComboBoxUtilisateur = new JComboBox();

	private JButton confirmerReservation = new JButton(
			Constantes.CONFIRMER_RESERVATION);
	private JButton acheterForfait = new JButton(Constantes.ACHETER_FORFAIT);
	private JButton nouveau = new JButton(Constantes.NOUVEL_UTILISATEUR);
	private JButton retourMenu = new JButton(Constantes.RETOUR_MENU);

	private JLabel informationLabel = new JLabel(Constantes.INFO_LABEL);

	public EditerInfosClientPanel(JDialog dialog, JFrame frame) {
		this.dialog = dialog;
		this.frame = frame;

		creerComboUtilisateur();

		north.setLayout(new FlowLayout());
		north.add(jComboBoxUtilisateur);
		north.add(confirmerReservation);
		north.add(acheterForfait);
		north.add(nouveau);
		north.add(retourMenu);

		confirmerReservation.addActionListener(this);
		acheterForfait.addActionListener(this);
		nouveau.addActionListener(this);
		retourMenu.addActionListener(this);

		this.setLayout(new BorderLayout());
		this.add(north, BorderLayout.NORTH);

		this.add(informationLabel, BorderLayout.SOUTH);
		this.setVisible(true);
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
			String erreur = Constantes.INFO_ERREUR;
			erreur = String.format(erreur.replace("?", "%s"),
					exception.getMessage());
			informationLabel.setText(erreur);
		}
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		// TODO JBG
		if (actionEvent.getSource() == retourMenu) {
			frame.getContentPane().removeAll();
			frame.getContentPane().add(new MenuPanel(frame));
			frame.setTitle(Constantes.ANNULATION_RESERVATION);
			frame.pack();
			frame.setVisible(true);
		} else if (actionEvent.getSource() == acheterForfait) {
			rafraichissement();
			this.add(new AchatForfaitPanel(this), BorderLayout.CENTER);
			this.repaint();
			frame.pack();
		} else if (actionEvent.getSource() == nouveau) {
			rafraichissement();
			this.add(new NouvelUtilisateurPanel());
			this.repaint();
			frame.pack();
		} else if (actionEvent.getSource() == confirmerReservation) {
			rafraichissement();
			this.add(new ConfirmerReservationPanel(this,
					(Utilisateur) jComboBoxUtilisateur.getSelectedItem()));
			this.repaint();
			frame.pack();
		}
	}

	private void rafraichissement() {
		this.removeAll();
		this.add(north, BorderLayout.NORTH);
		this.add(informationLabel, BorderLayout.SOUTH);
	}

	public void changerLabelInfo(String s) {
		informationLabel.setText(s);
	}

	public Utilisateur getCurrentUtilisateur() {
		return (Utilisateur) jComboBoxUtilisateur.getSelectedItem();
	}
}
