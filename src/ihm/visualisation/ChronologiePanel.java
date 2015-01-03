package ihm.visualisation;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import data.Salle;

/**
 * Panel correspondant a la chronologie des reservations.
 * 
 * @author grimonprez
 * 
 */
public class ChronologiePanel extends JPanel {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2789381418305747266L;

	List<Salle> listeSalles;

	public ChronologiePanel() {
		super();
	}

	public ChronologiePanel(List<Salle> listeSalles) {
		super();
		this.listeSalles = listeSalles;
		this.setLayout(new GridLayout(1, 0));
		this.setVisible(true);
	}

	/**
	 * Recharge le planning
	 * 
	 * @param listSalles
	 */
	public void recharger(List<Salle> listSalles) {
		this.removeAll();
		this.listeSalles = listSalles;
		for (Salle salle : listSalles) {
			this.add(new ChronologieSallePanel(salle));
		}
	}
}
