package gui;


import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import DataObjects.Customer;
import Messages.MessageFullMemberRegister;
import Messages.MessageFullMemberRegisterReply;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;

public class FullMemberRegistration extends AbstractGUIComponent {
	protected Customer cst;
	private JTextField textField_carNum;
	private JTextField textField_date;
	public FullMemberRegistration(final IGUINavigator navigator, final Customer cst) {
		this.cst = cst;
		setLayout(null);

		JLabel lblCid = new JLabel("Car number");
		lblCid.setBounds(74, 59, 72, 14);
		add(lblCid);

		textField_carNum = new JTextField();
		textField_carNum.setBounds(177, 56, 86, 20);
		add(textField_carNum);
		textField_carNum.setColumns(10);

		JLabel lblStartDate = new JLabel("Start date");
		lblStartDate.setBounds(72, 102, 95, 14);
		add(lblStartDate);

		textField_date = new JTextField();
		textField_date.setBounds(177, 99, 86, 20);
		add(textField_date);
		textField_date.setColumns(10);


		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigator.goBack();
			}
		});
		btnCancel.setBounds(231, 233, 86, 34);
		add(btnCancel);
		
		JButton btnCreate = new JButton("Submit");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] arr = new String[8];
				arr[0] = Integer.toString(100000 + new Random().nextInt(900000));
				arr[1] = textField_carNum.getText();
				arr[2] = cst.getId();
				arr[3] = cst.getfName();
				arr[4] = cst.getlName();
				arr[5] = cst.getEmail();
				arr[6] = textField_date.getText();
				arr[7] = "1";
				client.send(new MessageFullMemberRegister(arr));
				MessageFullMemberRegisterReply fmrr = (MessageFullMemberRegisterReply)client.getMessage();
				fmrr.doAction();
				navigator.goBack();
			}
		});
		btnCreate.setBounds(90, 233, 86, 34);
		add(btnCreate);
		
		
	}
	
}
