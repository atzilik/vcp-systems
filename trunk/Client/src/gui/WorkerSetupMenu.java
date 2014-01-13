package gui;

import DataObjects.Worker;
import Messages.MessageSetupPL;
import Messages.MessageSetupPLReply;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author omri
 *This class is responsible for initiate parking lot => set as active and insert depth.
 */
public class WorkerSetupMenu extends AbstractGUIComponent {
	private int depthSize;
	private int parkingLotID;
	private Worker worker;
	private JTextField textFieldDepthSize;
	/**
	 * 
	 * @param navigator navigate between panels
	 * @param parkingLotID
	 * @param worker
	 */
	public WorkerSetupMenu(final IGUINavigator navigator, final int parkingLotID, final Worker worker){
		setLayout(null);
		this.parkingLotID = parkingLotID;
		this.worker=worker;
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
				parkinglots[parkingLotID].setActive(true);
				//navigator.goBack();
				
			}
		});
		btnSetup.setBounds(137, 176, 89, 23);
		add(btnSetup);
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigator.goToWorkerMenu(worker);
			}
		});
		btnCancel.setBounds(233, 176, 89, 23);
		add(btnCancel);
	}
}
