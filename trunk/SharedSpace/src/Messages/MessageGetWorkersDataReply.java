package Messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DataObjects.RateRequest;
import DataObjects.Worker;

/**
 * 
 * @author omri
 *This class is responsible for ahowing all worker date.
 */
public class MessageGetWorkersDataReply extends Message {
	
	private ArrayList<Worker> workerArray;
	private Worker wkr;
	
	/**
	 * 
	 * @param workerArray array list of all the worker data
	 */
	public MessageGetWorkersDataReply(ArrayList<Worker> workerArray) {
		
		this.setWorkerArray(workerArray);
	}
	
	public MessageGetWorkersDataReply(){
	}

	@Override
	public Message doAction() {
		return null;
	}

	public ArrayList<Worker> getWorkerArray() {
		return workerArray;
	}

	public void setWorkerArray(ArrayList<Worker> workerArray) {
		this.workerArray = workerArray;
	}

}
