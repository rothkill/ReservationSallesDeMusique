package ihm.visualisation;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.List;

import data.Reservation;
import data.Salle;
import data.Utilisateur;

public class ChronologieSallePanelReservation extends ChronologieSallePanel
		implements MouseListener {

	private Utilisateur utilisateur;
	private boolean isSelection;
	private Reservation reservation;

	private int x;
	private int y;

	public ChronologieSallePanelReservation(Salle salle,
			List<Reservation> listeReservations, Utilisateur utilisateur) {
		super(salle, listeReservations);
		this.utilisateur = utilisateur;
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getY() <= 100 || e.getY() >= 750) {
			return;
		}

		for (Reservation reservation : listeReservations) {
			String format1 = new SimpleDateFormat("HH").format(reservation
					.getDateDebutReservation());
			int debut = Integer.parseInt(format1);

			if (e.getY() >= debut * 50
					&& e.getY() <= debut * 50 + reservation.getDuree() * 50) {
				return;
			}
		}
		isSelection = true;
		System.out.println("selection");
	}

	public void paint(Graphics graphics) {
		super.paint(graphics);
		if (reservation != null) {
			graphics.setColor(new Color(0, 100, 255));

			String format1 = new SimpleDateFormat("HH").format(reservation
					.getDateDebutReservation());
			int debut = Integer.parseInt(format1);

			graphics.fillRect(0, debut * 50, 300,
					debut * 50 + reservation.getDuree() * 50);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (isSelection) {
			System.out.println("selection");
			if (e.getY() <= 100 || e.getY() >= 750) {
				isSelection = false;
				return;
			}

			for (Reservation reservation : listeReservations) {
				String format1 = new SimpleDateFormat("HH").format(reservation
						.getDateDebutReservation());
				int debut = Integer.parseInt(format1);

				if (e.getY() >= debut * 50
						&& e.getY() <= debut * 50 + reservation.getDuree() * 50) {
					isSelection = false;
					return;
				}
			}

			int clickY = y / 50 + 9;
			int releaseY = e.getY() / 50 + 9;

			if (clickY - releaseY == 0) {
				isSelection = false;
				return;
			}

			if (clickY - releaseY <= 0) {
				// TODO new reservetion
				// reservation = new Reservation(idReservation, dateReservation,
				// dateDebutReservation, dateFinReservation, confirmation,
				// salle, utilisateur, tarif)
			} else {
				// TODO new reservetion
			}

		} else {
			System.out.println("notselection");
		}
		isSelection = false;
	}

	public Reservation getReservation() {
		return reservation;
	}

}
