package gui;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Frame extends JFrame {
	
	public Frame(){
		setSize(new Dimension(400, 300));
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
