package Messages;

import javax.swing.JOptionPane;

public class MessageRequestChangeParkingRateReply extends Message {

	boolean isDone;
	public MessageRequestChangeParkingRateReply(boolean b) {
		isDone = b;// TODO Auto-generated constructor stub
	}

	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		if(isDone)
			JOptionPane.showMessageDialog(null, "Done");
		else
			JOptionPane.showMessageDialog(null, "Error");
		return null;
	}

}
