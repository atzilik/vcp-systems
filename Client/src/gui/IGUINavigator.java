package gui;

import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import DataObjects.Customer;
import DataObjects.Worker;

public interface IGUINavigator {
	
	public void goToCustomerLogin();
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
	public void goBack();

}
