package gui;

import DataObjects.Worker;
import Messages.MessageSetupPL;
import Messages.MessageSetupPLReply;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WorkerSetupMenu extends AbstractGUIComponent {
	private int depthSize;
	private int parkingLotID;
	private JTextField textFieldDepthSize;
	
	public WorkerSetupMenu(final IGUINavigator navigator, final int parkingLotID){
		setLayout(null);
		this.parkingLotID = parkingLotID;
		
		JLabel lblInsertNewDepth = new JLabel("(Optional) Insert new depth size:");
		lblInsertNewDepth.setBounds(158, 99, 222, 14);
		add(lblInsertNewDepth);
		
		textFieldDepthSize = new JTextField();
		textFieldDepthSize.setBounds(182, 124, 86, 20);
		add(textFieldDepthSize);
		textFieldDepthSize.setColumns(10);
		
		JButton btnSetup = new JButton("Setup");
		btnSetup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MessageSetupPL msp;
				if (textFieldDepthSize.getText().equals(""))
				{
					msp = new MessageSetupPL(parkingLotID);
				}
				else
				{
					msp = new MessageSetupPL(parkingLotID, Integer.parseInt(textFieldDepthSize.getText()));
				}
				client.send(msp);
				MessageSetupPLReply splr = (MessageSetupPLReply)client.getMessage();
				splr.doAction();
				navigator.goBack();
			}
		});
		btnSetup.setBounds(182, 211, 89, 23);
		add(btnSetup);
	}
}
