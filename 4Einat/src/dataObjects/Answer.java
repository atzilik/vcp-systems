package dataObjects;

import java.util.ArrayList;

public class Answer {

	private String qText;
	private String aText;
	private ArrayList<String> mulAText;
	private ArrayList<String> mulAprefixText;
	
	public Answer(String qText, String aText) {
		
		this.setaText(aText);
		this.setqText(qText);
		
	}
	
	public Answer(String qText, ArrayList<String> mulAText) {
				
		this.setqText(qText);
		this.setMulAText(mulAText);
	}
	
	public Answer(String qText, ArrayList<String> mulAprefixText, ArrayList<String> mulAText) {
		
		this.setaText(qText);
		this.setMulAprefixText(mulAprefixText);
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

	public ArrayList<String> getMulAprefixText() {
		return mulAprefixText;
	}

	public void setMulAprefixText(ArrayList<String> mulAprefixText) {
		this.mulAprefixText = mulAprefixText;
	}
}
