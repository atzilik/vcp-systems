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
	private ComplaintMenu complaintMenu;
	private CustomerComplaintMenu customerComplaintMenu;
	private CustomerMenu customerMenu;
	private WorkerMenu workerMenu;
	private FullMemberRegistration fullMemberRegistration;
	private InsertAlternativeParkingLot insertAlternativeParkinglot;
	private CustomerLogin login;
	private MemberRegistration mainRegistrationMenu;
	private RateRequests rateRequests;
	private ReportDisabledParkingSpace reportDisabledParkingSpace;
	private ReservationMenu reservationMenu;
	private ReserveParkingSpace reserveParkingSpace;
	private STDMemberRegistration standardMemberRegistration;
	private JPanel lastScreen;
	private EnableDisableFacility enableDisableFacility; 
	private SnapShotReport snapShotReport;
	private CheckReservation checkReservation;
	
	public Frame(){
		//		login = new Login(this);
		//		setContentPane(login);	
		setSize(new Dimension(1366, 768));
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
	public void goToCheckReservation(Customer cst) {
		// TODO Auto-generated method stub
		lastScreen = (JPanel) getContentPane();
		CheckReservation cr = new CheckReservation(this, cst.getId());
		setContentPane(cr);		
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
	public void goToIssueComplaint(Customer customer, Map<String,Integer> mp) {
		// TODO Auto-generated method stub
		lastScreen = (JPanel) getContentPane();
		CustomerComplaintMenu ccm = new CustomerComplaintMenu(this, customer, mp);
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
	public void goToReservation(Customer customer, Map<String,Integer> mp, int type) {
		// TODO Auto-generated method stub
		lastScreen = (JPanel) getContentPane();
		ReservationMenu rm = new ReservationMenu(this, customer, mp, type);
		setContentPane(rm);		
		validate();
	}

	@Override
	public void goToCheckIn(Customer customer, Map<String,Integer> mp) {
		// TODO Auto-generated method stub
		lastScreen = (JPanel) getContentPane();
		CheckInMenu cim = new CheckInMenu(this, customer, mp);
		setContentPane(cim);		
		validate();
	}

	@Override
	public void goToCheckOut(Customer customer, Map<String,Integer> mp) {
		// TODO Auto-generated method stub
		lastScreen = (JPanel) getContentPane();
		CheckOutMenu com = new CheckOutMenu(this, customer, mp);
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
	public void goToWorkerLogin(){
		lastScreen = (JPanel) getContentPane();
		WorkerLogin wl = new WorkerLogin(this);
		setContentPane(wl);	
		setTitle("Worker Login");
		validate();
	}
	public void goToReserveParkingSpace(Map<String,Integer> mp, Worker wkr){
		// TODO Auto-generated method stub
		lastScreen = (JPanel) getContentPane();
		ReserveParkingSpace rps = new ReserveParkingSpace(this,mp,wkr);
		setContentPane(rps);
		validate();
	}
	public void goToReportDisabledParkingSpace(Worker wkr){
		// TODO Auto-generated method stub
		lastScreen = (JPanel) getContentPane();
		ReportDisabledParkingSpace rdps = new ReportDisabledParkingSpace(this,wkr);
		setContentPane(rdps);
		validate();
	}
	public void goToInsertAlternativeParkingLot(Map<String,Integer> mp, Worker wkr){
		// TODO Auto-generated method stub
		lastScreen = (JPanel) getContentPane();
		InsertAlternativeParkingLot inpl = new InsertAlternativeParkingLot(this,mp,wkr);
		setContentPane(inpl);
		validate();
	}
	public void goToComplaintMenu(Worker wkr){
		// TODO Auto-generated method stub
		lastScreen = (JPanel) getContentPane();
		ComplaintMenu cm = new ComplaintMenu(this, wkr);
		setContentPane(cm);
		validate();
	}
	public void goToChangeRatesReq(Worker wkr){
		// TODO Auto-generated method stub
		lastScreen = (JPanel) getContentPane();
		ChangeRateMenu crm = new ChangeRateMenu(this,wkr);
		setContentPane(crm);
		validate();	
	}
	public void goToChangeRates(Worker wkr){
		// TODO Auto-generated method stub
		lastScreen = (JPanel) getContentPane();
		RateRequests rr = new RateRequests(this,wkr);
		setContentPane(rr);
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

	@Override
	public void goToLoginMenu() {
		// TODO Auto-generated method stub
		lastScreen = (JPanel) getContentPane();
        LoginMenu lm = new LoginMenu(this);
        setContentPane(lm);             
        setTitle("Login");
        validate();
	}

	@Override
	public void goToWorkerSetupMenu(int parkingLotID, Worker worker) {
		// TODO Auto-generated method stub
		lastScreen = (JPanel) getContentPane();
        WorkerSetupMenu wsm = new WorkerSetupMenu(this, parkingLotID, worker);
        setContentPane(wsm);             
        setTitle("Login");
        validate();
	}
	@Override
	public void goToEnableDisableFacility (Worker worker) {
		// TODO Auto-generated method stub
		lastScreen = (JPanel) getContentPane();
        EnableDisableFacility edf = new  EnableDisableFacility(this, worker);
        setContentPane(edf);             
        setTitle("Enable/Disable Facility");
        validate();
	}
	@Override
	public void goToWorkerDataMenu(Worker wkr) {
		// TODO Auto-generated method stub
		lastScreen = (JPanel) getContentPane();
		WorkersDataMenu wdm = new WorkersDataMenu(this, wkr);
		setContentPane(wdm);
		setTitle("Wrokers Data");
		validate();
	}

	@Override
	public void goToreservationsDataMenu(Worker wkr) { 
		// TODO Auto-generated method stub
		lastScreen = (JPanel) getContentPane();
		ReservationDataMenu rdm = new ReservationDataMenu(this, wkr);
		setContentPane(rdm);
		setTitle("Reservations Data");
		validate();
	}
	@Override
	public void goToSnapShotReport(Map<String,Integer> mp){
		// TODO Auto-generated method stub
		lastScreen = (JPanel) getContentPane();
        SnapShotReport ssr = new SnapShotReport(this,mp);
        setContentPane(ssr);             
        setTitle("SnapShot Report");
        validate();	
	}

	@Override
	public void goToComplaintReportMenu(Worker wkr, Worker workerToBack) {
		// TODO Auto-generated method stub
		lastScreen = (JPanel) getContentPane();
		ComplaintReportMenu crm = new ComplaintReportMenu(this, wkr, workerToBack);
		setContentPane(crm);
		setTitle("Complaint Report Menu");
		validate();
	}

	@Override
	public void goToCEOChoosePLReport(Map<String,Integer> mp, Worker worker){
		lastScreen = (JPanel) getContentPane();
		CEOChoosePLReport plr = new CEOChoosePLReport(this, mp, worker);
		setContentPane(plr);
		setTitle("ParkingLots Reports");
		validate();
	}
	
	public void goToReservationDataMenuParkingLot(Worker wkr, Worker workerToBack) {
		// TODO Auto-generated method stub
		lastScreen = (JPanel) getContentPane();
		ReservationDataParkingLotManager rdplm = new ReservationDataParkingLotManager(this, wkr,  workerToBack);
		setContentPane(rdplm);
		setTitle("Reservation Data");
		validate();
	}
	@Override
	public void goToDisabledReport(Worker wkr, Worker workerToBack) {
		// TODO Auto-generated method stub
		lastScreen = (JPanel) getContentPane();
		DisabledParkingSpaceReport dpsr = new DisabledParkingSpaceReport(this, wkr, workerToBack);
		setContentPane(dpsr);
		setTitle("Disable Parking Space Report");
		validate();
	}

	@Override
	public void goToPerformenceReportMenu(Worker wkr) {
		// TODO Auto-generated method stub
		lastScreen = (JPanel) getContentPane();
		PerformenceReportMenu prm = new PerformenceReportMenu(this, wkr);
		setContentPane(prm);
		setTitle("Performence Report");
		validate();
		
	}
	@Override
	public void goToActivityReportMenu(Worker wkr) {
		// TODO Auto-generated method stub
		lastScreen = (JPanel) getContentPane();
		ActivityReportMenu arm = new ActivityReportMenu(this, wkr);
		setContentPane(arm);
		setTitle("Activity Report");
		validate();
	}
}

