package gui;
import javax.swing.JButton;

public class CsMenu extends Frame {

	public CsMenu() {
		// TODO Auto-generated constructor stub
		super();
		
		JButton btnNewButton = new JButton("<html>Reserve<br />parking space</html>");
		btnNewButton.setBounds(46, 90, 117, 52);
		getContentPane().add(btnNewButton);
		
		JButton btncomlaintmenu = new JButton("<html>Comlaint<br />menu</html>");
		btncomlaintmenu.setBounds(112, 161, 117, 52);
		getContentPane().add(btncomlaintmenu);
		
		JButton btnreserveLocalparkingSpace = new JButton("<html>Reserve local<br />parking space</html>");
		btnreserveLocalparkingSpace.setBounds(173, 90, 117, 52);
		getContentPane().add(btnreserveLocalparkingSpace);
	}

}
