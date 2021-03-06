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
 * Panel affichant la chronologie des r�servations d'une journ�e pour une salle
 * donn�e.
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

	protected List<Reservation> lesReservations;

	/**
	 * @param salle
	 * @param La
	 *            liste des r�servations de la journ�e
	 */
	public ChronologieSallePanel(Salle salle, List<Reservation> lesReservations) {
		this.salle = salle;
		this.lesReservations = lesReservations;
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

		for (int i = 50; i <= 800; i += 50) {
			graphics.drawLine(0, i, 300, i);
			g2.drawString("" + ((i / 50) + 7), 0, i - 10);
		}

		g2.drawString(salle.getNom(), 0, 20);
		for (Reservation reservation : lesReservations) {
			g2.setColor(new Color((int) (Math.random() * (255)), (int) (Math
					.random() * (255)), (int) (Math.random() * (255)), 128));
			String format1 = new SimpleDateFormat("HH").format(reservation
					.getDateDebutReservation());
			int debut = Integer.parseInt(format1);

			String format2 = new SimpleDateFormat("HH").format(reservation
					.getDateFinReservation());
			int fin = Integer.parseInt(format2);

			g2.fillRect(0, ((debut - 8) * 50), 300,
					(((fin - 8) * 50) - (debut - 8) * 50));

			if (new Date().compareTo(reservation.getDateReservation()) > 0) {
				graphics.setColor(Color.BLACK);
				graphics.drawLine(0, (debut - 8) * 50, 300, ((fin - 8) * 50));
			}

		}

	}
}
