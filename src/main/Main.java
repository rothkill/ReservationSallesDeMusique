package main;

import java.awt.BorderLayout;

import ihm.MenuPanel;

import javax.swing.JFrame;

import utils.Constantes;

public class Main {

	/**
	 * main
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame(Constantes.APPLICATION_TITLE);
		frame.getContentPane().add(new MenuPanel(frame), BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setSize(400, 400);
		frame.pack();
		frame.setVisible(true);
	}

}
