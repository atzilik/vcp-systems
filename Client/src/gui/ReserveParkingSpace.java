package gui;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import DataObjects.CustomerService;
import DataObjects.ParkingLot;
import DataObjects.ParkingSpace;
import DataObjects.Robot;
import DataObjects.Worker;
import Messages.MessageFullPLStatus;
import Messages.MessageReservePSpace;
import Messages.MessageReservePSpaceReply;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JComboBox;
/**
 * 
 * @author Alon
 *This class is responsible for reserving a parking space either as Customer Service and worker.
 */
public class ReserveParkingSpace extends AbstractGUIComponent {
	/**
	 * Map is used to match parking lot name to its id.  The worker will know which parkinglot he's dealing with
	 */
	private Map<String,Integer> parkingLots;	
	private JTextField textFieldCarID;
	private int parkingLotID;
	/**
	 * 
	 * @param navigator to navigate between panels
	 * @param mp for the map as described above
	 * @param worker could be a customer service or a regular worker
	 */
	public ReserveParkingSpace(final IGUINavigator navigator, Map<String,Integer> mp, final Worker worker) {
		setLayout(null);
		this.parkingLots=mp;
		//final  comboBoxParkLot = null;
		final JComboBox comboBoxParkLot = new JComboBox();
		if (worker instanceof CustomerService)
		{
			Set<String> keys = parkingLots.keySet();
			for (Iterator<String> i = keys.iterator(); i.hasNext();)
			{
				comboBoxParkLot.addItem(i.next());
			}
			comboBoxParkLot.setBounds(150, 6, 123, 20);
			add(comboBoxParkLot);
			//parkingLotIDcs=parkingLots.get(comboBoxParkLot.getSelectedItem());
		}
		if (!(worker instanceof CustomerService)){
			JLabel lblParkingLot = new JLabel("Parking Lot:" +parkinglots[worker.getParkingLotID()].getName());
			lblParkingLot.setBounds(68, 9, 161, 14);
			add(lblParkingLot);
			parkingLotID= worker.getParkingLotID() ;
		}
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String carNum=textFieldCarID.getText();
				if (parkinglots[parkingLots.get(comboBoxParkLot.getSelectedItem())].isActive()==false){
					JOptionPane.showMessageDialog(null, "Parking Lot is not active!", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				ParkingSpace ps = search_legal_space();
				if (ps==null){ //search for a legal parking space... if null then redirect customer to alt parking sapce
					JOptionPane.showMessageDialog(null, "Parking Lot is Full! Customers are redirected to alt parking lot", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				client.send(new MessageReservePSpace(parkingLotID,ps.getFloor(),ps.getRow(),ps.getDepth(),carNum));
				MessageReservePSpaceReply mrps = (MessageReservePSpaceReply)client.getMessage();
				mrps.doAction();
				parkinglots[parkingLotID].getRobot().setFreespace(parkinglots[parkingLotID].getRobot().getFreespace()- 1);
				if (parkinglots[parkingLotID].getRobot().getFreespace()==0){
					parkinglots[parkingLotID].getRobot().setParkingLotFull(true);
					client.send(new MessageFullPLStatus(parkingLotID,true));
					JOptionPane.showMessageDialog(null, "Parking Lot is Full! Customers are redirected to alt parking lot", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		btnSubmit.setBounds(134, 220, 80, 23);
		add(btnSubmit);
		textFieldCarID = new JTextField();
		textFieldCarID.setBounds(102, 89, 96, 20);
		add(textFieldCarID);
		textFieldCarID.setColumns(10);

		JLabel lblCarid = new JLabel("CarID:");
		lblCarid.setBounds(25, 92, 80, 14);
		add(lblCarid);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigator.goBack();
			}
		});
		btnCancel.setBounds(235, 220, 89, 23);
		add(btnCancel);
	}
	/**
	 * Search for a legal space to reserve a parkingspace
	 * @return the specific parkingspace
	 */
	public ParkingSpace search_legal_space(){
		for (int i=ParkingLot.FLOORS_SIZE-1;i>=0;i--)
			for (int j=ParkingLot.ROWS_SIZE-1;j>=0;j--)
				for (int k=parkinglots[parkingLotID].getDepthSize()-1;k>=0;k--){
					if (parkinglots[parkingLotID].getParkingspace()[i][j][k].isAvailable()){
						parkinglots[parkingLotID].getParkingspace()[i][j][k].setReserved(true);
						return parkinglots[parkingLotID].getParkingspace()[i][j][k];
					}
				}
		JOptionPane.showMessageDialog(null, "Unable to reserve a parkingspace");
		return null;
	}
}


