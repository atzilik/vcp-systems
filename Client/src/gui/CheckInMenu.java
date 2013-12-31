package gui;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import DataObjects.Customer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CheckInMenu extends AbstractGUIComponent {
	private Customer cst;
	private JTextField textField_1;
	public CheckInMenu(final IGUINavigator navigator, Customer cst) {
		this.cst = cst;
		setLayout(null);
		JLabel lblCarid = new JLabel("Car number");
		lblCarid.setBounds(61, 87, 86, 20);
		add(lblCarid);
		
		textField_1 = new JTextField();
		textField_1.setBounds(172, 84, 92, 26);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSubmit.setBounds(51, 165, 115, 29);
		add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigator.goBack();
			}
		});
		btnCancel.setBounds(216, 165, 115, 29);
		add(btnCancel);
	}

}
