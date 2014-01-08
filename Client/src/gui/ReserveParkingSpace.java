package gui;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import DataObjects.Worker;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class ReserveParkingSpace extends AbstractGUIComponent {
	public ReserveParkingSpace(final IGUINavigator navigator) {
		setLayout(null);
				
		JLabel lblParkingLotId = new JLabel("Parking Lot ID:");
		lblParkingLotId.setBounds(68, 9, 71, 14);
		add(lblParkingLotId);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSubmit.setBounds(166, 123, 80, 23);
		add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigator.goBack();
			}
		});
		btnCancel.setBounds(166, 266, 89, 23);
		add(btnCancel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(150, 6, 123, 20);
		add(comboBox);
	}
}
