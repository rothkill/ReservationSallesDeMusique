package ihm.visualisation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JPanel;

import data.Reservation;
import data.Salle;

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
	 *            liste des réservations de la journée;
	 */
	public ChronologieSallePanel(Salle salle) {
		this.salle = salle;
		// TODO
		this.setSize(new Dimension(300, 800));
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
			g2.drawString("" + (i / 50) + 9, 0, i - 10);
		}

		g2.drawString(salle.getNom(), 0, 20);
		g2.setColor(new Color(0, 155, 155));

		for (Reservation reservation : salle.getListeReservation()) {
			String format1 = new SimpleDateFormat("HH").format(reservation
					.getDateDebutReservation());
			int debut = Integer.parseInt(format1);

			g2.fillRect(0, debut * 50, 300, debut * 50 + reservation.getDuree()
					* 50);

		}

	}
}
