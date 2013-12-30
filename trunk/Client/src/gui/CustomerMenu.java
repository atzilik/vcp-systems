package gui;

import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;

import client.Client;
import DataObjects.Customer;
import DataObjects.STDCustomer;
public class CustomerMenu extends AbstractGUIComponent {
	private Customer cst;	
	/**
	 * 
	 * @param cst 
	 */
	public CustomerMenu(Customer cst){
		super();
		setSize(new Dimension(600, 400));
		this.cst = cst;	

		if (cst instanceof STDCustomer)
		{
			openSTDCustomerMenu();
		}
		else
		{
			openMemberMenu();
		}
		
	}

	public void openSTDCustomerMenu(){
		JButton btnCheckRes = new JButton("Check reservation");
		btnCheckRes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openWindow("CheckReservationStatus");
			}
		});
		btnCheckRes.setBounds(98, 242, 147, 42);
		add(btnCheckRes);

		JButton btnIssueComplaint = new JButton("Issue complaint");
		btnIssueComplaint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openWindow("CustomerComplaintMenu");
			}
		});
		btnIssueComplaint.setBounds(326, 242, 135, 42);
		add(btnIssueComplaint);

		JButton btnReservation = new JButton("<html>Reserve<br />in advance</html>");
		btnReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openWindow("ReserveInAdvance");
			}
		});
		btnReservation.setBounds(227, 156, 119, 45);
		add(btnReservation);

		JLabel lblname = new JLabel(cst.getfName() + " " + cst.getlName());
		lblname.setBounds(221, 31, 215, 14);
		add(lblname);

		JButton btnMemberRegister = new JButton("Member register");
		btnMemberRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openWindow("MemberRegister");
			}
		});
		btnMemberRegister.setBounds(50, 156, 135, 42);
		add(btnMemberRegister);
		
		JButton btnReserveNow = new JButton("Reserve now");
		btnReserveNow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openWindow("ReserveNow");
			}
		});
		btnReserveNow.setBounds(399, 156, 119, 45);
		add(btnReserveNow);
		
		JButton btnCheckIn = new JButton("Check in");
		btnCheckIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openWindow("Check in");
			}
		});
		btnCheckIn.setBounds(126, 73, 119, 45);
		add(btnCheckIn);
		
		JButton btnCheckOut_1 = new JButton("Check out");
		btnCheckOut_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openWindow("Check out");
			}
		});
		btnCheckOut_1.setBounds(326, 73, 119, 45);
		add(btnCheckOut_1);
	}

	public void openMemberMenu(){
		JButton btnNewButton = new JButton("Check in");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openWindow("Check in");
			}
		});
		btnNewButton.setBounds(111, 77, 122, 61);
		add(btnNewButton);
		
		JButton btnCheckOut = new JButton("Check out");
		btnCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openWindow("Check out");
			}
		});
		btnCheckOut.setBounds(325, 77, 122, 61);
		add(btnCheckOut);
		
		JButton btnIssueComplaint_1 = new JButton("Issue complaint");
		btnIssueComplaint_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openWindow("CustomerComplaintMenu");
			}
		});
		btnIssueComplaint_1.setBounds(209, 171, 122, 61);
		add(btnIssueComplaint_1);
		
		setVisible(true);
	}


	public void openWindow(String str){
		
		if (str.equals("CheckReservationStatus"))
		{
			CheckReservationStatus crs = new CheckReservationStatus(this);
			crs.setVisible(true);
		}
		else if (str.equals("CustomerComplaintMenu"))
		{
			CustomerComplaintMenu ccm = new CustomerComplaintMenu(this);
			ccm.setVisible(true);
		}
		else if (str.equals("ReserveInAdvance") || str.equals("ReserveNow"))
		{
			List<Object> list = new ArrayList<Object>();
			list.add("Get Parking Lots");
			list.add(this);
			try {
				client.sendToServer(list);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (str.equals("MemberRegister"))
		{
			MainRegistrationMenu mrm = new MainRegistrationMenu(this);
			mrm.setVisible(true);
		}
		else if (str.equals("Check in"))
		{
			CheckInMenu cim = new CheckInMenu(this);
			cim.setVisible(true);
		}
		else if (str.equals("Check out"))
		{
			CheckOutMenu com = new CheckOutMenu(this);
			com.setVisible(true);
		}

	}

	public Customer getCst() {
		return cst;
	}

	public void setCst(Customer cst) {
		this.cst = cst;
	}
}
