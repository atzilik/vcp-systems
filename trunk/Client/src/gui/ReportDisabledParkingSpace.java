package gui;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

import DataObjects.Worker;

public class ReportDisabledParkingSpace extends AbstractGUIComponent {
	private JTextField textField;
	private JTextField textField_1;
	public ReportDisabledParkingSpace(final IGUINavigator navigator) {
		super();		
	
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		setLayout(null);
		btnCreate.setBounds(62, 5, 65, 23);
		add(btnCreate);
		
		JLabel lblPlace = new JLabel("Place:");
		lblPlace.setBounds(132, 9, 29, 14);
		add(lblPlace);
		
		textField = new JTextField();
		textField.setBounds(166, 6, 86, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblReason = new JLabel("Reason:");
		lblReason.setBounds(257, 9, 40, 14);
		add(lblReason);
		
		textField_1 = new JTextField();
		textField_1.setBounds(302, 6, 86, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigator.goBack();
			}
		});
		btnCancel.setBounds(186, 63, 89, 23);
		add(btnCancel);
		
	}
}
