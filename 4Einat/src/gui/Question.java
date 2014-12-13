package gui;

import java.awt.Component;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Question extends JPanel {

	private static final long serialVersionUID = 3700041806039633626L;

	public Question(String text, Component comp) {
		
		setLayout(new FlowLayout());
		add(comp);
		add(new JLabel(text));
		
	}
	
}
