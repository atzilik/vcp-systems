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
	public void goToIssueComplaint(Customer customer);
	public void goToReservation(Customer customer, Map<String,Integer> mp,int type);
	public void goToMemberRegister(Customer customer);
	public void goToCheckIn(Customer customer, Map<String,Integer> mp);
	public void goToCheckOut(Customer customer, Map<String,Integer> mp);
	public void goToFullMemberRegisteration(Customer customer);
	public void goToAddCarMenu(Customer customer, Map<String,Integer> mp, int type);
	public void goToSTDMemberRegisteration(Customer customer, Map<String,Integer> mp);
	public void goToReserveParkingSpace();
	public void goToReportDisabledParkingSpace(Worker worker);
	public void goToWorkerSetupMenu(int parkingLotID);
	public void goToInsertAlternativeParkingLot(Map<String,Integer> mp, Worker worker);
	public void goToComplaintMenu();
	public void goToChangeRatesReq(Worker wkr);
	public void goToChangeRates(Worker wkr);
	public void goBack();

}
