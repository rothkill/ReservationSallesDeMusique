package ihm.visualisation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import data.Categorie;
import data.Reservation;
import data.Salle;
import data.Utilisateur;

/**
 * Panel affichant la chronologie des réservations d'une journée pour une salle
 * donnée.
 * 
 * @author JeanBaptiste
 *
 */
public class ChronologieSallePanel extends JPanel {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2261051581037359748L;

	protected Salle salle;

	/**
	 * @param salle
	 * @param La
	 *            liste des réservations de la journée
	 */
	public ChronologieSallePanel(Salle salle) {
		this.salle = salle;
		// TODO
		this.setSize(new Dimension(300, 800));
		this.setVisible(true);
		this.repaint();
	}

	@Override
	public void paint(Graphics graphics) {
		Graphics2D g2 = (Graphics2D) graphics;
		g2.setColor(new Color(0, 0, 0));
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, WIDTH, HEIGHT);
		graphics.setColor(Color.BLACK);

		for (int i = 50; i <= 750; i += 50) {
			graphics.drawLine(0, i, 300, i);
			g2.drawString("" + ((i / 50) + 8), 0, i - 10);
		}

		g2.drawString(salle.getNom(), 0, 20);
		g2.setColor(new Color((int) (Math.random() * (255)), (int) (Math
				.random() * (255)), (int) (Math.random() * (255)), 128));
		for (Reservation reservation : salle.getListeReservation()) {
			String format1 = new SimpleDateFormat("HH").format(reservation
					.getDateDebutReservation());
			int debut = Integer.parseInt(format1);

			String format2 = new SimpleDateFormat("HH").format(reservation
					.getDateFinReservation());
			int fin = Integer.parseInt(format2);

			g2.fillRect(0, ((debut - 8) * 50), 300,
					(((fin - 8) * 50) - (debut - 8) * 50));

		}

	}

	public static void main(String[] args) {
		// TODO retirer ce main
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1, 0));
		JFrame frame = new JFrame();
		frame.setSize(600, 600);
		Salle s = new Salle(1, new Categorie(1, "name", 1, 1, 1), "Salle",
				"Sale");
		List<Reservation> liste = new ArrayList<Reservation>();

		Calendar cal = Calendar.getInstance(); // creates calendar
		cal.setTime(new Date()); // sets calendar time/date
		cal.add(Calendar.HOUR_OF_DAY, 1); // adds one hour
		cal.getTime(); // returns new date object, one hour in the future

		Reservation r = new Reservation(1, new Date(), new Date(),
				cal.getTime(), true, s, new Utilisateur(1, "name", "tel", 0), 0);

		liste.add(r);

		panel.add(new ChronologieSallePanel(s));
		panel.add(new ChronologieSallePanel(s));
		s.setListeReservation(liste);
		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}
}
