package gui;


import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;

import DataObjects.Customer;
import Messages.MessageIssueComplaint;
import Messages.MessageIssueComplaintReply;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
/**
 * used for customer in order to insert a complaint
 * @author Gal
 *
 */
public class CustomerComplaintMenu extends AbstractGUIComponent {
	/**
	 * Customer instance with the current customer details
	 */
	private Customer cst;
	private JTextArea textArea;
	private JComboBox comboBoxParkLot;
	/**
	 * Map is used to match parking lot name to its id.
	 */
	private Map<String,Integer> parkingLots;
	/**
	 * 
	 * @param navigator to navigate between panels
	 * @param cst customer instance
	 * @param mp for the map as described above
	 */
	public CustomerComplaintMenu(final IGUINavigator navigator, final Customer cst, Map<String,Integer> mp){
		this.cst = cst;
		this.parkingLots = mp; 
		setLayout(null);
		textArea = new JTextArea();
		textArea.setBounds(128, 51, 196, 101);
		add(textArea);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigator.goBack();
			}
		});
		btnCancel.setBounds(212, 237, 115, 29);
		add(btnCancel);

		JLabel lblReason = new JLabel("Reason");
		lblReason.setBounds(37, 51, 69, 20);
		add(lblReason);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] arr = new String[6];
				arr[0] =  Integer.toString(100000 + new Random().nextInt(900000));
				arr[1] = cst.getId();
				arr[2] = textArea.getText();
				arr[3] = new java.sql.Date(new java.util.Date().getTime()).toString();
				arr[4] = Integer.toString(parkingLots.get(comboBoxParkLot.getSelectedItem()));
				arr[5] = cst.getCarId();
				client.send(new MessageIssueComplaint(arr));
				MessageIssueComplaintReply icr = (MessageIssueComplaintReply)client.getMessage();
				icr.doAction();
				navigator.goBack();
				
			}
		});
		btnSubmit.setBounds(47, 237, 115, 29);
		add(btnSubmit);
		
		comboBoxParkLot = new JComboBox();
		comboBoxParkLot.setBounds(128, 175, 146, 26);
		Set<String> keys = parkingLots.keySet();
		for (Iterator<String> i = keys.iterator(); i.hasNext();)
		{
			comboBoxParkLot.addItem(i.next());
		}
		add(comboBoxParkLot);
	}
}
