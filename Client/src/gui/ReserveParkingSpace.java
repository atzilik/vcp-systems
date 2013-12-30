package gui;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReserveParkingSpace extends AbstractGUIComponent {
	private JTextField textField;
	public ReserveParkingSpace() {
				
		JLabel lblParkingLotId = new JLabel("Parking Lot ID:");
		lblParkingLotId.setBounds(68, 45, 79, 14);
		add(lblParkingLotId);
		
		textField = new JTextField();
		textField.setBounds(157, 42, 86, 20);
		add(textField);
		textField.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSubmit.setBounds(154, 198, 89, 23);
		add(btnSubmit);
	}

}
