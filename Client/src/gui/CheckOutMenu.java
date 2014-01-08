package gui;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import DataObjects.Customer;
import Messages.MessageCheckout;
import Messages.MessageCheckoutReply;
import Messages.MessageUpdatePLMap;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JComboBox;

public class CheckOutMenu extends AbstractGUIComponent {
	private Customer cst;
	private Map<String,Integer> parkingLots;
	JComboBox comboBox;
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
							while (parkinglots[cor.getParkingLotID()].getRobot().isBusy());
							parkinglots[cor.getParkingLotID()].getRobot().unPark(Integer.parseInt(cor.getCustomer().getCarId()), cor.getFloor(), cor.getRow(), cor.getDepth());
							client.send(new MessageUpdatePLMap(cor.getParkingLotID(),parkinglots[cor.getParkingLotID()].getParkingspace()));
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
