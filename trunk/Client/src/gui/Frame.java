package gui;

import java.awt.Dimension;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import DataObjects.Customer;

import client.Client;

public class Frame extends JFrame implements IGUINavigator {	
	
	private AddCarMenu addCarMenu;
	private ChangeRateMenu changeRateMenu;
	private CheckInMenu checkInMenu;
	private CheckOutMenu checkoutMenu;
	private CancelReservation checkReservationStatus;
	private ClientGui clientGui;
	private ComplaintMenu complaintMenu;
	private CustomerComplaintMenu customerComplaintMenu;
	private CustomerMenu customerMenu;
	private WorkerMenu workerMenu;
	private FullMemberRegistration fullMemberRegistration;
	private InsertAlternativeParkingLot insertAlternativeParkinglot;
	private KioskReservationMenu kioskReservationMenu;
	private Login login;
	private MemberRegistration mainRegistrationMenu;
	private RateRequests rateRequests;
	private ReportDisabledParkingSpace reportDisabledParkingSpace;
	private ReservationMenu reservationMenu;
	private ReserveParkingSpace reserveParkingSpace;
	private STDMemberRegistration standardMemberRegistration;
	private JPanel lastScreen;
	
		
	
	public Frame(){
		login = new Login(this);
		setContentPane(login);	
		setSize(new Dimension(640, 480));
		setResizable(false);
		setVisible(true);		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		
	}

	@Override
	public void goToCustomerMenu(Customer customer) {
		// TODO Auto-generated method stub
		lastScreen = (JPanel) getContentPane();
		CustomerMenu cm = new CustomerMenu(this, customer);
		setContentPane(cm);		
		validate();
	}


	@Override
	public void goToTestclass2() {
			lastScreen = (JPanel) getContentPane();
			Testclass2 tc2 = new Testclass2(this);
			setContentPane(tc2);		
			validate();
	}
	
	@Override
	public void goToTestclass3() {
		lastScreen = (JPanel) getContentPane();
		Testclass3 tc3 = new Testclass3(this);
		setContentPane(tc3);		
		validate();		
	}
	
	@Override
	public void goToGuiTestclass() {
		lastScreen = (JPanel) getContentPane();
		GuiTestClass guiTC = new GuiTestClass(this);
		setContentPane(guiTC);		
		validate();				
	}

	@Override
	public void goBack() {
		JPanel temp = (JPanel) getContentPane();
		setContentPane(lastScreen);
		lastScreen = temp;		
	}
	
	
	
	public static void main(String args[]) {
				
		@SuppressWarnings("unused")
		Frame mainFrame = new Frame();
		
	}

	@Override
	public void goToCancelReservation(Customer customer) {
		// TODO Auto-generated method stub
		lastScreen = (JPanel) getContentPane();
		CancelReservation cr = new CancelReservation(this, customer);
		setContentPane(cr);		
		validate();
	}

	@Override
	public void goToIssueComplaint(Customer customer) {
		// TODO Auto-generated method stub
		lastScreen = (JPanel) getContentPane();
		CustomerComplaintMenu ccm = new CustomerComplaintMenu(this, customer);
		setContentPane(ccm);		
		validate();
	}

	@Override
	public void goToMemberRegister(Customer customer) {
		// TODO Auto-generated method stub
		lastScreen = (JPanel) getContentPane();
		MemberRegistration mr = new MemberRegistration(this, customer);
		setContentPane(mr);		
		validate();
	}

	@Override
	public void goToReservation(Customer customer, Map<String,Integer> mp) {
		// TODO Auto-generated method stub
		lastScreen = (JPanel) getContentPane();
		ReservationMenu rm = new ReservationMenu(this, customer, mp);
		setContentPane(rm);		
		validate();
	}

	@Override
	public void goToCheckIn(Customer customer) {
		// TODO Auto-generated method stub
		lastScreen = (JPanel) getContentPane();
		CheckInMenu cim = new CheckInMenu(this, customer);
		setContentPane(cim);		
		validate();
	}

	@Override
	public void goToCheckOut(Customer customer) {
		// TODO Auto-generated method stub
		lastScreen = (JPanel) getContentPane();
		CheckOutMenu com = new CheckOutMenu(this, customer);
		setContentPane(com);		
		validate();
	}

	@Override
	public void goToFullMemberRegisteration(Customer customer) {
		// TODO Auto-generated method stub
		lastScreen = (JPanel) getContentPane();
		FullMemberRegistration fmr = new FullMemberRegistration(this, customer);
		setContentPane(fmr);		
		validate();
	}

	@Override
	public void goToSTDMemberRegisteration(Customer customer) {
		// TODO Auto-generated method stub
		lastScreen = (JPanel) getContentPane();
		STDMemberRegistration smr = new STDMemberRegistration(this, customer);
		setContentPane(smr);		
		validate();
	}
	

	
}
