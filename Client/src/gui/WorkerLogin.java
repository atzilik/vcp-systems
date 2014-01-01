package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Messages.MessageCustomerLogin;
import Messages.MessageCustomerLoginReply;
import Messages.MessageWorkerLogin;
import Messages.MessageWorkerLoginReply;

public class WorkerLogin extends AbstractGUIComponent {

	private JTextField UserName;
	private JTextField pass;


	public WorkerLogin(final IGUINavigator navigator){
		setLayout(null);

		JLabel lblUserName = new JLabel("User Name:");
		lblUserName.setBounds(54, 62, 154, 14);
		add(lblUserName);

		UserName = new JTextField();
		UserName.setBounds(220, 59, 86, 20);
		add(UserName);
		UserName.setColumns(10);

		JLabel lblCarNum = new JLabel("Password:");
		lblCarNum.setBounds(54, 113, 86, 14);
		add(lblCarNum);

		pass = new JTextField();
		pass.setBounds(220, 110, 86, 20);
		add(pass);
		pass.setColumns(10);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(113, 189, 95, 37);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client.send(new MessageWorkerLogin(UserName.getText(), pass.getText()));
				MessageWorkerLoginReply wlr = (MessageWorkerLoginReply)client.getMessage();
				wlr.doAction();
				if (wlr.getWrk() != null)
					navigator.goToWorkerMenu(wlr.getWrk());
			}
		});
		add(btnSubmit);

		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(252, 189, 95, 37);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserName.setText(null);
				pass.setText(null);
			}
		});
		add(btnClear);

	}
	
	public static void main(String args[]) {

		Frame frame = new Frame();
		frame.setContentPane(new WorkerLogin(frame));
		frame.setTitle("Login");
		frame.validate();
	}
}
