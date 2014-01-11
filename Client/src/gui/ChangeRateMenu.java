package gui;


import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import DataObjects.Worker;
import Messages.MessageRequestChangeParkingRate;
import Messages.MessageRequestChangeParkingRateReply;
import Messages.MessageWorkerLogin;
import Messages.MessageWorkerLoginReply;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChangeRateMenu extends AbstractGUIComponent {
	private JTextField textField_1;
	private JTextField textField_2;
	Worker wrk;

	public ChangeRateMenu(final IGUINavigator navigator, Worker worker) {
		super();
		setLayout(null);
		
		wrk = worker;
						
		JLabel lblNewLabel = new JLabel("Occasional Parking Rate:");
		lblNewLabel.setBounds(29, 55, 119, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Reserved Parking Rate:");
		lblNewLabel_1.setBounds(29, 89, 114, 14);
		add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(176, 86, 86, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(176, 52, 86, 20);
		add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client.send(new MessageRequestChangeParkingRate(wrk.getParkingLotID(), Float.parseFloat(textField_1.getText()), Float.parseFloat(textField_2.getText()),wrk.getId()));
				MessageRequestChangeParkingRateReply wlr = (MessageRequestChangeParkingRateReply)client.getMessage();
				wlr.doAction();
				navigator.goToWorkerMenu(wrk);
			}
		});
		btnSend.setBounds(191, 214, 57, 23);
		add(btnSend);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigator.goBack();
			}
		});
		btnCancel.setBounds(260, 214, 89, 23);
		add(btnCancel);
		

	}
}
