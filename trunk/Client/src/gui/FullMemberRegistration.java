package gui;


import javax.swing.JLabel;
import javax.swing.JButton;

import DataObjects.Customer;
import DataObjects.STDCustomer;
import DataObjects.STDMember;
import Messages.MessageMemberRegister;
import Messages.MessageMemberRegisterReply;
import Messages.MessageCheckMemberID;
import Messages.MessageCheckMemberIDReply;
import Messages.MessageSTDToFullRegister;
import Messages.MessageSTDToFullRegisterReply;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;

public class FullMemberRegistration extends AbstractGUIComponent {
	protected Customer cst;
	public FullMemberRegistration(final IGUINavigator navigator, final Customer cst) {
		this.cst = cst;
		setLayout(null);

		JLabel lblStartDate = new JLabel("Upgrade to full Member?");
		lblStartDate.setBounds(96, 69, 156, 34);
		add(lblStartDate);


		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cst instanceof STDCustomer)
					navigator.goToMemberRegister(cst);
				else
					navigator.goToCustomerMenu(cst);
			}
		});
		btnCancel.setBounds(289, 233, 86, 34);
		add(btnCancel);
		JButton btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] arr = new String[8];
				arr[1] = cst.getCarId();
				arr[2] = cst.getId();
				arr[3] = cst.getfName();
				arr[4] = cst.getlName();
				arr[5] = cst.getEmail();
				arr[6] = new java.sql.Date(new java.util.Date().getTime()).toString();
				arr[7] = "3";
				if (cst instanceof STDCustomer)
				{
					client.send(new MessageCheckMemberID());
					MessageCheckMemberIDReply cmir = (MessageCheckMemberIDReply)client.getMessage();
					arr[0] = cmir.getMemberID();
					((STDCustomer) cst).setMemberID(arr[0]);
					((STDCustomer) cst).setRegisteredToMember(true);
					
					client.send(new MessageMemberRegister(arr,arr[7]));
					MessageMemberRegisterReply fmrr = (MessageMemberRegisterReply)client.getMessage();
					fmrr.doAction();
				}
				else //STDMember
				{
					arr[0] = ((STDMember) cst).getMemberId();
					client.send(new MessageSTDToFullRegister(arr, "3"));
					MessageSTDToFullRegisterReply stfrr= (MessageSTDToFullRegisterReply)client.getMessage();
					stfrr.doAction();
					
				}
				
				
			}
		});
		btnOK.setBounds(44, 233, 86, 34);
		add(btnOK);

		JButton btnAddAnotherCar = new JButton("Add another car");
		btnAddAnotherCar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigator.goToAddCarMenu(cst,null,3);
			}
		});
		btnAddAnotherCar.setBounds(140, 233, 139, 34);
		add(btnAddAnotherCar);


	}

}
