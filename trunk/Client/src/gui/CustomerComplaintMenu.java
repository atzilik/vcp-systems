package gui;


import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;

import DataObjects.Customer;
import Messages.MessageIssueComplaint;
import Messages.MessageIssueComplaintReply;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;

public class CustomerComplaintMenu extends AbstractGUIComponent {
	private Customer cst;
	private JTextArea textArea;
	public CustomerComplaintMenu(final IGUINavigator navigator, final Customer cst){
		this.cst = cst;
		setLayout(null);
		textArea = new JTextArea();
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
				String[] arr = new String[4];
				arr[0] =  Integer.toString(100000 + new Random().nextInt(900000));
				arr[1] = cst.getId();
				arr[2] = textArea.getText();
				arr[3] = new java.sql.Date(new java.util.Date().getTime()).toString();
				client.send(new MessageIssueComplaint(arr));
				MessageIssueComplaintReply icr = (MessageIssueComplaintReply)client.getMessage();
				icr.doAction();
				navigator.goBack();
				
			}
		});
		btnSubmit.setBounds(54, 179, 115, 29);
		add(btnSubmit);
	}

}
