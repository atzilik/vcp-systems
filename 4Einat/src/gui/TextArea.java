package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;

public class TextArea extends JTextArea {
	
	private static final long serialVersionUID = -8503263915123687486L;
	private JTextArea textArea;
	
	public TextArea() {
					
		this.textArea = new JTextArea();
		this.textArea.setLineWrap(true);
		this.textArea.setWrapStyleWord(true);   		   	
   		setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
   		setBorder(BorderFactory.createLineBorder(Color.BLACK));
   		
   		   		
		
	}
	
	public TextArea(int width, int height) {
		
		this.textArea = new JTextArea();
		this.textArea.setLineWrap(true);
		this.textArea.setWrapStyleWord(true);   	
		setPreferredSize(new Dimension(width,height));
   		setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
   		setBorder(BorderFactory.createLineBorder(Color.BLACK));
   		setLayout(new BorderLayout());
		
	}
	
	public JTextArea getTextArea() {
		return textArea;
	}

}


