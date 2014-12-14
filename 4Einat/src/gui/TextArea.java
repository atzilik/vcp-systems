package gui;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;

public class TextArea extends JTextArea {
	
	
	public TextArea(int width, int height) {
		
		setLineWrap(true);
   		setWrapStyleWord(true);
   		setPreferredSize(new Dimension(width,height));
   		setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
   		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
	}
}
