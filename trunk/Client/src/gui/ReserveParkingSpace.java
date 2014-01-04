package gui;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import DataObjects.Worker;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReserveParkingSpace extends AbstractGUIComponent {
	private JTextField textField;
	public ReserveParkingSpace(final IGUINavigator navigator) {
		setLayout(null);
				
		JLabel lblParkingLotId = new JLabel("Parking Lot ID:");
		lblParkingLotId.setBounds(109, 9, 71, 14);
		add(lblParkingLotId);
		
		textField = new JTextField();
		textField.setBounds(185, 6, 86, 20);
		add(textField);
		textField.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSubmit.setBounds(276, 5, 65, 23);
		add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigator.goBack();
			}
		});
		btnCancel.setBounds(158, 62, 89, 23);
		add(btnCancel);
	}

}
