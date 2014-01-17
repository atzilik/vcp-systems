package gui;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import DataObjects.Customer;
import DataObjects.STDCustomer;
import DataObjects.STDMember;
import Messages.*;


import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.swing.JComboBox;

/**
 * the class used for adding another car for member registration
 * @author Gal
 *
 */
public class AddCarMenu extends AbstractGUIComponent{
	private JTextField textFieldCarNum;
	/**
	 * Customer instance with the current customer details
	 */
	private Customer cst;
	private JTextField textField;
	/**
	 * Map is used to match parking lot name to its id.  The customer will know which parkinglot he's dealing with
	 */
	private Map<String,Integer> parkingLots;
	private JComboBox comboBox;
	/**
	 * 
	 * @param navigator to navigate between panels
	 * @param cst customer instance
	 * @param mp for the map as described above
	 * @param type used to distinguish if we are dealing with STDMember or FullMember registration
	 */
	public AddCarMenu(final IGUINavigator navigator, final Customer cst, Map<String,Integer> mp, final int type) {
		this.cst = cst;
		this.parkingLots = mp;
		setLayout(null);
		JLabel lblCarNum = new JLabel("Car Number:");
		lblCarNum.setBounds(53, 73, 90, 14);
		add(lblCarNum);
		
		textFieldCarNum = new JTextField();
		textFieldCarNum.setBounds(153, 70, 86, 20);
		add(textFieldCarNum);
		textFieldCarNum.setColumns(10);
		
		JButton btnApplay = new JButton("Submit");
		btnApplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch(type)
				{
				//adding car as STDMember
				case 2:{
					String[] arr = new String[10];
					if (cst instanceof STDCustomer)
					{
						if (((STDCustomer)cst).isRegisteredToMember())
							arr[0] = ((STDCustomer)cst).getMemberID();
						else
						{
							client.send(new MessageGenerateValidID(1));
							MessageGenerateValidIDReply cmir = (MessageGenerateValidIDReply)client.getMessage();
							arr[0] = cmir.getiD();
							((STDCustomer) cst).setMemberID(arr[0]);
							((STDCustomer) cst).setRegisteredToMember(true);
						}
					}
					else
					{
						arr[0] = ((STDMember)cst).getMemberId();
					}
					arr[1] = textFieldCarNum.getText();
					arr[2] = cst.getId();
					arr[3] = cst.getfName();
					arr[4] = cst.getlName();
					arr[5] = cst.getEmail();
					arr[6] = new java.sql.Date(new java.util.Date().getTime()).toString();
					arr[7] = Integer.toString(parkingLots.get(comboBox.getSelectedItem()));
					arr[8] = textField.getText();
					arr[9] = "2";
					client.send(new MessageMemberRegister(arr,arr[9]));
					MessageMemberRegisterReply mrr = (MessageMemberRegisterReply)client.getMessage();
					mrr.doAction();
					navigator.goToMemberRegister(cst);
					break;
				}
				//adding car as FullMember
				case 3:{
					String[] arr = new String[8];
					arr[1] = textFieldCarNum.getText();
					arr[2] = cst.getId();
					arr[3] = cst.getfName();
					arr[4] = cst.getlName();
					arr[5] = cst.getEmail();
					arr[6] = new java.sql.Date(new java.util.Date().getTime()).toString();
					arr[7] = "3";
					if (cst instanceof STDCustomer)
					{
						if (((STDCustomer)cst).isRegisteredToMember())
							arr[0] = ((STDCustomer)cst).getMemberID();
						else
						{
							client.send(new MessageGenerateValidID(1));
							MessageGenerateValidIDReply cmir = (MessageGenerateValidIDReply)client.getMessage();
							arr[0] = cmir.getiD();
							((STDCustomer) cst).setMemberID(arr[0]);
							((STDCustomer) cst).setRegisteredToMember(true);
							client.send(new MessageMemberRegister(arr, arr[7]));
							MessageMemberRegisterReply fmrr = (MessageMemberRegisterReply)client.getMessage();
							fmrr.doAction();
							navigator.goToMemberRegister(cst);
						}
					}
					else
					{
						arr[0] = ((STDMember)cst).getMemberId();
					}
					client.send(new MessageSTDToFullRegister(arr, arr[7]));
					MessageSTDToFullRegisterReply stfrr= (MessageSTDToFullRegisterReply)client.getMessage();
					stfrr.doAction();
					navigator.goToFullMemberRegisteration(cst);
					break;
				}
				}
				
			}
		});
		btnApplay.setBounds(90, 200, 86, 44);
		add(btnApplay);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigator.goBack();
			}
		});
		btnCancel.setBounds(214, 200, 86, 44);
		add(btnCancel);
		
		if (type == 2)
		{
			JLabel label = new JLabel("Parking lot");
			label.setBounds(53, 148, 90, 20);
			add(label);
			
			comboBox = new JComboBox();
			comboBox.setBounds(140, 146, 109, 26);
			Set keys = parkingLots.keySet();
			for (Iterator i = keys.iterator(); i.hasNext();)
			{
				comboBox.addItem(i.next());
			}
			add(comboBox);
			
			JLabel label_1 = new JLabel("Estimate check out hour");
			label_1.setBounds(53, 115, 190, 20);
			add(label_1);
			
			textField = new JTextField();
			textField.setColumns(10);
			textField.setBounds(198, 115, 87, 20);
			add(textField);
		}
		
	}

}