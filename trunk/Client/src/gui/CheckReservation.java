package gui;

import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JTable;

import DataObjects.Customer;
import DataObjects.Reservation;
import Messages.MessageCheckReservation;
import Messages.MessageCheckReservationReply;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author Boaz
 *This class is responsible of showing all of the reservation of the customer.
 */
public class CheckReservation extends AbstractGUIComponent{
	private String id;	
	private IGUINavigator navigator;
	
	/**
	 * 
	 * @param navigator nevigate between panels
	 * @param id get the id of the customer that we want to show his reservation 
	 */
	public CheckReservation(final IGUINavigator navigator, final String id){
		this.id = id;	
		this.navigator = navigator;
		setLayout(null);
		
		JLabel lblCheckReservation = new JLabel("Check Reservation");
		lblCheckReservation.setBounds(164, 26, 131, 16);
		add(lblCheckReservation);
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client.send(new MessageCheckReservation(id));
				final MessageCheckReservationReply  crr = (MessageCheckReservationReply) client.getMessage();
				crr.doAction();
				if (crr.getreservationArray() != null)
					PrintTable(crr.getreservationArray());
			}
		});
		btnGenerate.setBounds(91, 55, 97, 25);
		add(btnGenerate);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				navigator.goBack();
			}
		});
		btnCancel.setBounds(249, 55, 97, 25);
		add(btnCancel);
		
		

	}
	/**
	 * this method shows the report of the customer reservation
	 * 
	 * @param res list of all of the customer reservation
	 */
private void PrintTable(ArrayList<Reservation> res) {
		
		
		int length = res.size();
		Object[][] data = new Object[length+1][8];
		int i=0;
		
		data[i][0] = "reservationId";
		data[i][1] = "carId";
		data[i][2] = "customerId";
		data[i][3] = "parkingLotId";
		data[i][4] = "estCinDate";
		data[i][5] = "estCinHour";
		data[i][6] = "estCotDate";
		data[i][7] = "estCotHour";
		
		i++;
		
		for(Reservation rs: res)
		{
			data[i][0] = rs.getRid();
			data[i][1] = rs.getCarId();
			data[i][2] = rs.getCid();
			data[i][3] = rs.getPl();
			data[i][4] = rs.getEstCinDate();
			data[i][5] = rs.getEstCinHour();
			data[i][6] = rs.getEstCoutDate();
			data[i][7] = rs.getEstCoutHour();
			
			i++;
		}
		
		String[] columnNames = {"reservationId",
                "carId",
                "customerId",
                "parkingLotId",
                "estCinDate",
                "estCinHour",
                "estCotDate",
                "estCotHour"};
		
		JTable reservationTable = new JTable(data , columnNames);
		reservationTable.setBounds(new Rectangle(10, 100, 620, i*23));
	

		reservationTable.setVisible(true);
		
		this.add(reservationTable,null);
		
		this.revalidate();
		this.repaint();
	}
}
