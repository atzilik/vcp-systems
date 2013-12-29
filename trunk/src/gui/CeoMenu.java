package gui;
import javax.swing.JButton;

public class CeoMenu extends Frame {

	public CeoMenu() {
		// TODO Auto-generated constructor stub
		super();
		
		JButton btnNewButton = new JButton("<html>Activity<br />report</html>");
		btnNewButton.setBounds(23, 71, 102, 49);
		getContentPane().add(btnNewButton);
		
		JButton button = new JButton("<html>Snapshot<br />report</html>");
		button.setBounds(23, 11, 102, 49);
		getContentPane().add(button);
		
		JButton btnexceptionsreport = new JButton("<html>Exceptions<br />report</html>");
		btnexceptionsreport.setBounds(23, 141, 102, 49);
		getContentPane().add(btnexceptionsreport);
		
		JButton btnperformancereport = new JButton("<html>Performance<br />report</html>");
		btnperformancereport.setBounds(23, 201, 102, 49);
		getContentPane().add(btnperformancereport);
		
		JButton btnreservationdata = new JButton("<html>Reservation<br />data</html>");
		btnreservationdata.setBounds(205, 201, 102, 49);
		getContentPane().add(btnreservationdata);
		
		JButton btnchangeRatesrequests = new JButton("<html>Change Rates<br />requests</html>");
		btnchangeRatesrequests.setBounds(220, 11, 102, 49);
		getContentPane().add(btnchangeRatesrequests);
		
		JButton btnworkersdata = new JButton("<html>Workers<br />data</html>");
		btnworkersdata.setBounds(205, 147, 102, 49);
		getContentPane().add(btnworkersdata);
	}

}
