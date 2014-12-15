package gui;

import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class MultiFreeTextPanel extends JPanel {

	private static final long serialVersionUID = 7042736428402595350L;
	private ArrayList<String> qTexts = new ArrayList<String>();
	private ArrayList<TextArea> qTextAreas = new ArrayList<TextArea>();
	
	public MultiFreeTextPanel(){
		
		GridLayout grid = new GridLayout(0, 2);
		grid.setHgap(0);
		grid.setVgap(4);
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setLayout(grid);


	}
	
	public void addQuestion(String qText, int width, int height) {
		
		TextArea textArea = new TextArea(width, height);				
		qTextAreas.add(textArea);
		qTexts.add(qText);
		add(textArea);
		JLabel label = new JLabel(qText);
		label.setPreferredSize(label.getMaximumSize());
		add(label);
		
	}
	
	public ArrayList<String> getQuestions() {
		return qTexts;
	}
	
	public ArrayList<String> getAnswers() {
		
		ArrayList<String> answers = new ArrayList<String>();
		for(TextArea spTa : qTextAreas) 
		{
			JTextArea textArea = spTa.getTextArea();
			answers.add(textArea.getText());
		}
		return answers;
	}
}
