package ihm;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

/**
 * Panel du cas d'utilisation 1 : visualisation des reservations.
 * 
 * @author grimonprez
 * 
 */
public class VisualiserReservationPanel extends JPanel implements
		ActionListener {

	private JPanel north = new JPanel();
	private ChronologiePanel center = new ChronologiePanel();
	
	

	public VisualiserReservationPanel() {

		this.setLayout(new BorderLayout());
		this.add(north, BorderLayout.NORTH);
		this.add(center, BorderLayout.CENTER);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO JBG

	}

}
