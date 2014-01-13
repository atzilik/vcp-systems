//package Messages;
//
//import java.sql.ResultSet;
//
//import javax.swing.JOptionPane;
//
//public class MessageCheckFDReply extends Message{
//	private int ans;
//	
//	public MessageCheckFDReply(int ans) {
//		this.ans = ans;
//	}
//	
//	public int getAns() {
//		return ans;
//	}
//
//	@Override
//	public Message doAction() {
//		switch (ans)
//		{
//			case 0: {JOptionPane.showMessageDialog(null, "parkingLot active."); // good
//			break;}		
//			case 1: {JOptionPane.showMessageDialog(null, "ParkingLot is not active.", "Error", JOptionPane.ERROR_MESSAGE);
//			break;}
//			case 2: {JOptionPane.showMessageDialog(null, "ParkingLot is full.", "Error", JOptionPane.ERROR_MESSAGE);
//			break;}
//		}
//
//		return null;
//	}
//
//
//}
