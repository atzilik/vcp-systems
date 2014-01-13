package gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;

import DataObjects.Worker;
import javax.swing.JLabel;
/**
 * menu for ceo for choose parking lot reports
 * @author Omri
 *
 */
public class CEOChoosePLReport extends AbstractGUIComponent {
	/**
	 * parking lots map
	 */
	private Map<String,Integer> parkingLots;	
	private final JComboBox comboBoxParkLot;
	
	public CEOChoosePLReport(final IGUINavigator navigator,final Map<String,Integer> mp, final Worker worker) {
		setLayout(null);
		this.parkingLots=mp;
		comboBoxParkLot = new JComboBox();
		Set<String> keys = parkingLots.keySet();
		for (Iterator<String> i = keys.iterator(); i.hasNext();)
		{
			comboBoxParkLot.addItem(i.next());
		}
		comboBoxParkLot.setBounds(116, 6, 136, 20);
		add(comboBoxParkLot);
		comboBoxParkLot.setBounds(21, 62, 138, 20);
		add(comboBoxParkLot);
		
		final Worker wkr = new Worker();
			
		
		JButton btnreservationreport = new JButton("<html>Reservation<br />report</html> ");
		btnreservationreport.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				wkr.setParkingLotID(mp.get(comboBoxParkLot.getSelectedItem()));
				navigator.goToReservationDataMenuParkingLot(wkr,worker);
			}
		});
		btnreservationreport.setBounds(157, 94, 106, 45);
		add(btnreservationreport);

		JButton btncomplaintsreport = new JButton("<html>Complaints<br />report</html> ");
		btncomplaintsreport.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				wkr.setParkingLotID(mp.get(comboBoxParkLot.getSelectedItem()));
				navigator.goToComplaintReportMenu(wkr,worker);
				
			}
		});
		btncomplaintsreport.setBounds(38, 161, 106, 45);
		add(btncomplaintsreport);

		JButton btndisabledreport = new JButton("<html>Disabled<br />report</html> ");
		btndisabledreport.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				wkr.setParkingLotID(mp.get(comboBoxParkLot.getSelectedItem()));
				navigator.goToDisabledReport(wkr,worker);
			}
		});
		btndisabledreport.setBounds(157, 161, 106, 45);
		add(btndisabledreport);
		
		
		JLabel lblPleaseChooseA = new JLabel("Please choose a parking lot:");
		lblPleaseChooseA.setBounds(21, 23, 179, 20);
		add(lblPleaseChooseA);
		
		JButton btnGoBack = new JButton("Go Back");
		btnGoBack.setBounds(158, 249, 89, 23);
		btnGoBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					navigator.goToWorkerMenu(worker);
			}
		});
		add(btnGoBack);
	}
}
