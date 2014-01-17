package gui;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import DataObjects.Customer;
import Messages.MessageCheckout;
import Messages.MessageCheckoutReply;
import Messages.MessageEmptyReply;
import Messages.MessageUpdatePLMap;
import Messages.MessageFullPLStatus;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JComboBox;

/**
 * used for customer check out from the parking lot
 * @author Gal
 *
 */
public class CheckOutMenu extends AbstractGUIComponent {
	/**
	 * cst Customer instance with the current customer details
	 */
	private Customer cst;
	/**
	 * Map is used to match parking lot name to its id.  
	 */
	private Map<String,Integer> parkingLots;
	JComboBox comboBox;
	/**
	 * 
	 * @param navigator to navigate between panels
	 * @param cst customer instance
	 * @param mp for the map as described above
	 */
	public CheckOutMenu(final IGUINavigator navigator, final Customer cst, Map<String,Integer> mp) {
		this.cst = cst;
		this.parkingLots = mp;
		setLayout(null);
		
		JButton btnSubmit = new JButton("OK");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client.send(new MessageCheckout(cst, parkingLots.get(comboBox.getSelectedItem())));
				final MessageCheckoutReply cor = (MessageCheckoutReply)client.getMessage();
				cor.doAction();
				navigator.goBack();
				if (cor.isEmpty() == false)
				{
					//call robot to unpark car
					new Thread(new Runnable() {

						@Override
						public void run() {
							// TODO Auto-generated method stub
							boolean needToUpdate = false;;
							if (parkinglots[cor.getParkingLotID()].getRobot().isParkingLotFull())
								needToUpdate = true;
							parkinglots[cor.getParkingLotID()].getRobot().unPark(Integer.parseInt(cor.getCustomer().getCarId()), cor.getFloor(), cor.getRow(), cor.getDepth());
							client.send(new MessageUpdatePLMap(cor.getParkingLotID(),parkinglots[cor.getParkingLotID()].getParkingspace()));
							MessageEmptyReply er = (MessageEmptyReply)client.getMessage();
							if (needToUpdate)
							{
								client.send(new MessageFullPLStatus(cor.getParkingLotID(), false));
								MessageEmptyReply fps = (MessageEmptyReply)client.getMessage();
							}
						}
					}).start();
				}
			}
		});
		btnSubmit.setBounds(51, 165, 115, 29);
		add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigator.goBack();
			}
		});
		btnCancel.setBounds(216, 165, 115, 29);
		add(btnCancel);
		
		JLabel lblNewLabel = new JLabel("Check in with car " + cst.getCarId() + " ?");
		lblNewLabel.setBounds(117, 95, 249, 29);
		add(lblNewLabel);
		
		comboBox = new JComboBox();
		comboBox.setBounds(133, 53, 138, 20);
		Set<String> keys = parkingLots.keySet();
		for (Iterator<String> i = keys.iterator(); i.hasNext();)
		{
			comboBox.addItem(i.next());
		}
		add(comboBox);
	}

}
