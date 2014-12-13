package dataObjects;

public class QDataSingleSelect extends AbsQData {

	private String[] qValues;
	
	public QDataSingleSelect(String qText, QType qType, String[] qValues) {
		
		super(qText,qType);
		this.qValues = qValues;
		
	}
	
	public String[] getQvalues() {
		return qValues;
	}
	
}
