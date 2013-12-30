package gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import client.Client;

public class Frame extends JFrame implements IGUINavigator {	
	
	private AddCarMenu addCarMenu;
	private ChangeRateMenu changeRateMenu;
	private CheckInMenu checkInMenu;
	private CheckOutMenu checkoutMenu;
	private CheckReservationStatus checkReservationStatus;
	private ClientGui clientGui;
	private ComplaintMenu complaintMenu;
	private CustomerComplaintMenu customerComplaintMenu;
	private CustomerMenu customerMenu;
	private WorkerMenu workerMenu;
	private FullMemberRegistration fullMemberRegistration;
	private InsertAlternativeParkingLot insertAlternativeParkinglot;
	private KioskReservationMenu kioskReservationMenu;
	private Login login;
	private MainRegistrationMenu mainRegistrationMenu;
	private RateRequests rateRequests;
	private ReportDisabledParkingSpace reportDisabledParkingSpace;
	private ReservationMenu reservationMenu;
	private ReserveParkingSpace reserveParkingSpace;
	private StandardMemberRegistration standardMemberRegistration;
	private JPanel lastScreen;
	
		
	
	public Frame(){
		
		login = new Login();
		
		setContentPane(login);
				
		setSize(new Dimension(400, 300));
		setResizable(false);
		setVisible(true);		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		
	}

	@Override
	public void goToMainScreen() {
		// TODO Auto-generated method stub
		
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
	

	
}
