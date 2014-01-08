package Messages;

import javax.swing.JOptionPane;

public class MessageAprroveRateRequestReplay extends Message {
	
	private boolean done;
	
	public MessageAprroveRateRequestReplay(boolean b) {
		setDone(b);
	}

	@Override
	public Message doAction() {
		if(isDone())
			JOptionPane.showMessageDialog(null, "Done");
		else
			JOptionPane.showMessageDialog(null, "Error");
		return null;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

}
