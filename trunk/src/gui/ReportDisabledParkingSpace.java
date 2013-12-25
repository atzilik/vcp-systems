package gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReportDisabledParkingSpace extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	public ReportDisabledParkingSpace() {
		setTitle("Report Disable Parking Space");
		
		setSize(new Dimension(400, 300));
		getContentPane().setLayout(null);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnCreate.setBounds(148, 207, 89, 23);
		getContentPane().add(btnCreate);
		
		JLabel lblPlace = new JLabel("Place:");
		lblPlace.setBounds(79, 44, 46, 14);
		getContentPane().add(lblPlace);
		
		textField = new JTextField();
		textField.setBounds(148, 38, 146, 26);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblReason = new JLabel("Reason:");
		lblReason.setBounds(79, 80, 46, 14);
		getContentPane().add(lblReason);
		
		textField_1 = new JTextField();
		textField_1.setBounds(148, 74, 146, 26);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		setLocationRelativeTo(null);
		
	}
}
