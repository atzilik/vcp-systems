package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class StandardMemberRegistration extends FullMemberRegistration{
	private JTextField textField;
	public StandardMemberRegistration() {
		
		JLabel lblEstimateChackOut = new JLabel("Estimate chack out hour");
		lblEstimateChackOut.setBounds(36, 92, 190, 20);
		getContentPane().add(lblEstimateChackOut);
		
		textField = new JTextField();
		textField.setBounds(219, 89, 104, 26);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblParkingLot = new JLabel("Parking lot");
		lblParkingLot.setBounds(36, 125, 90, 20);
		getContentPane().add(lblParkingLot);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(127, 122, 109, 26);
		getContentPane().add(comboBox);
	}
}
