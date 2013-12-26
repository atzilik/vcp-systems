package gui;


import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChangeRateMenu extends Frame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	public ChangeRateMenu() {
		super();
		
		setTitle("Change Rate Menu");
		
		JLabel lblNewLabel = new JLabel("Occasional Parking Rate:");
		lblNewLabel.setBounds(43, 41, 125, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Reserved Parking Rate:");
		lblNewLabel_1.setBounds(43, 66, 125, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Standard Membership Rate:");
		lblNewLabel_2.setBounds(43, 95, 143, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblOccasionalParkingRate = new JLabel("Occasional Parking Rate:");
		lblOccasionalParkingRate.setBounds(43, 120, 143, 14);
		getContentPane().add(lblOccasionalParkingRate);
		
		textField = new JTextField();
		textField.setBounds(208, 38, 86, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(208, 63, 86, 20);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(208, 92, 86, 20);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(208, 117, 86, 20);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSend.setBounds(142, 216, 89, 23);
		getContentPane().add(btnSend);
	}

}
