package gui;

import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import DataObjects.Customer;
import DataObjects.Worker;

public interface IGUINavigator {
	
	public void goToLoginMenu();
	public void goToCustomerLogin();
	public void goToWorkerLogin();
	public void goToCustomerMenu(Customer customer);
	public void goToWorkerMenu(Worker worker);
	public void goToCancelReservation(Customer customer);
	public void goToIssueComplaint(Customer customer, Map<String,Integer> mp);
	public void goToReservation(Customer customer, Map<String,Integer> mp,int type);
	public void goToMemberRegister(Customer customer);
	public void goToCheckIn(Customer customer, Map<String,Integer> mp);
	public void goToCheckOut(Customer customer, Map<String,Integer> mp);
	public void goToFullMemberRegisteration(Customer customer);
	public void goToAddCarMenu(Customer customer, Map<String,Integer> mp, int type);
	public void goToSTDMemberRegisteration(Customer customer, Map<String,Integer> mp);
	public void goToReserveParkingSpace(Map<String,Integer> mp, Worker worker);
	public void goToReportDisabledParkingSpace(Worker worker);
	public void goToWorkerSetupMenu(int parkingLotID, Worker worker);
	public void goToInsertAlternativeParkingLot(Map<String,Integer> mp, Worker worker);
	public void goToComplaintMenu(Worker wkr);
	public void goToChangeRatesReq(Worker wkr);
	public void goToChangeRates(Worker wkr);
	public void goToEnableDisableFacility(Worker wkr);
	public void goToWorkerDataMenu(Worker wkr);
	public void goToreservationsDataMenu(Worker wkr);
	public void goToSnapShotReport(Map<String,Integer> mp);
	public void goToComplaintReportMenu(Worker wkr);
	public void goToCEOChoosePLReport(Map<String,Integer> mp, Worker worker);
	public void goToReservationDataMenuParkingLot(Worker wkr);
	public void goToCheckReservation(String id);
	public void goBack();

}
