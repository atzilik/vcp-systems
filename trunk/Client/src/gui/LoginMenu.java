package gui;
import javax.swing.JButton;

import DataObjects.ParkingLot;
import Messages.MessageMapParkingLot;
import Messages.MessageMapParkingLotReply;

import client.Client;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginMenu extends AbstractGUIComponent {
	public LoginMenu(final IGUINavigator navigator){
		setLayout(null);
		
		JButton btnCustLogin = new JButton("Customer Login");
		btnCustLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigator.goToCustomerLogin();
			}
		});
		btnCustLogin.setBounds(55, 130, 148, 73);
		add(btnCustLogin);
		
		JButton btnWorkerLogin = new JButton("Worker Login");
		btnWorkerLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigator.goToWorkerLogin();
			}
		});
		btnWorkerLogin.setBounds(255, 130, 148, 73);
		add(btnWorkerLogin);
		
	}
	
	public static void main(String args[]) {
		String host = null;
		try
		{
			host = args[0];
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			host = "localhost";
		}
		Frame frame = new Frame();
		LoginMenu lm = new LoginMenu(frame);
		lm.ClientSingleton(host, Client.DEFAULT_PORT);
		MessageMapParkingLot ma = new MessageMapParkingLot(AbstractGUIComponent.NUM_OF_PARKINGLOTS);
		client.send(ma);
		MessageMapParkingLotReply plr = (MessageMapParkingLotReply)client.getMessage();
		lm.setParkingLots(plr.getParkinglot());
		frame.setContentPane(lm);
		frame.setTitle("Login");
		frame.validate();

	}
}
