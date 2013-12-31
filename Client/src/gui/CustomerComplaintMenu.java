package gui;


import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;

import DataObjects.Customer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CustomerComplaintMenu extends AbstractGUIComponent {
	private Customer cst;
	
	public CustomerComplaintMenu(final IGUINavigator navigator, Customer cst){
		this.cst = cst;
		setLayout(null);
		JTextArea textArea = new JTextArea();
		textArea.setBounds(128, 51, 196, 101);
		add(textArea);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigator.goBack();
			}
		});
		btnCancel.setBounds(219, 179, 115, 29);
		add(btnCancel);

		JLabel lblReason = new JLabel("Reason");
		lblReason.setBounds(37, 51, 69, 20);
		add(lblReason);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnSubmit.setBounds(54, 179, 115, 29);
		add(btnSubmit);
	}

}
