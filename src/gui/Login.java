package gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import client.ClientConsole;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame{
	private ClientConsole cc;
	private boolean customer;
	private JTextField userName;
	private JPasswordField pass;
	private JButton btnCustomerLogin;
	private JButton btnWorkerLogin;

	public Login(String host, int port){
		setTitle("Login");

		cc = new ClientConsole(host,port);
		JOptionPane.showMessageDialog(new JPanel(), "Conncetion to the Server was successful");
		setSize(new Dimension(400, 300));
		getContentPane().setLayout(null);
		setSize(new Dimension(400, 300));
		getContentPane().setLayout(null);

		btnCustomerLogin = new JButton("Customer Login");
		btnCustomerLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCustomer(true);
				displayLogin();
			}
		});
		btnCustomerLogin.setBounds(45, 95, 135, 69);
		getContentPane().add(btnCustomerLogin);

		btnWorkerLogin = new JButton("Worker Login");
		btnWorkerLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCustomer(false);
				displayLogin();
			}
		});
		btnWorkerLogin.setBounds(217, 95, 135, 69);
		getContentPane().add(btnWorkerLogin);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void displayLogin(){
		JPanel panel = new JPanel();
		panel.setLayout(null);

		
		JLabel lblUserName = new JLabel("User Name:");
		lblUserName.setBounds(85, 69, 71, 14);
		getContentPane().add(lblUserName);

		userName = new JTextField();
		userName.setBounds(210, 66, 86, 20);
		getContentPane().add(userName);
		userName.setColumns(10);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(85, 136, 71, 14);
		getContentPane().add(lblPassword);

		pass = new JPasswordField();
		pass.setBounds(210, 133, 86, 20);
		getContentPane().add(pass);
		pass.setColumns(10);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] arr = new String[4];
				arr[0] = "check login";
				arr[1] = userName.getText();
				arr[2] = new String(pass.getPassword());
				arr[3] = (isCustomer())?"yes":"no";
				cc.accept(arr);
			}
		});
		btnSubmit.setBounds(104, 209, 89, 23);
		getContentPane().add(btnSubmit);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userName.setText(null);
				pass.setText(null);
			}
		});
		btnClear.setBounds(272, 209, 89, 23);
		getContentPane().add(btnClear);
		getContentPane().add(panel);
		this.remove(btnCustomerLogin);
		this.remove(btnWorkerLogin);
		this.repaint();
	}

	public boolean isCustomer() {
		return customer;
	}

	public void setCustomer(boolean customer) {
		this.customer = customer;
	}

}
