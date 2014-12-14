package gui;

import javax.swing.JPanel;

public class FreeTextRangePanel extends JPanel {

	private String qText1;
	private String qText2;
	private TextArea textArea1;
	private TextArea textArea2;
	
	public FreeTextRangePanel(String qText1, String qtext2, int width, int height) {
		
		this.qText1 = qText1;
		this.qText2 = qtext2;
		textArea1 = new TextArea(width,height);
		textArea2 = new TextArea(width,height);
		add(textArea1);
		add(textArea2);
		
		
	}
	
	public String getQtext1() {
		return qText1;
	}
	
	public String getAtext1() {
		return textArea1.getText();
	}
	
	public String getQtext2() {
		return qText2;		
	}
	
	public String getAtext2() {
		return textArea2.getText();
	}
	
	
}
