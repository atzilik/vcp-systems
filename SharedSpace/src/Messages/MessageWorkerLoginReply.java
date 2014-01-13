package Messages;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import DataObjects.Worker;

/**
 * 
 * @author omri
 *This class is responsible for login of the worker.
 */


public class MessageWorkerLoginReply extends Message {

	private Worker wrk;
	
	public MessageWorkerLoginReply(){
		wrk = null;
	}
	/**
	 * 
	 * @param wrk instans of the worker
	 */
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
