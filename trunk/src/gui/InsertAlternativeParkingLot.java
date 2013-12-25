package gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class InsertAlternativeParkingLot extends JFrame {
	public InsertAlternativeParkingLot() {
		setTitle("Insert Alternative Parking Lot");
		
		setSize(new Dimension(400, 300));
		getContentPane().setLayout(null);
		
		JLabel lblParkinglot = new JLabel("Parking Lot:");
		lblParkinglot.setBounds(45, 45, 68, 14);
		getContentPane().add(lblParkinglot);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(132, 42, 97, 20);
		getContentPane().add(comboBox);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSubmit.setBounds(140, 206, 89, 23);
		getContentPane().add(btnSubmit);
	}
}
