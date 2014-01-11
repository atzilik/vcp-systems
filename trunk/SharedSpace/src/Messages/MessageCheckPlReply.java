package Messages;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

import DataObjects.Reservation;

public class MessageCheckPlReply extends Message {
	int ans;	
	
	public MessageCheckPlReply(int ans){
		this.ans = ans;
	}
	
	public int getAns() {
		return ans;
	}
	
	@Override
	public Message doAction() {
		switch (ans)
		{
			case 0: {JOptionPane.showMessageDialog(null, "welcom."); // good
			break;}		
			case 1: {JOptionPane.showMessageDialog(null, "Wrong ParkingLot.", "Error", JOptionPane.ERROR_MESSAGE);
			break;}
			case 2: {JOptionPane.showMessageDialog(null, "Parking already used.", "Error", JOptionPane.ERROR_MESSAGE);
			break;}
			case 3: {JOptionPane.showMessageDialog(null, "you can't park on a weekend.");
			break;}
		}

		return null;
	}
}
