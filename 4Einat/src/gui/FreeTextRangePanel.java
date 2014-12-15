package gui;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.JTextComponent;

public class FreeTextRangePanel extends JPanel {

	private String qText1;
	private String qText2;
	private TextArea spTextArea1;
	private TextArea spTextArea2;
	
	public FreeTextRangePanel(String qText1, String qtext2, int width, int height) {
		
		this.qText1 = qText1;
		this.qText2 = qtext2;
		spTextArea1 = new TextArea(width,height);
		spTextArea2 = new TextArea(width,height);
		add(spTextArea1);
		add(spTextArea2);
		
		
	}
	
	public String getQtext1() {
		return qText1;
	}
	
	public String getAtext1() {
		
		JTextArea textArea = spTextArea1.getTextArea();
		
		return textArea.getText();
	}
	
	public String getQtext2() {
		return qText2;		
	}
	
	public String getAtext2() {
		
		JTextArea textArea = spTextArea2.getTextArea();
		
		return textArea.getText();
	}
	
	
}
