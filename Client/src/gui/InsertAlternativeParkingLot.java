package gui;


import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class InsertAlternativeParkingLot extends AbstractGUIComponent {
	public InsertAlternativeParkingLot() {
						
		JLabel lblParkinglot = new JLabel("Parking Lot:");
		lblParkinglot.setBounds(45, 45, 68, 14);
		add(lblParkinglot);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(132, 42, 97, 20);
		add(comboBox);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSubmit.setBounds(140, 206, 89, 23);
		add(btnSubmit);
	}
}
