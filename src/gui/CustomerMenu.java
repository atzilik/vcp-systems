package gui;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import logic.Customer;
import logic.STDCustomer;
import logic.STDMember;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class CustomerMenu extends JFrame{
	private Customer cst;
	/**
	 * 
	 * @param cst 
	 */
	public CustomerMenu(Customer cst){
		this.cst = cst;
		setTitle("Main Menu");
		setSize(new Dimension(587, 364));
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		
		
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
		getContentPane().add(btnCheckRes);

		JButton btnIssueComplaint = new JButton("Issue complaint");
		btnIssueComplaint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openWindow("CustomerComplaintMenu");
			}
		});
		btnIssueComplaint.setBounds(326, 242, 135, 42);
		getContentPane().add(btnIssueComplaint);

		JButton btnReservation = new JButton("<html>Reserve<br />in advance</html>");
		btnReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openWindow("ReserveInAdvance");
			}
		});
		btnReservation.setBounds(227, 156, 119, 45);
		getContentPane().add(btnReservation);

		JLabel lblname = new JLabel(cst.getfName() + " " + cst.getlName());
		lblname.setBounds(221, 31, 215, 14);
		getContentPane().add(lblname);

		JButton btnMemberRegister = new JButton("Member register");
		btnMemberRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openWindow("MemberRegister");
			}
		});
		btnMemberRegister.setBounds(50, 156, 135, 42);
		getContentPane().add(btnMemberRegister);
		
		JButton btnReserveNow = new JButton("Reserve now");
		btnReserveNow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openWindow("ReserveNow");
			}
		});
		btnReserveNow.setBounds(399, 156, 119, 45);
		getContentPane().add(btnReserveNow);
		
		JButton btnCheckIn = new JButton("Check in");
		btnCheckIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openWindow("Check in");
			}
		});
		btnCheckIn.setBounds(126, 73, 119, 45);
		getContentPane().add(btnCheckIn);
		
		JButton btnCheckOut_1 = new JButton("Check out");
		btnCheckOut_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openWindow("Check out");
			}
		});
		btnCheckOut_1.setBounds(326, 73, 119, 45);
		getContentPane().add(btnCheckOut_1);
		
		setVisible(true);

	}

	public void openMemberMenu(){
		JButton btnNewButton = new JButton("Check in");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openWindow("Check in");
			}
		});
		btnNewButton.setBounds(111, 77, 122, 61);
		getContentPane().add(btnNewButton);
		
		JButton btnCheckOut = new JButton("Check out");
		btnCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openWindow("Check out");
			}
		});
		btnCheckOut.setBounds(325, 77, 122, 61);
		getContentPane().add(btnCheckOut);
		
		JButton btnIssueComplaint_1 = new JButton("Issue complaint");
		btnIssueComplaint_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openWindow("CustomerComplaintMenu");
			}
		});
		btnIssueComplaint_1.setBounds(209, 171, 122, 61);
		getContentPane().add(btnIssueComplaint_1);
		
		setVisible(true);
	}


	public void openWindow(String str){
		setVisible(false);
		
		if (str.equals("CheckReservationStatus"))
		{
			CheckReservationStatus crs = new CheckReservationStatus(this);
		}
		else if (str.equals("CustomerComplaintMenu"))
		{
			CustomerComplaintMenu ccm = new CustomerComplaintMenu(this);
		}
		else if (str.equals("ReserveInAdvance") || str.equals("ReserveNow"))
		{
			ReservationMenu rm = new ReservationMenu(this);
		}
		else if (str.equals("MemberRegister"))
		{
			MainRegistrationMenu mrm = new MainRegistrationMenu(this);
		}
		else if (str.equals("Check in"))
		{
			CheckInMenu cim = new CheckInMenu(this);
		}
		else if (str.equals("Check out"))
		{
			CheckOutMenu com = new CheckOutMenu(this);
		}

	}

	public Customer getCst() {
		return cst;
	}

	public void setCst(Customer cst) {
		this.cst = cst;
	}
}
