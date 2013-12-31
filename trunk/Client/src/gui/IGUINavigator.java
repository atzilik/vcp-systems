package gui;

import java.util.Map;

import javax.swing.JPanel;

import DataObjects.Customer;

public interface IGUINavigator {
	
	public void goToCustomerMenu(Customer customer);
	public void goToCancelReservation(Customer customer);
	public void goToIssueComplaint(Customer customer);
	public void goToReservation(Customer customer, Map<String,Integer> mp);
	public void goToMemberRegister(Customer customer);
	public void goToCheckIn(Customer customer);
	public void goToCheckOut(Customer customer);
	public void goToFullMemberRegisteration(Customer customer);
	public void goToSTDMemberRegisteration(Customer customer);
	public void goToGuiTestclass();
	public void goToTestclass2();
	public void goToTestclass3();
	public void goBack();

}
