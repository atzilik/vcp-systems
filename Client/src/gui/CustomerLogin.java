package gui;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import DataObjects.ParkingLot;
import Messages.MessageCustomerLogin;
import Messages.MessageCustomerLoginReply;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import client.Client;


public class CustomerLogin extends AbstractGUIComponent {
	private JTextField Id;
	private JTextField carNum;


	public CustomerLogin(final IGUINavigator navigator){
		setLayout(null);

		JLabel lblID = new JLabel("CustomerID/MemberID:");
		lblID.setBounds(54, 62, 154, 14);
		add(lblID);

		Id = new JTextField();
		Id.setBounds(220, 59, 86, 20);
		add(Id);
		Id.setColumns(10);

		JLabel lblCarNum = new JLabel("Car Number:");
		lblCarNum.setBounds(54, 113, 86, 14);
		add(lblCarNum);

		carNum = new JTextField();
		carNum.setBounds(220, 110, 86, 20);
		add(carNum);
		carNum.setColumns(10);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(113, 189, 95, 37);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client.send(new MessageCustomerLogin(Id.getText(), carNum.getText()));
				MessageCustomerLoginReply clr = (MessageCustomerLoginReply)client.getMessage();
				clr.doAction();
				if (clr.getCustomer() != null)
					navigator.goToCustomerMenu(clr.getCustomer());
			}
		});
		add(btnSubmit);

		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(252, 189, 95, 37);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Id.setText(null);
				carNum.setText(null);
			}
		});
		add(btnClear);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigator.goToLoginMenu();
			}
		});
		btnCancel.setBounds(181, 252, 95, 37);
		add(btnCancel);

	}

}
