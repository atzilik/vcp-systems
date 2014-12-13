package dataObjects;

public abstract class AbsQData {

	protected String qText;
	protected QType qType;	
	
	public AbsQData(String qText, QType qType) {
		
		this.qText = qText;
		this.qType = qType;		
	}
			
	public String getqText() {		
		return qText;		
	}
	
	public QType getqType() {
		
		return qType;
	}	
	
	public enum QType { FREE_TEXT, BIRTH_DATE, SINGLE_SELECTION, MULTI_SELECTION, }
	
}
