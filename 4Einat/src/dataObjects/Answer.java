package dataObjects;

import java.util.ArrayList;

public class Answer {

	private String qText;
	private String aText;
	private ArrayList<String> mulAText;
	private ArrayList<String> mulAprefixText;
	
	// free_text or birth_date answer
	public Answer(String qText, String aText) {
		
		this.setAtext(aText);
		this.setqText(qText);
		
	}
	
	// free_text_range answer
	public Answer(String qText, ArrayList<String> mulAText) {
				
		this.setqText(qText);
		this.setMulAText(mulAText);
	}
	
	// free_text_multi answer
	public Answer(String qText, ArrayList<String> mulAprefixText, ArrayList<String> mulAText) {
		
		this.setAtext(qText);
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

	public void setAtext(String aText) {
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
