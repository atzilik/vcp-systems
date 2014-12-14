package gui;

import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class RadioButtonPanel extends JPanel {

	private ArrayList<JRadioButton> rButtons;
	
	public RadioButtonPanel() {
		
		rButtons = new ArrayList<JRadioButton>();
	}
	
	public void addRbtnToList(JRadioButton b) {
		
		rButtons.add(b);
	}
	
	public String getSelected() {
				
		for(JRadioButton b :rButtons) {
			
			if(b.isSelected())
				return b.getText();
		}
		
		return "no_selection";
	}
}
