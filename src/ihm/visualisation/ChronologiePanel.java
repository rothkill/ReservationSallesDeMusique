package ihm.visualisation;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JPanel;

import data.Salle;

/**
 * Panel correspondant a la chronologie des reservations.
 * 
 * @author grimonprez
 * 
 */
public class ChronologiePanel extends JPanel implements ActionListener {

	List<Salle> listeSalles;

	public ChronologiePanel() {
		super();
	}

	public ChronologiePanel(List<Salle> listeSalles) {
		super();
		this.listeSalles = listeSalles;
		this.setLayout(new GridLayout(0, 1));

		// TODO JBG
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO JBG

	}

	public void recharger(List<Salle> listSalles) {
		this.removeAll();
		for (Salle salle : listSalles) {
			this.add(new ChronologieSallePanel(salle));
		}
	}
}
