package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import DataObjects.Customer;
import DataObjects.STDCustomer;
import Messages.MessageGenerateValidID;
import Messages.MessageGenerateValidIDReply;
import Messages.MessageMemberRegister;
import Messages.MessageMemberRegisterReply;
/**
 * register ad a standard member
 * @author Gal
 *
 */
public class STDMemberRegistration extends AbstractGUIComponent {
	private JTextField textField_hour;
	/**
	 * customer instance
	 */
	private Customer cst;
	/**
	 * map used to get the parking lot id from the comboBox
	 */
	private Map<String,Integer> parkingLots;
	private JComboBox comboBox;
	/**
	 * 
	 * @param navigator to navigate between panels
	 * @param cst customer instance
	 * @param mp used to get the parking lot id from the comboBox
	 */
	public STDMemberRegistration(final IGUINavigator navigator, final Customer cst, Map<String,Integer> mp) {
		this.cst = cst;
		this.parkingLots = mp;
		setLayout(null);
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigator.goToMemberRegister(cst);
			}
		});
		btnCancel.setBounds(310, 233, 86, 34);
		add(btnCancel);
		
		JButton btnCreate = new JButton("Submit");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] arr = new String[10];
				if (((STDCustomer)cst).isRegisteredToMember())
				{
					arr[0] = ((STDCustomer) cst).getMemberID();
				}
				else
				{
					client.send(new MessageGenerateValidID(1));
					MessageGenerateValidIDReply cmir = (MessageGenerateValidIDReply)client.getMessage();
					arr[0] = cmir.getiD();
					((STDCustomer) cst).setMemberID(arr[0]);
					((STDCustomer) cst).setRegisteredToMember(true);
				}
				arr[1] = cst.getCarId();
				arr[2] = cst.getId();
				arr[3] = cst.getfName();
				arr[4] = cst.getlName();
				arr[5] = cst.getEmail();
				arr[6] = new java.sql.Date(new java.util.Date().getTime()).toString();
				arr[7] = Integer.toString(parkingLots.get(comboBox.getSelectedItem()));
				arr[8] = textField_hour.getText();
				arr[9] = "2";
				client.send(new MessageMemberRegister(arr,arr[9]));
				MessageMemberRegisterReply fmrr = (MessageMemberRegisterReply)client.getMessage();
				fmrr.doAction();
			}
		});
		btnCreate.setBounds(56, 233, 86, 34);
		add(btnCreate);
		
		JLabel lblEstimateChackOut = new JLabel("Estimate check out hour");
		lblEstimateChackOut.setBounds(85, 49, 190, 20);
		add(lblEstimateChackOut);

		textField_hour = new JTextField();
		textField_hour.setBounds(230, 49, 87, 20);
		add(textField_hour);
		textField_hour.setColumns(10);

		JLabel lblParkingLot = new JLabel("Parking lot");
		lblParkingLot.setBounds(85, 82, 90, 20);
		add(lblParkingLot);

		comboBox = new JComboBox();
		comboBox.setBounds(172, 80, 109, 26);
		Set keys = parkingLots.keySet();
		for (Iterator i = keys.iterator(); i.hasNext();)
		{
			comboBox.addItem(i.next());
		}
		add(comboBox);
		
		JButton btnAddAnotherCar = new JButton("Add another car");
		btnAddAnotherCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigator.goToAddCarMenu(cst,parkingLots,2);
			}
		});
		btnAddAnotherCar.setBounds(152, 233, 148, 34);
		add(btnAddAnotherCar);
		
		
	}

}