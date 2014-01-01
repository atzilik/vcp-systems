package Messages;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import DataObjects.Worker;



public class MessageWorkerLoginReply extends Message {

	private Worker wrk;
	
	public MessageWorkerLoginReply(){
		wrk = null;
	}
	
	public MessageWorkerLoginReply(Worker wrk){
		this.wrk = wrk;
	}
	

	public Worker getWrk() {
		return wrk;
	}

	
	public Message doAction() {
		if (wrk == null)
		{
			JOptionPane.showMessageDialog(null, "Wrong username or password.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Login successful.");
		}
		return null;
		
	}

}
