package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JScrollPane;

public class ScrollPane extends JScrollPane {

	private TextArea textArea;
	
	public ScrollPane(TextArea textArea, int width, int height) {
			
		super(textArea);
		this.textArea = textArea;			
		setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
		setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);
		Dimension d = new Dimension(width,height);
		setPreferredSize(d);
		
	}
	
	public TextArea getTextArea() {
		return textArea;
	}
}
