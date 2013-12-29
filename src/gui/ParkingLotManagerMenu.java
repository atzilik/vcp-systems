package gui;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ParkingLotManagerMenu extends Frame {

	public ParkingLotManagerMenu() {
		// TODO Auto-generated constructor stub
		super();
		
		JButton btnChangeRates = new JButton("<html>Open Change<br />Rates Menu</html> ");
		btnChangeRates.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ChangeRateMenu crm = new ChangeRateMenu();
				setVisible(false);
				crm.setVisible(true);
			}
		});
		btnChangeRates.setBounds(38, 94, 106, 45);
		getContentPane().add(btnChangeRates);
		
		JButton btnreservationreport = new JButton("<html>Reservation<br />report</html> ");
		btnreservationreport.setBounds(157, 94, 106, 45);
		getContentPane().add(btnreservationreport);
		
		JButton btncomplaintsreport = new JButton("<html>Complaints<br />report</html> ");
		btncomplaintsreport.setBounds(38, 161, 106, 45);
		getContentPane().add(btncomplaintsreport);
		
		JButton btndisabledreport = new JButton("<html>Disabled<br />report</html> ");
		btndisabledreport.setBounds(157, 161, 106, 45);
		getContentPane().add(btndisabledreport);
	}
}
