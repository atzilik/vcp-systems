package gui;


import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

import DataObjects.Worker;
import Messages.MessageAltParking;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class InsertAlternativeParkingLot extends AbstractGUIComponent {
	private Map<String,Integer> parkingLots;	
	private final JComboBox comboBoxParkLot;
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
				String arr[] = new String[2];
				arr[0]=Integer.toString(worker.getParkingLotID());
				arr[1]=Integer.toString(parkingLots.get(comboBoxParkLot.getSelectedItem()));
				client.send(new MessageAltParking(arr));
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
