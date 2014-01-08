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
			case 1: {JOptionPane.showMessageDialog(null, "Wrong ParkinkLot.", "Error", JOptionPane.ERROR_MESSAGE);
			break;}
			case 2: {JOptionPane.showMessageDialog(null, "Parkink already used.", "Error", JOptionPane.ERROR_MESSAGE);
			break;}
		}

		return null;
	}
}
