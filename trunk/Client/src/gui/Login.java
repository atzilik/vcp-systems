package gui;

import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;

import Messages.MessageLogin;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class Login extends Frame{
	private JTextField userName;
	private JPasswordField pass;
	

	public Login(){
		super();
		setTitle("Login");
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
				try {
					getClient().sendToServer(new MessageLogin(userName.getText(), new String(pass.getPassword())));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				String[] arr = new String[3];
//				arr[0] = "check login";
//				arr[1] = userName.getText();
//				arr[2] = new String(pass.getPassword());
//				try {
//					getC().sendToServer(arr);
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				new Thread(new Runnable() {
//					
//					@Override
//					public void run() {
//						// TODO Auto-generated method stub
//						try {
//							Thread.sleep(100);
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//						if (getCc().getClient().isLogin() == true)
//						{
//							setVisible(false);
//						}
//					}
//				}).start();
				
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

	}


}
