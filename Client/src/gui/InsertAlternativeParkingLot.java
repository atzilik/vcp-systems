package gui;


import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

import DataObjects.Worker;
import Messages.MessageAltParking;
import Messages.MessageAltParkingReply;
import Messages.MessageIssueComplaintReply;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * The class gives the worker an option for changing the default alt parking space
 * @author Alon
 *
 */
public class InsertAlternativeParkingLot extends AbstractGUIComponent {
	/**
	 * Map is used to match parking lot name to its id.  The worker will know which parkinglot he's dealing with
	 */
	private Map<String,Integer> parkingLots;	
	private final JComboBox comboBoxParkLot;
	/**
	 * 
	 * @param navigator to navigate between panels
	 * @param mp for the map as described above
	 * @param worker will be a regular worker
	 */
	public InsertAlternativeParkingLot(final IGUINavigator navigator,Map<String,Integer> mp, final Worker worker) {
		setLayout(null);
		this.parkingLots=mp;				
		JLabel lblParkinglot = new JLabel("Parking Lot:");
		lblParkinglot.setBounds(10, 9, 96, 14);
		add(lblParkinglot);
		
		comboBoxParkLot = new JComboBox();
		Set<String> keys = parkingLots.keySet();
		for (Iterator<String> i = keys.iterator(); i.hasNext();)
		{
			comboBoxParkLot.addItem(i.next());
		}
		comboBoxParkLot.setBounds(116, 6, 136, 20);
		add(comboBoxParkLot);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String arr[] = new String[3];
				arr[0]=Integer.toString(worker.getParkingLotID());
				arr[1]=Integer.toString(parkingLots.get(comboBoxParkLot.getSelectedItem()));
				arr[2]=(String)comboBoxParkLot.getSelectedItem();
				client.send(new MessageAltParking(arr));
				MessageAltParkingReply mapr = (MessageAltParkingReply)client.getMessage();
				mapr.doAction();
			}
		});
		btnSubmit.setBounds(262, 5, 89, 23);
		add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigator.goBack();
			}
		});
		btnCancel.setBounds(361, 5, 89, 23);
		add(btnCancel);
	}
}
