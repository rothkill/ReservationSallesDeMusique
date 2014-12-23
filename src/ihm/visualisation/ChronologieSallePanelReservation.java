package ihm.visualisation;

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
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
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

		// TODO JBG : reservation
	}

}
