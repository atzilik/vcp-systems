package gui;

import DataObjects.Worker;
import Messages.MessageDisableFacility;
import Messages.MessageDisableFacilityReply;
import Messages.MessageSetupPLReply;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * This class lets the worker enable/disable a parkinglot... setting it as active/not active
 */
public class EnableDisableFacility extends AbstractGUIComponent {
	private Worker wkr;
/**
 * 
 * @param navigator for navigating between panels
 * @param worker will be a regular worker
 */
	public EnableDisableFacility(final IGUINavigator navigator, final Worker worker) {
		setLayout(null);
		this.wkr=worker;
		JButton btnEnabledisableFacility = new JButton("Enable/Disable Facility");
		btnEnabledisableFacility.setBounds(112, 126, 194, 61);
		add(btnEnabledisableFacility);
		JLabel lblNewLabel = new JLabel();
		if (parkinglots[worker.getParkingLotID()].isActive()){
			lblNewLabel = new JLabel("This facility is now active");
		}
		else{
			lblNewLabel = new JLabel("This facility is inactive");
		}
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(112, 75, 194, 40);
		add(lblNewLabel);

		//if facility is active then let worker disable it
		if (parkinglots[worker.getParkingLotID()].isActive()){
			btnEnabledisableFacility.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					parkinglots[worker.getParkingLotID()].setActive(false);
					client.send(new MessageDisableFacility(worker.getParkingLotID()));
					MessageDisableFacilityReply mdfr = (MessageDisableFacilityReply)client.getMessage();
					mdfr.doAction();
					navigator.goBack();
					}	});
		}	
		else {
			btnEnabledisableFacility.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					navigator.goToWorkerSetupMenu(worker.getParkingLotID(),worker);
					parkinglots[worker.getParkingLotID()].setActive(true);
				}});
		}
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigator.goToWorkerMenu(wkr);
			}
		});
		btnCancel.setBounds(157, 226, 89, 23);
		add(btnCancel);
	}
}
