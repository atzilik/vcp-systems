package dataObjects;

public class QDataFreeText extends AbsQData {

	private int size;
	
	public QDataFreeText(String qText, QType qType,int size) {
		super(qText, qType);
		this.size = size;
	}

	
}
