package gui;

import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class CheckBoxPanel extends JPanel {

	private ArrayList<JCheckBox> chkboxList;
	
	public void addCheckBox(JCheckBox chkBox) {
		
		chkboxList.add(chkBox);		
	}
	
	public ArrayList<JCheckBox> getChkBoxList() {
		return chkboxList;
	}
	
	public ArrayList<String> getCheckedBoxes() {
		
		ArrayList<String> checkedBoxes = new ArrayList<String>();
		for(JCheckBox chkBox : chkboxList)
		{
			if(chkBox.isSelected())
				checkedBoxes.add(chkBox.getActionCommand());
		}
		
		return checkedBoxes;
	}
}
