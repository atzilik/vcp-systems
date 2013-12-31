package gui;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;

import Messages.MessageLogin;
import Messages.MessageLoginReply;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;

public class Login extends AbstractGUIComponent {
	private JTextField userName;
	private JPasswordField pass;


	public Login(final IGUINavigator navigator){
		setLayout(null);

		JLabel lblUserName = new JLabel("User Name:");
		lblUserName.setBounds(54, 62, 86, 14);
		add(lblUserName);

		userName = new JTextField();
		userName.setBounds(187, 59, 86, 20);
		add(userName);
		userName.setColumns(10);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(54, 113, 73, 14);
		add(lblPassword);

		pass = new JPasswordField();
		pass.setBounds(187, 110, 86, 20);
		add(pass);
		pass.setColumns(10);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(113, 189, 95, 37);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client.send(new MessageLogin(userName.getText(), new String(pass.getPassword())));
				MessageLoginReply mlr = (MessageLoginReply)client.getMessage();
				mlr.doAction();
				//customer
				if (mlr.isCustomer() == true)
				{
					navigator.goToCustomerMenu(mlr.getCust());
				}
				//worker
				else
				{
					
				}
			}
		});
		add(btnSubmit);

		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(252, 189, 95, 37);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userName.setText(null);
				pass.setText(null);
			}
		});
		add(btnClear);

	}


}
