package dataObjects;

import java.util.ArrayList;

public class Answer {

	private String qText;
	private String aText;
	private ArrayList<String> mulAText;
	
	public Answer(String qText, String aText) {
		
		this.setaText(aText);
		this.setqText(qText);
		
	}
	
	public Answer(String qText, ArrayList<String> mulAText) {
				
		this.setqText(qText);
		this.setMulAText(mulAText);
	}

	public String getqText() {
		return qText;
	}

	public void setqText(String qText) {
		this.qText = qText;
	}

	public String getaText() {
		return aText;
	}

	public void setaText(String aText) {
		this.aText = aText;
	}

	public ArrayList<String> getMulAText() {
		return mulAText;
	}

	public void setMulAText(ArrayList<String> mulAText) {
		this.mulAText = mulAText;
	}
}
