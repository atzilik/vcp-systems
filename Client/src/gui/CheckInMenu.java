package gui;


import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import DataObjects.Customer;
import DataObjects.DateConvert;
import DataObjects.FullMember;
import DataObjects.Reservation;
import DataObjects.Robot;
import DataObjects.STDCustomer;
import DataObjects.STDMember;
import Messages.MessageCheckPl;
import Messages.MessageCheckPlReply;
import Messages.MessageCustomerLogin;
import Messages.MessageCustomerLoginReply;
import Messages.MessageEmptyReply;
import Messages.MessageFullPLStatus;
import Messages.MessageInsertPcReply;
import Messages.MessageGetReservation;
import Messages.MessageGetReservationReply;
import Messages.MessageInsertPc;
import Messages.MessageInsertPcReply;
import Messages.MessageUpdatePLMap;
import Messages.MessageCheckCO;
import Messages.MessageCheckCOReply;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import client.Client;

/**
 * 
 * @author Boaz
 *This class is responsible for the check in of the customer.
 */
public class CheckInMenu extends AbstractGUIComponent {
	/**
	 * Map is used to match parking lot name to its id.  The worker will know which parkinglot he's dealing with
	 */
	private Customer cst;
	private JTextField textField_1;
	private Map<String,Integer> parkingLots;
	private JComboBox comboBox;
	
	/**
	 * 
	 * @param navigator navigate between panels
	 * @param cst to know the customer that checked in
	 * @param mp for the map discribe above
	 */

	public CheckInMenu(final IGUINavigator navigator, final Customer cst, Map<String,Integer> mp) {
		this.cst = cst;
		this.parkingLots = mp;

		setLayout(null);

		JButton btnSubmit = new JButton("OK");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String spl = Integer.toString(parkingLots.get(comboBox.getSelectedItem()));
				final int ipl = parkingLots.get(comboBox.getSelectedItem());

				//check active and not full
				if (parkinglots[ipl].isActive() && parkinglots[ipl].isFull() == false)
				{
					if (cst instanceof STDCustomer)
					{
						client.send(new MessageCheckCO(cst.getId(), cst.getCarId()));
						final MessageCheckCOReply  ccor = (MessageCheckCOReply) client.getMessage();
						ccor.doAction();
						if (ccor.getAns()==1)
							navigator.goBack();
						else														
						{
							client.send(new MessageGetReservation(cst.getId(), cst.getCarId(),spl));
							MessageGetReservationReply grr = (MessageGetReservationReply) client.getMessage();
							grr.doAction();
							if (grr.getAns()==1)
								navigator.goBack();
							else
							{
								if (grr.getReservation()!= null)  // there is a res
								{
									// compare time if before can't chack in
									client.send(new MessageInsertPc(grr.getReservation(),grr.isLate()));
									final MessageInsertPcReply  ipr = (MessageInsertPcReply) client.getMessage();
									ipr.doAction();
									navigator.goBack();
									//call robot
									new Thread(new Runnable() {

										@Override
										public void run() {
											// TODO Auto-generated method stub
											parkinglots[Integer.parseInt(ipr.getRes().getPl())].getRobot().parkCar(ipr.getRes().getCarId(), ipr.getRes().getEstCoutDate(),ipr.getRes().getEstCoutHour());
											client.send(new MessageUpdatePLMap(Integer.parseInt(ipr.getRes().getPl()),parkinglots[Integer.parseInt(ipr.getRes().getPl())].getParkingspace()));
											MessageEmptyReply er = (MessageEmptyReply)client.getMessage();
											if (parkinglots[Integer.parseInt(ipr.getRes().getPl())].getRobot().isParkingLotFull())
											{
												client.send(new MessageFullPLStatus(Integer.parseInt(ipr.getRes().getPl()), true));
												MessageEmptyReply fps = (MessageEmptyReply)client.getMessage();
											}
										}
									}).start();
								}
							}
						}


					}
					else if (cst instanceof STDMember)
					{
						client.send(new MessageCheckPl(((STDMember) cst).getMemberId(), cst.getCarId(),ipl,((STDMember) cst).getParkingLotId()));
						MessageCheckPlReply cplr = (MessageCheckPlReply) client.getMessage();
						cplr.doAction();
						if (cplr.getAns()==0)
						{
							client.send(new MessageInsertPc(cst));
							final MessageInsertPcReply  ipr = (MessageInsertPcReply) client.getMessage();
							ipr.doAction();
							navigator.goBack();
							//call robot
							new Thread(new Runnable() {

								@Override
								public void run() {
									// TODO Auto-generated method stub
									parkinglots[((STDMember)ipr.getCustomer()).getParkingLotId()].getRobot().parkCar(Integer.parseInt(((STDMember)ipr.getCustomer()).getCarId()), DateConvert.getCurrentSqlDate(),((STDMember)ipr.getCustomer()).getStdCheckOut());
									client.send(new MessageUpdatePLMap(((STDMember)ipr.getCustomer()).getParkingLotId(),parkinglots[((STDMember)ipr.getCustomer()).getParkingLotId()].getParkingspace()));
									MessageEmptyReply er = (MessageEmptyReply)client.getMessage();
									if (parkinglots[((STDMember) cst).getParkingLotId()].getRobot().isParkingLotFull())
									{
										client.send(new MessageFullPLStatus(((STDMember) cst).getParkingLotId(), true));
										MessageEmptyReply fps = (MessageEmptyReply)client.getMessage();
									}
								}
							}).start();
						}
					}
					else if (cst instanceof FullMember)
					{
						// to do 14 days in a raw

						client.send(new MessageCheckCO(((FullMember) cst).getMemberId(), cst.getCarId()));
						final MessageCheckCOReply  ccor = (MessageCheckCOReply) client.getMessage();
						ccor.doAction();
						if (ccor.getAns()==1)
							navigator.goBack();
						else
						{
							client.send(new MessageInsertPc(cst,ipl));
							final MessageInsertPcReply  ipr = (MessageInsertPcReply) client.getMessage();
							ipr.doAction();
							navigator.goBack();
							// call robot
							new Thread(new Runnable() {

								@Override
								public void run() {
									// TODO Auto-generated method stub
									parkinglots[ipl].getRobot().parkCar(Integer.parseInt(((FullMember)ipr.getCustomer()).getCarId()), DateConvert.getCurrentSqlDate(),DateConvert.getCurrentSqlTime());
									client.send(new MessageUpdatePLMap(ipl, parkinglots[ipl].getParkingspace()));
									MessageEmptyReply er = (MessageEmptyReply)client.getMessage();
									if (parkinglots[ipl].getRobot().isParkingLotFull())
									{
										client.send(new MessageFullPLStatus(ipl, true));
										MessageEmptyReply fps = (MessageEmptyReply)client.getMessage();
									}
								}
							}).start();
						}
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Parking lot is either full or not active.", "Error", JOptionPane.ERROR_MESSAGE);
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

