package gui;

import java.awt.Dimension;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;

import DataObjects.Customer;
import DataObjects.Worker;

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
	private CustomerLogin login;
	private MemberRegistration mainRegistrationMenu;
	private RateRequests rateRequests;
	private ReportDisabledParkingSpace reportDisabledParkingSpace;
	private ReservationMenu reservationMenu;
	private ReserveParkingSpace reserveParkingSpace;
	private STDMemberRegistration standardMemberRegistration;
	private JPanel lastScreen;
	
		
	
	public Frame(){
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
		setTitle("Customer Menu");
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
		setTitle("Cancel Reservation");
		validate();
	}

	@Override
	public void goToIssueComplaint(Customer customer) {
		// TODO Auto-generated method stub
		lastScreen = (JPanel) getContentPane();
		CustomerComplaintMenu ccm = new CustomerComplaintMenu(this, customer);
		setContentPane(ccm);
		setTitle("Issue Complaint");
		validate();
	}

	@Override
	public void goToMemberRegister(Customer customer) {
		// TODO Auto-generated method stub
		lastScreen = (JPanel) getContentPane();
		MemberRegistration mr = new MemberRegistration(this, customer);
		setContentPane(mr);		
		setTitle("Member Register");
		validate();
	}

	@Override
	public void goToReservation(Customer customer, Map<String,Integer> mp,int type) {
		// TODO Auto-generated method stub
		lastScreen = (JPanel) getContentPane();
		ReservationMenu rm = new ReservationMenu(this, customer, mp, type);
		setContentPane(rm);	
		setTitle("Reservation");
		validate();
	}

	@Override
	public void goToCheckIn(Customer customer, Map<String,Integer> mp) {
		// TODO Auto-generated method stub
		lastScreen = (JPanel) getContentPane();
		CheckInMenu cim = new CheckInMenu(this, customer, mp);
		setContentPane(cim);	
		setTitle("Check In");
		validate();
	}

	@Override
	public void goToCheckOut(Customer customer, Map<String,Integer> mp) {
		// TODO Auto-generated method stub
		lastScreen = (JPanel) getContentPane();
		CheckOutMenu com = new CheckOutMenu(this, customer, mp);
		setContentPane(com);	
		setTitle("Check Out");
		validate();
	}

	@Override
	public void goToFullMemberRegisteration(Customer customer) {
		// TODO Auto-generated method stub
		lastScreen = (JPanel) getContentPane();
		FullMemberRegistration fmr = new FullMemberRegistration(this, customer);
		setContentPane(fmr);
		setTitle("Full Member Registration");
		validate();
	}

	@Override
	public void goToSTDMemberRegisteration(Customer customer, Map<String,Integer> mp) {
		// TODO Auto-generated method stub
		lastScreen = (JPanel) getContentPane();
		STDMemberRegistration smr = new STDMemberRegistration(this, customer, mp);
		setContentPane(smr);
		setTitle("Standard Member Registration");
		validate();
	}

	@Override
	public void goToWorkerMenu(Worker worker) {
		// TODO Auto-generated method stub
		lastScreen = (JPanel) getContentPane();
		WorkerMenu wm = new WorkerMenu(this, worker);
		setContentPane(wm);		
		setTitle("Worker Menu");
		validate();
	}

	@Override
	public void goToAddCarMenu(Customer customer, Map<String,Integer> mp, int type) {
		// TODO Auto-generated method stub
		lastScreen = (JPanel) getContentPane();
		AddCarMenu wm = new AddCarMenu(this, customer, mp, type);
		setContentPane(wm);		
		setTitle("Add Car");
		validate();
	}

	@Override
	public void goToCustomerLogin() {
		// TODO Auto-generated method stub
		lastScreen = (JPanel) getContentPane();
		CustomerLogin wm = new CustomerLogin(this);
		setContentPane(wm);		
		setTitle("Login");
		validate();
	}


	

	
}
